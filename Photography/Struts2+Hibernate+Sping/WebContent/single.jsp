<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />

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

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Owl Carousel -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">
	
	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
		function dpic(pid) {
			$.ajax({
				   type: "POST",
				   url: "D1",
				   data: "pid=" + pid,
				   success: function(msg){
					 alert(msg);
					 if(msg == '扣除您响应的下载积分.' || msg == '您之前下载过这张图片,此次免积分下载.'){
						 window.location.href='D2?pid='+pid;
					 }
				  }
				});
		}
		function downAlbums(aid) {
			$.ajax({
				   type: "POST",
				   url: "AD1",
				   data: "aid=" + aid,
				   success: function(msg){
					  alert(msg);
					 if(msg == '已经下载专辑内所有图片，如果您之前下载过其中的图片，将不会扣除您对应的积分.'){
						 window.location.href='AD2?aid='+aid;
					 }
				  }
				});
		}
		
	</script>
	</head>
<body> 
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<aside id="fh5co-aside" role="complementary" class="border js-fullheight">

			<h1 id="fh5co-logo"><a href="index.jsp"><img src="images/logo-colored.png" alt="Free HTML5 Bootstrap Website Template"></a></h1>
			<nav id="fh5co-main-menu" role="navigation">
				<ul>
					<li><a href="<%=basePath %>index.jsp">主页</a></li>
					<li><a href="<%=basePath %>Albums_getCount.action">概述</a></li>
					<li><a href="<%=basePath %>login.jsp">登录</a></li>
					<li><a href="<%=basePath %>sign-up.jsp">注册</a></li>
					<li><a href="<%=basePath %>admin/index.jsp">管理</a></li>
				</ul>
			</nav>

			<div class="fh5co-footer">
			<c:if test="${user != null}">
					<h4 style="color: gray;">${user.UName },下午好</h4>
					<h5 id="priceadd" style="color: gray;">您的积分:${user.UPrice }</h5>
					<h5 style="color: gray;"><jsp:include page="lignday.html"></jsp:include><button onclick="location.href='User_loginOut.action';">注销</button></h5>
				</c:if>
				<ul>
					<li><a href="https://www.facebook.com/"><i class="icon-facebook"></i></a></li>
					<li><a href="https://twitter.com/"><i class="icon-twitter"></i></a></li>
					<li><a href="https://www.instagram.com/"><i class="icon-instagram"></i></a></li>
					<li><a href="http://www.linkedin.com/"><i class="icon-linkedin"></i></a></li>
				</ul>
			</div>
		</aside>
		<div id="fh5co-main">
			<div class="fh5co-narrow-content animate-box fh5co-border-bottom" data-animate-effect="fadeInLeft">
				<h2 class="fh5co-heading" ><span>${album.AName }&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp; ${album.paUser.UName }</span></h2>
				<p style="word-wrap:break-word;">${album.AProfile }</p>
				<div class="col-md-12">
					<p class="text-center"><a onclick="downAlbums(${album.AId})" class="btn btn-primary btn-outline">下载专辑</a></p>
				</div>
				<div class="row">
					<c:forEach items="${album.paPictures }" var="p">
					<div class="col-md-12">
						<span>作者：${p.PName}</span>
						&nbsp;&nbsp;&nbsp;&nbsp; <span>拍摄时间：${p.PTime}</span>
						&nbsp;&nbsp;&nbsp;&nbsp; <span>下载积分：${p.PPrice}</span>
						<figure>
						<img src="<%=basePath %>getPic?type=2&id=${p.PId}" class="img-responsive">
							<figcaption style="word-wrap:break-word;">${p.PProfile }</figcaption>
						</figure>
					</div>
					<div class="col-md-12">
						<p class="text-center"><a onclick="dpic('${p.PId}')" class="btn btn-primary btn-outline">下载</a></p>
					</div>
					</c:forEach>
				</div>
			</div>
			<div align="center">
			<span style="color: red;">为维护版权利益，所有图片进行加密处理，下载图片可以查看原图片.</span>
			</div>
		</div>
	</div>
	<!-- 返回顶部 -->
	<jsp:include page="rocket.html"></jsp:include>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Counters -->
	<script src="js/jquery.countTo.js"></script>
	
	<!-- MAIN JS -->
	<script src="js/main.js"></script>

	</body>
</html>

