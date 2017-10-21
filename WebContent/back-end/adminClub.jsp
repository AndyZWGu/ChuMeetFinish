<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>
<%
session.setAttribute("page", "club");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ClubService clubSvc = new ClubService();
	List<ClubVO> list = clubSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta charset="utf-8" />
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

.table>thead>tr>th {
	padding: .5em;
	font-weight: bold;
	line-height: 1.5em;
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
					<li class="active"><a data-toggle="tab" href="#actMain">活動管理</a>
					</li>
					
				</ul>
			
				<div class="tab-content">
					<div class="tab-pane fade in active">
						
						<table class="table  table-hover">
							<thead>
								<tr class="bg-danger">
									<th class="col-md-1">編號</th>
									<th class="col-md-1">社團名稱</th>
									<th class="col-md-6">介紹內容</th>
									<th class="col-md-2">建立日期</th>
									<th class="col-md-1">社團狀態</th>
									<th class="col-md-1">變更狀態</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="clubVO" items="${list}">
								<c:if test="${clubVO.clubID!=0}">
									<tr>
										<td>${clubVO.clubID}</td>
										<td><H6>${clubVO.clubName}</H6></td>
										<td><H6>${clubVO.clubContent}</H6></td>
										<td>${clubVO.clubStartDate}</td>
										
										<c:if test="${clubVO.clubStatus==0}">
										<td>停權</td>
										</c:if>
										<c:if test="${clubVO.clubStatus==1}">
										<td>正常</td>
										</c:if>
										
										
										
									<td>
										
<%-- 										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/club/club.do"> --%>
<!-- 											<input type="submit" value="修改" class="btn btn-sm btn btn-info"> -->
<!-- 											<input type="hidden"name="action" value="toUpdateClub">  -->
<%-- 											<input type="hidden"name="clubID" value="${clubVO.clubID}"> 						 --%>
<!-- 										</FORM> -->
										
										<c:if test="${clubVO.clubStatus==1}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/club/club.do">
											<input type="submit" value="停權"class="btn btn-sm btn-warning">
											<input type="hidden"name="action" value="pause"> 
											<input type="hidden"name="clubID" value="${clubVO.clubID}"> 
										</FORM>
										</c:if>
										<c:if test="${clubVO.clubStatus==0}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/club/club.do">
											<input type="submit" value="恢復"class="btn btn-sm btn-success">
											<input type="hidden"name="action" value="ok"> 
											<input type="hidden"name="clubID" value="${clubVO.clubID}"> 
										</FORM>
										</c:if>
									</td>
										
									</tr>
									</c:if>
								</c:forEach>
							</tbody>

						</table>
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
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/jquery-3.1.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material.min.js"
	type="text/javascript"></script>
<!--  Charts Plugin -->
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js"></script>
<!-- Material Dashboard javascript methods -->
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material-dashboard.js"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script
	src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/demo.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		// Javascript method's body can be found in assets/js/demos.js
		demo.initDashboardPageCharts();

	});
</script>

</html>