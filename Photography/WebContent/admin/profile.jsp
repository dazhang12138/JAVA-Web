<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	if(session.getAttribute("user") == null){
		request.setAttribute("result", "请登录");
		request.getRequestDispatcher("../login.jsp").forward(request, response);
		return;
	}
	if(request.getAttribute("downs") == null){
		request.getRequestDispatcher("../GetAllDowns?path=admin/profile.jsp&type=1").forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>PhotographyArt</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <script type="text/javascript" src="assets/js/jquery.js"></script>

    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/loader-style.css">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/css/profile.css">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/minus.png">
    <script type="text/javascript">
    	function applyfor() {
    		$.ajax({
				   type: "POST",
				   url: "../ApplyFor",
				   success: function(msg){
						$("#rreessuu").html(msg);
				  	}
			});
		}
    </script>
</head>

<body><div id="awwwards" class="right black"><a href="http://www.awwwards.com/best-websites/apricot-navigation-admin-dashboard-template" target="_blank">best websites of the world</a></div>
    <!-- Preloader -->
    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div>
    <!-- TOP NAVBAR -->
    <nav role="navigation" class="navbar navbar-static-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" class="navbar-toggle" type="button">
                    <span class="entypo-menu"></span>
                </button>
                <button class="navbar-toggle toggle-menu-mobile toggle-left" type="button">
                    <span class="entypo-list-add"></span>
                </button>

                <div id="logo-mobile" class="visible-xs">
                   <h1>Photography</h1>
                </div>

            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <jsp:include page="Collectthenavlinks.jsp"></jsp:include>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- /END OF TOP NAVBAR -->

    <!-- SIDE MENU -->
    <div id="skin-select">
        <div id="logo">
            <h2>Photography</h2>
        </div>

        <a id="toggle">
            <span class="entypo-menu"></span>
        </a>
        <div class="dark">
            <form action="#">
                <span>
                    <input type="text" name="search" value="" class="search rounded id_search" placeholder="Search Menu..." autofocus="">
                </span>
            </form>
        </div>

        <div class="search-hover">
            <form id="demo-2">
                <input type="search" placeholder="Search Menu..." class="id_search">
            </form>
        </div>

        <div class="skin-part">
            <div id="tree-wrap">
                <div class="side-bar">
					<jsp:include page="menu.jsp"></jsp:include>
                    <div class="side-dash">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END OF SIDE MENU -->

    <!--  PAPER WRAP -->
    <div class="wrap-fluid">
        <div class="container-fluid paper-wrap bevel tlbr">

            <!-- CONTENT -->
            <!--TITLE -->
            <div class="row">
                <div id="paper-top">
                    <div class="col-sm-3">
                        <h2 class="tittle-content-header">
                            <i class="entypo-vcard"></i> 
                            <span>个人简介
                            </span>
                        </h2>

                    </div>

                    <div class="col-sm-7">
                        <div class="devider-vertical visible-lg"></div>
                        <div class="tittle-middle-header">

                            <div class="alert">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <span class="tittle-alert entypo-info-circled"></span>
                                Welcome back,&nbsp;
                                <strong>Dave mattew!</strong>&nbsp;&nbsp;Your last sig in at Yesterday, 16:54 PM
                            </div>


                        </div>

                    </div>
                    <div class="col-sm-2">
                        <div class="devider-vertical visible-lg"></div>
                        <div class="btn-group btn-wigdet pull-right visible-lg">
                            <div class="btn">
                                Widget</div>
                            <button data-toggle="dropdown" class="btn dropdown-toggle" type="button">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul role="menu" class="dropdown-menu">
                                <li>
                                    <a href="#">
                                        <span class="entypo-plus-circled margin-iconic"></span>Add New</a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="entypo-heart margin-iconic"></span>Favorite</a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="entypo-cog margin-iconic"></span>Setting</a>
                                </li>
                            </ul>
                        </div>


                    </div>
                </div>
            </div>
            <!--/ TITLE -->

            <!-- BREADCRUMB -->
            <ul id="breadcrumb">
                <li>
                    <span class="entypo-home"></span>
                </li>
                <li><i class="fa fa-lg fa-angle-right"></i>
                </li>
                <li><a href="#" title="Sample page 1">个人信息</a>
                </li>
                <li><i class="fa fa-lg fa-angle-right"></i>
                </li>
                <li><a href="#" title="Sample page 1">简介</a>
                </li>
                <li class="pull-right">
                    <div class="input-group input-widget">

                        <input style="border-radius:15px" type="text" placeholder="Search..." class="form-control">
                    </div>
                </li>
            </ul>

            <!-- END OF BREADCRUMB -->

            <div class="content-wrap">
                <!-- PROFILE -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="well profile">
                            <div class="col-sm-12">
                                <div class="col-xs-12 col-sm-4 text-center">

                                    <ul class="list-group">
                                        <li class="list-group-item text-left">
                                            <span class="entypo-user"></span>&nbsp;&nbsp;简介</li>
                                        <li class="list-group-item text-center">
                                            <img src="<%=basePath %>admin/assets/img/touxiang.png" alt="" class="img-circle img-responsive img-profile">

                                        </li>
                                        <li class="list-group-item text-center">
                                            <span class="pull-left">
                                                <strong>等级</strong>
                                            </span>
                                            <div class="ratings">

                                                <a href="#">
                                                    <span class="fa fa-star"></span>
                                                </a>
                                                <a href="#">
                                                    <span class="fa fa-star-o"></span>
                                                </a>
                                                <a href="#">
                                                    <span class="fa fa-star-o"></span>
                                                </a>
                                                <a href="#">
                                                    <span class="fa fa-star-o"></span>
                                                </a>
                                                <a href="#">
                                                    <span class="fa fa-star-o"></span>
                                                </a>

                                            </div>
                                        </li>
                                        <li class="list-group-item text-right">
                                            <span class="pull-left">
                                                <strong>登录天数</strong>
                                            </span>${user.u_signday }</li>
                                        <li class="list-group-item text-right">
                                            <span class="pull-left">
                                                <strong>上次签到日期</strong>
                                            </span>${user.u_signdate != null ? user.u_signdate : "暂无签到" }</li>
                                        <li class="list-group-item text-right">
                                            <span class="pull-left">
                                                <strong>角色</strong>
                                            </span>
                 						<c:if test="${user.u_role == '0' }">
                 						管理员
                 						</c:if>
                 						<c:if test="${user.u_role == '1' }">
                 						普通用户
                 						</c:if>
                 						</li>
                 						<li class="list-group-item text-right">
                 							<span class="pull-left">
                                                <strong>修改</strong>
                                            </span>
                 							<a type="button" class="btn btn-warning" href="<%=basePath %>admin/alterUser.jsp" >修改个人信息</a>
                 						</li>
                                    </ul>
                                    <a type="button" class="btn btn-warning" onclick="applyfor()" >申请管理员</a>
                                    <span id="rreessuu" style="color: red;"></span>
                                </div>
                                <div class="col-xs-12 col-sm-8 profile-name">
                                    <h2>个人信息
                                        <span class="pull-right social-profile">
                                            <i class="entypo-facebook-circled"></i>  
                                            <i class="entypo-twitter-circled"></i>  
                                            <i class="entypo-linkedin-circled"></i>  
                                            <i class="entypo-github-circled"></i>  
                                            <i class="entypo-gplus-circled"></i>
                                        </span>
                                    </h2>
                                    <hr>

                                    <dl class="dl-horizontal-profile">
                                        <dt><span class="tags">编号</span></dt>
                                        <dd>${user.u_id }</dd>

                                        <dt><span class="tags">姓名</span></dt>
                                        <dd>${user.u_name }</dd>

                                        <dt><span class="tags">邮箱</span></dt>
                                        <dd>${user.u_email }</dd>

                                        <dt><span class="tags">手机号</span></dt>
                                        <dd>${user.u_phone }</dd>

                                        <dt><span class="tags">用户名</span></dt>
                                        <dd>${user.u_uname }</dd>

                                        <dt><span class="tags">密码</span></dt>
                                        <dd>******</dd>

                                        <dt><span class="tags">积分</span></dt>
                                        <dd>${user.u_price }</dd>

                                        <dt><span class="tags">余额</span></dt>
                                        <dd>${user.u_balance }</dd>

                                        <dt><span class="tags">状态</span></dt>
                                        <dd>正常</dd>
                                    </dl>
                                    <hr>
                                    <h5>
                                        <span class="entypo-arrows-ccw"></span>&nbsp;&nbsp;动态</h5>

                                    <div class="table-responsive" style="height: 100px;overflow: auto;">
                                        <table class="table table-hover table-striped table-condensed">

                                            <tbody>
                                            <c:forEach items="${downs }" var="d">
                                                <tr>
                                                    <td><i class="pull-right fa fa-edit"></i>
                                                     ${d.d_date } : ${d.d_type==1 ? "签到" : d.d_type==2 ? "下载--图片编号：" : d.d_type==3 ? "被下载--图片编号：" : "积分兑换"} ${d.d_type!=1 && d.d_type!=4 ? d.pic.p_id : "" } : 积分 [${d.d_update }]</td>
                                                </tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 divider text-center">
                                <div class="col-xs-12 col-sm-4 emphasis">
                                    <h2>
                                        <strong>${user.u_balance } ￥</strong>
                                    </h2>
                                    <p>
                                        <small>余额</small>
                                    </p>
                                    <button class="btn btn-success btn-block" onclick="location.href='<%=basePath %>admin/money.jsp'">
                                        <span class="fa fa-plus-circle"></span>&nbsp;&nbsp;充值</button>
                                </div>
                                <div class="col-xs-12 col-sm-4 emphasis">
                                    <h2>
                                        <strong>${user.u_price }</strong>
                                    </h2>
                                    <p>
                                        <small>积分</small>
                                    </p>
                                    <button class="btn btn-info btn-block" onclick="location.href='<%=basePath %>admin/pointsfor.jsp'">
                                        <span class="fa fa-user"></span>&nbsp;&nbsp;兑换积分</button>
                                </div>
                               <div class="col-sm-4 emphasis">
                                    <h2>
                                        <strong>${user.u_signday }</strong>
                                    </h2>
                                    <p>
                                        <small>登录天数</small>
                                    </p>
                                    <button class="btn btn-default btn-block" onclick="location.href='<%=basePath %>index.jsp'">                                        <span class="fa fa-user"></span>&nbsp;&nbsp;去签到</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END OF PROFILE -->


                <!-- /END OF CONTENT -->



                <!-- FOOTER -->
                <div class="footer-space"></div>
                <div id="footer">
                    <div class="devider-footer-left"></div>
                    <div class="time">
                        <p id="spanDate">
                        <p id="clock">
                    </div>
                    <div class="copyright">创作者|
                        <span class="entypo-heart"></span> | Mr.Da</div>
                    <div class="devider-footer"></div>

                </div>
                <!-- / END OF FOOTER -->

            </div>
        </div>
        <!--  END OF PAPER WRAP -->

        <!-- RIGHT SLIDER CONTENT -->
 <!-- RIGHT SLIDER CONTENT -->
		<jsp:include page="RIGHTSLIDERCONTENT.jsp"></jsp:include>
    <!-- END OF RIGHT SLIDER CONTENT-->
		<!-- 火箭返回顶部 -->
		<jsp:include page="rocket.html"></jsp:include>
        <!-- MAIN EFFECT -->
        <script type="text/javascript" src="assets/js/preloader.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap.js"></script>
        <script type="text/javascript" src="assets/js/app.js"></script>
        <script type="text/javascript" src="assets/js/load.js"></script>
        <script type="text/javascript" src="assets/js/main.js"></script>
</div></body>

</html>
