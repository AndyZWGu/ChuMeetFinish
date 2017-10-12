<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	AdminService adminSvc = new AdminService();
	List<AdminVO> list = adminSvc.statusadmin();
	pageContext.setAttribute("list", list);
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
	List<AdmPrilVO> admPrilList = (List)session.getAttribute("admPrilList");
	
	session.setAttribute("page", "admin");
%>

<html>
<head>
<link rel="apple-touch-icon" sizes="76x76"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/apple-icon.png" />
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/favicon.png" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>ChuMeet-Manage</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<!-- Bootstrap core CSS     -->
<link
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<!--  Material Dashboard CSS    -->
<link
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/material-dashboard.css"
	rel="stylesheet" />
<!--  CSS for Demo Purpose, don't include it in your project     -->
<link
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/demo.css"
	rel="stylesheet" />
<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons'
	rel='stylesheet' type='text/css'>
<style>
.table, .table th, .table td, .table select {
	text-align: center;
	vertical-align: middle;
	line-height: 1em;
}

.table>tbody>tr>td {
	line-height: 1em;
	padding: .3em;
}

.cclabel:after {
	content: '';
	position: absolute;
	top: 0px;
	left: 2px;
	width: 20px;
	height: 20px;
	background: #fff;
	border-radius: 20px;
	transition: 0.3s;
}

.table>thead>tr>th {
	padding: .5em;
	font-weight: bold;
	line-height: 1.5em;
}

.cclabel:active:after {
	width: 20px;
}
.btn-link{
     background:none!important;
     color:inherit;
     border:none; 
     padding:0!important;
     font: inherit;
     /*border is optional*/
     cursor: pointer;
}
</style>
</head>

<body>



<c:if test="${adminVO!=null}">
	<div class="wrapper">
		
		<!-- Sidebar -->
		<c:import url = "/back-end/backEndSidebar.jsp">
		</c:import>
		
		<div class="main-panel">
				<!-- Navbar -->
				<c:import url = "/back-end/backEndNavbar.jsp">
				</c:import>
			<!--/////////////CONTENT///////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<div class="container">
				
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="admin.jsp">管理員資料</a>
					</li>
					<li><a data-toggle="tab" href="admPrilType.jsp">管理員權限</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group  is-empty">
						<input type="text" class="form-control" placeholder="搜索">
						<span class="material-input"></span>
					</div>
					<button type="submit" class="btn btn-white btn-round btn-just-icon">
						<i class="material-icons">search</i>
						<div class="ripple-container"></div>
					</button>
				</form>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="actMain">

						<table class="table table-hover">
							<thead>
								<tr class="bg-danger">

									<th class="col-md-1">編號</th>
									<th class="col-md-1">姓名</th>
									<th class="col-md-1">帳號</th>
									<th class="col-md-1">信箱</th>
									<th class="col-md-1">時間</th>
									<th class="col-md-1">修改</th>
									<th class="col-md-1">暫停</th>
								</tr>
							</thead>
							<tbody>



								<c:forEach var="adminVO" items="${list}">
									<tr>

										<td>${adminVO.adminID}</td>
										<td>${adminVO.adminName}</td>
										<td>${adminVO.adminMail}</td>
										<td>${adminVO.adminEmail}</td>
										<td>${adminVO.adminDate}</td>


										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/admin.do">
												<input type="submit" value="修改"
													class="btn btn-sm btn-warning"> <input
													type="hidden" name="adminID" value="${adminVO.adminID}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/admin.do">
												<input type="submit" value="刪除"
													class="btn btn-danger btn-sm statbtn"> <input
													type="hidden" name="action" value="ststus1"> <input
													type="hidden" name="adminID" value="${adminVO.adminID}">
											</FORM>
										</td>
									</tr>

								</c:forEach>

							</tbody>
						</table>
						<ul>
							<a href='admPril.jsp' class="btn btn-success">新增</a>
						</ul>



					</div>
					<!--====================2nd-->
					<div class="tab-pane fade" id="admPrilType.jsp">
						<div class="tt">
							<div class="tab-content">
								<div class="tab-pane fade in active">
									<h2>管理員權限管理</h2>
									<table class="table table-hover">
										<thead>
											<tr class="bg-danger">
												<th class="col-md-1">編號</th>
												<th class="col-md-1">網頁管理</th>
												<th class="col-md-1">姓名</th>
												<th class="col-md-1">帳號</th>
												<th class="col-md-1">權限</th>
											</tr>
										</thead>
										<tbody>



											<tr>
												<td>NO.1</td>
												<td>管理員管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox" align="center">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.2</td>
												<td>網站管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.3</td>
												<td>App推播管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.4</td>
												<td>會員管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.5</td>
												<td>成就與獎賞管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.6</td>
												<td>檢舉管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.7</td>
												<td>活動社團分類管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.8</td>
												<td>活動管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
											<tr>
												<td>NO.9</td>
												<td>社團管理</td>
												<td></td>
												<td></td>
												<td>
													<div class="checkbox">
														<label> <input type="checkbox"
															name="optionsCheckboxes">
														</label>
													</div>
												</td>
											</tr>
										</tbody>

									</table>
									<button class="btn btn-success">新增</button>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
		</div>
	</div>
	</c:if>
</body>
<!--   Core JS Files   -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js"></script>
<!-- Material Dashboard javascript methods -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material-dashboard.js"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/demo.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		// Javascript method's body can be found in assets/js/demos.js
		demo.initDashboardPageCharts();

	});
</script>

</html>