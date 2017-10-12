<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemMailVO memMailVO = (MemMailVO) request.getAttribute("memMailVO");
	String mailName = (String)request.getAttribute("mailName");
%>

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">

	<div class="row profile-content blog-item">
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>/front-end/member/memberHome.do">首頁</a></li>
			<li><a href="<%=request.getContextPath()%>/front-end/member/memberMail.do">收件夾列表</a></li>
			<li class="active">信件訊息</li>
		</ul>
		<!--********************會員收件夾********************-->
		<hr class="colorgraph">
		<div class="row">
		<div class="col-md-12 col-sm-12">
			<h1>${memMailVO.mailTitle}</h1>
			<h3>${memMailVO.mailContent}</h3>
			<h4 class="text-right">寄件人:${mailName}</h4>
			<h4 class="text-right"><fmt:formatDate value="${memMailVO.mailDate}" pattern="yyyy/MM/dd hh:mm" /></h4>
		</div>
		</div>
		<!--********************會員收件夾********************-->
		<hr class="colorgraph">
		<!--**************************留言功能**************************-->
		<div class="row">
			<div class="post-comment padding-top-40">
				<h3>留言</h3>
				<form role="form" method="post" action="<%=request.getContextPath()%>/front-end/member/memberMail.do?memMailID=${memMailVO.memMailID}">
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