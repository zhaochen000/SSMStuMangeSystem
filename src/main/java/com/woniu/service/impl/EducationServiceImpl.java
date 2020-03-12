package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.EducationDao;
import com.woniu.pojo.NewInfo;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;
import com.woniu.service.IEducationService;
@Service
@Transactional
public class EducationServiceImpl implements IEducationService {

	@Autowired
	EducationDao educationDao;
	
	@Override
	public int countAll(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		return educationDao.countAll(pageBean);
	}

	@Override
	public List<User> selectByPage(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		return educationDao.selectByPage(pageBean);
	}

	@Override
	public void add(NewInfo newInfo) {
		// TODO Auto-generated method stub
		educationDao.add(newInfo);
	}

	@Override
	public void delete(Integer infoID) {
		// TODO Auto-generated method stub
		educationDao.delete(infoID);
	}

	@Override
	public void update(NewInfo newInfo) {
		// TODO Auto-generated method stub
		educationDao.update(newInfo);
	}

	@Override
	public NewInfo findOne(Integer infoID) {
		// TODO Auto-generated method stub
		return educationDao.findOne(infoID);
	}

	@Override
	public void delNewInfos(Integer[] newInfos) {
		// TODO Auto-generated method stub
		educationDao.delNewInfos(newInfos);
	}

	@Override
	public List<NewInfo> findAll() {
		// TODO Auto-generated method stub
		return educationDao.findAll();
	}

	@Override
	public NewInfo findOneByLookID(Integer infoID) {
		// TODO Auto-generated method stub
		return educationDao.findOneByLookID(infoID);
	}

}
