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

		<!--********************會員獎賞********************-->
		<h1 class="text-center">揪點兌換</h1>
		<hr class="colorgraph">
		<div class="row">
			<div class=" col-xs-offset-1 col-sm-10">
				<form action="#" class="content-search-view2 achForm">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="尋找獎賞">
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
						<div class="container-fluid bg-3 text-center reward">
							<div class="row">
								<div class="col-sm-4">
									<img src="../src/index/avatar/1.jpg" alt="Image"
										class="img-responsive thumbnail">
									<p>Lorem ipsum..</p>
								</div>
								<div class="col-sm-4">
									<img src="../src/index/avatar/1.jpg" alt="Image"
										class="img-responsive thumbnail">
									<p>Lorem ipsum..</p>
								</div>
								<div class="col-sm-4">
									<img src="../src/index/avatar/1.jpg" alt="Image"
										class="img-responsive thumbnail">
									<p>Lorem ipsum..</p>
								</div>
							</div>
						</div>
					</div>
					<div id="menu1" class="tab-pane fade"></div>
					<div id="menu2" class="tab-pane fade"></div>
				</div>
			</div>
		</div>
		<!--********************會員獎賞********************-->
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