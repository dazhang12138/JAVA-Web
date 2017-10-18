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

	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<!-- Start Sign In Form -->
					<form action="<%=basePath %>User_login.action" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn">
						<h2>登录</h2>
						<div class="form-group">
							<input name="user.u_uname" type="text" class="form-control" id="username" placeholder="用户名" autocomplete="off">
						</div>
						<div class="form-group">
							<input name="user.u_pwd" type="password" class="form-control" id="password" placeholder="密码" autocomplete="off">
						</div>
						<div class="form-group">
							<p>还没有注册? <a href="<%=basePath %>sign-up.jsp">注册</a> | <a href="<%=basePath %>forgot.jsp">忘记密码?</a></p>
						</div>
						<div class="form-group">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="进入" class="btn btn-primary">
							<input style="margin-left: 20%;" type="button" value="返回" class="btn btn-primary" onclick="window.history.back();">
						</div>
						<div><span style="color: red;">${result }</span> </div>
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

