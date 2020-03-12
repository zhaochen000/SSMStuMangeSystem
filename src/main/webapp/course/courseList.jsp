<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${ItemPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ItemPath}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ItemPath}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	.tree-closed {
	    height : 40px;
	}
	.tree-expanded {
	    height : auto;
	}
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
					<i class="glyphicon glyphicon-user"></i> ${loginUser.uname } <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="loginOut"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
            <input type="text" class="form-control" placeholder="查询">
          </form>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<!-- <ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="user/index"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="role/index"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="permission/index"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					
				</ul> -->
				
			
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">课程信息</h1>
          		<button type="button" class="btn btn-default btn-warning" onclick="location.href='${ItemPath}/course/courseAdd.jsp'">
				  <span class="glyphicon"></span> 增加课程
				</button>
			<table class="table  table-bordered">
              <thead>
                <tr >
				  <th width="30"></th>
                  <th>课程名称</th>
                  <th>课程类型</th>
                  <th>课程学分</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach items="${courseList}" var="course" varStatus="i">
	                <tr>
					  <!--  <td><input type="checkbox" name="checkboxs" value="${course.courseid}" id="checkboxs"></td>-->
					   <td>${i.count}</td>
	                  <td>${course.coursename}</td>
	                  <td>${course.coursetype}</td>
	                  <td>${course.coursescore}   (学分)</td>
	                  <td>
						  <button type="button" onclick="location.href='${ItemPath}/course/courseEdit?courseid='+${course.courseid}" class="btn btn-primary btn-xs"><i  class=" glyphicon glyphicon-pencil"></i></button>
					      <button type="button" onclick="location.href='${ItemPath}/course/courseDel?courseid='+${course.courseid}" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
					  </td>
	                </tr>         
              	</c:forEach>
              </tbody>
			  <tfoot>
			  </tfoot>
            </table>
          
        </div>
      </div>
    </div>
    <script src="${ItemPath}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ItemPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ItemPath}/script/docs.min.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
                    // jquery对象的回调方法中的this关键字为DOM对象
                    // $(DOM) ==> JQuery
				    if ( $(this).find("ul") ) { // 3 li
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
        </script>
  </body>
</html>