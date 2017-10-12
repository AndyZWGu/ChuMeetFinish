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

		<!--********************會員設定********************-->
		<h1 class="text-center">會員個人頁面設定</h1>
		<hr class="colorgraph">
		<div class="row">
			<form class="form-horizontal" method="post" id="memSettingForm"
				action="<%=request.getContextPath()%>/front-end/member/memberHome.do">
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memInt">自我介紹:</label>
					<div class="col-sm-8 col-md-8">
						<textarea class="form-control" rows="5" id="memInt" name="memInt">${memVO.memInt}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2" for="memPriv">隱私權設定:</label>
					<div class="col-sm-8 col-md-8">
						<select class="form-control" id="memPriv" name="memPriv">
							<c:choose>
								<c:when test="${memVO.memPriv==2}">
									<option selected="selected">公開</option>
								</c:when>
								<c:otherwise>
									<option>公開</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${memVO.memPriv==1}">
									<option selected="selected">僅好友</option>
								</c:when>
								<c:otherwise>
									<option>僅好友</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${memVO.memPriv==0}">
									<option selected="selected">不公開</option>
								</c:when>
								<c:otherwise>
									<option>不公開</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
				</div>
				<!-- 確認 -->
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-success" id="submitBtn"
							onclick="memSettingChange()">確認修改</button>
						<input type="hidden" name="action" value="changeSetting">
					</div>
				</div>
			</form>
		</div>
		<!--********************會員設定********************-->
		<hr class="colorgraph">
	</div>

</div>