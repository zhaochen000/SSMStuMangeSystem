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
	//退出系统
	@RequestMapping("loginOut")
	public String logout(HttpSession session) {
		//登录用户退出登录 ,移除session中的某个数据
		//session.removeAttribute("loginUser");
		//清除session中的所有数据
		//session.invalidate();
		return "redirect:login";
	}
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	//登录方法
	@ResponseBody
	@RequestMapping("loginJsp")
	public Object loginAjax(User user,HttpSession session) {
		Message message = new Message();
		//调用Service中的登录方法，返回登录用户的对象
		User loginUser = userService.login(user);
		if(loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			
			//获得当前登录用户的许可权限
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
			//将登录用户的菜单存入session
			session.setAttribute("root", root);
			message.setFlag(true);
			
		}else {
			message.setFlag(false);
		}
	  
		return message;
	}
}
