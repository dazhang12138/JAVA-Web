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

	</head>
	<body>

	<div id="fh5co-page">

		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<aside id="fh5co-aside" role="complementary" class="border js-fullheight">

			<h1 id="fh5co-logo"><a href="index.jsp"><img src="images/logo-colored.png" alt="Free HTML5 Bootstrap Website Template"></a></h1>
			<nav id="fh5co-main-menu" role="navigation">
				<ul>
					<li ><a href="<%=basePath %>index.jsp">主页</a></li>
					<li class="fh5co-active"><a href="<%=basePath %>QueryCount">概述</a></li>
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

			<div class="fh5co-narrow-content">
				<div class="row">
					<div class="col-md-5">
						<h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">我是摄影师Da</h2>
						<p class="fh5co-lead animate-box" data-animate-effect="fadeInLeft">我创立这个网站，为所有的摄影爱好者提供最好的摄影平台.致力于摄影协同发展.无论战争、无论和平.</p>
						<p class="animate-box" data-animate-effect="fadeInLeft">在这里你可以自由的评论我们，也可以上传自己的作品供人欣赏.平台还在成长中，需要我们共同维护这里，如果你有好的建议或意见，可以联系我们.</p>
					</div>
					<div class="col-md-6 col-md-push-1 animate-box" data-animate-effect="fadeInLeft">
						<img src="images/img_1225.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive"> 
					</div>
				</div>
				
			</div>

			<div class="fh5co-narrow-content  animate-box fh5co-border-bottom" data-animate-effect="fadeInLeft">
				<h2 class="fh5co-heading" >追随的脚步</span></h2>

				<div class="row">
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_MaxRiv.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Max Riv</h3>
						<h4>荷兰, Principal</h4>
						<p>Max Riv作品主要拍摄于高山地区，其作品气势磅礴，但视觉焦点常常会放在画面中的一个非常孤单渺小的个人身上，背景绮丽的风光恰好体现出人类不畏艰难的探险精神.</p>
					</div>
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_KilianSchönberger.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Kilian Schönberger</h3>
						<h4>德国, Principal</h4>
						<p>有趣的是Schönberger是一位色盲摄影师，不过这丝毫不影响他的摄影创作，其作品非常注重对场景光影、气氛和质感的表达.</p>
					</div>
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_PeterLik.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Peter Lik</h3>
						<h4>澳大利亚, Principal</h4>
						<p>Peter Lik可能是这里面争议最大的一位摄影师。他的作品《Phantom》以650万美元的成交价格问鼎全球最贵照片，但由于作品水平一般，摄影界、收藏界普遍认为这只是Peter Lik的营销手段而已.</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_VincentFavre.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Vincent Favre</h3>
						<h4>法国, Principal</h4>
						<p>Favre涉猎就比较广了，高山、冰川、海景均有体现，其作品给人以静谧感，让人有一种身临其境的感觉.</p>
					</div>
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_LarsvandeGoor.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3> Lars van de Goor</h3>
						<h4>荷兰, Principal</h4>
						<p>Lars van de Goor曾入围哈苏摄影大师赛风光/自然组10人名单，色彩是他最重要的表现方法.</p>
					</div>
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_EvgeniDinev.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Evgeni Dinev</h3>
						<h4>保加利亚, Principal</h4>
						<p>诞生大量童话文学的东欧也培养了像Evgeni Dinev这样的情感表达浓烈的风光摄影师。Dinev可谓是占尽了天时地利，很多普通场景在他的镜头下往往会冒出一些意想不到的事件.</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_TreyRatcliff.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Trey Ratcliff</h3>
						<h4>新西兰, Principal</h4>
						<p>评论家认为Ratcliff作品个人风格明显，色彩对比大胆，但很好体现出自然界惊世之美，照片独特、令人惊叹.</p>
					</div>
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_DarrenWhite.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>Darren White</h3>
						<h4>美国, Principal</h4>
						<p>准确来说Darren White是一位纯粹的商业摄影师，所以非常讲求“卖点”，有时是风光本身，有时则是技术.</p>
					</div>
					<div class="col-md-4 fh5co-staff">
						<img src="images/person_DavidKeochkerian.jpg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
						<h3>David Keochkerian</h3>
						<h4>法国, Principal</h4>
						<p>“不会拍照的博士不是好医生”，这句话套用在David Keochkerian身上应该再合适不过了。Keochkerian是风光摄影里所谓的技术派，不仅拍摄题材广泛，器材涉猎也颇为丰富，作品还展现出医生特有的敏锐洞察力.</p>
					</div>
				</div>
			</div>

		

			<div class="fh5co-narrow-content fh5co-border-bottom">
				<h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">说明</h2>
				<div class="row">
					<div class="col-md-6">
						<div class="fh5co-feature animate-box" data-animate-effect="fadeInLeft">
							<div class="fh5co-icon">
								<i class="icon-strategy"></i>
							</div>
							<div class="fh5co-text">
								<h3>自由</h3>
								<p>在这里我们承诺所有言行的自由.</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="fh5co-feature animate-box" data-animate-effect="fadeInLeft">
							<div class="fh5co-icon">
								<i class="icon-telescope"></i>
							</div>
							<div class="fh5co-text">
								<h3>隐私</h3>
								<p>在这里我们承诺所有信息安全，我们不会泄露任何隐私信息。</p>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="fh5co-feature animate-box" data-animate-effect="fadeInLeft">
							<div class="fh5co-icon">
								<i class="icon-circle-compass"></i>
							</div>
							<div class="fh5co-text">
								<h3>提议</h3>
								<p>站长在这里向所有人提议：无论是管理员还是用户共同维护世界和平、维护网站信息合法、共创和平社会。</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="fh5co-feature animate-box" data-animate-effect="fadeInLeft">
							<div class="fh5co-icon">
								<i class="icon-tools-2"></i>
							</div>
							<div class="fh5co-text">
								<h3>维护</h3>
								<p>为维护网站工作日常所需，急聘网站管理员。能力越大，责任越大。</p>
							</div>
						</div>
					</div>

				</div>
			</div>
		<div class="fh5co-testimonial" >
				<div class="fh5co-narrow-content">
					<div class="owl-carousel-fullwidth animate-box" data-animate-effect="fadeInLeft">
		            <div class="item">
		            	<figure>
		            		<img src="images/testimonial_person2.jpg" alt="Free HTML5 Bootstrap Template">
		            	</figure>
		              	<p class="text-center quote">为了营造一个开放和欢迎的环境，我们的贡献者和维护者承诺，无论年龄、体型、残疾、种族、
		              	性别身份和表情、经验、国籍、个人外貌、种族、宗教、性别身份和取向，都将参与到我们的项目和社区中，为每个人提供免费的体验。
		              	<br>In the interest of fostering an open and welcoming environment, 
		              	we ascontributors and maintainers pledge to making participation 
		              	in our project andour community a harassment-free experience for 
		              	everyone, regardless of age, bodysize, disability, ethnicity, gender 
		              	identity and expression, level of experience,nationality, personal 
		              	appearance, race, religion, or sexual identity andorientation.</p>
		            </div>
		            <div class="item">
		            	<figure>
		            		<img src="images/testimonial_person3.jpg" alt="Free HTML5 Bootstrap Template">
		            	</figure>
		              	<p class="text-center quote">管理人员负责澄清可接受行为的标准，
		              	并期望在任何不可接受行为的实例中采取适当和公正的纠正措施。
		              	<br>管理人员有权利和责任去删除、编辑或拒绝提交、修改和其他不符合这一行为准则的贡献，
		              	或者禁止对他们认为不适当、具有威胁、冒犯或有害的其他行为作出临时或永久的贡献。
		              	<br>Project maintainers are responsible for clarifying the standards of 
		              	acceptablebehavior and are expected to take appropriate and fair 
		              	corrective action inresponse to any instances of unacceptable behavior.
		              	<br>Project maintainers have the right and responsibility to remove,
		              	 edit, orreject comments, commits, code, wiki edits, issues, and
		              	  other contributionsthat are not aligned to this Code of Conduct,
		              	   or to ban temporarily orpermanently any contributor for other
		              	    behaviors that they deem inappropriate,threatening, offensive,
		              	     or harmful.</p>
		            </div>
		            <div class="item">
		            	<figure>
		            		<img src="images/testimonial_person4.jpg" alt="Free HTML5 Bootstrap Template">
		            	</figure>
		              	<p class="text-center quote">通过联系项目团队836160973da@gmail.com，
		              	可以报告滥用、骚扰或其他不可接受行为的实例。项目团队将对所有投诉进行审查和调查，
		              	并将以其认为合适的方式作出回应。项目团队有义务对事件的记者保持保密。
		              	具体实施政策的进一步细节可能另行公布。
		              	不遵守或执行行为守则的项目维护者可能会面临由项目领导其他成员所决定的临时或永久性的影响。
		              	Instances of abusive, harassing, or otherwise unacceptable 
		              	behavior may bereported by contacting the project team at 
		              	[INSERT EMAIL ADDRESS]. Allcomplaints will be reviewed and 
		              	investigated and will result in a response thatis deemed 
		              	necessary and appropriate to the circumstances. The project 
		              	team isobligated to maintain confidentiality with regard to 
		              	the reporter of an incident.Further details of specific enforcement 
		              	policies may be posted separately.
		              	Project maintainers who do not follow or enforce the Code of Conduct 
		              	in goodfaith may face temporary or permanent repercussions as determined by 
		              	othermembers of the project’s leadership.</p>
		            </div>
		          </div>
				</div>
			</div>
			<div class="fh5co-counters" style="background-image: url(images/hero.jpg);" data-stellar-background-ratio="0.5" id="counter-animate">
				<div class="fh5co-narrow-content animate-box">
					<div class="row" >
						<div class="col-md-4 text-center">
							<span class="fh5co-counter js-counter" data-from="0" data-to="${cu }" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">用户</span>
						</div>
						<div class="col-md-4 text-center">
							<span class="fh5co-counter js-counter" data-from="0" data-to="${ca }" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">专辑</span>
						</div>
						<div class="col-md-4 text-center">
							<span class="fh5co-counter js-counter" data-from="0" data-to="${cp }" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">图片</span>
						</div>
					</div>
				</div>
			</div>
			<div id="fh5co-main">
				<div class="fh5co-more-contact" style="background: #fff;">
					<div class="fh5co-narrow-content">
						<div class="row">
							<div class="col-md-4">
								<div class="fh5co-feature fh5co-feature-sm animate-box"
									data-animate-effect="fadeInLeft">
									<div class="fh5co-icon">
										<i class="icon-envelope-o"></i>
									</div>
									<div class="fh5co-text">
										<p>83616097da@gmail.com</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="fh5co-feature fh5co-feature-sm animate-box"
									data-animate-effect="fadeInLeft">
									<div class="fh5co-icon">
										<i class="icon-map-o"></i>
									</div>
									<div class="fh5co-text">
										<p>中国, 上海, 老街巷, 053号 .邮编:00110</p>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="fh5co-feature fh5co-feature-sm animate-box"
									data-animate-effect="fadeInLeft">
									<div class="fh5co-icon">
										<i class="icon-phone"></i>
									</div>
									<div class="fh5co-text">
										<p>+86 176 3377 7700</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	

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

