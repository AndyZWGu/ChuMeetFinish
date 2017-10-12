<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	
%>
<link
	href="<%=request.getContextPath()%>/HTML/src/member/css/memAch.css"
	rel="stylesheet">

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">

	<div class="row profile-content blog-item ach">

		<!--********************會員成就********************-->
		<h1 class="text-center">會員成就</h1>
		<hr class="colorgraph">
		<div class="row">
			<div class=" col-xs-offset-1 col-sm-10">
				<form action="#" class="content-search-view2 achForm">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="尋找成就">
						<span class="input-group-btn">
							<button type="submit" class="btn btn-primary">搜尋</button>
						</span>
					</div>
				</form>
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#home">全部</a></li>
					<li><a data-toggle="tab" href="#menu1">已達成</a></li>
					<li><a data-toggle="tab" href="#menu2">未達成</a></li>
				</ul>

				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<div class="row">
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-primary">
									<div class="tile-header">
										<h3>歡迎光臨</h3>
										<span>歡迎第一次加入揪咪大家庭所獲的之成就</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="65.5%" style="width: 65.5%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">65.5</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-red">
									<div class="tile-header">
										<h3>我是新朋友</h3>
										<span>歡迎您第十次參加活動之成就！</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="23.2%" style="width: 23.2%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">23.2</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-blue">
									<div class="tile-header">
										<h3>Visitors</h3>
										<span>so far in our blog, and our website.</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="78%" style="width: 78%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">78</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-aqua">
									<div class="tile-header">
										<h3>Visitors</h3>
										<span>so far in our blog, and our website.</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="22%" style="width: 22%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">22</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-green">
									<div class="tile-header">
										<h3>Visitors</h3>
										<span>so far in our blog, and our website.</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="94%" style="width: 94%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">94</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-cyan">
									<div class="tile-header">
										<h3>Visitors</h3>
										<span>so far in our blog, and our website.</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="45.9%" style="width: 45.9%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">45.9</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-purple">
									<div class="tile-header">
										<h3>Visitors</h3>
										<span>so far in our blog, and our website.</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="27%" style="width: 27%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">27</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="tile-progress tile-pink">
									<div class="tile-header">
										<h3>Visitors</h3>
										<span>so far in our blog, and our website.</span>
									</div>
									<div class="tile-progressbar">
										<span data-fill="3" style="width: 3%;"></span>
									</div>
									<div class="tile-footer">
										<h4>
											<span class="pct-counter">3</span>% 達成
										</h4>
										<a href="#"><span>獎賞:50Pt</span></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="menu1" class="tab-pane fade">
						<h3>以達成</h3>
					</div>
					<div id="menu2" class="tab-pane fade">
						<h3>未達成</h3>
					</div>
				</div>
			</div>
		</div>
		<!--********************會員成就********************-->
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