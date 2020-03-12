package com.woniu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.UserDao;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.Permission;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDao userDao;

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	@Override
	public List<User> selectByPage(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		return userDao.selectByPage(pageBean);
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return userDao.countAll();
	}

	@Override
	public int countAll(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		return userDao.countAll(pageBean);
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);
	}

	@Override
	public User findOne(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findOne(uid);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(Integer uid) {
		// TODO Auto-generated method stub
		userDao.delete(uid);
	}

	@Override
	public void deleteUsers(Integer[] uids) {
		// TODO Auto-generated method stub
		userDao.deleteUsers(uids);
	}

	//·ÖÅä½ÇÉ«
	@Override
	public void assignRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.assignRoles(map);
	}

	@Override
	public void assignRolesDel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.assignRolesDel(map);
	}

	@Override
	public List<Permission> selectPermissionByUser(User loginUser) {
		// TODO Auto-generated method stub
		return userDao.selectPermissionByUser(loginUser);
	}



	
}
