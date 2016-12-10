<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description"
			content="控制台管理程序">
		<meta name="author" content="baijw">
		<!-- App title -->
		<title>智慧服饰</title>
		<!-- App CSS -->
		<link href="css/homestyle.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="fixed-left widescreen" style="overflow-y: hidden">
	
	
		<!-- Begin page -->
		<div id="wrapper">
	
			<!-- Top Bar Start -->
			<div class="topbar">
	
				<!-- LOGO -->
				<div class="topbar-left">
					<a	href="#"	class="logo"> 
						<img class="homeIcon" src="img/logo.png" style="height: 50px; -webkit-border-radius: 20px;
																						-moz-border-radius: 20px;
																						border-radius: 20px;">
						<span><img src="img/logo-word1.png" style="width: 150px;"></span>
					</a>
				</div>
	
				<nav class="navbar navbar-custom">
					<ul class="nav navbar-nav">
						<li class="nav-item">
							<button
								class="button-menu-mobile open-left waves-light waves-effect">
								<img src="images/menu.png">
							</button>
						</li>
					</ul>
	
					<ul class="nav navbar-nav pull-right">
						<li class="nav-item dropdown notification-list">
						<a class="nav-link dropdown-toggle arrow-none waves-effect waves-light nav-user"
							data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="false"> 
						<img src="img/profile.png" alt="user" class="img-circle">
							</a>
							<div class="dropdown-menu dropdown-menu-right dropdown-arrow profile-dropdown " aria-labelledby="Preview">
								<!-- item-->
								<div class="dropdown-item noti-title">
									<h5 class="text-overflow">
										<small>Welcome ! ${userName}</small>
									</h5>
								</div>
	
								<!-- item-->
								<a href="logout.do" class="dropdown-item notify-item">
									<img src="images/logout.png" />
									<span>注销</span>
								</a>
								
								<a class="userinfoform dropdown-item notify-item" href="javascript:void(0)" menuurl="/UserInfoForm.html" >
									<img src="images/iconfont-myfill.png" />
									<span>信息维护</span>
								</a>
							</div></li>
					</ul>
				</nav>
	
			</div>
			<!-- Top Bar End -->
	
			<!-- ========== Left Sidebar Start ========== -->
			<div class="left side-menu">
				<div class="slimScrollDiv"
					style="position: relative; overflow: hidden; width: auto; height: 503px;">
					<div class="sidebar-inner slimscrollleft"
						style="overflow: hidden; width: auto; height: 503px;">
						<!--- Sidemenu -->
						<div id="sidebar-menu">
							<ul>
								<#list menulist as item>
									<li class="has_sub" ;">
										<a href="javascript:void(0);" class="waves-effect" style="font-size:16px;background-color:#64b0f2; font-family:微软雅黑 ;color:#ffffff !important; border-bottom: 1px solid rgba(24, 137, 234,0.6);">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span></i> <span> ${item.menuName} </span></a>
										<ul class="list-unstyled">
											<#list item.submenus as record>
												<li>
													<a class="contentlink" href="javascript:void(0)" menuurl="${record.menuUrl}" >${record.menuName}</a>
												</li>
											</#list>
										</ul>
									</li>
								</#list>
							</ul>
							<div class="clearfix"></div>
						</div>
						<!-- Sidebar -->
						<div class="clearfix"></div>
					</div>
					<div class="slimScrollBar"
						style="width: 5px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 397.884px; visibility: visible; background: rgb(220, 220, 220);"></div>
					<div class="slimScrollRail"
						style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div>
				</div>
			</div>
			<!-- Left Sidebar End -->
	
			<!-- Start content-page -->
			<div class="content-page">
				<!-- Start content -->
				<div class="content">
					<div class="container">
						<iframe id="content_frame" frameborder="no" src="getAllLocation.do"></iframe><!--里面可以定义网页-->
					</div>
					<!-- container -->
				</div>
				<!-- content -->
			</div>
			<!-- End content-page -->
	
			<footer class="footer text-right"> 2016 &copy; 兰州大学先进计算实验室. <span class="pull-right">兰州市第三人民医院老年科特别版</span></footer>
			
		</div>
		<!-- END wrapper -->
	
		<script src="bootstrap/js/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="js/waves.js"></script>
		<script src="bootstrap/js/jquery.app.js"></script>
		
		<script type="text/javascript">
			$("#content_frame,.container").height($(window).height()-$(".footer").height()-$(".navbar").height()-70).width("90%");
			$(".contentlink").click(function(){
				var menuUrl = this.getAttribute("menuurl");
				$("#content_frame").attr("src","${rc.getContextPath()}"+menuUrl);
				$(".contentlink").parent().attr("class","disactive");
				$(".contentlink").parent().parent().attr("style","");
				$(this).parent().attr("class","active");
				$(this).parent().parent().attr("style","display: block");
			});
			$(".userinfoform").click(function(){
				var menuUrl = this.getAttribute("menuurl");
				$("#content_frame").attr("src","${rc.getContextPath()}"+menuUrl);
			});
			
		</script>
		
	</body>
</html>