package com.woniu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.Role;
import com.woniu.pojo.User;
import com.woniu.pojo.UserRole;
import com.woniu.service.IRoleService;
import com.woniu.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	IRoleService roleService;
	@RequestMapping("/index")
	public String index() {	
		return "user/index";
	}
	@RequestMapping("/add")
	public String save() {
		System.out.println("UserController.save()");
		return "user/add";
	}
	@RequestMapping("/assignRole")
	public String assignRole(Integer uid,Model model) {
		
		//�ñ������еĽ�ɫ
		List<Role> roleList = roleService.findAll();
		//�����û��ѷ���Ľ�ɫ
		List<UserRole> userRoleList = roleService.findRoleByUid(uid);
		System.out.println(122);
		//����δ����Ľ�ɫ
		List<Role> NoAssignRoleList = new ArrayList<Role>();
		for(UserRole role:userRoleList){
			//���н�ɫ�Ƿ�����ѷ����ɫ
			if(roleList.contains(role.getRole())){
				//�����н�ɫ���Ƴ��ѷ����ɫ
				roleList.remove(role.getRole());
				System.out.println("122333===="+roleList);
				
			}
		}
		
		NoAssignRoleList = roleList;
		model.addAttribute("NoAssignRoleList", NoAssignRoleList);
		model.addAttribute("uid", uid);
		model.addAttribute("userRoleList", userRoleList);
		return "user/assignRole";
	}
	@ResponseBody
	@RequestMapping("/assignRoleOwn")
	public Object assignRoleDo(Integer uid,Integer[] rightRids) {
		Message message = new Message();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("uid", uid);
			map.put("rids", rightRids);
			System.out.println(map);
			userService.assignRoles(map);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/assignRoleDel")
	public Object assignRoleDel(Integer uid,Integer[] leftRids) {
		Message message = new Message();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			for(Integer integer:leftRids){
				System.out.println(integer);
			}
			map.put("uid", uid);
			map.put("rids", leftRids);
			userService.assignRolesDel(map);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	/*@ResponseBody
	@RequestMapping("/addUser")
	public Object addUser(User user) {
		Message message = new Message();
		try {
			user.setUpwd("123");
			userService.add(user);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}*/
	@RequestMapping("/update")
	public String update(Integer uid,Model model) {
		User user= userService.findOne(uid);
		model.addAttribute("user",user);
		return "user/update";
	}
	@ResponseBody
	@RequestMapping("/updateUser")
	public Object updateUser(User user) {
		Message message = new Message();
		try {
            System.out.println(user);
			userService.update(user);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer uid) {
		Message message = new Message();
		try {
			userService.delete(uid);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/deleteUsers")
	public Object deleteUsers(Integer[] uids) {
		Message message = new Message();
		try {
			userService.deleteUsers(uids);
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
			//��һ�ν���ʱ��û���κ������ύ��pageBean���󣬵��Ǹö����Ѿ�ʵ��������ʱ����Ϊnull
			if(pageBean.getNowPage()==null) {
				pageBean.setNowPage(1);
			}
			pageBean.setPageRow(2);
			//������ݱ��е������û���Ϣ
			List<User> userList = userService.selectByPage(pageBean);
			pageBean.setList(userList);
			int countRow = userService.countAll(pageBean);
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
}
