package com.woniu.service;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.PageBean;
import com.woniu.pojo.Permission;
import com.woniu.pojo.User;

public interface IUserService {

	User login(User user);

	List<User> selectByPage(PageBean<User> pageBean);
	List<User> findAll();
	int countAll();
	int countAll(PageBean<User> pageBean);
	void add(User user);
	User findOne(Integer uid);
	void update(User user);
	void delete(Integer uid);

	void deleteUsers(Integer[] uids);
    //分配角色
	void assignRoles(Map<String, Object> map);
    //取消角色分配
	void assignRolesDel(Map<String, Object> map);
    //根据登录的用户信息，查找所有的许可，即权限 
	List<Permission> selectPermissionByUser(User loginUser);

	
}
