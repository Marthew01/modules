package com.web.admin.controller;

import com.web.admin.config.shiro.PasswordHelper;
import com.web.dao.model.User;
import com.web.dao.model.common.ResultVo;
import com.web.dao.result.ResultVoUtil;
import com.web.service.IUserService;
import com.web.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员
 *
 * @author zhang-rongyao
 * @version V 1.0
 * @Package com.fapiao.layui.controller
 * @date 2021/1/7/007 16:32
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    PasswordHelper passwordHelper;

    @Autowired
    RedisService<User> redisService;

    /**
     * 注册用户
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        passwordHelper.encryptPassword(user);
        userService.save(user);
        log.info("用户: {} 注册成功", username);
        model.addAttribute("msg", "用户: " + username + " 注册成功");
        return "/common/success";
    }


    /**
     * 返回员工列表
     *
     * @return ResultVo
     */
    @GetMapping("/userList")
    @Cacheable(cacheNames = "pageList",key = "#user.username==null?'allpage':#user.username")
    @ResponseBody
    public ResultVo userList(User user) {
        return ResultVoUtil.success("查询成功", userService.getPageList(user));
    }

    /*@GetMapping("/getlist")
    @Cacheable(cacheNames = "userList",key = "'alluser'")
    @ResponseBody
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }
*/


}
