<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	List<MemberVO> memSearchList = (List) request.getAttribute("memSearchList");
	pageContext.setAttribute("memSearchList", memSearchList);
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
<link href="<%=request.getContextPath()%>/HTML/src/index/index-byAGu.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/slider.css" rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/HTML/src/member/css/memSerach.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
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
	<div class="main content">
		<div class="container">
			<div class="row">
				<div class=" col-sm-12 col-md-12 text-center">
					<h1>搜尋會員</h1>
				</div>
				<!-- BEGIN CONTENT -->
				<div class="col-md-4 wow fadeInLeft" data-wow-delay=".05s"
					data-wow-duration=".1">
					<div class="content-page">
						<form
							action="<%=request.getContextPath()%>/front-end/member/memberSearch.do"
							class="content-search-view2" method="post">
							<div class="input-group">
								<h2>進階查詢</h2>
							</div>
							<div class="input-group">
								<!-- 							<div class="input-daterange input-group" id="datepicker">
								<input type="text" class="input-sm form-control" name="start"
									placeholder="起始日" /> <span class="input-group-addon">to</span>
								<input type="text" class="input-sm form-control" name="end"
									placeholder="結束日" />
							</div> -->
								<h6>生日</h6>
								<input type="date" class="input-sm form-control"
									placeholder="生日" name="memBD">
								<h6>加入時間範圍</h6>
								<div class="input-daterange input-group" id="datepicker">
									<input type="text" class="input-sm form-control" name="start"
										placeholder="起始日" /> <span class="input-group-addon">to</span>
									<input type="text" class="input-sm form-control" name="end"
										placeholder="結束日" />
								</div>
							</div>
							<div class="input-group">
								<h6>追蹤數：</h6>
								<input type="text" class="form-control" id="num"
									placeholder="大於" name="memFollows">
							</div>
							<div class="input-group">
								<h6>好友數：</h6>
								<input type="text" class="form-control" id="num"
									placeholder="大於" name="memFriends">
							</div>
							<div class="input-group">
								<h6>評價數：</h6>
								<input type="text" class="form-control" id="num"
									placeholder="大於" name="memStar">
							</div>
							<div class="input-group">
								<h6>登入次數：</h6>
								<input type="text" class="form-control" id="num"
									placeholder="數量" name="memLoginNum">
							</div>
							<div class="input-group">
								<h6>出生地：</h6>
								<select class="form-control" id="born" name="memLocBorn">
									<option></option>
									<option>臺北市</option>
									<option>基隆市</option>
									<option>新北市</option>
									<option>宜蘭縣</option>
									<option>桃園市</option>
									<option>新竹市</option>
									<option>新竹縣</option>
									<option>苗栗縣</option>
									<option>臺中市</option>
									<option>彰化縣</option>
									<option>南投縣</option>
									<option>嘉義市</option>
									<option>嘉義縣</option>
									<option>雲林縣</option>
									<option>臺南市</option>
									<option>高雄市</option>
									<option>澎湖縣</option>
									<option>金門縣</option>
									<option>屏東縣</option>
									<option>臺東縣</option>
									<option>花蓮縣</option>
									<option>連江縣</option>
								</select>
							</div>
							<div class="input-group">
								<h6>居住地：</h6>
								<select class="form-control" id="live" name="memLocLive">
									<option></option>
									<option>臺北市</option>
									<option>基隆市</option>
									<option>新北市</option>
									<option>宜蘭縣</option>
									<option>桃園市</option>
									<option>新竹市</option>
									<option>新竹縣</option>
									<option>苗栗縣</option>
									<option>臺中市</option>
									<option>彰化縣</option>
									<option>南投縣</option>
									<option>嘉義市</option>
									<option>嘉義縣</option>
									<option>雲林縣</option>
									<option>臺南市</option>
									<option>高雄市</option>
									<option>澎湖縣</option>
									<option>金門縣</option>
									<option>屏東縣</option>
									<option>臺東縣</option>
									<option>花蓮縣</option>
									<option>連江縣</option>
								</select>
							</div>
							<div class="input-group">
								<h6>排序</h6>
								<select class="form-control" name="sort">
									<option></option>
									<option>追蹤數(降序)</option>
									<option>追蹤數(升序)</option>
									<option>好友數排序(降序)</option>
									<option>好友數排序(升序)</option>
									<option>評價數排序(降序)</option>
									<option>評價數排序(升序)</option>
									<option>加入日期排序(降序)</option>
									<option>加入日期排序(升序)</option>
									<option>登入次數排序(降序)</option>
									<option>登入次數排序(升序)</option>
								</select>
							</div>
							<div class="input-group">
								<h6>送出查詢</h6>
								<input id="register" type="submit" value="確定"
									class="btn btn-primary"> <input type="hidden"
									name="action" value="compositeSearch">
							</div>
						</form>
					</div>
				</div>
				<!-- END CONTENT -->
				<!--Result-->
				<div class="col-md-8 wow fadeInRight" data-wow-delay=".05s"
					data-wow-duration=".1">
					<div class="content-page">
						<form
							action="<%=request.getContextPath()%>/front-end/member/memberSearch.do"
							class="content-search-view2" method="post">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="尋找會員"
									name="memName"> <span class="input-group-btn">
									<button type="submit" class="btn btn-primary">搜尋</button> <input
									type="hidden" name="action" value="memNameSearch">
								</span>
							</div>
						</form>
						<%@ include file="memberSearchPageMessage.file"%>
						<c:forEach items="${memSearchList}" var="memSearch"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"
							varStatus="status">

							<div class="search-result-item col-sm-12 col-md-12">
								<div class="col-sm-3 col-md-3">
									<img
										src="<%=request.getContextPath()%>/front-end/member/memberSearch/avatar.do?memID=${memSearch.memID}"
										class="thumbnail" alt="Cinque Terre" width="100" height="100">
											<c:if test="${memSearch.memID == memVO.memID && memVO!=null}">
											<h1>本人!!</h1>
												<a class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/member/memberHome.do">我家的小屋</a>
											</c:if>
											<c:if test="${memSearch.memID != memVO.memID && memVO!=null}">
												<a class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${memSearch.memID}">前往小屋</a>
											</c:if>
								</div>
								<div class="col-sm-9 col-md-9">
									<h4>
											<p>姓名:${memSearch.memName}</p>
											<c:if test="${memSearch.memGender==0}">
												<p>性別:妹子</p>
											</c:if>
											<c:if test="${memSearch.memGender==1}">
												<p>性別:男</p>
											</c:if>
											<c:if test="${memSearch.memLocLive!=null}">
												<p>居住:~${memSearch.memLocLive}~的人類</p>
											</c:if>
											<hr>
											<p>${memSearch.memInt}</p>
											<hr>
											<p>登入次數:${memSearch.memLoginNum}</p>
											<p>加入揪咪時間:${memSearch.memJoinDate}</p>
									</h4>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<%@ include file="memberSearchPagination.file"%>
			<!--Result-->
			<!--分頁-->
			<!--             <div class="row">

                <div class=" col-xs-offset-5 col-md-6 col-sm-6">
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
            </div> -->
			<!--分頁-->
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
	<script
		src="<%=request.getContextPath()%>/HTML/src/member/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript">
		$('.input-daterange input').each(function() {
			$(this).datepicker('clearDates');
		});
	</script>

</body>
<!-- END BODY -->

</html>