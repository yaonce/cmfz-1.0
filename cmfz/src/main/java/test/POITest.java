package test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

public class POITest {
    public static void main(String[] args) throws Exception {
        //创建工作簿对象
        Workbook workbook = new HSSFWorkbook();
        //创建工作表对象
        Sheet sheet = workbook.createSheet("user");

        //   第2列  ,  列宽20
        sheet.setColumnWidth(2, 20 * 256);
        //创建第一行
        Row row = sheet.createRow(0);
        //修改日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //创建细胞风格
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //创建单元格样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        //居中
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        //修改字体
        Font font = workbook.createFont();
        font.setFontName("楷体");
        font.setBold(true);
        font.setColor(font.COLOR_RED);
        font.setItalic(true);
        cellStyle1.setFont(font);
        String[] strs = {"编号", "姓名", "生日"};
        for (int i = 0; i < strs.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(strs[i]);
        }
        workbook.write(new FileOutputStream(new File("E:\\test.xls")));
        workbook.close();

    }
}
