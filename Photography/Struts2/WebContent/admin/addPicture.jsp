<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link href="assets/js/iCheck/flat/all.css" rel="stylesheet">
    <link href="assets/js/colorPicker/bootstrap-colorpicker.css" rel="stylesheet">
    <link href="assets/js/switch/bootstrap-switch.css" rel="stylesheet">
    <link href="assets/js/idealform/css/jquery.idealforms.css" rel="stylesheet">
    <link href="assets/js/colorPicker/bootstrap-colorpicker.css" rel="stylesheet">
    <link href="assets/js/validate/validate.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/js/timepicker/bootstrap-timepicker.css">
    <link rel="stylesheet" href="assets/js/datepicker/datepicker.css">
    <link rel="stylesheet" href="assets/js/datepicker/clockface.css">
    <link rel="stylesheet" href="assets/js/datepicker/bootstrap-datetimepicker.css">
    <link rel="stylesheet" type="text/css" href="assets/js/tag/jquery.tagsinput.css">
    

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/minus.png">
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
                            <i class="icon-document-edit"></i> 
                            <span>添加图片
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
                <li><a href="#" title="Sample page 1">专辑管理</a>
                </li>
                <li><i class="fa fa-lg fa-angle-right"></i>
                </li>
                <li><a href="#" title="Sample page 1">添加图片</a>
                </li>
                <li class="pull-right">
                    <div class="input-group input-widget">

                        <input style="border-radius:15px" type="text" placeholder="Search..." class="form-control">
                    </div>
                </li>
            </ul>

            <!-- END OF BREADCRUMB -->
			<div style="margin-left: 50px;margin-top: 20px">
				<button class="btn btn-info" onclick="window.history.back();">返回</button>
			</div>

            <div class="content-wrap">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="nest" id="basicClose">
                            <div class="title-alt">
                                <h6>图片 :<span style="color: red;">${result }</span></h6>
                                <div class="titleClose">
                                    <a class="gone" href="#basicClose">
                                        <span class="entypo-cancel"></span>
                                    </a>
                                </div>
                                <div class="titleToggle">
                                    <a class="nav-toggle-alt" href="#basic">
                                        <span class="entypo-up-open"></span>
                                    </a>
                                </div>

                            </div>

                            <div class="body-nest" id="basic">
                                <div class="form_center">
                                    <form role="form" action="<%=basePath %>Upload_addPicture.action" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">图片名称:</label>
                                        	<input name="picture.albums.a_id" type="hidden" value="<%=request.getParameter("id") %>">
                                            <input name="picture.p_name" type="text" id="exampleInputEmail1" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">图片简介</label>
                                       		<textarea name="picture.p_profile"  placeholder="对这张图片的描述……" class="textarea form-control2"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">下载需要积分:</label>
                                       	<select name="picture.p_price">
                                       		<option value="2">2</option>
                                       		<option value="5">5</option>
                                       		<option value="10">10</option>
                                       		<option value="15">15</option>
                                       		<option value="20">20</option>
                                       		<option value="30">30</option>
                                       	</select>
                                        </div>
                                          <div class="form-group">
                                            <label for="exampleInputPassword1">拍摄时间</label>
                                            <div class="well">
                                        	<div data-date-viewmode="years" data-date-format="yyyy-mm-dd" data-date="2017-01-01" id="dpYears" class="input-group date">
                                            	<input name="date" type="text" value="2017-01-01" class="form-control" id="ssn2">
                                            	<span class="input-group-addon add-on entypo-calendar "></span>
                                        	</div>
                                    	</div>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputFile">图片</label>
                                            <input name="pic" type="file" id="exampleInputFile">
                                            <p class="help-block">选择本地的一张图片添加到专辑中,必须是合法的可以经过审核的图片.</p>
                                        </div>
                                        <button class="btn btn-info" type="submit">创建</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
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
    <script type="text/javascript" src="assets/js/colorPicker/bootstrap-colorpicker.min.js"></script>
    <script type="text/javascript" src="assets/js/inputMask/jquery.maskedinput.js"></script>
    <script type="text/javascript" src="assets/js/switch/bootstrap-switch.js"></script>
    <script type="text/javascript" src="assets/js/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="assets/js/idealform/jquery.idealforms.js"></script>

    <script type="text/javascript" src="assets/js/timepicker/bootstrap-timepicker.js"></script>
    <script type="text/javascript" src="assets/js/datepicker/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="assets/js/datepicker/clockface.js"></script>
    <script type="text/javascript" src="assets/js/datepicker/bootstrap-datetimepicker.js"></script>


    <script type="text/javascript" src="assets/js/tag/jquery.tagsinput.js"></script>

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
     <script type="text/javascript">
    $('#datetimepicker1').datetimepicker({
        language: 'pt-BR'
    });
    $('#dp1').datepicker()
    $('#dpYears').datepicker();
    $('#timepicker1').timepicker();
    $('#t1').clockface();
    $('#t2').clockface({
        format: 'HH:mm',
        trigger: 'manual'
    });

    $('#toggle-btn').click(function(e) {
        e.stopPropagation();
        $('#t2').clockface('toggle');
    });
    </script>
    <script>
    $(document).ready(function() {
        //Validation
        $('#contact-form').validate({
            rules: {
                name: {
                    minlength: 2,
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                subject: {
                    minlength: 2,
                    required: true
                },
                message: {
                    minlength: 2,
                    required: true
                }
            },
            highlight: function(element) {
                $(element).closest('.control-group').removeClass('success').addClass('error');
            },
            success: function(element) {
                element
                    .text('OK!').addClass('valid')
                    .closest('.control-group').removeClass('error').addClass('success');
            }
        });

        // MASKED INPUT

        $("#date").mask("99/99/9999", {
            completed: function() {
                alert("Your birthday was: " + this.val());
            }
        });
        $("#phone").mask("(999) 999-9999");

        $("#money").mask("99.999.9999", {
            placeholder: "*"
        });
        $("#ssn").mask("99--AAA--9999", {
            placeholder: "*"
        });


        //COLOR PICKER
        window.prettyPrint && prettyPrint();

        // Code for those demos
        var _createColorpickers = function() {
            $('#cp1').colorpicker({
                format: 'hex'
            });
            $('#cp2').colorpicker();
            $('#cp3').colorpicker();
            var bodyStyle = $('body')[0].style;
            $('#cp4').colorpicker().on('changeColor', function(ev) {
                bodyStyle.backgroundColor = ev.color.toHex();
            });
        }

        _createColorpickers();

        $('.bscp-destroy').click(function(e) {
            e.preventDefault();
            $('.bscp').colorpicker('destroy');
        });

        $('.bscp-create').click(function(e) {
            e.preventDefault();
            _createColorpickers();
        });


    });
    </script>

    <script type="text/javascript">
    function onAddTag(tag) {
        alert("Added a tag: " + tag);
    }

    function onRemoveTag(tag) {
        alert("Removed a tag: " + tag);
    }

    function onChangeTag(input, tag) {
        alert("Changed a tag: " + tag);
    }

    $(function() {

        $('#tags_1').tagsInput({
            width: 'auto'
        });
        $('#tags_2').tagsInput({
            width: 'auto',
            onChange: function(elem, elem_tags) {
                var languages = ['php', 'ruby', 'javascript'];
                $('.tag', elem_tags).each(function() {
                    if ($(this).text().search(new RegExp('//b(' + languages.join('|') + ')//b')) >= 0)
                        $(this).css('background-color', '#FBB44C');
                });
            }
        });
        $('#tags_3').tagsInput({
            width: 'auto',

            //autocomplete_url:'test/fake_plaintext_endpoint.html' //jquery.autocomplete (not jquery ui)
            autocomplete_url: 'test/fake_json_endpoint.html' // jquery ui autocomplete requires a json endpoint
        });


        // Uncomment this line to see the callback functions in action
        //          $('input.tags').tagsInput({onAddTag:onAddTag,onRemoveTag:onRemoveTag,onChange: onChangeTag});       

        // Uncomment this line to see an input with no interface for adding new tags.
        //          $('input.tags').tagsInput({interactive:false});
    });
    </script>
    
</body>
</html>
