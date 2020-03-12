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
		��̬ģ��zTree������
		List<Permission> permissionList = new ArrayList<Permission>();
		Permission root = new Permission();
		root.setName("Ȩ�޹���");
		root.setOpen(true);
		Permission c1 = new Permission();
		c1.setName("�û�����");
		Permission c2 = new Permission();
		c2.setName("��ɫ����");
		root.getChildren().add(c1);
		root.getChildren().add(c2);
		permissionList.add(root);
		*/
		/*
		����˵�ֻ�����㣬����ʹ�����ַ�ʽ
		//�ҵ�����ɽڵ�
		Permission root = permissionService.findRoot();
		//�ҵ�������ɽڵ�
		List<Permission> permissionList = permissionService.findAll();
		for (Permission permission : permissionList) {
			//�ҵ����ڵ�
			if(permission.getParentid()==root.getPid()) {
				root.getChildren().add(permission);
			}
		}
		List<Permission> zTree = new ArrayList<Permission>();
		zTree.add(root);
		*/
		//1.�ҵ�����ɽڵ�
		//Permission root = permissionService.findRoot();
		//ʹ�õݹ�ķ�����Ӳ��
		//queryZTree(root);
		//List<Permission> zTree = new ArrayList<Permission>();
		//zTree.add(root);
		//return zTree;
		
		//2.ʹ��Ƕ��forѭ�� 
		//�ҵ�������ɽڵ�
	/*	List<Permission> permissionList = permissionService.findAll();
		List<Permission> zTree = new ArrayList<Permission>();
		for(Permission child:permissionList) {
			//�жϽڵ��Ƿ�Ϊ���ڵ�
			if(child.getParentid()==null) {
				zTree.add(child);
			}else {
				//ѭ�����нڵ㣬�ҵ�child�ĸ��ڵ�
				for(Permission p:permissionList) {
					if(child.getParentid()==p.getPid()) {
						p.getChildren().add(child);
						break;
					}
				}
			}
		}*/
		//3.ʹ��map��forѭ�����������
		//�ҵ�������ɽڵ�
		List<Permission> permissionList = permissionService.findAll();
		List<Permission> zTree = new ArrayList<Permission>();
		
		//�Ƚ����еĽڵ���ӵ�map��
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
		//���ݸ��ڵ㣬��Ѱ�ӽڵ�
		List<Permission> children = permissionService.selectByParentid(permission.getPid());
		//�ݹ����
		if(children!=null) {
			for(Permission child:children) {
				queryZTree(child);
			}
		}
		//��ӵ����ڵ���
		permission.setChildren(children);
	}
}
