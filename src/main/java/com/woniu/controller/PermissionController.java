package com.woniu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.Permission;
import com.woniu.service.IPermissionService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	IPermissionService permissionService;
	@RequestMapping("index")
	public String index() {
		return "permission/index";
	}
	@RequestMapping("save")
	public String save(Integer pid,Model model) {
		model.addAttribute("pid",pid);
		return "permission/save";
	}
	@RequestMapping("update")
	public String update(Integer pid,Model model) {
		Permission permission = permissionService.findOne(pid);
		model.addAttribute("permission", permission);
		return "permission/update";
	}
	@ResponseBody
	@RequestMapping("savePermission")
	public Object savePermission(Permission permission) {
		Message message = new Message();
		try {
			System.out.println(permission);
			permissionService.save(permission);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("updatePermission")
	public Object updatePermission(Permission permission) {
		Message message = new Message();
		try {
			permissionService.update(permission);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("deletePermission")
	public Object deletePermission(Integer pid) {
		Message message = new Message();
		try {
			permissionService.delete(pid);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}

	@ResponseBody
	@RequestMapping("loadData")
	public Object loadData() {
		/*
		静态模拟zTree的数据
		List<Permission> permissionList = new ArrayList<Permission>();
		Permission root = new Permission();
		root.setName("权限管理");
		root.setOpen(true);
		Permission c1 = new Permission();
		c1.setName("用户管理");
		Permission c2 = new Permission();
		c2.setName("角色管理");
		root.getChildren().add(c1);
		root.getChildren().add(c2);
		permissionList.add(root);
		*/
		/*
		如果菜单只有两层，可以使用这种方式
		//找到根许可节点
		Permission root = permissionService.findRoot();
		//找到所有许可节点
		List<Permission> permissionList = permissionService.findAll();
		for (Permission permission : permissionList) {
			//找到父节点
			if(permission.getParentid()==root.getPid()) {
				root.getChildren().add(permission);
			}
		}
		List<Permission> zTree = new ArrayList<Permission>();
		zTree.add(root);
		*/
		//1.找到根许可节点
		//Permission root = permissionService.findRoot();
		//使用递归的方法添加层次
		//queryZTree(root);
		//List<Permission> zTree = new ArrayList<Permission>();
		//zTree.add(root);
		//return zTree;
		
		//2.使用嵌套for循环 
		//找到所有许可节点
	/*	List<Permission> permissionList = permissionService.findAll();
		List<Permission> zTree = new ArrayList<Permission>();
		for(Permission child:permissionList) {
			//判断节点是否为根节点
			if(child.getParentid()==null) {
				zTree.add(child);
			}else {
				//循环所有节点，找到child的父节点
				for(Permission p:permissionList) {
					if(child.getParentid()==p.getPid()) {
						p.getChildren().add(child);
						break;
					}
				}
			}
		}*/
		//3.使用map和for循环来解决问题
		//找到所有许可节点
		List<Permission> permissionList = permissionService.findAll();
		List<Permission> zTree = new ArrayList<Permission>();
		
		//先将所有的节点添加到map中
		Map<Integer,Permission> map = new HashMap<Integer,Permission>();
		for (Permission permission : permissionList) {
			permission.setOpen(true);
			map.put(permission.getPid(), permission);
		}
		
		for (Permission permission : permissionList) {
			if(permission.getParentid()==null) {
				zTree.add(permission);
			}else {
				Permission p = map.get(permission.getParentid());
				p.getChildren().add(permission);  
			}
		}
		return zTree;
	}
	
	public void queryZTree(Permission permission) {
		//根据根节点，找寻子节点
		List<Permission> children = permissionService.selectByParentid(permission.getPid());
		//递归遍历
		if(children!=null) {
			for(Permission child:children) {
				queryZTree(child);
			}
		}
		//添加到根节点中
		permission.setChildren(children);
	}
}
