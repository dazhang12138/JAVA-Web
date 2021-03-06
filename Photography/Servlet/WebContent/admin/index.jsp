<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	if(request.getAttribute("albums") == null){
		request.getRequestDispatcher("../AllAblums?path=admin/index.jsp&stat=1").forward(request, response);
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
    <link rel="stylesheet" href="assets/js/button/ladda/ladda.min.css">

    <link href="assets/js/iCheck/flat/all.css" rel="stylesheet">
    <link href="assets/js/iCheck/line/all.css" rel="stylesheet">

    <link href="assets/js/colorPicker/bootstrap-colorpicker.css" rel="stylesheet">
    <link href="assets/js/switch/bootstrap-switch.css" rel="stylesheet">
    <link href="assets/js/validate/validate.css" rel="stylesheet">
    <link href="assets/js/idealform/css/jquery.idealforms.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/minus.png">
    <script type="text/javascript">
    	function cheakAll() {
			var seleall = document.getElementById("all");
			var sele = document.getElementsByName("allcheck");
			if(seleall.checked){
				for (var i = 0; i < sele.length; i++) {
					sele[i].checked=true;
				}
			}else{
				for (var i = 0; i < sele.length; i++) {
					sele[i].checked=false;
				}
			}
		}
    	function deleteAll() {
    		var sele = document.getElementsByName("allcheck");
    		var str = "";
    		for (var i = 0; i < sele.length; i++) {
    			if(sele[i].checked){
	    			str += sele[i].value+",";
    			}
			}
    		if(confirm('此操作甚是危险，请再三检测。将会删除所选择的所有图片')){
    			alert('系统检测到危险操作，已终止运行。');
	    		//location.href="DeleteBook?type=choicedelete&str="+str;
    		}
    		//alert(str.substring(0,50)+"\n"+str.substring(50,100)+"\n"+str.substring(100,150));
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
            <!-- CONTENT
            TITLE -->
            <div class="row">
                <div id="paper-top">
                    <div class="col-sm-3">
                        <h2 class="tittle-content-header">
                            <i class="icon-document-edit"></i> 
                            <span>专辑管理
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
          <!--   / TITLE

            BREADCRUMB -->
            <ul id="breadcrumb">
                <li>
                    <span class="entypo-home"></span>
                </li>
                <li><i class="fa fa-lg fa-angle-right"></i>
                </li>
                <li><a href="#" title="Sample page 1">专辑管理</a>
                </li>
                <li><i class="fa fa-lg fa-angle-right"></i>
                </li>
                <li><a href="#" title="Sample page 1">我的专辑</a>
                </li>
                <li class="pull-right">
                    <div class="input-group input-widget">

                        <input style="border-radius:15px" type="text" placeholder="Search..." class="form-control">
                    </div>
                </li>
            </ul>

          <!--   END OF BREADCRUMB -->
          <div align="center" style="margin-top: 10px;">
          <section class="progress-demo">
             <a href="<%=basePath %>AllAblums?path=admin/index.jsp&stat=1" class="ladda-button" data-color="mint" data-style="expand-right" data-size="xs">刷新列表</a>
             <button class="ladda-button" data-color="mint" data-style="expand-right" data-size="xs" onclick="deleteAll()">删除选择的专辑</button>
             </section>
             </div>
		<div class="content-wrap">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="nest" id="tableStaticClose">
                            <div class="title-alt">
                                <h6>我的专辑列表 : <span style="color: red;">${result }</span></h6>
                                <div class="titleClose">
                                    <a class="gone" href="#tableStaticClose">
                                        <span class="entypo-cancel"></span>
                                    </a>
                                </div>
                                <div class="titleToggle">
                                    <a class="nav-toggle-alt" href="#tableStatic">
                                        <span class="entypo-up-open"></span>
                                    </a>
                                </div>

                            </div>

                            <div class="body-nest" id="tableStatic">

                                <section id="flip-scroll">

                                    <table class="table table-bordered table-striped cf" id="devCompanyInfos">
                                        <thead class="cf">
                                            <tr>
                                                <th class="numeric"><input type="checkbox" id="all" onclick="cheakAll()"></th>
                                                <th class="numeric">专辑编号</th>
                                                <th class="numeric">专辑名称</th>
                                                <th class="numeric">创建作者</th>
                                                <th class="numeric">创建时间</th>
                                                <th class="numeric" style="max-width: 700px;">简介</th>
                                                <th class="numeric">图片</th>
                                                <th class="numeric">修改</th>
                                            </tr>
                                        </thead>
                                        <tbody >
                                        	<c:forEach items="${albums }" var="a">
                                            <tr>
                                                <td class="numeric"><input type="checkbox" name="allcheck" value="${a.a_id }"></td>
                                                <td class="numeric">${a.a_id }</td>
                                                <td class="numeric">${a.a_name }</td>
                                                <td class="numeric">${a.user.u_name }</td>
                                                <td class="numeric">${a.a_time }</td>
                                                <td class="numeric" style="max-width: 700px;word-wrap:break-word;">${a.a_profile }</td>
                                                <td class="numeric"> 
                                                <a type="button" class="btn btn-primary" href="<%=basePath %>admin/pic.jsp?type=1&id=${a.a_id }">查看封面</a> 
                                                <a type="button" class="btn btn-danger" href="<%=basePath %>QueryAlbumsPicture?path=admin/albumsDetails.jsp&id=${a.a_id}">专辑图片</a>
                                                </td>
                                                <td class="numeric">
                                                <a type="button" class="btn btn-default" href="<%=basePath %>GetAlbum?id=${a.a_id}">
                                    <span class="entypo-pencil"></span>&nbsp;&nbsp;修改</a>
                                                <a type="button" class="btn btn-info"  href="<%=basePath %>DeleteAlbums?id=${a.a_id}"  onclick="return confirm('是否删除 : '+'${a.a_name}'+' 专辑\n这将会删除专辑下所有图片，请三思。')" >
                                 <span class="entypo-trash"></span>&nbsp;&nbsp;删除</a></td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </section>
                            </div>
                            
                             <div class="gridItem" style="padding: 5px; width: 300px; float: left; text-align: left; height: 20px; font-size: 15px;" >   
                共有 <span id="spanTotalInfor"></span> 条记录       
                当前第<span id="spanPageNum"></span>页     
                共<span id="spanTotalPage"></span>页  
        </div>  
        <div class="gridItem" style="margin-left:50px;  padding: 5px; width: 400px; float: right; text-align: center; height: 20px; vertical-align: middle; font-size: 15px;">     
            <span id="spanFirst" >首页</span>   
            <span id="spanPre">上一页</span>  
            <span id="spanInput" style="margin: 0px; padding: 0px 0px 4px 0px; height:100%; ">   
                第<input id="Text1" type="text" class="TextBox" onkeyup="changepage()"   style="height:24px; text-align: center;width:30px ;color:red;" />页   
            </span>  
            <span id="spanNext">下一页</span>   
            <span  id="spanLast">尾页</span>   
        </div>  
                            
                            
                        </div>
                    </div>
                </div>
            </div>
		<div id="footer">
                <div class="devider-footer-left"></div>
                <div class="time">
                    <p id="spanDate">
                    </p><p id="clock">
                </p></div>
                <div class="copyright">创作者|
                    <span class="entypo-heart"></span> | Mr.Da</div>
                <div class="devider-footer"></div>

            </div>
            <!-- / END OF FOOTER -->


        </div>
    </div>
    <!--  END OF PAPER WRAP -->
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
    

    <!-- /MAIN EFFECT -->
   <script type="text/javascript" src="assets/js/iCheck/jquery.icheck.js"></script>
    <script type="text/javascript" src="assets/js/switch/bootstrap-switch.js"></script>
    
    <script type="text/javascript">  
              var theTable = document.getElementById("devCompanyInfos");//Id选择对了就OK！   ${devCompanyInfos }是所有后台返回数据  
              var txtValue = document.getElementById("Text1").value;  
              function changepage() {  
                var txtValue2 = document.getElementById("Text1").value;  
                if (txtValue != txtValue2) {  
                  if (txtValue2 > pageCount()) {  
                  }  
                  else if (txtValue2 <= 0) {  
                  }  
                  else if (txtValue2 == 1) {  
                    first();  
                  }  
                  else if (txtValue2 == pageCount()) {  
                    last();  
                  }  
                  else {  
                    hideTable();  
                    page = txtValue2;  
                    pageNum2.value = page;  
                    currentRow = pageSize * page;  
                    maxRow = currentRow - pageSize;  
                    if (currentRow > numberRowsInTable) currentRow = numberRowsInTable;  
                    for (var i = maxRow; i < currentRow; i++) {  
                      theTable.rows[i].style.display = '';  
                    }  
                    if (maxRow == 0) { preText(); firstText(); }  
                    showPage();  
                    nextLink();  
                    lastLink();  
                    preLink();  
                    firstLink();  
                   }  
                  txtValue = txtValue2;  
                }  
            }  
        </script>  
        <!-- 引入分页js -->  
        <script type="text/javascript" src="assets/js/Pagging.js"></script>  
    
    
    <!--  PLUGIN -->
    <script>
    $(document).ready(function() {
        //CHECKBOX PRETTYFY
        $('.skin-flat input').iCheck({
            checkboxClass: 'icheckbox_flat-red',
            radioClass: 'iradio_flat-red'
        });
        $('.skin-line input').each(function() {
            var self = $(this),
                label = self.next(),
                label_text = label.text();

            label.remove();
            self.iCheck({
                checkboxClass: 'icheckbox_line-blue',
                radioClass: 'iradio_line-blue',
                insert: '<div class="icheck_line-icon"></div>' + label_text
            });
        });
        //Switch Button

        $('.make-switch').bootstrapSwitch('setSizeClass', 'switch-small');
    });
    </script>
</body>
</html>
