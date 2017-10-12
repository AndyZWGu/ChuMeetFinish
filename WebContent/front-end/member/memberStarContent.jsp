<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	
%>

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">

	<div class="row profile-content blog-item">

		<!--********************會員評價********************-->
		<h1 class="text-center">會員評價</h1>
		<hr class="colorgraph">
		<div class="row">
			<div class="col-sm-12 col-md-12 text-center">
				<div class="row">
					<div id="stars" class="starrr"></div>
					平均 <span id="count">3顆</span> <i class="fa fa-star"
						aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i>
					<i class="fa fa-star" aria-hidden="true"></i> <i
						class="fa fa-star-o" aria-hidden="true"></i> <i
						class="fa fa-star-o" aria-hidden="true"></i>
				</div>
				<div class="col-sm-4 col-md-4">
					<h2>活動名稱</h2>
					<small>完成於2017.07.07</small>
					<div class="row lead">
						<a href="#"><span>您獲得15筆評價</span></a>
					</div>
				</div>
				<div class="col-sm-4 col-md-4">
					<h2>活動名稱</h2>
					<small>完成於2017.07.07</small>
					<div class="row lead">
						<a href="#"><span>您獲得15筆評價</span></a>
					</div>
				</div>
				<div class="col-sm-4 col-md-4">
					<h2>活動名稱</h2>
					<small>完成於2017.07.07</small>
					<div class="row lead">
						<a href="#"><span>您獲得15筆評價</span></a>
					</div>
				</div>
				<div class="col-sm-4 col-md-4">
					<h2>活動名稱</h2>
					<small>完成於2017.07.07</small>
					<div class="row lead">
						<a href="#"><span>您獲得15筆評價</span></a>
					</div>
				</div>
				<div class="col-sm-4 col-md-4">
					<h2>活動名稱</h2>
					<small>完成於2017.07.07</small>
					<div class="row lead">
						<a href="#"><span>您獲得15筆評價</span></a>
					</div>
				</div>
				<div class="col-sm-4 col-md-4">
					<h2>活動名稱</h2>
					<small>完成於2017.07.07</small>
					<div class="row lead">
						<a href="#"><span>您獲得15筆評價</span></a>
					</div>
				</div>
			</div>
		</div>
		<!--********************會員評價********************-->
		<!--分頁-->
		<div class="row">
			<div class=" col-xs-offset-4 col-md-6 col-sm-6">
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
		</div>
		<!--分頁-->
		<hr class="colorgraph">
	</div>

</div>