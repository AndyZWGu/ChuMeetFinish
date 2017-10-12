 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
 <% 
 	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
 %>
 <!-- BEGIN HEADER -->
  <div class="header">
    <div class="container">
      <a class="site-logo" href="<%=request.getContextPath()%>/front-end/index.jsp"><img src="<%=request.getContextPath()%>/HTML/assets/LOGO/ChuMeet_NavLogo_25.png" alt="ChuMeet"></a>

      <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>


      <!-- BEGIN NAVIGATION -->
      <div class="header-navigation pull-right font-transform-inherit">
        <ul>
          <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="<%=request.getContextPath()%>/member/memNF.html"> 會員中心 </a>
            <ul class="dropdown-menu">
              <li><a href="<%=request.getContextPath()%>/front-end/member/memberSearch.do">搜尋會員</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/member/memberNFSearch.do">會員動態</a></li>
            </ul>
          </li>
          <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="<%=request.getContextPath()%>/act/act.html"> 活動廣場 </a>
            <ul class="dropdown-menu">
              <li><a href="<%=request.getContextPath()%>/front-end/act/act.jsp">我的活動</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/act/act.jsp">揪咪推薦</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/act/act.jsp">政府藝文活動</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/act/act.jsp">周末特調</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/act/act.jsp">熱門標籤</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/act/actPOI.jsp">活動分類</a></li>
              <li><a href="<%=request.getContextPath()%>/front-end/act/actMap.jsp">揪揪地圖</a></li>
            </ul>
          </li>
          <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="<%=request.getContextPath()%>/club/club_ALL.html"> 社團大廳 </a>
            <ul class="dropdown-menu">
            <c:if test="${memVO!=null}">
              <li><a href="<%=request.getContextPath()%>/front-end/club/clubMem.do?memID=${memVO.memID}&action=listAllJoinClub">我的社團</a></li>
            </c:if>
            <c:if test="${memVO==null}">
              <li><a href="<%=request.getContextPath()%>/front-end/club/MemAllClub.jsp">我的社團</a></li>
            </c:if>
              <li><a href="<%=request.getContextPath()%>/front-end/club/ClubAll.jsp">社團推薦</a></li>
            </ul>

          </li>

          <!-- BEGIN TOP SEARCH -->
          <li class="menu-search">
            <span class="sep"></span>
            <i class="fa fa-search search-btn"></i>
            <div class="search-box">
              <form action="<%=request.getContextPath()%>/front-end/member/memberRegister.do">
                <div class="input-group">
                  <input type="text" placeholder="Search" class="form-control">
                  <span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">Search</button>
                    </span>
                </div>
              </form>
            </div>
          </li>
          <!-- END TOP SEARCH -->

        </ul>
      </div>
      <!-- END NAVIGATION -->
    </div>
  </div>
