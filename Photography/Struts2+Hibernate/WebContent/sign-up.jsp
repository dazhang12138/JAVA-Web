<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Photography</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="login/css/bootstrap.min.css">
	<link rel="stylesheet" href="login/css/animate.css">
	<link rel="stylesheet" href="login/css/style.css">

	<!-- Modernizr JS -->
	<script src="login/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<!-- 输入检测 -->
	<script type="text/javascript">
		function DetecUName() {
			var uname = document.getElementById("uname").value;
			$.ajax({
				   type: "POST",
				   url: "DetecName",
				   data: "uname=" + uname,
				   success: function(msg){
						$("#duname").html(msg);
				  }
				});
		}
		function DetecEmail() {
			var email = document.getElementById("email").value;
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(email == ''){
				$("#demail").html('不能为空');
			}else if(!myreg.test(email)){
				$("#demail").html('请填写正确邮箱格式');
			}else{
				$.ajax({
					   type: "POST",
					   url: "DetecEmail",
					   data: "email=" + email,
					   success: function(msg){
							$("#demail").html(msg);
					  }
					});
			}
		}
		function DetecPwd() {
			var pwd = document.getElementById("password").value;
			if(pwd == ''){
				$("#dpwd").html('不能为空');
			}else if(pwd.length < 6){
				$("#dpwd").html('您的密码过于简单注意安全');
			}else{
				$("#dpwd").html('');
			}
		}
		function DetecPhone() {
			var phone = document.getElementById("phone").value;
			if(phone == ''){
				$("#dphone").html('不能为空');
			}else if(phone.length != 11){
				$("#dphone").html('请输入正确手机号码');
			}else if(isNaN(phone)){
				$("#dphone").html('请输入数字');
			}else{
				$("#dphone").html('');
			}
		}
		function DetecName() {
			var name = document.getElementById("name").value;
			if(name == ''){
				$("#dname").html('不能为空');
			}else{
				$("#dname").html('');
			}
		}
	</script>
	</head>
	<body>

		<div class="container">
			<div class="row">
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<!-- Start Sign In Form -->
					<form action="<%=basePath %>User_register.action" class="fh5co-form animate-box" data-animate-effect="fadeIn">
						<h2>注册</h2>
						<div class="form-group">
							<div class="alert alert-success" role="alert">请认真填写注册信息.</div>
						</div>
						<div class="form-group">
							<input name="user.UName" type="text" class="form-control" id="name" placeholder="姓名" autocomplete="off" onblur="DetecName()">
							<span style="color: red;" id="dname"></span>
						</div>
						<div class="form-group">
							<input name="user.UUname" type="text" class="form-control" id="uname" placeholder="用户名" autocomplete="off" onblur="DetecUName()">
							<span style="color: red;" id="duname"></span>
						</div>
						<div class="form-group">
							<input name="user.UPwd" type="password" class="form-control" id="password" placeholder="密码" autocomplete="off" onblur="DetecPwd()">
							<span style="color: red;" id="dpwd"></span>
						</div>
						<div class="form-group">
							<input name="user.UPhone" type="text" class="form-control" id="phone" placeholder="手机号" autocomplete="off" onblur="DetecPhone()">
							<span style="color: red;" id="dphone"></span>
						</div>
						<div class="form-group">
							<input name="user.UEmail" type="email" class="  form-control" id="email" placeholder="邮箱" autocomplete="off" onblur="DetecEmail()">
							<span style="color: red;" id="demail"></span>
						</div>
						<div class="form-group">
							<p>已经注册? <a href="<%=basePath %>login.jsp">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${result }</span> </p>
						</div>
						<div class="form-group">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="注册" class="btn btn-primary">
							<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
							<input type="button" value="返回" class="btn btn-primary" onclick="window.history.back();" style="margin-left:20%">
						</div>
					</form>
					<!-- END Sign In Form -->

				</div>
			</div>
		</div>
	
	<!-- jQuery -->
	<script src="login/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="login/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="login/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="login/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="login/js/main.js"></script>

	</body>
</html>

