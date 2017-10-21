<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMB.model.*"%>
<%@ page import="com.clubNews.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.model.*"%>
<%

ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");

ClubMBService clubMBSvc = new ClubMBService();
List<ClubMBVO> clubMBlist = (List<ClubMBVO>) request.getAttribute("clubMBlist");
pageContext.setAttribute("clubMBlist",clubMBlist);
// 找會員名字
MemberService memSvc=new MemberService();
List<MemberVO> mbMemNameList=new ArrayList<MemberVO>();
for(ClubMBVO list:clubMBlist){
	mbMemNameList.add(memSvc.getOneMember(list.getMemID()));
}
request.setAttribute("mbMemNameList",mbMemNameList);

//找社團會員
ClubMemService clubMemSvc = new ClubMemService();
List<ClubMemVO> clubMemlist = (List) request.getAttribute("clubMemlist");
pageContext.setAttribute("clubMemlist",clubMemlist);




ClubNewsService clubNewsSvc = new ClubNewsService();
List<ClubNewsVO> clubNewslist = (List<ClubNewsVO>) request.getAttribute("clubNewslist");
//找公告名字
List<MemberVO> newsMemNameList=new ArrayList<MemberVO>();
for(ClubNewsVO list1:clubNewslist){
	newsMemNameList.add(memSvc.getOneMember(list1.getMemID()));
}
request.setAttribute("newsMemNameList",newsMemNameList);



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
     <link
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.9.0/sweetalert2.min.css"
	rel="stylesheet">
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
<div class="content">
  <div class="container">
    <div class="row">
<!--社團名稱開始-->
      <div class="col-md-12 col-sm-12">
          <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/club/ClubAll.jsp">社團推薦</a></li>
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
                <li class="active"><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubOne">社團首頁</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubMem">社團成員</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubNews">社團公告</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubRoomContent.jsp">社團專屬聊天室</a></li>
                <%if(  clubMemVO.getClubMemType()==3&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=updateClub" >管理社團</a></li>
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
<%-- 		        <li><i class="fa fa-user"></i>${clubVO.clubmemID}</li>  <!-- 要幫我查到會員名稱 -->        --%>
		       <li><i class="fa fa-users"></i><c:out value="成員數:${clubMemlist.size()}"/></li>
<!-- 		        <li><i class="fa fa-comments"></i> 17</li> -->
<!-- 		        <li><i class="fa fa-tags"></i> 音樂, 校園, 專業</li> -->
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
      		
      		<c:forEach var="clubMBVO" items="${clubMBlist}" varStatus="status">
        <div class="media">                    
          <a href="javascript:;" class="pull-left">
          	<img src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubMBVO.memID}" alt="" class="media-object">
          </a>
          <div class="media-body">
           
 
              <h4 class="media-heading">${mbMemNameList[status.index].memName}<span>${clubMBVO.clubMBDate}</span>
            </h4>         
           
          
<%--             <h4 class="media-heading">${clubMBVO.memID}<span>${clubMBVO.clubMBDate}</span> --%>
<!--             </h4> -->

     
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
				  	<input type="hidden"  name="memID" value="${memVO.memID}">
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
<%-- <%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%> --%>
                	<div>
<%--                 		<%if(clubMemVO.getClubMemType()==1){%> --%>

<c:if test="${memVO!=null}">
					 	<%if(clubMemVO.getClubMemType()==0||clubMemVO.getClubMemStatus()==0){%>
					
						<button class="btn btn-block btn-primary" onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubMem.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=joinClub'" value="加入社團" class="btn btn-block btn-primary">加入社團</button>
  						 <%}%>
</c:if>					 
<%-- 						<%} %>	 --%>
<%-- 						<button class="btn btn-block btn-success"onclick="javascript:location.href='<%=request.getContextPath()%>/club/clubMem.do?clubID=${clubVO.clubID}&action=quitClub'" value="退出社團" class="btn btn-block btn-primary">退出社團</button> --%>
						<!-- Button trigger modal -->
						
					 	<%if(clubMemVO.getClubMemStatus()==1){%>
					 	<c:if test="${memVO.memID!=clubVO.clubmemID }">
						<button  class="btn btn-block btn-success" data-toggle="modal" data-target="#exampleModal">
						退出社團
						</button>	
						</c:if>	
 						 <%}%>
 						 
 						<%if(clubMemVO.getClubMemStatus()==1){%>
					 	<c:if test="${memVO.memID==clubVO.clubmemID }">
						<button  class="btn btn-block btn-success" >
						如要退出請先變更社長
						</button>	
						</c:if>	
 						 <%}%> 
 						 

						<%if(  (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>
						<button class="btn btn-block btn-success">創立社團活動</button>
						<%} %>	
					</div>
<%-- <% }%>					 --%>
               	<!-- END BTN--> 
               	 <!-- BEGIN BLOG PHOTOS STREAM -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>社長</h2>
                    
                    <ul class="list-unstyled">
                       <c:if test="${clubVO.clubmemID == memVO.memID}">
                      		<li><a href="<%=request.getContextPath()%>/front-end/member/memberHome.do"><img alt="" src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubVO.clubmemID}"></a></li>
                      	</c:if>
                      <c:if test="${clubVO.clubmemID != memVO.memID}">
                      	<li><a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${clubVO.clubmemID}"><img alt="" src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubVO.clubmemID}"></a></li>
                      </c:if>
                    </ul>                    
                  </div>
                  <!-- END BLOG PHOTOS STREAM -->
                <!-- BEGIN BLOG PHOTOS STREAM -->
                  <div class="blog-photo-stream margin-bottom-20">
                    <h2>社團成員</h2>
                    <ul class="list-unstyled">
                    <c:forEach var="clubMemlist" items="${clubMemlist}">

                    	<c:if test="${clubMemlist.memID == memVO.memID && clubMemlist.clubMemStatus==1}">
                      		<li><a href="<%=request.getContextPath()%>/front-end/member/memberHome.do"><img alt="" src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubMemlist.memID}"></a></li>
                      	</c:if>
                      	<c:if test="${clubMemlist.memID != memVO.memID && clubMemlist.clubMemStatus==1}">
                      		<li><a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${clubMemlist.memID}"><img alt="" src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubMemlist.memID}"></a></li>
                      	</c:if>

                      </c:forEach>
                    </ul>                    
                  </div>
                  <!-- END BLOG PHOTOS STREAM -->
                  <!-- BEGIN RECENT NEWS -->                            
                  
         
                  <div class="recent-news margin-bottom-10">
<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)){%>                  
           <h2>社團公告</h2>
           <c:forEach var="clubNewsVO" items="${clubNewslist}" varStatus="status" > 
           <c:if test="${clubNewsVO.clubNewsStatus==1 }">  
                    <div class="row margin-bottom-10">
          
                      <div class="col-md-3">
                        <img class="img-responsive" alt="" src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubNewsVO.memID}"> 
                      </div>
                      
                      <div class="col-md-9 recent-news-inner">
                      
                      
                      <h3><a href="javascript:;">${newsMemNameList[status.index].memName}</a></h3>
<%--                         <h3><a href="javascript:;">${clubNewsVO.memID}</a></h3> --%>
                        <p>${clubNewsVO.clubNewsTitle}</p>
                      </div> 
                                             
                    </div>
                    </c:if>
          </c:forEach>
          
          			<div class="col-md-12">
					<div class="moreBtn text-right">
					<a class="btn btn-sm btn-primary" href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubNews">more</a>
					</div>
					</div>  
          
<%}%>

<input id="report" type="button" class=" btn btn-danger btn-sm" value="檢舉"></input>

                    
                    
                    <!--start member news -->
  
                     <!--end member news -->
                    <!--社團最近活動-->

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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.9.0/sweetalert2.min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
	$('#report').on(
			'click',
			function() {
				swal.setDefaults({
					input : 'text',
					confirmButtonText : 'Next &rarr;',
					showCancelButton : true,
					confirmButtonColor : '#ff0000',
					animation : false,
					progressSteps : [ '1', '2' ]
				})

				var steps = [ {
					title : '檢舉',
					text : '請輸入檢舉標題'
				}, '請輸入檢舉內容' ]

				swal.queue(steps)
						.then(
								function(result) {
									swal.resetDefaults()
									swal({
										title : '檢舉!',
										html : '您已檢舉成功',
										confirmButtonText : '完成'
									})
									report = result;
									resuit("report");
								}, function() {
									swal.resetDefaults()
								})
			})
</script>
</body>
<!-- END BODY -->
</html></html>