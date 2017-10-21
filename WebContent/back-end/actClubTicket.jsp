<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.clubTicket.model.*"%>
<%session.setAttribute("page", "ticket"); %>

<%

ClubTicketService clubTicketSvc = new ClubTicketService();
List<ClubTicketVO> clubTKlist = clubTicketSvc.getAll();
pageContext.setAttribute("clubTKlist", clubTKlist);

MemberService memSvc=new MemberService();
List<MemberVO> clubMemNameList=new ArrayList<MemberVO>();
for(ClubTicketVO list:clubTKlist){
	clubMemNameList.add(memSvc.getOneMember(list.getReporter()));
}
	request.setAttribute("clubMemNameList",clubMemNameList);
	
	//
	ClubService clubSvc=new ClubService();
	List<ClubVO> clubNameList=new ArrayList<ClubVO>();
	for(ClubTicketVO list:clubTKlist){
		clubNameList.add(clubSvc.findByPrimaryKey((list.getClubID())));
	}
		request.setAttribute("clubMemNameList",clubMemNameList);
		request.setAttribute("clubNameList",clubNameList);
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
			<c:import url="/back-end/backEndSidebar.jsp">
			</c:import>

			<div class="main-panel">
				<!-- Navbar -->
				<c:import url="/back-end/backEndNavbar.jsp">
				</c:import>
				<!--/////////////CONTENT///////////////////-->
				<!--/////////////////////////////////////////////////////////////////////////////-->
				<!--/////////////////////////////////////////////////////////////////////////////-->
				<!--/////////////////////////////////////////////////////////////////////////////-->
				<!--/////////////////////////////////////////////////////////////////////////////-->
				<div class="container">


					<ul class="nav nav-tabs">
					<li class="active"><a href="actClubTicket.jsp">活動/社團檢舉管理</a></li>						
						<li><a href="adminmbImgTicket.jsp">留言/相片檢舉管理</a></li>
						<li><a href="adminAlbumTicket.jsp">相簿檢舉管理</a></li>
						<li><a href="adminMemTicket.jsp">會員檢舉管理</a></li>
					</ul>


					<form class="navbar-form navbar-right" role="search">
						<div class="form-group  is-empty">
							<input type="text" class="form-control" placeholder="搜索">
							<span class="material-input"></span>
						</div>
						<button type="submit"
							class="btn btn-white btn-round btn-just-icon">
							<i class="material-icons">search</i>
							<div class="ripple-container"></div>
						</button>
					</form>
					<div class="tab-content">
						<div class="tab-pane fade in active">

							<table class="table  table-hover">
								<thead>
									<tr class="bg-danger">
										<th class="col-md-1">流水號</th>
										<th class="col-md-1">檢舉人</th>
										<th class="col-md-1">被檢舉社團</th>
										<th class="col-md-1">檢舉內容</th>
										<th class="col-md-1">檢舉狀態</th>
										<th class="col-md-1">檢舉日期</th>
										<th class="col-md-1">更改狀態</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="clubTKVO" items="${clubTKlist}" varStatus="status">
										<c:if test="${clubTKVO.clubTkStatID==1||clubTKVO.clubTkStatID==2}">
										
										
										<tr>
											<td>${clubTKVO.clubTkID }</td>
											<td>${clubMemNameList[status.index].memName}</td>
											<td>${clubNameList[status.index].clubName}</td>
											<td>${clubTKVO.clubTkMsg }</td>
											<c:if test="${clubTKVO.clubTkStatID==1}">
											<h4><td>尚未處理</td></h4>
											</c:if>
											<c:if test="${clubTKVO.clubTkStatID==2}">
											<td><h5>處理中</h5></td>
											</c:if>
											
											<td>${clubTKVO.clubTkDate}</td>
											<%--                                     <td>${clubTKVO.clubTkCat}</td>  --%>
											
										<td>
<!-- 										1.尚未處理2.處理中0.刪除 -->
										<c:if test="${clubTKVO.clubTkStatID==2}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/club/clubTK.do">
											<input type="submit" value="未處理"class="btn btn-sm btn btn-info">
											<input type="hidden"name="action" value="not"> 
											<input type="hidden"name="clubTkID" value="${clubTKVO.clubTkID}"> 
										</FORM>
										</c:if>
										
										<c:if test="${clubTKVO.clubTkStatID==1}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/club/clubTK.do">
											<input type="submit" value="處理中"class="btn btn-sm btn-success">
											<input type="hidden"name="action" value="on"> 
											<input type="hidden"name="clubTkID" value="${clubTKVO.clubTkID}"> 
										</FORM>
										</c:if>
										
										<c:if test="${clubTKVO.clubTkStatID==1||clubTKVO.clubTkStatID==2}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/club/clubTK.do">
											<input type="submit" value="刪除"class="btn btn-sm btn-warning">
											<input type="hidden"name="action" value="deleteOneClubTk"> 
											<input type="hidden"name="clubTkID" value="${clubTKVO.clubTkID}"> 
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