<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="java.util.*"%>

<%
	// ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
	ClubMemService clubMemSvc = new ClubMemService();
	// List<ClubMemVO> clubMemlist = (List<ClubMemVO>) request.getAttribute("clubMemlist");
	// pageContext.setAttribute("clubMemlist",clubMemlist);
	// ClubService clubSvc = new ClubService();

	// //找尋單一社團成員的資料
	// ClubMemVO clubMemVO = (ClubMemVO) request.getAttribute("clubMemVO");
	//單一會員參加的社團
	List<ClubMemVO> memAllJoinClublist = (List<ClubMemVO>) request.getAttribute("memAllJoinClublist");
%>



<head>
<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
<!-- 共用Header -->
<!--  my styles  -->
<!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
<!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
<!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
<link
	href="<%=request.getContextPath()%>/HTML/assets/pages/css/slider.css"
	rel="stylesheet">

<link href="../src/club/css/MemAllClub.css" rel="stylesheet">
</head>
<body>
	<c:import url="/front-end/userHeader.jsp">
	</c:import>

	<!-- Header Start -->
	<c:import url="/front-end/header.jsp">
	</c:import>
	<div class="content">
		<div class="container">

			<div class="row col-md-7 custyle">
				<c:if test="${memVO==null}">
					<h1>請先登入會員！</h1>
				</c:if>
				<c:if test="${memVO!=null}">
					<table class="table table-striped custab">
					          <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
            <li class="active">我的社團</li>
          	</ul>
						<thead>

							<tr>
								<!--             <th>ID</th> -->
								<th>社團名稱</th>
								<th>成員等級</th>
								<th class="text-center">動作</th>
								<th>加入日期</th>
							</tr>
						</thead>

						<jsp:useBean id="clubSvc" scope="page"
							class="com.club.model.ClubService" />
						<c:forEach var="clubMemVO" items="${memAllJoinClublist}">
							<tr>
								<td>${clubSvc.findByPrimaryKey(clubMemVO.getClubID()).clubName}</td>
								<c:if test="${clubMemVO.clubMemType==1}">
									<td>一般社團成員</td>
								</c:if>
								<c:if test="${clubMemVO.clubMemType==2}">
									<td>社團幹部</td>
								</c:if>
								<c:if test="${clubMemVO.clubMemType==3}">
									<td>社長</td>
								</c:if>
								<td class="text-center"><a class='btn btn-info btn-xs'
									href="<%=request.getContextPath()%>/front-end/club/clubAll.do?clubID=${clubMemVO.clubID}&memID=${memVO.memID}&action=toClubOne"><span
										class="glyphicon glyphicon-edit"></span> 前往</a> <%--                 <c:if test="${clubMemVO.clubMemStatus==1 }"> --%>
								</td>

								<td>${clubMemVO.clubMemJoinDate}</td>
								<%--                 </c:if> --%>
								<%--                 		<%if(clubMemVO.getClubMemStatus()==1){%> --%>
								<!-- 						<button  class="btn btn-block btn-success" data-toggle="modal" data-target="#exampleModal"> -->
								<!-- 						退出社團 -->
								<!-- 						</button>		 -->
								<%--  						 <%}%> --%>



							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="row col-md-4 col-md-offset-1">
				<h3>多多參與其他的社團吧！</h3>
				<img class="thumbnail img-responsive"
					src="<%=request.getContextPath()%>/HTML/src/club/img/memAllClubImg.jpg" alt="">
			</div>
		</div>
	</div>


	<!-- BEGIN FOOTER -->
	<c:import url="/front-end/footer.jsp">
	</c:import>
	<!-- END FOOTER -->

	<!-- 共用Js -->
	<c:import url="/front-end/publicJS.jsp">
	</c:import>
	<!-- 共用Js -->
	<!-- <script src="../src/club/js/MemAllClub.js" type="text/javascript"></script>       -->
</body>
</html>