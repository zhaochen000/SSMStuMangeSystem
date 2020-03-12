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
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" id="queryUname" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" id="btnQuery" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="delUsers()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${ItemPath}/user/add'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
      <form id="delForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="checkAll"></th>
                  <th>老师id</th>
                  <th>班级id</th>
                  <th>课程id</th>
                  <th>教室id</th>
                  <th>时间</th>
                  <th>地点</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
            
             <tbody id="tableContent">
             
             </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination" id="byPage">
								
					    </ul>
					 </td>
				 </tr>

			  </tfoot>
            </table>
       </form>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${ItemPath}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ItemPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ItemPath}/scriItemPathpt/docs.min.js"></script>
	<script src="${ItemPath}/layer/layer.js"></script>
    <script type="text/javascript">
         var queryFlag = false;
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
			    <!--带查询条件-->
			    $("#btnQuery").click(function(){
			    	var queryVal = $("#queryUname").val();
			    	if(queryVal!=""){
			    		queryFlag = true;
			    	}
			    	queryByPage(1);
			    });
			    <!--批量删除-->
			    $("#checkAll").click(function(){
			    	 var flag = $(this).prop("checked");
			    	$.each($("#tableContent .delCheck"),function(index,obj){
			    		obj.checked=flag;
			    	}); 
			    });
			    
			    queryByPage(1);
            });
          
            function delUsers(){
            	layer.confirm("是否删除选中的用户?",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
    			    	url:"${ItemPath}/user/deleteSheduling",
    			    	type:"post",
    			    	data:$("#delForm").serialize(),
    			    	success:function(result){
    			    		if(result.flag){
    			    			layer.msg("删除成功!", {time:1000, icon:0, shift:6}, function(){});
    			    			window.location.href="${ItemPath}/scheduling/index";
    			    		}else{
    			    			layer.msg("删除失败!", {time:1000, icon:0, shift:5}, function(){});
    			    		}
    			    	}
    			    });
    			    
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            function updateUser(uid){
            	window.location.href="${ItemPath}/scheduling/update?uid="+uid;
            }
            function assignRole(uid){
            	window.location.href="${ItemPath}/scheduling/assignRole?uid="+uid;
            }
            <!--删除-->
            function deleteUser(uid){
            	layer.confirm("是否删除该用户?",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
    			    	url:"${ItemPath}/scheduling/delete",
    			    	type:"post",
    			    	data:{"uid":uid},
    			    	success:function(result){
    			    		if(result.flag){
    			    			layer.msg("删除成功!", {time:1000, icon:0, shift:6}, function(){});
    			    			window.location.href="${ItemPath}/scheduling/index";
    			    		}else{
    			    			layer.msg("删除失败!", {time:1000, icon:0, shift:5}, function(){});
    			    		}
    			    	}
    			    });
    			    
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            function queryByPage(nowPage){  
            	var jsonData = {"nowPage":nowPage};
            	if(queryFlag){
            		jsonData.queryVal= $("#queryUname").val();
            	}
            	
            	$.ajax({
            		url:"${ItemPath }/scheduling/indexByPage",
            		type:"post",
            		data:jsonData,
            		success:function(result){
            			if(result.flag){
            				var tableStr = "";
            				$.each(result.obj.list,function(index,sch){
            					//生成jQuery对象，进行装配或者使用html方法拼字符串
	            				tableStr+="<tr>";
		                        tableStr+="<td>"+(index+1) +"</td>";
		          				tableStr+="<td><input type='checkbox' class='delCheck' name='uids' value='"+sch.schuID+"'></td>";                  
		          			  <th>班级id</th>
		                      <th>课程id</th>
		                      <th>教室id</th>
		                      <th>时间</th>
		                      <th>地点</th>
		          				tableStr+="<td>"+sch.classID+"</td>";
		          			    tableStr+="<td>"+sch.courseID +"</td>";
		                        tableStr+="<td>"+sch.classRoomID +"</td>";
		                        tableStr+="<td>"+sch.lessonTime+"</td>";
		                        tableStr+="<td>"+sch.lessonPlace+"</td>";
		                        tableStr+="<td>";
		          				tableStr+="<button type='button' onclick='assignRole("+user.schuID+")' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
		          				tableStr+="<button type='button' onclick='updateUser("+user.schuID+")' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>";
		          				tableStr+="<button type='button' onclick='deleteUser("+user.schuID+")' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>";
		          				tableStr+="</td>";
	                          	tableStr+="</tr>";
            					
            				});
            				$("#tableContent").html(tableStr);
            				var byPageStr="";
            				byPageStr+="<li class='"+(result.obj.nowPage==1?'disabled':'')+"'><a href='#' onclick='queryByPage("+(result.obj.nowPage-1)+")'>上一页</a></li>";
							for(var i=1;i<=result.obj.countPage;i++){
								if(i==result.obj.nowPage){
	            					byPageStr+="<li class='active'><a href='#'  onclick='queryByPage("+(i)+")'>"+i+"<span class='sr-only'>(current)</span></a></li>";
								}else{
	            					byPageStr+="<li><a href='#' onclick='queryByPage("+(i)+")'>"+i+"</a></li>";
								}
							}
							byPageStr+="<li class='"+(result.obj.nowPage==result.obj.countPage?'disabled':'')+"'><a href='#'  onclick='queryByPage("+(result.obj.nowPage+1)+")'>下一页</a></li>";
            				$("#byPage").html(byPageStr);
            			}else{
            				layer.msg("查询失败!", {time:1000, icon:0, shift:5}, function(){});
            			}
            		}
            	});
            }
        </script>
  </body>
</html>