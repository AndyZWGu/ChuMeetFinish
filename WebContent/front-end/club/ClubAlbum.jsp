<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubAlbum.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%
    List<ClubAlbumVO> clubAlbumlist = (List<ClubAlbumVO>)request.getAttribute("clubAlbumlist");
    request.setAttribute("clubAlbumlist",clubAlbumlist);
    ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
    ClubMemVO clubMemVO = (ClubMemVO) request.getAttribute("clubMemVO");
%>

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

   


<!-- Header END -->
<!--主頁面要修改的都在這下面-->

<!-- BEGIN CONTENT -->


<div class="">
	
<!-- BEGIN CONTENT -->
<!-- BEGIN LEFT SIDEBAR -->            
      <div class="container">
       
        <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubOne">社團首頁</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubMem">社團成員</a></li>
                <li class="active"><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
                <li><a href="#tab_3" data-toggle="tab">社團公告</a></li>
                <li><a href="#tab_1" data-toggle="tab">社團活動</a></li>
                <li><a href="#tab_2" data-toggle="tab">管理社團</a></li>
        </ul>
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
          <!-- title -->
          <div class="col-md-12 col-sm-12">
          		<div class="row">
          			<div class="col-xs-12 col-sm-10">
          				<h1>熱音社</h1>
          			</div>
          			<div class="col-xs-12 col-sm-2">
          			
          				<div class="row">        					
          					<div class="col-xs-12 col-sm-6">
          						<input type ="button" class="btn btn-block btn-primary" style="width:80px;height:30px;""  onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubAlbum.do?clubID=${clubVO.clubID}&action=toClubAlbumAdd'"  value="新增相簿" ></input>
          					</div>
          					
  
          					<div class="col-xs-12 col-sm-6">
          						<button class="btn btn-block btn-success" style="width:80px;height:30px;">刪除相簿</button>
          					</div>
          				</div>   				
          			</div>
			  </div>
          </div>
         
          <!-- title --> 
            <div class="content-page">
              <div class="row">
                <!-- BEGIN change -->  
     <!--左邊-->
                <div class="col-xs-12 col-sm-3">
            <ul class="tabbable actl-tabbable">
            	<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubOne">社團首頁</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubMem">社團成員</a></li>
                <li class="active"><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubNews">社團公告</a></li>
                <li><a href="#tab_1" data-toggle="tab">社團活動</a></li>
                <%if(  clubMemVO.getClubMemType()==3&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=updateClub" >管理社團</a></li>
                <%}%> 
            	<%}%> 
            </ul> 
                </div>
      <!--左邊結束-->
    
	<!--右-->
<div class="col-xs-12 col-sm-9">     
<%-- 以下相簿forEach  --%>
<c:forEach var="clubAlbumVO" items="${clubAlbumlist}" > 
      <div class="col-lg-4 col-sm-6 col-xs-6">
        <div class="clubcCard">
          <a title="Image 2" href="#">
          
          
<%--           <img class="thumbnail img-responsive" src="<%=request.getContextPath()%>/club/clubAlbumImg.do?clubImgID=${clubImgVO.clubImgID}" class="img-responsive"> --%>

<img class="thumbnail img-responsive" src="../src/club/img/img/c.jpg" class="img-responsive">
<%-- <!--暫時沒改 --> <img src="<%=request.getContextPath()%>/club/clubAlbumImg.do?clubImgID=1&clubAlbumID=${clubAlbumVO.clubAlbumID}" class="img-responsive"> --%>
<%--失敗如何找ClubImgID最小 		 <img src="<%=request.getContextPath()%>/club/clubAlbumCover.do?clubImgID=${clubImgVO.clubImgID}&clubAlbumID=${clubAlbumVO.clubAlbumID}" class="img-responsive"> --%>
           	</a>
<!--以下相簿名稱  -->           	
			<a href="<%=request.getContextPath()%>/front-end/club/clubAlbum.do?clubID=${clubVO.clubID}&action=toClubImg&clubAlbumID=${clubAlbumVO.clubAlbumID}">${clubAlbumVO.clubAlbumName}</a>
          
        </div>
      </div>
</c:forEach>      


   
      
      <!--分頁-->
      <hr class="event-post-sep"> 
                  <ul class="pagination">
                    <li><a href="javascript:;">上一頁</a></li>
                    <li><a href="javascript:;">1</a></li>
                    <li><a href="javascript:;">2</a></li>
                    <li class="active"><a href="javascript:;">3</a></li>
                    <li><a href="javascript:;">4</a></li>
                    <li><a href="javascript:;">5</a></li>
                    <li><a href="javascript:;">下一頁</a></li>
                  </ul>
	  <!--分頁結束--> 
</div>              

      
      
      
      
   
 
    
    <hr>
  </div>
</div>
<div tabindex="-1" class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
		<button class="close" type="button" data-dismiss="modal">×</button>
		<h3 class="modal-title">Heading</h3>
	</div>
	<div class="modal-body">
		
	</div>
	<div class="modal-footer">
		<button class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
   </div>

        

               
               
               
        <!--右結束-->       
                <!-- END change -->            
              </div>
            </div>
          
          <!-- END CONTENT -->
        </div> 
       </div> 
      </div>
        <!-- END SIDEBAR & CONTENT -->

<!--主頁面要修改的都在這上面-->


  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->

	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->

<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>