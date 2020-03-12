<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<style type="text/css">
	body{
	   background: url(images/a.jpg)repeat;
	}
	#login-box {
		/*border:1px solid #F00;*/
		padding: 35px;
		border-radius:15px;
		background: #56666B;
		color: #fff;
	}

	</style>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="#" style="font-size:32px;">教务管理系统平台</a></div>
        </div>
      </div>
    </nav>
	<div class="container" id="top">
		<div class="row" style="margin-top: 230px; ">
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
				<form class="form-horizontal" role="form" action="loginJsp" id="from1" method="post">
				  <div class="form-group">
				   <label for="lastname" class="col-sm-3 control-label">用户名</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="uname" placeholder="请输入用户名" name="uname">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastPwd" class="col-sm-3 control-label">密码</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="upwd" placeholder="请输入密码" name="upwd">
				    </div>
				  </div>
			
				  <div class="form-group" >
				    <div class="col-sm-offset-6 col-sm-10">
				      <input  type="button" class="btn btn-info" onclick="loginInfo()" value="登录"/>
				      <input type="reset" class="btn btn-info" value="取消" />
				    </div>
				  </div> 
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>		
	</div>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"  src="js/bootstrap.min.js"></script>
	<script  src="layer/layer.js"></script>
	<script type="text/javascript"  >
	function loginInfo(){
	   var unameVal = $("#uname").val();
       if(unameVal==""){
        	//alet(1232);
        	layer.msg("用户名不能为空!", {time:1000, icon:0, shift:5}, function(){});
        	return;
        } 
        var upwdVal = $("#upwd").val();
        if(upwdVal==""){
        	layer.msg("密码不能为空!", {time:1000, icon:0, shift:5}, function(){});
        	return;
        } 
        //登录ajax
        $.ajax({
        	url:"loginJsp",
        	type:"post",
        	data:{"uname":$("#uname").val(),"upwd":$("#upwd").val()},
        	success:function(result){
        		if(result.flag){
        			window.location.href="main.jsp"; 
        		}else{
        			layer.msg("用户名或密码错误!", {time:1000, icon:0, shift:5}, function(){});
        		}
        	}
        });
    }  
    </script>
	
</body>
</html>