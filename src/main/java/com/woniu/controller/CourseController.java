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
 * @date: 2020��3��11�� ����5:28:31
 */
@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	ICourseService courseService;
	
	//���courseList��ת���б�ҳ��
	@RequestMapping("courseList")
	public String  courseList(Model model){
		List<Course> courseList=courseService.findAll();
		model.addAttribute("courseList",courseList);
		return "/course/courseList";
	}
	
	//��ӿγ�
	@RequestMapping("courseAdd")
	public String  courseAdd(Course course){
		courseService.save(course);
		return "ok";
	}
	
	//ɾ���γ�
	@RequestMapping("courseDel")
	public String  courseDel(Integer courseid){
		courseService.delete(courseid);
		return "redirect:/course/courseList";
	}
	
	//��ȡ�޸ĵĿγ���Ϣ��ת���޸�ҳ��
	@RequestMapping("courseEdit")
	public String  courseEdit(Integer courseid,Model model){
		System.out.println("CourseController.courseEdit()"+"-------------"+courseid);
		Course courseEdit=courseService.findOne(courseid);
		model.addAttribute("courseEdit", courseEdit);
		return "/course/courseEdit";
	}
	
	//ȷ���޸��ύ�����ݿⲢ��ת��Listҳ��
	@RequestMapping("courseEditSubmit")
	public String  courseEditSubmit(Course course){
		courseService.updateOne(course);
		return "/course/courseList";
	}
	
	
	
}
