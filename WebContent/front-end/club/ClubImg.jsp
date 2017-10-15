<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubImg.model.*"%>
<%@ page import="com.clubAlbum.model.*"%>


<%

ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");

ClubAlbumVO clubAlbumVO = (ClubAlbumVO) request.getAttribute("clubAlbumVO");

List<ClubImgVO> clubImglist = (List<ClubImgVO>)request.getAttribute("clubImglist");
// request.setAttribute("clubImglist",clubImglist);


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


<div class="main content">
<!-- BEGIN CONTENT -->
<!-- BEGIN LEFT SIDEBAR -->            
      <div class="container">
        <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/club/ClubAll.jsp">社團推薦</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
			<li class="active">社團照片</li>
        </ul>
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
          <!-- title -->
          <div class="col-md-12 col-sm-12">
          	
          	<div class="col-xs-12 col-sm-10">
            <h1>${clubAlbumVO.clubAlbumName}</h1>
            </div>
                      <div class="col-xs-12 col-sm-2">
          			
          				<div class="row">
          					<div class="col-xs-12 col-sm-6">
          						
          						<input type ="button"  class="btn btn-block btn-primary" style="width:80px;height:30px;""  onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubImg.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&clubAlbumID=${clubAlbumVO.clubAlbumID}&action=toClubImgAdd'"  value="新增相片" ></input>
          					</div>
<!--           					<div class="col-xs-12 col-sm-6"> -->
<!--           						<button class="btn btn-block btn-success" style="width:80px;height:30px;">刪除相片</button> -->
<!--           					</div> -->
          				</div>   				
          			</div>

          </div>

          <!-- title --> 
            <div class="content-page">
              <div class="row">
                <!-- BEGIN change -->  
<div class="container">
  <div class="row">
<c:forEach var="clubImgVO" items="${clubImglist}"> 
<c:if test="${clubImgVO.clubImgStatus!=0}"> 
      <div class="col-lg-4 col-sm-4 col-xs-4">
        <div class="clubcCard">
          <a title="${clubImgVO.clubImgContent}      創立日期:${clubImgVO.clubImgDate} "  href="#">
          

          	<img class="thumbnail img-responsive" src="<%=request.getContextPath()%>/front-end/club/clubAlbumImg.do?clubImgID=${clubImgVO.clubImgID}" class="img-responsive">
			 <center><h2>${clubImgVO.clubImgContent}</h2></center>
<%if( (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>  			 
			<!-- 修改按鈕 -->
			<div class="form-group col-md-6 col-sm-6 text-center">
			  <div><input type ="button"  class="btn btn-primary"  onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubImg.do?clubID=${clubVO.clubID}&clubAlbumID=${clubAlbumVO.clubAlbumID}&clubImgID=${clubImgVO.clubImgID}&action=toClubImgUpdate'"  value="修改" ></input></div>
			</div>
		      <!-- 刪除按鈕 -->
		      <div>
		      <form METHOD="post" ACTION="clubImg.do" name="form1" enctype="multipart/form-data">
			      <div class="form-group col-md-6 col-sm-6 text-center">
			         <input type="submit" value="刪除" class="btn btn-success">
			         <input type="hidden" name="action" value="deleteOneImg">
			         <input type="hidden" name="clubAlbumID" value="${clubAlbumVO.clubAlbumID}">
			         <input type="hidden" name="clubImgID" value="${clubImgVO.clubImgID}">
			    	 <input type="hidden" name="clubID" value="${clubVO.clubID}">  
			    	 <input type="hidden" name="memID" value="${memVO.memID}">  
			      </div>
			 </form>
			 </div>
<% }%>

			

<%-- 			<span>${clubImgVO.clubImgContent}</span> --%>
<%-- 			<span>  創立日期:${clubImgVO.clubImgDate}</span> --%>
          </a>
        </div>
      </div>
</c:if>      
</c:forEach>

      
<!--       分頁 -->
<!--       <hr class="event-post-sep">  -->
<!--                   <ul class="pagination"> -->
<!--                     <li><a href="javascript:;">上一頁</a></li> -->
<!--                     <li><a href="javascript:;">1</a></li> -->
<!--                     <li><a href="javascript:;">2</a></li> -->
<!--                     <li class="active"><a href="javascript:;">3</a></li> -->
<!--                     <li><a href="javascript:;">4</a></li> -->
<!--                     <li><a href="javascript:;">5</a></li> -->
<!--                     <li><a href="javascript:;">下一頁</a></li> -->
<!--                   </ul> -->
<!-- 	  分頁結束          -->

      
      
      
      
   
 
    
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
  </div>
</div>
        

               
               
               
               
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