<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="com.clubNews.model.*"%>
<%@ page import="com.clubMB.model.*"%>
<%@ page import="com.clubAlbum.model.*"%>
<%@ page import="com.member.model.*"%>
<%


ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
ClubNewsService clubNewsSvc = new ClubNewsService();
List<ClubNewsVO> clubNewslist = (List<ClubNewsVO>) request.getAttribute("clubNewslist");
request.setAttribute("clubNewslist",clubNewslist);
// List<ClubMBVO> clubMBlist = (List<ClubMBVO>) request.getAttribute("clubMBlist");
//找公告名字

MemberService memSvc=new MemberService();
List<MemberVO> newsMemNameList=new ArrayList<MemberVO>();
for(ClubNewsVO list1:clubNewslist){
	newsMemNameList.add(memSvc.getOneMember(list1.getMemID()));
}
request.setAttribute("newsMemNameList",newsMemNameList);


// 找尋單一社團成員的資料
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


<div class="content">	
<!-- BEGIN CONTENT -->
<!-- BEGIN LEFT SIDEBAR -->            
      <div class="container">
       
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
                                <div class="col-md-12 col-sm-12">
          <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
            <li><a href="<%=request.getContextPath()%>/front-end/club/ClubAll.jsp">社團推薦</a></li>
            <li class="active">社團公告</li>
          </ul>
      </div>
          
          <!-- title -->
          <div class="col-md-12 col-sm-12">
          		<div class="row">
          			<div class="col-xs-12 col-sm-10">
          				<h1>${clubVO.clubName}</h1>
          			</div>
			  	</div>
          </div>     
          <!-- title --> 
            <div class="content-page">
              <div class="row">
                <!-- BEGIN change -->  
<!--左邊開始-->





		      <div class="col-xs-12 col-sm-3">
		        <div class="row">   
            <ul class="tabbable actl-tabbable">
            	<%if(  (clubMemVO.getClubMemType()==1||clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubOne">社團首頁</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubMem">社團成員</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubAlbum">社團相簿</a></li>
                <li class="active"><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=toClubNews">社團公告</a></li>
                <li><a href="#tab_1" data-toggle="tab">社團活動</a></li>
                <%if(  clubMemVO.getClubMemType()==3&&clubMemVO.getClubMemStatus()==1   ){%>   
                <li><a href="<%=request.getContextPath()%>/front-end/club/clubOne.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&action=updateClub" >管理社團</a></li>
                <%}%> 
            	<%}%> 
            </ul>
		        </div>
		      </div>
<!--左邊結束-->
    
<!--右邊開始-->
<div class="col-xs-12 col-sm-9">     

   <!--mb-->
  <div class="col-xs-12 col-sm-12"> 
	<div class="blog-item">
      <h2>社團公告</h2>      
      <div class="comments">
      
<%if(  (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)  &&clubMemVO.getClubMemStatus()==1   ){%>  	
<FORM METHOD="post" ACTION="clubNews.do" name="form3">
      <div class="post-comment padding-top-40">
        <h3>新增公告</h3>
        <form role="form">
          <div class="form-group">
          	公告標題:<input type="text"  name="clubNewsTitle"><br><br>
            	<textarea class="form-control" rows="8" name="clubNewsContent" ></textarea>
          </div>
					<div>
						<input type="hidden"  name="clubID" value="${clubVO.clubID}">
						<input type="hidden"  name="memID" value="${memVO.memID}">
						<input type="hidden"  name="action" value="addClubNews">
						<input type="submit" value="送出" class="btn btn-block btn-primary">						
					</div>
        </form>
      </div>    
</FORM><br>
<% }%>

	    <%@include file="page1ClubNews.file" %>       
      	<c:forEach var="clubNewsVO" items="${clubNewslist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">

<!--         <div class="media">                     -->

<c:if test="${clubNewsVO.clubNewsStatus==1}">
	 	
		<div style="border-width:3px;border-style:solid;border-color:Silver;padding:0.01px;">

         		 <div class="media-body">
          			<div class="row">	
	          			<div class="col-xs-4 col-sm-2"><a href="javascript:;" class="pull-left"><img src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubNewsVO.memID}" alt="" class="media-object"> </a></div>
	          			<div class="col-xs-4 col-sm-2"><h3 class="media-heading">${newsMemNameList[status.index].memName}</h3></div>
	          			<div class="col-xs-4 col-sm-2"><h3 class="media-heading">${clubNewsVO.clubNewsTitle}</h3></div>
	          			
	          			<div class="col-xs-4 col-sm-2"><h4 class="media-heading"><p>${clubNewsVO.clubNewsDate}</p></h4></div>
	          			
	           			<div class="col-xs-4 col-sm-2">
	           			<%if(  (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)  &&clubMemVO.getClubMemStatus()==1   ){%>  	
	           			<input type ="button" style="width:80px;height:30px;" class="btn btn-block btn-primary" style="width:80px;height:30px;""  onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/club/clubNews.do?memID=${memVO.memID}&clubID=${clubVO.clubID}&clubNewsID=${clubNewsVO.clubNewsID}&action=clubNewsUpdate'"  value="變更公告" ></input>
	           			<%}%>
	           			</div>  
	           			  
	<!--             		<div class="col-xs-4 col-sm-2"><button class="btn btn-block btn-success" style="width:80px;height:30px;">刪除公告</button>	</div> -->
          		
	            		<div class="col-xs-4 col-sm-2">
	            		<%if(  (clubMemVO.getClubMemType()==2||clubMemVO.getClubMemType()==3)  &&clubMemVO.getClubMemStatus()==1   ){%>  	
					            <!-- 刪除按鈕 -->
							<form METHOD="post" ACTION="clubNews.do" name="form1">
								  <div class="form-group col-md-12 col-sm-12 text-center">
								         <input type="submit" value="刪除公告" class="btn btn-block btn-success">
								         <input type="hidden" name="action" value="deleteOneNews">
								         <input type="hidden" name="clubNewsID" value="${clubNewsVO.clubNewsID}">
								         <input type="hidden" name="memID" value="${memVO.memID}">
								    	 <input type="hidden" name="clubID" value="${clubVO.clubID}">  
								  </div>
							</form>	
						<%}%>		
            			</div> 	         			 
					</div>   
					 <hr>        		 
         		 <div class="col-xs-12 col-sm-12"><h3>公告內容:</h3></div>
       			 <div class="col-xs-12 col-sm-12"><h3>${clubNewsVO.clubNewsContent}</h3></div>

         		 </div>

        </div>



<!-- 失敗,會一直取道同一個值 -->
<!--  							<button style="width:80px;height:30px;" class="btn btn-block btn-success" data-toggle="modal" data-target="#exampleModal"> -->
<!-- 							  跳出內容 -->
<!-- 							</button>	 -->


<!-- 							        							點擊跳出NEWS內容  -->
<!-- 									Modal -->
<!-- 									<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 									  <div class="modal-dialog" role="document"> -->
<!-- 									    <div class="modal-content"> -->
<!-- 									      <div class="modal-header"> -->
<!-- 									      	<button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 									          <span aria-hidden="true">&times;</span> -->
<!-- 									        </button> -->
<!-- 									        <h3 class="modal-title" id="exampleModalLabel">公告內容:</h3> -->
<!-- 									      </div> -->
<!-- 									      <div class="modal-body"> -->
<!-- 									            <div class="col-xs-12 col-sm-12"><h3></h3></div> -->
<%--        			 								<div class="col-xs-12 col-sm-12"><h4>${clubNewsVO.clubNewsContent}</h4></div> --%>
<%--        			 								<div class="col-xs-12 col-sm-12"><h3>${clubNewsVO.clubNewsID}</h3></div> --%>
<!-- 									      </div> -->
<!-- 									      <div class="modal-footer"> -->
<!-- 									        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button> -->
<!-- 									      </div> -->
<!-- 									    </div> -->
<!-- 									  </div> -->
<!-- 									</div> -->
	
							
							
							
</c:if>

			
        <!--end media-->
        </c:forEach>
      </div>
              	<ul class="pagination col-md-12 column">
					<%@ include file="page2ClubNews.file" %>
                </ul>  
    </div>                                 
  </div>
</div>

<!--右邊結束--> 
    <hr>
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
<script>
//我的社團頁面
// 	$(document).ready(function() {
// 	$('.thumbnail').click(function(){
//     $('.modal-body').empty();
//   	var title = $(this).parent('a').attr("title");
//   	$('.modal-title').html(title);
//   	$($(this).parents('div').html()).appendTo('.modal-body');
//   	$('#myModal').modal({show:true});
// });
// });
	$('#myModal').modal('toggle')	

	
</script> 

<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>