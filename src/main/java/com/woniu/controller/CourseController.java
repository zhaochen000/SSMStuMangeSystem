package com.woniu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woniu.pojo.Course;
import com.woniu.service.ICourseService;

/**
 * @author AriesBn
 * @date: 2020年3月11日 下午5:28:31
 */
@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	ICourseService courseService;
	
	//获得courseList并转到列表页面
	@RequestMapping("courseList")
	public String  courseList(Model model){
		List<Course> courseList=courseService.findAll();
		model.addAttribute("courseList",courseList);
		return "/course/courseList";
	}
	
	//添加课程
	@RequestMapping("courseAdd")
	public String  courseAdd(Course course){
		courseService.save(course);
		return "ok";
	}
	
	//删除课程
	@RequestMapping("courseDel")
	public String  courseDel(Integer courseid){
		courseService.delete(courseid);
		return "redirect:/course/courseList";
	}
	
	//获取修改的课程信息并转到修改页面
	@RequestMapping("courseEdit")
	public String  courseEdit(Integer courseid,Model model){
		System.out.println("CourseController.courseEdit()"+"-------------"+courseid);
		Course courseEdit=courseService.findOne(courseid);
		model.addAttribute("courseEdit", courseEdit);
		return "/course/courseEdit";
	}
	
	//确定修改提交到数据库并跳转到List页面
	@RequestMapping("courseEditSubmit")
	public String  courseEditSubmit(Course course){
		courseService.updateOne(course);
		return "/course/courseList";
	}
	
	
	
}
