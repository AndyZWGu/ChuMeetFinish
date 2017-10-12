<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	List<MemNFVO> memNFList = (List) request.getAttribute("memNFList");
%>

<div class="col-md-9 wow bounce" data-wow-delay=".05s"
	data-wow-duration=".1">
	
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#home">動態列表</a></li>
  <li><a data-toggle="tab" href="#menu1">發布新動態</a></li>
</ul>
<div class="tab-content">
  <div id="home" class="tab-pane fade in active">
	<div class="row profile-content blog-item">
		<ul class="breadcrumb">
			<li><a
				href="<%=request.getContextPath()%>/front-end/member/memberHome.do">首頁</a></li>
			<li class="active">動態列表</li>
		</ul>
		<!--********************會員動態********************-->
		<div class="list-group">
			<c:forEach items="${memNFList}" var="memNFList" varStatus="status">
				<a
					href="<%=request.getContextPath()%>/front-end/member/memberNF.do?memNFID=${memNFList.memNFID}"
					class="list-group-item">${memNFList.nfTitle}
					<p class="text-right">
						<fmt:formatDate value="${memNFList.nfDate}" pattern="yyyy/MM/dd" />
					</p>
				</a>
			</c:forEach>
		</div>
		<!--********************會員動態********************-->
		<hr class="colorgraph">
              
		<!--**************************分頁**************************-->
		<div class="row">

			<div class=" col-xs-offset-4 col-md-6 col-sm-6">
				<ul class="pagination pull-center">
					<li><a href="javascript:;">«</a></li>
					<li><a href="javascript:;">1</a></li>
					<li><span>2</span></li>
					<li><a href="javascript:;">3</a></li>
					<li><a href="javascript:;">4</a></li>
					<li><a href="javascript:;">5</a></li>
					<li><a href="javascript:;">»</a></li>
				</ul>
			</div>
		</div>
		<!--**************************分頁**************************-->
		</div>
</ul> 
  </div>
  <div id="menu1" class="tab-pane fade">
					<form method="post" action="<%=request.getContextPath()%>/front-end/member/memberNF.do" enctype="multipart/form-data" role="form">
						<div class="form-group">
						<input type="text" class="form-control input-sm" name="nfTitle" placeholder="動態標題">
						</div>						
						<div class="form-group">
						<input type="file" id="photo" name="nfPic" >
                        <p class="help-block">請上傳一張照片作為您的動態照片</p>
                        </div>
                        <div class="form-group">
						<textarea class="form-control" rows="10" name="comment" placeholder="動態內容"></textarea>
						</div>
                        <div class="form-group">
                        <input type="hidden" name="action" value="newNF">
						<input type="submit" value="發布" class="btn btn-primary">
						</div>
                   	</form>
</ul> 
</div>


	</div>

</div>