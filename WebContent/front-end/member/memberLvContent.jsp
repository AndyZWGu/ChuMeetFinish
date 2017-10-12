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

		<!--********************會員等級********************-->
		<h1 class="text-center">會員等級</h1>
		<hr class="colorgraph">
		<div class="row bs-wizard" style="border-bottom: 0;">
			<div class="col-xs-3 bs-wizard-step complete">
				<div class="text-center bs-wizard-stepnum">銅牌初心者</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">隨處可見的稱號</div>
				<div class="bs-wizard-info text-center">剛加入揪咪網站獲得</div>
				<div class="bs-wizard-info text-center">享有優惠：無</div>
			</div>

			<div class="col-xs-3 bs-wizard-step complete">
				<!-- complete -->
				<div class="text-center bs-wizard-stepnum">銀牌</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">普通的稱號</div>
				<div class="bs-wizard-info text-center">郵箱認證&目標達成時獲得</div>
				<div class="bs-wizard-info text-center">目標：追蹤數達50</div>
				<div class="bs-wizard-info text-center">目標：好友數達25</div>
				<div class="bs-wizard-info text-center">目標：評價達50</div>
				<div class="bs-wizard-info text-center">
					享有優惠：揪點商品<i>9折</i>
				</div>
			</div>

			<div class="col-xs-3 bs-wizard-step active">
				<!-- complete -->
				<div class="text-center bs-wizard-stepnum">金牌</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">厲害點的稱號</div>
				<div class="bs-wizard-info text-center">郵箱認證&目標達成時獲得</div>
				<div class="bs-wizard-info text-center">目標：追蹤數達100</div>
				<div class="bs-wizard-info text-center">目標：好友數達50</div>
				<div class="bs-wizard-info text-center">目標：評價達100</div>
				<div class="bs-wizard-info text-center">目標：創立過活動</div>
				<div class="bs-wizard-info text-center">
					享有優惠：揪點商品<i>8折</i>
				</div>
			</div>

			<div class="col-xs-3 bs-wizard-step disabled">
				<!-- active -->
				<div class="text-center bs-wizard-stepnum">白金</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">熟練運用的稱號</div>
				<div class="bs-wizard-info text-center">目標達成時獲得</div>
				<div class="bs-wizard-info text-center">目標：追蹤數達250</div>
				<div class="bs-wizard-info text-center">目標：好友數達150</div>
				<div class="bs-wizard-info text-center">目標：評價達350</div>
				<div class="bs-wizard-info text-center">目標：參加過社團</div>
				<div class="bs-wizard-info text-center">
					享有優惠：揪點商品<i>7折</i>
				</div>
			</div>
			<div class="col-xs-3 bs-wizard-step disabled">
				<div class="text-center bs-wizard-stepnum">鑽石</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">高手等級的稱號</div>
				<div class="bs-wizard-info text-center">郵箱&手機認證&目標達成時獲得</div>
				<div class="bs-wizard-info text-center">目標：追蹤數達500</div>
				<div class="bs-wizard-info text-center">目標：好友數達250</div>
				<div class="bs-wizard-info text-center">目標：評價達750</div>
				<div class="bs-wizard-info text-center">目標：創立過社團</div>
				<div class="bs-wizard-info text-center">
					享有優惠：揪點商品<i>6折</i>
				</div>
			</div>

			<div class="col-xs-3 bs-wizard-step disabled">
				<!-- complete -->
				<div class="text-center bs-wizard-stepnum">大師</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">趨近於頂端的稱號</div>
				<div class="bs-wizard-info text-center">目標達成時獲得</div>
				<div class="bs-wizard-info text-center">目標：追蹤數達1000</div>
				<div class="bs-wizard-info text-center">目標：好友數達500</div>
				<div class="bs-wizard-info text-center">目標：評價達1500</div>
				<div class="bs-wizard-info text-center">
					享有優惠：揪點商品<i>5折</i>
				</div>
			</div>

			<div class="col-xs-3 bs-wizard-step disabled">
				<!-- complete -->
				<div class="text-center bs-wizard-stepnum">菁英</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center">所有人夢寐以求的終極稱號</div>
				<div class="bs-wizard-info text-center">目標達成時獲得</div>
				<div class="bs-wizard-info text-center">目標：追蹤數達2000</div>
				<div class="bs-wizard-info text-center">目標：好友數達1000</div>
				<div class="bs-wizard-info text-center">目標：評價達3000</div>
				<div class="bs-wizard-info text-center">
					享有優惠：揪點商品<i>3折</i>
				</div>
			</div>
		</div>
		<!--********************會員等級********************-->
		<hr class="colorgraph">
	</div>

</div>