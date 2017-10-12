<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	List<FriendsVO> memFriList = (List)request.getAttribute("memFriList");
	List<FriendsVO> friMemNameList = (List)request.getAttribute("friMemNameList");
%>

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#home">申請中</a></li>
  <li><a data-toggle="tab" href="#menu1">追隨</a></li>
  <li><a data-toggle="tab" href="#menu2">好友</a></li>
</ul>

<div class="tab-content">
  <div id="home" class="tab-pane fade in active">
    <h3>申請中</h3>
     <ul class="list-group">
  <li class="list-group-item">First item</li>
  <li class="list-group-item">Second item</li>
  <li class="list-group-item">Third item</li>
  		<c:forEach items="${memMailList}" var="memMailList" varStatus="status">
			<a href="<%=request.getContextPath()%>/front-end/member/memberMail.do?memMailID=${memMailList.memMailID}" class="list-group-item">${memMailList.mailTitle}
				<p class="text-right">申請人:${friMemNameList[status.index].memName}</p>
				<p class="text-right"><fmt:formatDate value="${friFriList.friendDate}" pattern="yyyy/MM/dd" /></p>
			</a>
		</c:forEach>
</ul> 
  </div>
  <div id="menu1" class="tab-pane fade">
    <h3>追隨</h3>
     <ul class="list-group">
  <li class="list-group-item">First item</li>
  <li class="list-group-item">Second item</li>
  <li class="list-group-item">Third item</li>
</ul> 
  </div>
  <div id="menu2" class="tab-pane fade">
    <h3>好友</h3>
     <ul class="list-group">
  <li class="list-group-item">First item</li>
  <li class="list-group-item">Second item</li>
  <li class="list-group-item">Third item</li>
</ul> 
  </div>
</div>


</div>