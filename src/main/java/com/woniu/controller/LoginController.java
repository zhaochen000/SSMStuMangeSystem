package com.woniu.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.Permission;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;

@Controller
public class LoginController {
	@Autowired
	IUserService userService;
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	//�˳�ϵͳ
	@RequestMapping("loginOut")
	public String logout(HttpSession session) {
		//��¼�û��˳���¼ ,�Ƴ�session�е�ĳ������
		//session.removeAttribute("loginUser");
		//���session�е���������
		//session.invalidate();
		return "redirect:login";
	}
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	//��¼����
	@ResponseBody
	@RequestMapping("loginJsp")
	public Object loginAjax(User user,HttpSession session) {
		Message message = new Message();
		//����Service�еĵ�¼���������ص�¼�û��Ķ���
		User loginUser = userService.login(user);
		if(loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			
			//��õ�ǰ��¼�û������Ȩ��
			List<Permission> permissionList =  userService.selectPermissionByUser(loginUser);
			Permission root = null;
			
			Map<Integer, Permission> map = new HashMap<Integer,Permission>();
			for (Permission permission : permissionList) {
				permission.setOpen(true);
				map.put(permission.getPid(), permission);
			}
			for (Permission permission : permissionList) {
				if(permission.getParentid()==null){
					root = permission;
				}else {
					Permission parent = map.get(permission.getParentid());
					parent.getChildren().add(permission);
				}
			}
			//����¼�û��Ĳ˵�����session
			session.setAttribute("root", root);
			message.setFlag(true);
			
		}else {
			message.setFlag(false);
		}
	  
		return message;
	}
}
