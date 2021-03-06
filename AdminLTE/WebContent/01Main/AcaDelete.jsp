<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타이틀임</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->
  
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="../common/AdminLTE-master/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="../common/AdminLTE-master/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="../common/AdminLTE-master/bower_components/Ionicons/css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet" href="../common/AdminLTE-master/bower_components/jvectormap/jquery-jvectormap.css">
<!-- Theme style -->
<link rel="stylesheet" href="../common/AdminLTE-master/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="../common/AdminLTE-master/dist/css/skins/_all-skins.min.css">

<!-- Google Font -->
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">


<header class="main-header">

<!-- Logo -->
<a href="index2.html" class="logo">
  <!-- mini logo for sidebar mini 50x50 pixels -->
  <span class="logo-mini"><b>S</b>C</span>
  <!-- logo for regular state and mobile devices -->
  <span class="logo-lg"><b>스터디</b>캐슬</span>
</a>

<!-- Header Navbar -->
<nav class="navbar navbar-static-top">
  <!-- Sidebar toggle button-->
  <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
    <span class="sr-only">Toggle navigation</span>
  </a>
</nav>
</header>

    
    
<aside class="main-sidebar">
<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">
	<!-- Sidebar Menu -->
	<ul class="sidebar-menu" data-widget="tree">
		<li class="header">관리자 메뉴</li>
		<li class="treeview">
			<a href="#">
				<span><%=session.getAttribute("USER_ID") %>님 로그인</span>
			</a>
		</li>
		<li class="treeview active menu-open">
			<a href="/marketing/list">
				<i class="fa fa-user"></i>
				<span>학원 정보 관리</span>
				<span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span> 
			</a>
			<ul class="treeview-menu">
				<li><a href="../01Main/adminList.do">목록</a></li>
				<li><a href="../01Main/AcaDelete.do">학원 정보 삭제</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="/push/list">
				<i class="fa fa-users"></i>
				<span>회원 관리</span>
				<span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span> 
			</a>
			<ul class="treeview-menu">
				<li><a href="../01Main/MembersList.do">목록</a></li>
				<li><a href="../01Main/MembersDelete.do">회원 정보 삭제</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="./01Main/Logout.jsp">
				<i class="fa fa-circle"></i>
				<span>로그아웃</span>
			</a>
			<ul class="treeview-menu">
				<li><a href="Logout.jsp">로그아웃하기</a></li>
			</ul>
		</li>
</section>
 <!-- Sidebar Menu -->
</aside>
 
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Admin data List<small>학원정보</small></h1>
    <!-- <ol class="breadcrumb">
        <li><a><i class="fa fa-download"></i>Marketing Link</a></li>
        <li class="active">Marketing Link</li>
    </ol> -->
</section>
 



<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-12">
			<div class="box">
            <div class="box-header">
              <h3 class="box-title">학원 정보 삭제</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                  <div class="input-group-btn">
                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
				<div class="box-body table-responsive no-padding" >
					<table class="table table-bordered">
						<tbody>
							<c:choose>
								<c:when test="${empty lists }">
									<tr>
										<td colspan="7">
											등록된 데이터가 없습니다.
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${lists }" var="row" varStatus="loop">
									
										<tr><!-- 60%총 -->
											<th class="text-center" style="width:5%;">idx</th>
											<th class="text-center" style="width:20%;">이미지</th>
											<th class="text-center" style="width:20%;">분류</th>
											<th class="text-center" style="width:10%;">캐슬 이름</th>
											<th class="text-center" style="width:20%;">캐슬 주소</th>
											<th class="text-center" style="width:20%;">캐슬 평점</th>
											<th class="text-center" style="width:5%;"></th>
										</tr>
										<tr>
											<td>1</td>
											<td style="padding: 3px;height:100px;wieth:200px;"><img src="../common/images/학원광고.jpg" style="width:100%;height:100%" /></td>
											<td>전체>학원>${row.category }</td>
											<td>${row.acaintrophoto }${row.introduce }${row.id }</td>
											<td></td>
											<td>/5.0</td>
											<td>
												<button data-action="delete" type="button" class="btn btn-danger btn-block btn-sm" data-value="311400"><i class="fa fa-times"></i></button>
											</td>
										</tr>
										
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>			
				</div>
				<div class="box-footer clearfix">
					<ul class="pagination pagination-sm no-margin pull-right">
						<li class="active">
							<a href="/news/list?page=1&amp;">1</a>
						</li>
						<li>
							<a href="/news/list?page=2&amp;">2</a>
						</li>
						<li>
							<a href="/news/list?page=3&amp;">3</a>
						</li>
						<li>
							<a href="/news/list?page=4&amp;">4</a>
						</li>
						<li>
							<a href="/news/list?page=5&amp;">5</a>
						</li>
						<li>
							<a href="/news/list?page=6&amp;">6</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		</div>
	</section>
<!-- Main content -->




</div>

<footer class="main-footer">
<!-- To the right -->
<div class="pull-right hidden-xs"></div>
<!-- Default to the left -->
<strong>Copyright &copy; 2018 <a href="#">스터디캐슬</a>.
</strong>
All rights reserved.
</footer>




   
</div>



<!-- jQuery 3 -->
<script src="/AdminLTE/common/AdminLTE-master/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/AdminLTE/common/AdminLTE-master/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="/AdminLTE/common/AdminLTE-master/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/AdminLTE/common/AdminLTE-master/dist/js/adminlte.min.js"></script>
<!-- Sparkline -->
<script src="/AdminLTE/common/AdminLTE-master/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap  -->
<script src="/AdminLTE/common/AdminLTE-master/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/AdminLTE/common/AdminLTE-master/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll -->
<script src="/AdminLTE/common/AdminLTE-master/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS -->
<script src="/AdminLTE/common/AdminLTE-master/bower_components/chart.js/Chart.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="/AdminLTE/common/AdminLTE-master/dist/js/pages/dashboard2.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../common/AdminLTE-master/dist/js/demo.js"></script>
<!-- /AdminLTE/WebContent/WEB-INF/common/AdminLTE-master/dist/js/demo.js -->
</body>
</html>