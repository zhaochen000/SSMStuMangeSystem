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
    //�����ɫ
	void assignRoles(Map<String, Object> map);
    //ȡ����ɫ����
	void assignRolesDel(Map<String, Object> map);
    //���ݵ�¼���û���Ϣ���������е���ɣ���Ȩ�� 
	List<Permission> selectPermissionByUser(User loginUser);

	
}
