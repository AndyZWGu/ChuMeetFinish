<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemNFVO memNFVO = (MemNFVO) request.getAttribute("memNFVO");
	List<MemMBVO> memNFMBList = (List) request.getAttribute("memNFMBList");
	List<MemberVO> mbMemNameList = (List) request.getAttribute("mbMemNameList");
%>

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">

	<div class="row profile-content blog-item">
		<ul class="breadcrumb">
			<li><a
				href="<%=request.getContextPath()%>/front-end/member/memberHome.do">首頁</a></li>
			<li><a
				href="<%=request.getContextPath()%>/front-end/member/memberNF.do">動態列表</a></li>
			<li class="active">動態內容</li>
		</ul>
		<!--********************會員動態********************-->
		<hr class="colorgraph">
		<div class="row">
		<div class="col-md-12 col-sm-12 memNFDetail">
			<h1>${memNFVO.nfTitle}</h1>
			<img src="<%=request.getContextPath()%>/front-end/member/memberNFSearch/avatar.do?memID=${memVO.memID}&memNFID=${memNFVO.memNFID}" alt="" class="thumbnail">
			<p>${memNFVO.nfContent}</p>
			<h4 class="text-right"><fmt:formatDate value="${memNFVO.nfDate}" pattern="yyyy/MM/dd hh:mm" /></h4>
		</div>
		</div>
		<!--**************************留言板**************************-->
		<hr class="colorgraph">
		<div class="row">
			<c:forEach items="${memNFMBList}" var="memNFMBList"  varStatus="status">
				<div class="media">
					<c:if test="${mbMemNameList[status.index].memID != memVO.memID}">
						<a
							href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${memNFMBList.memID}"
							class="pull-left"> <img
							src="<%=request.getContextPath()%>/front-end/member/guestHome/avatar.do?memID=${memNFMBList.memID}"
							alt="" class="media-object">
							<p>${mbMemNameList[status.index].memName}</p>
						</a>
					</c:if>
					<c:if test="${mbMemNameList[status.index].memID == memVO.memID}">
						<a
							href="<%=request.getContextPath()%>/front-end/member/memberHome.do?memID=${memNFMBList.memID}"
							class="pull-left"> <img
							src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memNFMBList.memID}"
							alt="" class="media-object">
							<p>${mbMemNameList[status.index].memName}</p>
						</a>
					</c:if>
					<div class="media-body">
						<p>${memNFMBList.mbContent}</p>
						<span><fmt:formatDate value="${memNFMBList.mbDate}" pattern="yyyy/MM/dd hh:mm" /></span>
						<div class="col-sm-12 text-right">
							<c:if test="${mbMemNameList[status.index].memID != memVO.memID}">
								<input type="button" value="檢舉" class="btn btn-danger btn-xs">
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
		<!--**************************留言功能**************************-->
		<div class="post-comment padding-top-40">
			<h3>留言</h3>
			<form role="form" method="post"
				action="<%=request.getContextPath()%>/front-end/member/memberNF.do?memNFID=${memNFVO.memNFID}">
				<div class="form-group">
					<h4>內容</h4>
					<textarea class="form-control" rows="10" name="comment"></textarea>
				</div>
				<p>
					<button class="btn btn-primary" type="submit">回覆</button>
					<input type="hidden" name="action" value="comment">
				</p>
			</form>
		</div>
		<!--**************************分頁**************************-->
		<!-- 		<div class="row">

			<div class=" col-xs-offset-5 col-md-6 col-sm-6">
				<ul class="pagination pull-center">
					<li><a href="javascript:;">«</a></li>
					<li><a href="javascript:;">1</a></li>
					<li><span>2</span></li>
					<li><a href="javascript:;">3</a></li>
					<li><a href="javascript:;">4</a></li>
					<li><a href="javascript:;">5</a></li>
					<li><a href="javascript:;">»</a></li>
				</ul>
			</div>
		</div> -->
		<!--**************************分頁**************************-->
	</div>

</div>