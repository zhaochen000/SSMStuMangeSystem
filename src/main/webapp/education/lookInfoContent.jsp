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

	<link rel="stylesheet" href="${ItemPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ItemPath}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ItemPath}/css/main.css">
	<style>
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
						<li><a href="${ItemPath}/loginOut"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 公告内容详情</h3>
			  </div>
			  <div class="panel-body">
        <form class="form-inline" role="form" style="float:left;">
          <div class="form-group has-feedback">
          <div class="input-group">

           </div>
         </div>

       </form> 

          <div class="table-responsive" id="infoContent">
              <form id="infoForm" id="">
              <input type="hidden" name="infoID" id="infoID" value="${infoID }" />
        
              </form>
          </div>  <br><br><br><br><br><br><br> <br><br><br><br><br><br><br>
	    </div>
	   </div>
      </div>
      </div>
    </div>
   

    <script src="${ItemPath}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ItemPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ItemPath}/script/docs.min.js"></script>
	<script src="${ItemPath}/layer/layer.js"></script>
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
			    
			    queryNewInfo();
            });
	    
            function  queryNewInfo(){
            	var infoID = ${param.infoID};
            	$.ajax({	
            		url:"${ItemPath}/education/indexLookInfo",
            		type:"post",
            		data:{"infoID":infoID},
            		success:function(result){
            			//alert(result.obj.infoContent);
            			var infoContentVal = result.obj.infoContent;
            			if(result.flag){

            				$("#infoContent").text(infoContentVal);
            			      
            			}
            		}
            	});
            }
          
        </script>
  </body>
</html>