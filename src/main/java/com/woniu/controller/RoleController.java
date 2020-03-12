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
import com.woniu.pojo.PageBean;
import com.woniu.pojo.Permission;
import com.woniu.pojo.Role;
import com.woniu.service.IPermissionService;
import com.woniu.service.IRoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	IRoleService roleService;
	@Autowired
	IPermissionService permissionService;
	@RequestMapping("index")
	public String index(){
		return "role/index";
	}
	@RequestMapping("add")
	public String add(){
		return "role/add";
	}
	@RequestMapping("assignPermission")
	public String assignPermission(Integer rid,Model model) {
		model.addAttribute("rid", rid);
		return "role/assignPermission";
	}
	@ResponseBody
	@RequestMapping("loadData")
	public Object loadData(Integer rid) {
		//使用map和for循环来解决问题
		//找到所有许可节点
		List<Permission> permissionList = permissionService.findAll();
		//获得该角色已分配的许可id
		List<Integer> assignPermissionIds =  roleService.findAssignByRid(rid);
		
		List<Permission> zTree = new ArrayList<Permission>();
		
		//先将所有的节点添加到map中
		Map<Integer,Permission> map = new HashMap<Integer,Permission>();
		for (Permission permission : permissionList) {
			System.out.println("RoleController.loadData()"+permission.getPid());
			//找到角色已经分配的许可，给他的属性checked设置为true，那么就可以默认选中
			if(assignPermissionIds.contains(permission.getPid())) {
				permission.setChecked(true);
			}else {
				permission.setChecked(false);
			}
			map.put(permission.getPid(), permission);
		}
		
		for (Permission permission : permissionList) {
			permission.setOpen(true);
			if(permission.getParentid()==null) {
				zTree.add(permission);
			}else {
				Permission p = map.get(permission.getParentid());
				p.getChildren().add(permission);
			}
		}
		
		return zTree;
	}
	@ResponseBody
	@RequestMapping("assignPermissionOwn")
	public Object assignPermissionOwn(Integer rid,Integer[] pids) {
		Message message = new Message();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("rid", rid);
			map.put("pids", pids);
			roleService.assignPermission(map);
		
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		System.out.println("------------------------");
		return message;
	}
	@ResponseBody
	@RequestMapping("addRole")
	public Object addRole(Role role){
		Message message = new Message();
		try{
			roleService.add(role);
			message.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			message.setFlag(false);
		}
		
		return message;
		
	}
	@RequestMapping("update")
	public String update(Integer rid,Model model){
		Role role = roleService.findOne(rid);
		model.addAttribute("role",role);
		return "role/update";
	}
	@ResponseBody
	@RequestMapping("updateRole")
	public Object updateRole(Role role){
		Message message = new Message();
		try{
			System.out.println(role);
			roleService.update(role);
			message.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			message.setFlag(false);
		}
		
		return message;
		
	}
	@ResponseBody
	@RequestMapping("delete")
	public Object delete(Integer rid){
		Message message = new Message();
		try{
			roleService.delete(rid);
			message.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			message.setFlag(false);
		}
		
		return message;
		
	}
	@ResponseBody
	@RequestMapping("deleteRoles")
	public Object deleteRoles(Integer[] rids){
		Message message = new Message();
		try{
			roleService.deleteRoles(rids);
			message.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			message.setFlag(false);
		}
		
		return message;
		
	}
	@ResponseBody
	@RequestMapping("indexByPage")
	public Object indexByPage(PageBean<Role> pageBean) {
		Message message = new Message();
		try {
			//第一次进来时，没有任何数据提交个pageBean对象，但是该对象已经实例化，这时参数为null
			if(pageBean.getNowPage()==null) {
				pageBean.setNowPage(1);
			}
			pageBean.setPageRow(3);
			//分页当前页显示的内容
			List<Role> roleList = roleService.selectByPage(pageBean);
			//获得总页数，先获得总行数
			int countRow = roleService.countByPage(pageBean);
			pageBean.setCountRow(countRow);
			pageBean.setCountPage(countRow%pageBean.getPageRow()==0?countRow/pageBean.getPageRow():countRow/pageBean.getPageRow()+1);
			pageBean.setList(roleList);
			message.setObj(pageBean);
			message.setFlag(true);
		}catch(Exception e) {
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
}
