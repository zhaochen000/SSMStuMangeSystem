package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.PermissionDao;
import com.woniu.pojo.Permission;
import com.woniu.service.IPermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	PermissionDao permissionDao;

	@Override
	public List<Permission> findAll() {
		// TODO Auto-generated method stub
		return permissionDao.findAll();
	}

	@Override
	public Permission findRoot() {
		// TODO Auto-generated method stub
		return permissionDao.findRoot();
	}

	@Override
	public List<Permission> selectByParentid(Integer pid) {
		// TODO Auto-generated method stub
		return permissionDao.selectByParentid(pid);
	}

	@Override
	public void save(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.save(permission);
	}

	@Override
	public Permission findOne(Integer pid) {
		// TODO Auto-generated method stub
		return permissionDao.findOne(pid);
	}

	@Override
	public void update(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.update(permission);
	}

	@Override
	public void delete(Integer pid) {
		// TODO Auto-generated method stub
		permissionDao.delete(pid);
	}
}
