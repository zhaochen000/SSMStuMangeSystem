package com.woniu.dao;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.PageBean;
import com.woniu.pojo.Role;
import com.woniu.pojo.UserRole;

public interface RoleDao {
    void add(Role role);
	List<Role> selectByPage(PageBean<Role> pageBean);
	int countByPage(PageBean<Role> pageBean);
	void save(Role role);
	Role findOne(Integer rid);
	void update(Role role);
	void delete(Integer rid);
	void deleteRoles(Integer[] rids);
	List<Role> findAll();
	List<UserRole> findRoleByUid(Integer uid);
	//List<Role> selectRolesByUid(Integer uid);
	void assignPermission(Map<String, Object> map);
	List<Integer> findAssignByRid(Integer rid);
	void deleteAssignPermissionByRid(Object object);
	
}
