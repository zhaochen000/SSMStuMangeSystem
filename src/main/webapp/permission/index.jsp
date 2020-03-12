<%@ page language="java" pageEncoding="utf-8"%>
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
	<link rel="stylesheet" href="${ItemPath }/ztree/zTreeStyle.css">
	<style>
	a:hover{
		cursor:pointer;
	}
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">教务管理平台</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.uname }<span class="caret"></span>
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 权限菜单列表</h3>
			  </div>
			  <div class="panel-body">
				<!-- 树的生成 -->
				<ul id="treeDemo" class="zTree"></ul>
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
        
        function queryZTree(){
        	//异步通过Controller获取数据
	        var setting={
	        		view: {
	        			//多选属性
						selectedMulti: false,
						//添加自定义节点
						  addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
							}
                            
						},  
						//添加浮动节点
						addHoverDom: function(treeId, treeNode){  
                      
							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
							aObj.attr("href", "javascript:;");
							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
							var s = '<span id="btnGroup'+treeNode.tId+'">';
							if ( treeNode.level == 0 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="permissionSave('+treeNode.pid+')"  >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 1 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="permissionUpdate('+treeNode.pid+')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								if (treeNode.children.length == 0) {
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="permissionDelete('+treeNode.pid+')" href="#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="permissionSave('+treeNode.pid+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 2 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="permissionUpdate('+treeNode.pid+')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="permissionDelete('+treeNode.pid+')" href="#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="permissionSave('+treeNode.pid+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							}
			
							s += '</span>';
							//aObj.after(s);在标签之后添加
							aObj.append(s);
						},
						removeHoverDom: function(treeId, treeNode){
						$("#btnGroup"+treeNode.tId).remove();
						}
					},
	        		async: {
	    				enable: true,
	    				url:"${ItemPath}/permission/loadData",
	    				autoParam:["id", "name=n", "level=lv"]
	    			}
	        };
	        $.fn.zTree.init($("#treeDemo"), setting);
        }
	        function permissionSave(pid){
				   window.location.href="${ItemPath}/permission/save?pid="+pid;
			}
	        function permissionUpdate(pid){
				   window.location.href="${ItemPath}/permission/update?pid="+pid;
		   }
        	function permissionDelete(pid){
        		layer.confirm("是否删除该许可?",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
    			    	url:"${ItemPath}/permission/deletePermission",
    			    	type:"post",
    			    	data:{"pid":pid},
    			    	success:function(result){
    			    		if(result.flag){
    			    			layer.msg("删除许可成功!", {time:1000, icon:6, shift:6}, function(){});
    			    			//获得当前文档中zTree对象
    							var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    							//强行异步加载父节点的子节点。
    							treeObj.reAsyncChildNodes(null, "refresh");
    			    			
    			    		}else{
    			    			layer.msg("删除许可失败!", {time:1000, icon:0, shift:5}, function(){});
    			    		}
    			    	}
    			    });
    			    
    			}, function(cindex){
    			    layer.close(cindex);
    			});
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
			   
			   
			    //$.fn.zTree.init($("#treeDemo"), setting);
			    queryZTree();
            });
           
        </script>
  </body>
</html>
