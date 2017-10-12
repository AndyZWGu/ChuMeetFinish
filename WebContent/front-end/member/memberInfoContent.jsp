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
		<!--********************會員資訊********************-->
		<h1 class="text-center">會員基本資訊</h1>
		<hr class="colorgraph">
		<div class="row">
			<form onsubmit="return check()" class="form-horizontal" method="post"
				id="memInfoForm"
				action="<%=request.getContextPath()%>/front-end/member/memberHome.do"
				data-toggle="validator">
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memEmail">註冊信箱:</label>
					<div class="col-sm-8 col-md-8">
						<input type="email" class="form-control" id="memEmail"
							placeholder="${memVO.memEmail}" disabled>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memPw">當前密瑪:</label>
					<div class="col-sm-8 col-md-8">
						<input type="password" class="form-control" id="memPw"
							placeholder="輸入新密碼" value="${memVO.memPw}" name="memPw" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memPw">確認密瑪:</label>
					<div class="col-sm-8 col-md-8">
						<input type="password" class="form-control" id="memPw"
							placeholder="確認密碼" data-match="#memPw" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memberType">驗證狀態</label>
					<div class="col-sm-4 col-md-4">
						<p>信箱</p>
						<input type="text" class="form-control" id="memberType"
							placeholder="${isMail}" disabled>
						<p>手機</p>
						<input type="text" class="form-control" id="memberType"
							placeholder="${isPhone}" disabled>
					</div>
					<div class="col-sm-4 col-md-4">
					<c:if test="${isMail=='未驗證'}">						
						<p>信箱驗證</p>
						<button class="btn btn-primary btn-sm" onclick="verifyEMail()">驗證信箱</button>
						<p id="verifyMessage"></p>
					</c:if>
					<c:if test="${isPhone=='未驗證'}">
						<p>手機驗證</p>
						<button class="btn btn-primary btn-sm" onclick="ajaxVerifyPhone()">驗證手機</button>
						<p id="verifyMessage"></p>
					</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memName">暱稱</label>
					<div class="col-sm-8 col-md-8">
						<input type="text" class="form-control" id="memName"
							placeholder="輸入新的暱稱" value="${memVO.memName}" name="memName">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memGender">性別</label>
					<div class="col-sm-8 col-md-8">
						<c:choose>
							<c:when test="${memVO.memGender == 1}">
								<label class="radio-inline"><input type="radio"
									name="memGender" checked disabled>男</label>
								<label class="radio-inline"><input type="radio"
									name="memGender" disabled>女</label>
							</c:when>
							<c:otherwise>
								<label class="radio-inline"><input type="radio"
									name="memGender" disabled>男</label>
								<label class="radio-inline"><input type="radio"
									name="memGender" checkde disabled>女</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memPhone">會員手機:</label>
					<div class="col-sm-8 col-md-8">
						<input type="text" class="form-control" id="memPhone"
							placeholder="${memVO.memPhone}" disabled>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memLocBorn">出生地:</label>
					<div class="col-sm-8 col-md-8">
						<select class="form-control" id="memLocBorn" name="memLocBorn" value="${memVO.memLocBorn}">
							<option>
								<c:if test="${memVO.memLocBorn==null}">
											未設定
										</c:if> ${memVO.memLocBorn}
							</option>
							<option>臺北市</option>
							<option>基隆市</option>
							<option>新北市</option>
							<option>宜蘭縣</option>
							<option>桃園市</option>
							<option>新竹市</option>
							<option>新竹縣</option>
							<option>苗栗縣</option>
							<option>臺中市</option>
							<option>彰化縣</option>
							<option>南投縣</option>
							<option>嘉義市</option>
							<option>嘉義縣</option>
							<option>雲林縣</option>
							<option>臺南市</option>
							<option>高雄市</option>
							<option>澎湖縣</option>
							<option>金門縣</option>
							<option>屏東縣</option>
							<option>臺東縣</option>
							<option>花蓮縣</option>
							<option>連江縣</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memLocLive">居住地:</label>
					<div class="col-sm-8 col-md-8">
						<select class="form-control" id="memLocLive" name="memLocLive" value="${memVO.memLocLive}">
							<option>
								<c:if test="${memVO.memLocLive==null}">
											未設定
										</c:if> ${memVO.memLocLive}
							</option>
							<option>臺北市</option>
							<option>基隆市</option>
							<option>新北市</option>
							<option>宜蘭縣</option>
							<option>桃園市</option>
							<option>新竹市</option>
							<option>新竹縣</option>
							<option>苗栗縣</option>
							<option>臺中市</option>
							<option>彰化縣</option>
							<option>南投縣</option>
							<option>嘉義市</option>
							<option>嘉義縣</option>
							<option>雲林縣</option>
							<option>臺南市</option>
							<option>高雄市</option>
							<option>澎湖縣</option>
							<option>金門縣</option>
							<option>屏東縣</option>
							<option>臺東縣</option>
							<option>花蓮縣</option>
							<option>連江縣</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memBD">會員生日</label>
					<div class="col-sm-8 col-md-8">
						<input type="Date" class="form-control" id="memBD"
							placeholder="<fmt:formatDate value="${memVO.memBD}" pattern="yyyy/MM/dd" />"
							disabled>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memJoinDate">加入時間</label>
					<div class="col-sm-8 col-md-8">
						<input type="Date" class="form-control" id="memJoinDate"
							placeholder="<fmt:formatDate value="${memVO.memJoinDate}" pattern="yyyy/MM/dd hh:mm" />"
							disabled>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memLoginNum">登入次數</label>
					<div class="col-sm-8 col-md-8">
						<input type="text" class="form-control" id="memLoginNum"
							placeholder="${memVO.memLoginNum}" disabled>
					</div>
				</div>
				<!-- 確認 -->
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary" id="submitBtn"
							value="確認修改" onclick="memInfoChange()"></input> 
							<input type="hidden" name="action" value="changeInfo">
					</div>
				</div>
			</form>
		</div>
		<!--********************會員資訊********************-->
		<hr class="colorgraph">
	</div>

</div>