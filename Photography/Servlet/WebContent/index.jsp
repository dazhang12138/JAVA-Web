<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%String path = request.getContextPath();
  		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  		if(request.getAttribute("albums") == null){
  			request.getRequestDispatcher("AllAblums?path=index.jsp&stat=0&type=1").forward(request, response);
  		}
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

	<!-- <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	 -->
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
	<!-- 签到 -->

	</head>
	<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<aside id="fh5co-aside" role="complementary" class="border js-fullheight">

			<h1 id="fh5co-logo"><a href="index.jsp"><img src="images/logo-colored.png" alt="Free HTML5 Bootstrap Website Template"></a></h1>
			<nav id="fh5co-main-menu" role="navigation">
				<ul>
					<li class="fh5co-active" ><a href="<%=basePath %>index.jsp">主页</a></li>
					<li><a href="<%=basePath %>QueryCount">概括</a></li>
					<li><a href="<%=basePath %>login.jsp">登录</a></li>
					<li><a href="<%=basePath %>sign-up.jsp">注册</a></li>
					<li><a href="<%=basePath %>admin/index.jsp">管理</a></li>
				</ul>
			</nav>
			
			<div class="fh5co-footer">
				<c:if test="${user != null}">
					<h4 style="color: gray;">${user.u_name },下午好</h4>
					<h5 id="priceadd" style="color: gray;">您的积分:${user.u_price }</h5>
					<h5 style="color: gray;"><jsp:include page="lignday.html"></jsp:include><button onclick="location.href='LoginOut';">注销</button></h5>
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
			 <div class="fh5co-gallery">
				<c:forEach items="${albums }" var="a">
					<div class="grid__item">
					<a class="gallery-item" href="QueryAlbumsPicture?path=single.jsp&id=${a.a_id }">
						 <%-- <img width="485" height="232" src="<%=basePath %>getPic?type=1&id=${a.a_id}">  --%>
						<img src="<%=basePath %>getPic?type=1&id=${a.a_id}">
						<span class="overlay">
							<h2>${a.a_name }</h2>
							<span>${a.a_time}</span>
						</span>
					</a>
					</div>
				</c:forEach>
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
	<!-- <script src="show/js/main.js"></script>  -->
	</body>
</html>

