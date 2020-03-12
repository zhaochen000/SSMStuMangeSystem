package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.NewInfo;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;

public interface EducationDao {

	int countAll(PageBean<User> pageBean);

	List<User> selectByPage(PageBean<User> pageBean);

	void add(NewInfo newInfo);

	void delete(Integer infoID);

	void update(NewInfo newInfo);

	NewInfo findOne(Integer infoID);

	void delNewInfos(Integer[] newInfos);

	List<NewInfo> findAll();

	NewInfo findOneByLookID(Integer infoID);

}
