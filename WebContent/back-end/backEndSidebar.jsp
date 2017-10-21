<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*"%>
<div class="sidebar" data-color="purple" style="overflow:hidden;"
	data-image="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/sidebar-1.jpg">
	<!--
            Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

            Tip 2: you can also add an image using data-image tag
        -->
	<div class="logo text-center">
		<img
			src="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/ChuMeet_logo_2.png">
		<strong>管理平台</strong>
	</div>
	<div class="sidebar-wrapper">
		<ul class="nav">

			<c:choose>
				<c:when test="${page=='index'}">
					<li class="active">
				</c:when>
				<c:otherwise>
					<li>
				</c:otherwise>
			</c:choose>

			<a href="<%=request.getContextPath()%>/back-end/index.jsp"><i class="material-icons">home</i>
				<p>
					<strong>首頁</strong>
				</p></a>
			</li>



			<c:forEach var="admPrilVO" items="${admPrilList}">
				<c:if test="${admPrilVO.admPrilID==1}">
					<c:choose>
						<c:when test="${page=='admin'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>

					<a href="<%=request.getContextPath()%>/back-end/admin.jsp"><i
						class="material-icons">person</i>
						<p>
							<strong>管理員管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==2}">
					<c:choose>
						<c:when test="${page=='info'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminAnn.jsp"><i
						class="material-icons">person</i>
						<p>
							<strong>網站管理</strong>
						</p></a>
					</li>
				</c:if>

				<c:if test="${admPrilVO.admPrilID==3}">
					<c:choose>
						<c:when test="${page=='app'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminappAnn.jsp"><i
						class="material-icons">phonelink_ring</i>
						<p>
							<strong>APP推播管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==4}">
					<c:choose>
						<c:when test="${page=='member'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminMember.jsp"><i
						class="material-icons text-gray">face</i>
						<p>
							<strong>會員管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==5}">
					<c:choose>
						<c:when test="${page=='achReward'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminReward.jsp"><i
						class="material-icons text-gray">face</i>
						<p>
							<strong>獎賞與成就管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==6}">
					<c:choose>
						<c:when test="${page=='ticket'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/actClubTicket.jsp"><i
						class="material-icons">library_books</i>
						<p>
							<strong>檢舉管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==7}">
					<c:choose>
						<c:when test="${page=='poi'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminPoi.jsp"><i
						class="material-icons">location_on</i>
						<p>
							<strong>活動社團分類管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==8}">
					<c:choose>
						<c:when test="${page=='act'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminAct.jsp"><i
						class="material-icons text-gray">directions_bike</i>
						<p>
							<strong>活動管理</strong>
						</p></a>
					</li>
				</c:if>
				<c:if test="${admPrilVO.admPrilID==9}">
					<c:choose>
						<c:when test="${page=='club'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="<%=request.getContextPath()%>/back-end/adminClub.jsp"><i
						class="material-icons text-gray">wc</i>
						<p>
							<strong>社團資料管理</strong>
						</p></a>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>