package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.CourseMapper;
import com.woniu.pojo.Course;
import com.woniu.service.ICourseService;

/**
 * @author AriesBn
 * @date: 2020年3月11日 下午5:38:48
 */
@Service
@Transactional
public class CourseServiceImpl implements ICourseService {
	
	@Autowired
	CourseMapper courseMapper;
	
	
	/* (non-Javadoc)
	 * @see com.woniu.service.ICourseService#findAll()
	 */
	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		return courseMapper.selectByExample(null);
	}


	/* (non-Javadoc)
	 * @see com.woniu.service.ICourseService#save(com.woniu.pojo.Course)
	 */
	@Override
	public void save(Course course) {
		courseMapper.insert(course);
	}


	/* (non-Javadoc)
	 * @see com.woniu.service.ICourseService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer courseid) {
		courseMapper.deleteByPrimaryKey(courseid);
	}


	/* (non-Javadoc)
	 * @see com.woniu.service.ICourseService#findOne(java.lang.Integer)
	 */
	@Override
	public Course findOne(Integer courseid) {
		// TODO Auto-generated method stub
		return courseMapper.selectByPrimaryKey(courseid);
	}


	/* (non-Javadoc)
	 * @see com.woniu.service.ICourseService#updateOne(com.woniu.pojo.Course)
	 */
	@Override
	public void updateOne(Course course) {
		// TODO Auto-generated method stub
		courseMapper.updateByPrimaryKey(course);
	}
}
