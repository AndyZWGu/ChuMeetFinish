<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%
// ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
ActMemService actmSvc = new ActMemService();
// List<ClubMemVO> clubMemlist = (List<ClubMemVO>) request.getAttribute("clubMemlist");
// pageContext.setAttribute("clubMemlist",clubMemlist);
// ClubService clubSvc = new ClubService();

// //找尋單一社團成員的資料
// ClubMemVO clubMemVO = (ClubMemVO) request.getAttribute("clubMemVO");
//單一會員參加的社團
MemberVO memVO= (MemberVO)session.getAttribute("memVO");
 actmSvc.myActList1(memVO.getMemID());
%>


<head>
    	<!-- 共用Header -->
  <c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
  <!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
  <!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
  <!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
  <!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/slider.css" rel="stylesheet">

 <link href="../src/club/css/MemAllClub.css" rel="stylesheet">
</head>
<body>
  <c:import url="/front-end/userHeader.jsp">
</c:import>

  <!-- Header Start -->
  <c:import url="/front-end/header.jsp">
</c:import>
<div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
    <c:if test="${memVO==null}">
    	<h1>請先登入會員！</h1>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    	<br>
    </c:if>
    <c:if test="${memVO!=null}">
    <table class="table table-striped custab">
	<thead>

        <tr>
<!--             <th>ID</th> -->
            <th>社團名稱</th>
            <th>Parent ID</th>
            <th class="text-center">Action</th>
            <th>加入日期</th>
        </tr>
    </thead>
    

<c:forEach var="actMemVO" items="${memAllJoinActlist}">	
            <tr>  
                <td>${actMemVO.actID}</td>
                <td>News Cate</td>
                <td class="text-center"><a class='btn btn-info btn-xs' href="<%=request.getContextPath()%>/front-end/club/clubAll.do?clubID=${clubMemVO.clubID}&action=toClubOne"><span class="glyphicon glyphicon-edit"></span> 前往</a> 
<%--                 <c:if test="${clubMemVO.clubMemStatus==1 }"> --%>
                </td>  
                
                <td>${actMemVO.actJoinDate}</td>
<%--                 </c:if> --%>
<%--                 		<%if(clubMemVO.getClubMemStatus()==1){%> --%>
<!-- 						<button  class="btn btn-block btn-success" data-toggle="modal" data-target="#exampleModal"> -->
<!-- 						退出社團 -->
<!-- 						</button>		 -->
<%--  						 <%}%> --%>
                
                

            </tr>
</c:forEach>
    </table>
    </c:if>
    </div>
</div>


       <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->

	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
<!-- <script src="../src/club/js/MemAllClub.js" type="text/javascript"></script>       -->
</body>
</html>