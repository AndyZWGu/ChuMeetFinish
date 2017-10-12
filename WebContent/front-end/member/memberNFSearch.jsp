<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	List<MemNFVO> memNFSearchList = (List) request.getAttribute("memNFSearchList");
	pageContext.setAttribute("memNFSearchList", memNFSearchList);
%>
<html>

<!-- Head BEGIN -->
<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
<!-- 共用Header -->
<!--  my styles  -->
<!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
<!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
<!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
<link
	href="<%=request.getContextPath()%>/HTML/src/member/css/guestNF.css"
	rel="stylesheet">
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
	<div class="main">
				<div class=" col-sm-12 col-md-12 text-center">
					<h1>會員動態</h1>
				</div>
				<div class="col-md-12 wow bounceInDown" data-wow-delay=".05s"
					data-wow-duration=".1">
					<div class="content-page">
						<form action="<%=request.getContextPath()%>/front-end/member/memberNFSearch.do" method="post" class="content-search-view2">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="尋找動態" name="">
								<span class="input-group-btn">
									<button type="submit" class="btn btn-primary">搜尋</button>
									<input type="hidden" name="action" value="memNameSearch">
								</span>

							</div>
							<div class="col-sm-2 col-md-2">
								<select class="form-control" id="sel1">
									<option>依人氣排序</option>
									<option>追蹤數排序</option>
									<option>評價數排序</option>
									<option>留言數排序</option>
								</select>
							</div>
							<div class="input-daterange input-group" id="datepicker">
								<input type="text" class="input-sm form-control" name="start"
									placeholder="起始日" /> <span class="input-group-addon">to</span>
								<input type="text" class="input-sm form-control" name="end"
									placeholder="結束日" />
							</div>

						</form>
					</div>
				</div>
				<!-- BEGIN CONTENT -->

				<!-- END CONTENT -->
				<!--Result-->
				        <div class="container">
            <div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 wow fadeInUp"
					data-wow-delay=".05s" data-wow-duration=".1">
					<c:forEach items="${memNFSearchList}" var="memNFSearchList" varStatus="status">
						<div class="wall">
						<c:if test="${memNFSearchList.memID != memVO.memID}">
							<a class="article" href="<%=request.getContextPath()%>/front-end/member/guestNF.do?memNFID=${memNFSearchList.memNFID}&memID=${memNFSearchList.memID}">
						</c:if>
						<c:if test="${memNFSearchList.memID == memVO.memID}">
							<a class="article" href="<%=request.getContextPath()%>/front-end/member/memberNF.do?memNFID=${memNFSearchList.memNFID}">
						</c:if>  
								<img src="<%=request.getContextPath()%>/front-end/member/memberNFSearch/avatar.do?memID=${memNFSearchList.memID}&memNFID=${memNFSearchList.memNFID}" />
								<h2>${memNFSearchList.nfTitle}</h2>
								<h6>${memNFSearchList.nfViews} Views</h6>
							</a>
						</div>
					</c:forEach>
				</div>
				</div>
				</div>
			<!--Result-->

	</div>
	<!--主頁面要修改的都在這上面-->

	<!-- BEGIN FOOTER -->
	<c:import url="/front-end/footer.jsp">
	</c:import>
	<!-- END FOOTER -->

	<!-- 共用Js -->
	<c:import url="/front-end/publicJS.jsp">
	</c:import>
	<script
		src="<%=request.getContextPath()%>/HTML/src/member/js/jaliswall.js"></script>
		<script
		src="<%=request.getContextPath()%>/HTML/src/member/js/bootstrap-datepicker.min.js"></script>
	<!-- 共用Js -->
	<script type="text/javascript">
		$(function() {
			$('.wall').jaliswall({
				item : '.article'
			});
		});
		$('.input-daterange input').each(function() {
			$(this).datepicker('clearDates');
		});
	</script>
</body>
<!-- END BODY -->

</html>