<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<ul class="topnav menu-left-nest">
	<li><a href="#" style="border-left: 0px solid !important;"
		class="title-menu-left"> <span class="widget-menu"></span> <i
			data-toggle="tooltip" class="entypo-cog pull-right config-wrap"></i>
	</a></li>
	<li><a class="tooltip-tip ajax-load"
		href="<%=basePath%>index.jsp"> <i class="icon-monitor"></i>
			<span>Photography</span>
	</a></li>
	<li><a class="tooltip-tip ajax-load"
		href="<%=basePath%>admin/lock.jsp"> <i class="icon-lock"></i> <span>锁屏</span>

	</a></li>
</ul>
<ul id="menu-showhide" class="topnav menu-left-nest">
	<li><a href="#" style="border-left: 0px solid !important;"
		class="title-menu-left"> <span class="component"></span> <i
			data-toggle="tooltip" class="entypo-cog pull-right config-wrap"></i>

	</a></li>

	<li><a class="tooltip-tip" href="#"> <i class="icon icon-graph-pie"></i>
			<span>专辑管理</span>
	</a>
		<ul>
			<li class="active"><a class="tooltip-tip2 ajax-load"
				href="<%=basePath%>admin/index.jsp"><i class="fontawesome-folder-open"></i><span>我的专辑</span></a></li>
			<li><a class="tooltip-tip2 ajax-load"
				href="<%=basePath%>admin/addAbums.jsp"><i class="entypo-list-add"></i><span>创建专辑</span></a></li>
		</ul></li>
	<li><a class="tooltip-tip" href="#"> <i class="icon-user"></i>
			<span>用户信息</span>
	</a>
		<ul>
			<li class="active"><a class="tooltip-tip2 ajax-load"
				href="<%=basePath%>admin/profile.jsp"><i class="entypo-vcard"></i><span>个人信息</span></a></li>
			<li><a class="tooltip-tip2 ajax-load"
				href="<%=basePath%>admin/money.jsp"><i
					class="fontawesome-credit-card"></i><span>充值</span></a></li>
		</ul></li>
</ul>

<ul id="menu-showhide" class="topnav menu-left-nest">
	<li><a href="#" style="border-left: 0px solid !important; display: inline-block; float: none;" class="title-menu-left">
       <span class="design-kit" style="display: inline-block; float: none;"></span>
      <i data-toggle="tooltip" class="entypo-cog pull-right config-wrap"></i></a></li>
	<li><a class="tooltip-tip" href="<%=basePath %>admin/allAlbums.jsp"> <i class="fontawesome-folder-close-alt"></i>
			<span>专辑管理</span>
	</a></li>
	<li><a class="tooltip-tip" href="<%=basePath%>admin/allUser.jsp"> <i class="fontawesome-group"></i>
			<span>用户管理</span>
	</a></li>
	<li><a class="tooltip-tip" href="<%=basePath%>Albums_allDown.action?path=admin/allDowns.jsp&type=2"> <i class="icon icon-window"></i>
			<span>所有动态</span>
	</a></li>
	<li><a class="tooltip-tip" href="#"> <i class="fontawesome-eye-open"></i>
			<span>审批</span>
	</a>
		<ul>
			<li class="active"><a class="tooltip-tip2 ajax-load" href="<%=basePath%>User_allApply.action"><i
					class="fontawesome-exchange"></i><span>用户审批</span></a></li>
					<li><a class="tooltip-tip2 ajax-load" href="<%=basePath%>admin/recharge.jsp"><i
					class="fontawesome-exchange"></i><span>充值审批</span></a></li>
		</ul></li>
</ul>
