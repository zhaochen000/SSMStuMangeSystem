package com.woniu.service;

import java.util.List;

import com.woniu.pojo.Course;

/**
 * @author AriesBn
 * @date: 2020年3月11日 下午5:38:13
 */
public interface ICourseService {

	/**
	 * @return
	 */
	List<Course> findAll();

	/**
	 * @param course
	 */
	void save(Course course);

	/**
	 * @param courseid
	 */
	void delete(Integer courseid);

	/**
	 * @param courseid
	 * @return
	 */
	Course findOne(Integer courseid);

	/**
	 * @param course
	 * @return
	 */
	void updateOne(Course course);

}
