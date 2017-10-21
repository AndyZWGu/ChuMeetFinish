<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	List<FriendsVO> memFriList = (List<FriendsVO>)request.getAttribute("memFriList");
	//申請中
	List<FriendsVO> toBeFriList = (List<FriendsVO>)request.getAttribute("toBeFriList");
	List<MemberVO> toBeFriMemNameList = (List)request.getAttribute("toBeFriMemNameList");
	//好友
	List<FriendsVO> isFriList = (List<FriendsVO>)request.getAttribute("isFriList");
	List<MemberVO> isFriMemNameList = (List<MemberVO>)request.getAttribute("isFriMemNameList");
	//追隨
	List<FriendsVO> followFriList = (List<FriendsVO>)request.getAttribute("followFriList");
	List<MemberVO> followFriMemNameList = (List<MemberVO>)request.getAttribute("followFriMemNameList");
%>

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#home">申請中</a></li>
  <li><a data-toggle="tab" href="#menu1">好友</a></li>
  <li><a data-toggle="tab" href="#menu2">追隨</a></li>
</ul>

<div class="tab-content">
  <div id="home" class="tab-pane fade in active">
    <h3>申請列表</h3>
      <table class="table table-hover">
    <thead>
      <tr>
        <th>申請人</th>
        <th>申請時間</th>
        <th>動作</th>
        <th>更多資訊</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${toBeFriList}" var="toBeFriList" varStatus="status">
      <tr>
        <td>${toBeFriMemNameList[status.index].memName}</td>
        <td><fmt:formatDate value="${toBeFriList.friendDate}" pattern="yyyy/MM/dd" /></td>
        <td>
        	<form action="<%=request.getContextPath()%>/front-end/member/memberCommunity.do">
        		<input type="hidden" name="friMem1" value="${toBeFriList.friMem1}"></input>
        		<input type="hidden" name="friMem2" value="${toBeFriList.friMem2}"></input>
        		<input type="hidden" name="action" value="success"></input>
        		<input type="submit" class="btn btn-success" value="同意"></input>
        	</form>
        	<form action="<%=request.getContextPath()%>/front-end/member/memberCommunity.do">
        		<input type="hidden" name="friMem1" value="${toBeFriList.friMem1}"></input>
        		<input type="hidden" name="friMem2" value="${toBeFriList.friMem2}"></input>
        		<input type="hidden" name="action" value="cancel"></input>
        		<input type="submit" class="btn btn-danger" value="取消"></input>
        	</form>
        </td>
        <td>
        	<a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${toBeFriList.friMem2}" class="btn btn-primary">前往小屋</a>
        </td>
      </tr>
	</c:forEach>
    </tbody>
  </table>

  </div>
  <div id="menu1" class="tab-pane fade">
    <h3>好友列表</h3>
      <table class="table table-hover">
    <thead>
      <tr>
        <th>好友名稱</th>
        <th>成立時間</th>
        <th>動作</th>
        <th>更多資訊</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${isFriList}" var="isFriList" varStatus="status">
      <tr onclick="window.document.location='<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${isFriList.friMem2}';">
        <td>${isFriMemNameList[status.index].memName}
        <img
					src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${isFriList.friMem2}"
					class="img-responsive thumbnail" alt="">
					</td>
        <td><fmt:formatDate value="${isFriList.friendDate}" pattern="yyyy/MM/dd" /></td>
        <td>
            <form action="<%=request.getContextPath()%>/front-end/member/memberCommunity.do">
        		<input type="hidden" name="friMem1" value="${isFriList.friMem1}"></input>
        		<input type="hidden" name="friMem2" value="${isFriList.friMem2}"></input>
        		<input type="hidden" name="action" value="cancelFri"></input>
        		<input type="submit" class="btn btn-danger" value="取消好友"></input>
        	</form>        	
        </td>
        <td>
        	<a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${isFriList.friMem2}" class="btn btn-primary">前往好友小屋</a>
        </td>
      </tr>
	</c:forEach>
    </tbody>
  </table>
</ul> 
  </div>
  <div id="menu2" class="tab-pane fade">
    <h3>追隨列表</h3>
           <table class="table table-hover">
    <thead>
      <tr>
        <th>追隨名稱</th>
        <th>狀態</th>
        <th>更多資訊</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${followFriList}" var="followFriList" varStatus="status">
      <tr onclick="window.document.location='<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${followFriList.friMem2}';">
        <td>${followFriMemNameList[status.index].memName}</td>
        <td><fmt:formatDate value="${followFriList.friendDate}" pattern="yyyy/MM/dd" /></td>
        <td>
            <form action="<%=request.getContextPath()%>/front-end/member/memberCommunity.do">
        		<input type="hidden" name="friMem1" value="${followFriList.friMem1}"></input>
        		<input type="hidden" name="friMem2" value="${followFriList.friMem2}"></input>
        		<input type="hidden" name="action" value="cancelFollow"></input>
        		<input type="submit" class="btn btn-danger" value="取消追隨"></input>
        	</form>        	
        </td>
        <td>
        	<a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${followFriList.friMem2}" class="btn btn-primary">前往小屋</a>
        </td>
      </tr>
	</c:forEach>
    </tbody>
  </table>
  </div>
</div>


</div>