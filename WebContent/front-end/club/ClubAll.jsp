<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>


<%
	ClubService clubSvc = new ClubService();
	List<ClubVO> list = clubSvc.getAll();
	request.setAttribute("list", list);
%>

<!-- Head BEGIN -->

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

</head>
<!-- Head END -->

<!-- Body BEGIN -->

<body class="chumeet">
	<c:import url="/front-end/userHeader.jsp">
	</c:import>

	<!-- Header Start -->
	<c:import url="/front-end/header.jsp">
	</c:import>

	<!-- Header END -->


	<!--主頁面要修改的都在這下面-->
	<c:if test="${memVO!=null}">
		<div class="main">
		<div class="content">
			<div class="container">
				<ul class="breadcrumb">
					<li><a
						href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
					<li class="active">社團推薦</li>
				</ul>




				<%@ include file="page1.file"%>
				<div class="row clearfix">
					<div class="col-sm-8 col-md-8 column">
						<div class="row clearfix">



							<c:forEach var="clubVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">
								<c:if test="${status.index!=0&&clubVO.clubStatus!=0}">
									<div class="col-sm-6 col-md-4 column">
										<div class="mix-inner">

											<!-- 						 			<div style="width:250px;height:100px;"> -->
											<img
												style="width:200px;height:120px;_height:expression(this.height < 120 ?
										120px"
												:
												this.width);" src="<%=request.getContextPath()%>/front-end/club/Photo.do?clubID=${clubVO.clubID}"
												class="img-responsive thumbnail">
											<!--                                     </div>  -->
											<div class="mix-details">

												<div class="col-sm-6">
													<p>${clubVO.clubName}</p>
												</div>
												<div class="col-sm-6">
													<a class="btn btn-sm btn-primary"
														href="<%=request.getContextPath()%>/front-end/club/clubAll.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubOne">
														詳情</a>
												</div>


												<%--                                            <p><c:out value="${clubVO.clubContent}" default=""/></p> --%>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>




						</div>
						<!--行div結束-->


						<!--畫面是小的時候有問題 -->
						<ul class="pagination col-md-8 column">
							<%@ include file="page2.file"%>
						</ul>


					</div>




					<div class="col-md-4 column">
						<div class="sidebar">


							<input type="button"
								onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/ClubAdd.jsp?action=addClub'"
								value="建立社團" class="btn btn-block btn-primary"></input> <br>
							<br>
							<h3>找尋您感興趣的社團?</h3>
							<img class="thumbnail img-responsive"
								src="<%=request.getContextPath()%>/HTML/src/club/img/addClub.jpg"
								alt="">
							<h3>或者試試建立社團來玩吧！</h3>
							<img class="thumbnail img-responsive"
								src="<%=request.getContextPath()%>/HTML/src/club/img/addClub2.jpg"
								alt="">
							<!-- 						<ul class="nav sidebar-categories margin-bottom-40"> -->
							<!-- 							<li class="active"><a href="index.html">綜合推薦</a></li> -->
							<!-- 							<li><a href="index.html">依分類</a></li> -->
							<!-- 							<li><a href="index.html">依興趣</a></li> -->
							<!-- 							<li><a href="index.html">依所在地</a></li> -->
							<!-- 						</ul> -->
						</div>
					</div>


				</div>
			</div>
			</div>
		</div>
	</c:if>
	<c:if test="${memVO==null}">
		<div class="main">
			<div class="container">
				<ul class="breadcrumb">
					<li><a href="index.html">首頁</a></li>
					<li class="active">社團推薦</li>
				</ul>




				<%@ include file="page1.file"%>
				<div class="row clearfix">
					<div class="col-sm-12 col-md-12 column">
						<div class="row clearfix">



							<c:forEach var="clubVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">
								<c:if test="${status.index!=0}">
									<div class="col-sm-6 col-md-4 column">
										<div class="mix-inner">

											<!-- 						 			<div style="width:250px;height:100px;"> -->
											<img
												style="width:200px;height:120px;_height:expression(this.height < 120 ?
										120px"
												:
												this.width);" src="<%=request.getContextPath()%>/front-end/club/Photo.do?clubID=${clubVO.clubID}"
												class="img-responsive thumbnail">
											<!--                                     </div>  -->
											<div class="mix-details">

												<h4>${clubVO.clubName}(登入享有更多功能)</h4>

												<%--                                            <p><c:out value="${clubVO.clubContent}" default=""/></p> --%>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>




						</div>
						<!--行div結束-->


						<!--畫面是小的時候有問題 -->
						<ul class="pagination col-md-12 column">
							<%@ include file="page2.file"%>
						</ul>


					</div>

				</div>
			</div>
		</div>
	</c:if>
	<!--主頁面要修改的都在這上面-->


	<!-- BEGIN FOOTER -->
	<c:import url="/front-end/footer.jsp">
	</c:import>
	<!-- END FOOTER -->

	<!-- 共用Js -->
	<c:import url="/front-end/publicJS.jsp">
	</c:import>
	<!-- 共用Js -->

	<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>


