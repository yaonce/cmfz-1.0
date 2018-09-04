package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/bar")
    public Map<String, Object> queryAll() {
        //模拟数据库
        //随机数
        Random random = new Random();
        Map<String, Object> map = new HashMap<>();

        List<String> xAxis = new ArrayList<>();
        xAxis.add("近1周");
        xAxis.add("近2周");
        xAxis.add("近3周");
        //根据数据库时间查找1,2,3周内注册情况
        List<Integer> mans = new ArrayList<>();
        mans.add(random.nextInt(20));
        mans.add(random.nextInt(20));
        mans.add(random.nextInt(20));

        List<Integer> womens = new ArrayList<>();
        womens.add(random.nextInt(20));
        womens.add(random.nextInt(20));
        womens.add(random.nextInt(20));

        map.put("xAxis", xAxis);
        map.put("mans", mans);
        map.put("womens", womens);

        return map;
    }

    @RequestMapping("/map")
    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        List<Object> mans = new ArrayList<>();
        mans.add(new UserDTO("河北", 30));
        mans.add(new UserDTO("河北", 20));
        mans.add(new UserDTO("山东", 30));
        mans.add(new UserDTO("山西", 10));
        mans.add(new UserDTO("陕西", 30));
        mans.add(new UserDTO("台湾", 100));

        List<Object> womens = new ArrayList<>();
        womens.add(new UserDTO("北京", 30));
        womens.add(new UserDTO("广东", 100));
        womens.add(new UserDTO("浙江", 30));
        womens.add(new UserDTO("云南", 200));
        map.put("mans", mans);
        map.put("womens", womens);
        return map;
    }

    @RequestMapping("down")
    public void down(String[] titles, String[] params, HttpServletResponse response, Integer rows, Integer page) {
        Workbook workbook = new HSSFWorkbook();
        //字体样式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //行标题
        Sheet sheet = workbook.createSheet("ems");
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            row.createCell(i).setCellValue(titles[i]);
        }
        //数据行
        List<User> users = userService.queryAll();
        for (int i = 0; i < users.size(); i++) {
            //从i+1行创建
            Row row1 = sheet.createRow(i + 1);
            //获得user对象
            User user = users.get(i);
            //获取类对象
            Class<? extends User> userClass = user.getClass();
            for (int j = 0; j < params.length; j++) {
                //定义每行
                Cell cell = row1.createCell(j);
                //获得方法
                String methodName = "get" + params[j].substring(0, 1).toUpperCase() + params[j].substring(1);
                //获得调用对象的方法
                try {
                    Method method = userClass.getMethod(methodName, null);
                    //调用方法
                    Object invoke = method.invoke(user, null);
                    if (invoke instanceof Date) {
                        sheet.setColumnWidth(j, 20 * 256);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue((Date) invoke);
                    } else if (invoke instanceof Integer) {
                        cell.setCellValue((Integer) invoke);
                    } else {
                        cell.setCellValue((String) invoke);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        String filename = new Date().getTime() + "userExcel.xls";
        try {
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(filename, "utf-8"));

            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 失败
    @RequestMapping("import1")
    public void userImport1(MultipartFile excel, HttpServletRequest request) {
        //需要上传的文件夹
        String upload = "upload";
        //绝对路径
        String realPath = request.getServletContext().getRealPath("/upload");
        String uploadRealPath = realPath + upload;
        //创建文件夹
        File file = new File(uploadRealPath);
        if (!file.exists()) file.mkdir();
        //起名字
        String originalFilename = excel.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = UUID.randomUUID().toString() + "." + extension;
        //上传
        try {
            excel.transferTo(new File(uploadRealPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取数据
        String url = request.getContextPath() + "/" + newName;
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File(url)));
            Sheet sheet = workbook.createSheet("ems");
            ArrayList<String> titles = new ArrayList<>();
            //获得表头
            Row row = sheet.getRow(0);
            //遍历添加
            for (int i = 0; i < row.getLastCellNum(); i++) {
                titles.add(row.getCell(i).getStringCellValue());
            }
            ArrayList<User> users = new ArrayList<>();
            //一共多少行
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                Row row1 = sheet.getRow(i);
                User user = new User();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=============");

    }

    @RequestMapping("import")
    public void userImport(MultipartFile excel) {
        try {
            //读取数据
            Workbook workbook = new HSSFWorkbook(excel.getInputStream());
            Sheet sheet = workbook.getSheet("ems");
            ArrayList<User> users = new ArrayList<>();
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                Integer id = (int) row.getCell(0).getNumericCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                User user = new User(id, username, password);
                System.out.println(user);
                users.add(user);
            }
            //入库


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
