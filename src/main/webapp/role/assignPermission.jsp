<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${ItemPath }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ItemPath }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ItemPath }/css/main.css">
	<link rel="stylesheet" href="${ItemPath }/css/doc.min.css">
	<link rel="stylesheet" href="${ItemPath }/ztree/zTreeStyle.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">教务管理平台</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.uname } <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="${ItemPath }/loginOut"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@ include file="../menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">权限分配列表</a></li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
			  <form id="assignPermissionForm">
			  	  <input type="hidden" name="rid" id="rid" value="${rid }" />
				  <button class="btn btn-success" id="btnAssign">分配许可</button>
				   
                  <br><br>
                  <ul id="permissionTree" class="ztree"></ul>
                </form>
			  </div>
			</div>
        </div>
      </div>
    </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		 
		</div>
	  </div>
	</div>
    <script src="${ItemPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ItemPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ItemPath }/script/docs.min.js"></script>
	<script src="${ItemPath }/layer/layer.js"></script>
	<script src="${ItemPath }/ztree/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript">
        function queryZtree(){
        	var setting = {
        		//异步加载须设置参数
    			async : {
    				enable : true,
    				url : "${ItemPath}/role/loadData?rid=${param.rid}",
    				autoParam : [ "id", "name=n", "level=lv" ]
    			},
    			check: {
    				enable: true  
    			}
    		};
        	 $.fn.zTree.init($("#permissionTree"), setting);
        }
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    //文档加载完毕再显示树的结构
			    queryZtree();
			    $("#btnAssign").click(function(){
			    	//获得树对象
			    	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
			    	//获得选中的项
			    	var nodes = treeObj.getCheckedNodes(true);
			    	//判断用户是否在树有选中项
			    	if(nodes.length==0){
			    		layer.msg("请选择需要分配的许可!", {time:1000, icon:6, shift:5}, function(){});
			    	}else{
			    		//先把角色id获得并拼接成字符串
			    		var dataVal = "rid=${param.rid}";
			    		//将选中的许可也拼接到这个字符串中
			    		$.each(nodes,function(index,node){
			    			dataVal+="&pids="+node.pid;
			    		});
				    	$.ajax({
				    		url:"${ItemPath}/role/assignPermissionOwn",
				    		type:"post",
				    		data:dataVal,
				    		success:function(result){
				    			if(result.flag){
				    				layer.msg("分配许可成功!", {time:1000, icon:6, shift:5}, function(){});
				    				window.location.href="${ItemPath}/role/index";
				    			}else{
				    				layer.msg("分配许可失败!", {time:1000, icon:5, shift:5}, function(){});
				    			}
				    		}
				    	});
			    	}
			    });
            });
            
        </script>
  </body>
</html>
