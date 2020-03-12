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
	<link rel="stylesheet" href="${ItemPath }/css/doc.min.css">
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
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="saveForm">
				  <div class="form-group">
				   <input type="hidden" id="infoID" value="${newInfo.infoID }" />
					<label for="exampleInputPassword1">公告名称</label>
					<input type="text" class="form-control" id="infoName" name="infoName" value="${newInfo.infoName }" placeholder="请输入公告名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">发布单位</label>
					<input type="text" class="form-control" id="infoAdd" name="infoAdd" value="${newInfo.infoAdd }" placeholder="请输入发布单位">
				  </div>	
				  <div class="form-group">
					<label for="exampleInputPassword1">发布时间</label>
					<input type="date" class="form-control" id="publishDate" name="publishDate" value="${newInfo.publishDate }" placeholder="请输入发布日期">
				  </div> 
				  <div class="form-group">
					<label for="exampleInputPassword1">公告内容</label>
					<input type="text" class="form-control" id="infoContent" name="infoContent" value="${newInfo.infoContent }" placeholder="请输入公告内容">
				  </div>
				  <button type="button" id="btnUpdate" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
				  <button type="button" id="btnReset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
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
		</div>
	  </div>
	</div>
    <script src="${ItemPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ItemPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ItemPath }/script/docs.min.js"></script>
	<script src="${ItemPath }/layer/layer.js"></script>
        <script type="text/javascript">
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
			    $("#btnReset").click(function(){ 
			    	$("#infoAdd").val("");
			    	$("#infoName").val("");
			    });
			    
			    $("#btnUpdate").click(function(){
			    	  var infoAddVal = $("#infoAdd").val();
			         if(infoAddVal==""){
			        	 layer.msg("发布单位不能为空!", {time:1000, icon:0, shift:5}, function(){});
			             return;
			         }
			         var infoNameVal = $("#infoName").val();
			         if(infoNameVal==""){
			        	 layer.msg("公告名称不能为空!", {time:1000, icon:0, shift:5}, function(){});
			        	 return;
			         } 
			         var publishDateVal = $("#publishDate").val();
			         if(publishDateVal==""){
			        	 layer.msg("公告内容日期不能为空!", {time:1000, icon:0, shift:5}, function(){});
			        	 return;
			         } 
			         var infoContentVal = $("#infoContent").val();
			         if(infoContentVal==""){
			        	 layer.msg("发布内容不能为空!", {time:1000, icon:0, shift:5}, function(){});
			        	 return;
			         } 
			    
			    	$.ajax({
			    		url:"${ItemPath}/education/updateNewInfo",
			    		type:"post",
			    		data:{"infoID":$("#infoID").val(),"infoName":$("#infoName").val(),"infoAdd":$("#infoAdd").val(),"publishDate":$("#publishDate").val(),"infoContent":$("#infoContent").val()},
			    		success:function(result){
			    			if(result.flag){
			    				layer.msg("修改成功!", {time:1000, icon:0, shift:6}, function(){});
			    				window.location.href="${ItemPath}/education/indexInfo";
			    			}else{
			    				layer.msg("修改失败!", {time:1000, icon:0, shift:5}, function(){});
			    			}
			    		}
			    	});
			    });
            });
        </script>
  </body>
</html>
