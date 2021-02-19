package com.web.service;

import com.web.dao.model.User;
import com.web.dao.model.common.RestPage;

import java.util.List;

/**
 * @author zhang-rongyao
 * @version V 1.0
 * @Package com.fapiao.layui.service
 * @date 2021/1/6/006 16:32
 */

public interface IUserService {

    public User findUserById(long id);

    public User findUserByName(String name);

    public List<User> getUserList();

    public void save(User user);

    public void edit(User user);

    public void delete(long id);

    public RestPage<User> getPageList(User user);
}
