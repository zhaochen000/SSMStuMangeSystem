package com.woniu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.RoleDao;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.Role;
import com.woniu.pojo.UserRole;
import com.woniu.service.IRoleService;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	RoleDao roleDao;

	@Override
	public List<Role> selectByPage(PageBean<Role> pageBean) {
		// TODO Auto-generated method stub
		return roleDao.selectByPage(pageBean);
	}

	@Override
	public int countByPage(PageBean<Role> pageBean) {
		// TODO Auto-generated method stub
		return roleDao.countByPage(pageBean);
	}

	@Override
	public void add(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public Role findOne(Integer rid) {
		// TODO Auto-generated method stub
		return roleDao.findOne(rid);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public void delete(Integer rid) {
		// TODO Auto-generated method stub
		roleDao.delete(rid);
	}

	@Override
	public void deleteRoles(Integer[] rids) {
		// TODO Auto-generated method stub
		roleDao.deleteRoles(rids);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	@Override
	public List<UserRole> findRoleByUid(Integer uid) {
		// TODO Auto-generated method stub
		return roleDao.findRoleByUid(uid);
		
	}
	/*@Override
	public List<Role> selectRolesByUid(Integer uid) {
		// TODO Auto-generated method stub
		return roleDao.selectRolesByUid(uid);
	}*/

	@Override
	public void assignPermission(Map<String, Object> map) {
		// TODO Auto-generated method stub
		roleDao.deleteAssignPermissionByRid(map.get("rid"));
		roleDao.assignPermission(map);
	}

	@Override
	public List<Integer> findAssignByRid(Integer rid) {
		// TODO Auto-generated method stub
		return roleDao.findAssignByRid(rid);
	}

	



}
