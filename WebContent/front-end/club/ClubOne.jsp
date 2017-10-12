<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMB.model.*"%>
<%@ page import="com.clubNews.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="java.util.*"%>

<%

ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");

ClubMBService clubMBSvc = new ClubMBService();
List<ClubMBVO> clubMBlist = (List<ClubMBVO>) request.getAttribute("clubMBlist");
pageContext.setAttribute("clubMBlist",clubMBlist);



ClubNewsService clubNewsSvc = new ClubNewsService();
List<ClubNewsVO> clubNewslist = (List<ClubNewsVO>) request.getAttribute("clubNewslist");

// 找尋單一社團成員的資料
ClubMemVO clubMemVO = (ClubMemVO) request.getAttribute("clubMemVO");

%>
<%-- <jsp:useBean id="clubMBSvc" scope="page" class="com.clubMB.model.ClubMBService" /> --%>




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
<div class="">
  <div class="container">
    <div class="row">
<!--社團名稱開始-->
      <div class="col-md-12 col-sm-12">
          <ul class="breadcrumb">
            <li><a href="../index.html">ChuMeet!</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/club/ClubAll.jsp">搜索社團</a></li>
            <li class="active">社團首頁</li>
          </ul>
      </div>
      <div class="col-md-12 col-sm-12">
        <h1>${clubVO.clubName}</h1>
      </div>
<!--名稱結束--> 

<!--左邊開始-->
      <div class="col-xs-12 col-sm-3">
        <div class="row">   
            <ul class="tabbable actl-tabbable">
            	<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li class="active"><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubOne">社團首頁</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubMem">社團成員</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=toClubNews">社團公告</a></li>
                <li><a href="#tab_1" data-toggle="tab">社團活動</a></li>
                <%if(  clubMemVO.getClubMemType()==3&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?clubID=${clubVO.clubID}&action=updateClub" >管理社團</a></li>
                <%}%> 
            	<%}%> 
            </ul>
        </div>
      </div>
<!--左邊結束-->


<!--中間開始-->
     
<div class="col-xs-12 col-sm-6">
<!--picture-->
		 <div class="thumbnail">
  			  <img src="<%=request.getContextPath()%>/front-end/club/Photo.do?clubID=${clubVO.clubID}" class="img-responsive">                          
		</div>  
<!--pictureEND-->
    
<!--middleIcon-->
		<div class="col-xs-12 col-sm-12">
		    <div class="blog-item-img">
		      <ul class="blog-info">
		        <li><i class="fa fa-user"></i>${clubVO.clubmemID}</li>  <!-- 要幫我查到會員名稱 -->       
		       <li><i class="fa fa-users"></i><c:out value="${clubMemlist.size()}"/></li>
		        <li><i class="fa fa-comments"></i> 17</li>
		        <li><i class="fa fa-tags"></i> 音樂, 校園, 專業</li>
		      </ul>
		    </div> 
		</div>

<!--uploadBTN-->



<!--intro-->
	    <div class="col-xs-12 col-sm-12">      
	      <h3>社團介紹:</h3>        
	      <p>${clubVO.clubContent}</p>         
		</div>
<!--introEND-->

<!--mb-->
<div class="col-xs-12 col-sm-12"> 

<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>
	<div class="blog-item">
      <h2>留言板</h2>
<!-- 留言內容開始 -->      
      <div class="comments">
<%--       		<%@ include file="page1ClubMB.file" %>  --%>
<%--       		<c:forEach var="clubMBVO" items="${clubMBlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
      		<c:forEach var="clubMBVO" items="${clubMBlist}">
        <div class="media">                    
          <a href="javascript:;" class="pull-left">
          	<img src="../assets/pages/img/people/img1-small.jpg" alt="" class="media-object">
          </a>
          <div class="media-body">
          
          
            <h4 class="media-heading">${clubMBVO.memID}<span>${clubMBVO.clubMBDate}</span>
            </h4>

     
            <p>${clubMBVO.clubMBContent}</p>
            

          </div>
        </div>
        	</c:forEach>
<%--         	<%@ include file="page2ClubMB.file" %>  --%>

      </div>
<!-- 留言內容結束-->       
		<FORM METHOD="post" ACTION="clubOne.do" name="form3">
	      <div class="post-comment padding-top-40">
	        	<h3>社團留言</h3>
		        <form role="form">
		          <div class="form-group">
		            <textarea class="form-control" rows="8" name=oneClubMB></textarea>
		          </div>
		
				  <div>
					<input type="hidden"  name="clubID" value="${clubVO.clubID}">
					<input type="hidden"  name="action" value="addOneClubMB">
					<input type="submit" value="送出" class="btn btn-block btn-primary">						
				  </div>
		        </form>
	       </div>   
		</FORM>                                   
	</div>
<%}%>	
		
</div>
<!--mbEND-->
    

    
  </div>    
<!--中間結束-->


<!--右邊開始-->
      <div class="col-xs-12 col-sm-3 ">
                <!-- BEGIN BTN-->   
  
                	<div>
<%--                 		<%if(clubMemVO.getClubMemType()==1){%> --%>
					 	<%if(clubMemVO.getClubMemType()==0||clubMemVO.getClubMemStatus()==0){%>
						<button class="btn btn-block btn-primary" onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubMem.do?clubID=${clubVO.clubID}&action=joinClub'" value="加入社團" class="btn btn-block btn-primary">加入社團</button>
  						 <%}%>
<%-- 						<%} %>	 --%>
<%-- 						<button class="btn btn-block btn-success"onclick="javascript:location.href='<%=request.getContextPath()%>/club/clubMem.do?clubID=${clubVO.clubID}&action=quitClub'" value="退出社團" class="btn btn-block btn-primary">退出社團</button> --%>
						<!-- Button trigger modal -->
						
					 	<%if(clubMemVO.getClubMemStatus()==1){%>
						<button  class="btn btn-block btn-success" data-toggle="modal" data-target="#exampleModal">
						退出社團
						</button>		
 						 <%}%>

						<%if(  (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>
						<button class="btn btn-block btn-success">創立社團活動</button>
						<%} %>	
					</div>
					
               	<!-- END BTN--> 
                <!-- BEGIN BLOG PHOTOS STREAM -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>社團成員</h2>
                    <ul class="list-unstyled">
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/people/img5-small.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/works/img1.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/people/img4-large.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/works/img6.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/pics/img1-large.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/pics/img2-large.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/works/img3.jpg"></a></li>
                      <li><a href="javascript:;"><img alt="" src="../assets/pages/img/people/img2-large.jpg"></a></li>
                    </ul>                    
                  </div>
                  <!-- END BLOG PHOTOS STREAM -->
                  <!-- BEGIN RECENT NEWS -->                            
                  <h2>社團公告</h2>
         
                  <div class="recent-news margin-bottom-10">
<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>                  
           <c:forEach var="clubNewsVO" items="${clubNewslist}" >   
                    <div class="row margin-bottom-10">
          
                      <div class="col-md-3">
                        <img class="img-responsive" alt="" src="../assets/pages/img/people/img2-large.jpg"> 
                      </div>
                      
                      <div class="col-md-9 recent-news-inner">
                        <h3><a href="javascript:;">${clubNewsVO.memID}</a></h3>
                        <p>${clubNewsVO.clubNewsTitle}</p>
                      </div> 
                                             
                    </div>
          </c:forEach>
<%}%>

                    
					<div class="col-md-12">
					<div class="moreBtn">
					<button>more</button>
					</div>
					</div>   
                    
                    <!--start member news -->
                    <h2>成員異動</h2>
                    <div class="row margin-bottom-10">
                      <div class="col-md-3">
                        <img class="img-responsive" alt="" src="../assets/pages/img/people/img3-large.jpg">                        
                      </div>
                      <div class="col-md-9 recent-news-inner">
                        <h3><a href="javascript:;">Tesiusto baissimos</a></h3>
                        <p>加入社團</p>
                      </div>		              
                    </div>
                    

                    
                     <!--end member news -->
                    <!--社團最近活動-->
                    <h2>最新活動</h2>
                    <!--活動1-->
                    <div class="row margin-bottom-10">
                      <div class="col-md-3">
                        <img class="img-responsive" alt="" src="../assets/pages/img/people/img3-large.jpg">                        
                      </div>
                      <div class="col-md-9 recent-news-inner">
                        <h3><a href="javascript:;">社遊</a></h3>
                        <p>2017年12月12日</p>
                      </div>                  
                    </div>

          <div class="col-md-12">
          <div class="moreBtn">
          <button>more</button>
          </div>
          </div>
                    <!--END社團最近活動-->
                  </div>
                  <!-- END RECENT NEWS -->                            
      </div>
<!--右邊結束-->
    </div>
  </div>
</div>  

<br><br>



							<!--退出社團的警告  -->
									<!-- Modal -->
									<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">請再次確認</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <div class="modal-body">
									        確定要退出嗎?
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
									        <buttontype="button" class="btn btn-primary" onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubMem.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=quitClub'" value="退出社團" class="btn btn-block btn-primary">確認</button>
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


</body>
<!-- END BODY -->
</html></html>