package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(Admin admin, String myCode, HttpSession session, Model model) {
        try {
            String code = (String) session.getAttribute("code");

            if (!code.equalsIgnoreCase(myCode)) throw new RuntimeException("验证码错误");
            Admin login = adminService.login(admin);
            if (login == null) throw new RuntimeException("账号或密码错误");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return false;
        }
    }

    @RequestMapping("code")
    @ResponseBody
    public void code(HttpSession session, HttpServletResponse response) throws IOException {
        //生成随机数
        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();
        //将随机数存入session
        session.setAttribute("code", code);
        ServletOutputStream os = response.getOutputStream();
        cvc.write(os);
        os.close();
    }
}
