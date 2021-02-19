package com.web.admin.controller;

import com.web.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 登录
 * @author zhang-rongyao
 * @version V 1.0
 * @Package com.fapiao.layui.controller
 * @date 2021/1/6/006 16:15
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping(value = {"/login","/"})
    public String toLogin(Model model) {
        return "/login";
    }
    /**
     * 登录
     * @param model user
     * @return main.html
     */
    @PostMapping(value = "/login")
    public String login(User user, Model model) {
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        //开始认证，这一步会跳到我们自定义的 Realm 中
        subject.login(token);
        return "main";
    }
}
