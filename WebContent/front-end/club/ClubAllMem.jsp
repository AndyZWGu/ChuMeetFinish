<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="java.util.*"%>

<%
ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
ClubMemService clubMemSvc = new ClubMemService();
List<ClubMemVO> clubMemlist = (List<ClubMemVO>) request.getAttribute("clubMemlist");
pageContext.setAttribute("clubMemlist",clubMemlist);

//找尋單一社團成員的資料
ClubMemVO clubMemVO = (ClubMemVO) request.getAttribute("clubMemVO");
%>

<!DOCTYPE html>
<html>

<!-- Head BEGIN -->
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
    <link href="<%=request.getContextPath()%>/HTML/src/club/css/clubfirst.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/HTML/src/club/css/act.css" rel="stylesheet">
</head>
<!-- Head END -->

    <!-- Body BEGIN -->

    <body class="chumeet">
  <c:import url="/front-end/userHeader.jsp">
</c:import>

  <!-- Header Start -->
  <c:import url="/front-end/header.jsp">
</c:import>
<!-- Header END -->

<!--主頁面要修改的都在這下面-->

<!-- BEGIN CONTENT -->


<div class="main">
   
      <div class="container">
              <div class="row">
                <!--左邊-->
                <div class="col-xs-12 col-sm-12"><h1>熱音社</h1></div>
                <div class="col-xs-12 col-sm-3">

            <ul class="tabbable actl-tabbable">
            	<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubOne">社團首頁</a></li>
                <li class="active"><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubMem">社團成員</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubNews">社團公告</a></li>
                <li><a href="#tab_1" data-toggle="tab">社團活動</a></li>
                <%if(  clubMemVO.getClubMemType()==3&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=updateClub" >管理社團</a></li>
                <%}%> 
            	<%}%> 
            </ul> 
                </div>
                <!--左邊結束-->



                <!--中間社團成員-->
                <div class="col-xs-12 col-sm-9">

                <div class="col-sm-12 col-md-12"> 
                  <span><label>成員數:<c:out value="${clubMemlist.size()}"/></label></span>    
                  <span><label>管理員:(10)</label></span>
                  <span><label>搜尋成員</label><input type="text"></span>
                  <hr>
                </div>
        <div><%@ include file="page1ClubMem.file" %></div>
                            
	<c:forEach var="clubMemVO" items="${clubMemlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
                           <!--第一個-->  
                    <div class="col-sm-6 col-md-6">     
      				 <div class="container-fluid well ">
                    	<div class="row-fluid">
                       	  <div class="span4 col-sm-4">
                          <img src="image/music.jpg" class="img-circle" width="80" height="80">
                          </div>
        
                          <div class="col-sm-6 col-md-6">
                              <h3>${clubMemVO.memID}</h3>
                              <h6>Email: MyEmail@servidor.com</h6>
                              <h6>加入日期:${clubMemVO.clubMemJoinDate}</h6>
                          </div>
<%--     <%if(clubMemVO.getClubMemStatus()==2||clubMemVO.getClubMemStatus()==3){%>   --%>
     <%if(  (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)  &&clubMemVO.getClubMemStatus()==1   ){%>  
                          <div class="col-sm-2 col-md-2">
                              <div class="btn-group">  
                                  <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
                                   		   變更 
								  </a>
                                  <ul class="dropdown-menu">
                                 	  <%if(clubMemVO.getClubMemType()==3){%>
                                      <li data-toggle="modal" data-target="#exampleModal1"><a href="#"><span class="icon-wrench" ></span> 變更為幹部</a></li>
                                      <%}%>
                                      <%if(clubMemVO.getClubMemType()==3){%>
                                      <li><a href="#"><span class="icon-wrench" data-toggle="modal" data-target="#exampleModal2"></span> 變更為社長</a></li>
                                      <%}%>
                                      <%if(clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3){%>
                                      <li><a href="#"><span class="icon-trash"></span> 剔除</a></li>
                                      <%}%>
                                  </ul>
                              
                              </div>
                          </div>
      <%}%>
                       </div>
                   </div>  
				</div> 
                   <!--第一個結束-->
      </c:forEach>

<!-- 				<ul class="pagination"> -->
<!--                     <li><a href="javascript:;">上一頁</a></li> -->
<!--                     <li><a href="javascript:;">1</a></li> -->
<!--                     <li><a href="javascript:;">2</a></li> -->
<!--                     <li class="active"><a href="javascript:;">3</a></li> -->
<!--                     <li><a href="javascript:;">4</a></li> -->
<!--                     <li><a href="javascript:;">5</a></li> -->
<!--                     <li><a href="javascript:;">下一頁</a></li> -->
<!--                 </ul>              -->
	    <%@ include file="page2ClubMem.file" %>    

            

             	 </div>  
                 
                  <!--社團成員結束-->

              </div>
      </div>      
	 
</div>


  
  
  
							<!--退出社團的警告  -->
									<!-- Modal -->
									<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">請再次確認</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <div class="modal-body">
									        確定要變更嗎?
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
<%-- 									        <buttontype="button" class="btn btn-primary" onclick="javascript:location.href='<%=request.getContextPath()%>/club/clubMem.do?clubID=${clubVO.clubID}&action=quitClub'" value="退出社團" class="btn btn-block btn-primary">確認</button> --%>
									      </div>
									    </div>
									  </div>
									</div>  


<!--主頁面要修改的都在這上面-->


  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->

	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
<script>
	//社團的
	$('#myModal').modal('toggle')
	
</script> 
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>