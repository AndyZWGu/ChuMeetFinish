<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%
String account = (String)session.getAttribute("account"); //登入帳號
MemberVO memVO= (MemberVO)session.getAttribute("memVO"); //登入帳號的所有會員資訊
%>
<!-- Start TOP BAR 2-->
<div class="pre-header">
    <div class="container">
      <div class="row">
        <!-- BEGIN TOP BAR LEFT PART -->
        <div class="col-md-6 col-sm-6 additional-shop-info">
          <ul class="list-unstyled list-inline">
            <li><span><strong>ChuMeet</strong></span></li>
            <li><span>Nice to meet you <i class="fa fa-smile-o" aria-hidden="true"></i></span> </li>
            <c:if test="${account!=null}" var="loginStatus2" scope="session" > 
            	<li><a href="<%=request.getContextPath()%>/front-end/act/actStart.jsp"><span class="topst"> 開始揪團吧！ </span></a></li>
            </c:if>
          </ul>


        </div>
        <!-- END TOP BAR LEFT PART -->

<!--依據使用者是否登入改變 -->
<c:if test="${account!=null}" var="loginStatus" scope="session" > 
        <!-- 登入時 -->
        <!-- BEGIN TOP BAR MENU -->
        <div class="col-md-6 col-sm-6 additional-nav">
          <ul class="list-unstyled list-inline pull-right">
            <li><span>哈囉，${memVO.memName}</span> <a href="<%=request.getContextPath()%>/front-end/member/memberHome.do"><i class="fa fa-home" aria-hidden="true"></i>會員首頁</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/member/memberLogout.do"><i class="fa fa-sign-out" aria-hidden="true"></i>登出</a></li>
            <!--  偽裝CSS請用btn-link-->
<%--             <li>
            	<form action="<%=request.getContextPath()%>/front-end/member/memberLogout.do" method="post">
            	<i class="fa fa-sign-out" aria-hidden="true"></i><input type="submit" value="登出" class="btn-link">
				<input type="hidden" name="action" value="logout"> 
            	</form>
            </li> --%>
            <li><a href="<%=request.getContextPath()%>/front-end/member/mail.jsp"><i class="fa fa-envelope-o" aria-hidden="true"></i>消息</a></li>
          </ul>
        </div>
        <!-- END TOP BAR MENU -->
</c:if>
<!--依據使用者是否登入改變 -->  
<c:if test="${account==null}" var="loginStatus2" scope="session" > 
        <!-- 訪客時 -->
        <div class="col-md-6 col-sm-6 additional-nav">
          <ul class="list-unstyled list-inline pull-right">
            <li><span>親愛的訪客～</span><a href="<%=request.getContextPath()%>/front-end/member/login.jsp"><i class="fa fa-sign-in" aria-hidden="true"></i>登入</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/member/register.jsp"><i class="fa fa-user-plus" aria-hidden="true"></i>註冊</a></li>
          </ul>
        </div>   
</c:if> 


      </div>
    </div>
  </div>
  <!-- END TOP BAR 2-->