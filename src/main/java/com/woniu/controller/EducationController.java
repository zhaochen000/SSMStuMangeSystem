package com.woniu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.NewInfo;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;
import com.woniu.service.IEducationService;

@Controller
@RequestMapping("/education")
public class EducationController {
	
	@Autowired
	IEducationService educationService;
	
	@RequestMapping("/newInfoAdd")
	public String save() {
		return "education/newInfoAdd";
	}
	@RequestMapping("/indexInfo")
	public String indexInfo() {
		return "education/indexInfo";
	}
	@RequestMapping("/lookInfo")
	public String lookInfo(Model model,Integer infoID) {
		model.addAttribute("infoID",infoID);
		return "education/lookInfoContent";
	}
	@ResponseBody
	@RequestMapping("/indexLookInfo")
	public Object indexLookInfo(Model model,Integer infoID) {
		Message message = new Message();
		//PageBean<NewInfo> pageBean = new PageBean<NewInfo>();
		NewInfo newInfo=null;
		try {
		      System.out.println(infoID);
		      newInfo = educationService.findOneByLookID(infoID);
		      System.out.println("=========="+newInfo);
		     // pageBean.setList(newInfo);
			  message.setFlag(true);
			
		}catch(Exception e) {
			message.setFlag(false);
		}
		message.setObj(newInfo);
		return message;
	}
	@ResponseBody
	@RequestMapping("/add")
	public Object addUser(NewInfo newInfo) {
		Message message = new Message();
		try {
			
			educationService.add(newInfo);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer infoID) {
		Message message = new Message();
		try {
			educationService.delete(infoID);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("/update")
	public String update(Integer infoID,Model model) {
		NewInfo newInfo= educationService.findOne(infoID);
		model.addAttribute("newInfo",newInfo);
		return "education/updateNewInfo";
	}
	@ResponseBody
	@RequestMapping("/updateNewInfo")
	public Object updateNewInfo(NewInfo newInfo) {
		Message message = new Message();
		try {
         
			educationService.update(newInfo);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/delNewInfos")
	public Object delNewInfos(Integer[] newInfos) {
		Message message = new Message();
		try {
			System.out.println("--------"+newInfos);
			educationService.delNewInfos(newInfos);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/indexByPage")
	public Object indexByPage(PageBean<User> pageBean) {
		Message message = new Message();
		try {
			//第一次进来时，没有任何数据提交个pageBean对象，但是该对象已经实例化，这时参数为null
			if(pageBean.getNowPage()==null) {
				pageBean.setNowPage(1);
			}
			pageBean.setPageRow(2);
			//获得数据表中的所有用户信息
			List<User> userList = educationService.selectByPage(pageBean);
			pageBean.setList(userList);
			int countRow = educationService.countAll(pageBean);
			pageBean.setCountRow(countRow);
			int countPage = pageBean.getCountRow()%pageBean.getPageRow()==0?pageBean.getCountRow()/pageBean.getPageRow():pageBean.getCountRow()/pageBean.getPageRow()+1;
			pageBean.setCountPage(countPage);
			message.setFlag(true);
		}catch(Exception e) {
			message.setFlag(false);
		}
		message.setObj(pageBean);
		return message;
	}
	@ResponseBody
	@RequestMapping("/indexNewInfo")
	public Object indexNewInfo(Model model) {
		Message message = new Message();
		PageBean<NewInfo> pageBean = new PageBean<NewInfo>();
		try {
		
			List<NewInfo> newInfoList = educationService.findAll();		
			for (NewInfo newInfo : newInfoList) {
				System.out.println(newInfo);
			}
			pageBean.setList(newInfoList);
			message.setFlag(true);
		
		}catch(Exception e) {
			message.setFlag(false);
		}
		message.setObj(pageBean);
		return message;
	}
	

}
