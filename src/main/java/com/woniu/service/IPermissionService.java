package com.woniu.service;

import java.util.List;

import com.woniu.pojo.Permission;

public interface IPermissionService {

	List<Permission> findAll();

	Permission findRoot();

	List<Permission> selectByParentid(Integer pid);

	void save(Permission permission);

	Permission findOne(Integer pid);

	void update(Permission permission);

	void delete(Integer pid);

}
