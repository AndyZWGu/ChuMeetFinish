<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberVO guestVO = (MemberVO) session.getAttribute("guestVO");
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
	String account = (String) session.getAttribute("account");
	Integer memPriv = (Integer) request.getAttribute("memPriv");
	List<MemMBVO> memMBList = (List) request.getAttribute("memMBList");
	List<MemberVO> mbMemNameList = (List) request.getAttribute("mbMemNameList");
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
<link href="<%=request.getContextPath()%>/HTML/src/act/css/act.csss"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/HTML/src/member/css/guestHome.css"
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
		<div class="container">
			<div class="row profile">
				<div class="col-md-3 wow fadeInLeft" data-wow-delay=".05s"
					data-wow-duration=".1">
					<div class="profile-sidebar">
						<!-- SIDEBAR USERPIC -->
						<%-- base64 寫法 
          	<c:forEach items="${pictureList}" var="picture">
            	<div class="profile-userpic"> <img src="data:image/jpg;base64,${picture}" class="" alt=""> </div>
            </c:forEach>
            --%>
						<div class="profile-userpic">
							<img
								src="<%=request.getContextPath()%>/front-end/member/guestHome/avatar.do?memID=${guestVO.memID}"
								class="avatar" alt="">
						</div>
						<!-- END SIDEBAR USERPIC -->
						<!-- SIDEBAR USER TITLE -->
						<div class="profile-usertitle">
							<div class="profile-usertitle-name">${guestVO.memName}</div>
							<div class="profile-usertitle-level">剛加入的初心者</div>
						</div>
						<div class="row overvie">
							<div
								class="col-md-4 user-pad text-center glyphicon glyphicon-star-empty">
								<h5>跟隨</h5>
								<h4>9,527</h4>
							</div>
							<div
								class="col-md-4 user-pad text-center glyphicon glyphicon-user">
								<h5>好友數</h5>
								<h4>486</h4>
							</div>
							<div
								class="col-md-4 user-pad text-center glyphicon glyphicon-thumbs-up">
								<h5>評分數</h5>
								<h4>6,666</h4>
							</div>
						</div>
						<!-- END SIDEBAR USER TITLE -->
						<!-- SIDEBAR MENU -->
						<!--**************************隱私權判斷範圍(不開)**************************-->
						<c:if test="${account == null}">
							<div class="profile-usermenu text-center">
								<hr>
								<h4>您未登入會員</h4>
								<br>
								<h5>加入會員即可使用會員專屬互動功能</h5>
							</div>
						</c:if>
						<!--**************************隱私權判斷範圍(不開)**************************-->
						<c:if test="${account != null}">
							<!-- SIDEBAR BUTTONS -->
							<div class="profile-userbuttons row">
								<div class="col-sm-12 col-me-12">
									<form id="form1" action="#">
										<hr>
										<h3>會員專屬</h3>
										<div class="form-group">
										<a href="<%=request.getContextPath()%>/front-end/member/guestNF.do?memID=${guestVO.memID}">
											<input type="button"
												class="form-control btn btn-primary btn-sm" value="查看動態"
												name="guestNF"></input></a>
										</div>
										<div class="form-group">
											<input id="follow" type="button"
												class="form-control btn btn-warning btn-sm" value="追隨"
												name="follow"></input>
										</div>
										<div class="form-group">
											<input id="friend" type="button"
												class="form-control btn btn-success btn-sm" value="加好友"
												name="friends"></input>
										</div>
										<div class="form-group">
											<input id="email" type="button"
												class="form-control btn btn-info btn-sm" value="站內信"
												name="email"></input>
										</div>
										<button id="commit1" type="submit"
											class="form-control btn btn-default">Submit</button>
									</form>
								</div>
								<div class="col-sm-12 col-me-12">
									<form id="form2" action="post">
										<div class="form-group">
											<input id="blacklist" type="button"
												class="form-control btn btn-danger btn-sm" value="設為黑名單"></input>
										</div>
										<div class="form-group">
											<input id="report" type="button"
												class="form-control btn btn-danger btn-sm" value="檢舉"></input>
										</div>
										<button id="commit2" type="submit"
											class="form-control btn btn-default">Submit</button>
									</form>
								</div>
								<!-- <button id="follow" type="button" class="btn btn-warning btn-sm">追隨</button>
                            <button id="friend" type="button" class="btn btn-success btn-sm">好友邀請</button>
                            <button id="email" type="button" class="btn btn-info btn-sm">站內信</button>
                            <br>
                            <hr>
                            <button id="blacklist" type="button" class="btn btn-danger btn-sm">設為黑名單</button>
                            <button id="report" type="button" class="btn btn-danger btn-sm">檢舉</button> -->
							</div>
						</c:if>
						<!-- END MENU -->
					</div>
				</div>
				<!--**************************隱私權判斷範圍(不開)**************************-->
				<c:if
					test="${guestVO.memPriv==0 || guestVO.memPriv==1&&memPriv==0}">
					<div class="col-md-9 wow fadeInRight" data-wow-delay=".05s"
						data-wow-duration=".1">
						<div class="row profile-content blog-item text-center">
							<h1>不公開的會員頁面</h1>
							<hr>
							<h4>您沒有該名會員好友,或是該會員已將頁面設為不公開！</h4>
						</div>
					</div>
				</c:if>
				<!--**************************隱私權判斷範圍(開或是好友)**************************-->
				<c:if
					test="${guestVO.memPriv==2 || guestVO.memPriv==1&&memPriv==1}">
					<div class="col-md-9 wow fadeInRight" data-wow-delay=".05s"
						data-wow-duration=".1">
						<!--  statitics -->

						<!-- /.row -->
						<!--  statitics -->

						<div class="row profile-content blog-item">
							<h2>關於我</h2>
							<div class="introduction">${guestVO.memInt}</div>
							<hr class="colorgraph">
							<!--********************首頁********************-->
							<!-- BEGIN CONTENT -->
							<div class="col-md-12 col-sm-12">
								<ul class="nav nav-tabs">
									<li class="active"><a data-toggle="tab" href="#home">參加中的活動</a></li>
									<li><a data-toggle="tab" href="#act">參加的社團</a></li>
									<li><a data-toggle="tab" href="#club">追蹤</a></li>
								</ul>

								<div class="tab-content">
									<div id="act" class="tab-pane fade in active">
										<div class="container-fluid bg-3 text-center reward">
											<div class="row">
												<div class="col-sm-4">
													<img
														src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memVO.memID}"
														alt="Image" class="img-responsive thumbnail">
													<p>Lorem ipsum..</p>
												</div>
												<div class="col-sm-4">
													<img
														src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memVO.memID}"
														alt="Image" class="img-responsive thumbnail">
													<p>Lorem ipsum..</p>
												</div>
												<div class="col-sm-4">
													<img
														src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memVO.memID}"
														alt="Image" class="img-responsive thumbnail">
													<p>Lorem ipsum..</p>
												</div>
												<div class="row">
													<a href="http://www.google.com"><input type="button"
														class="btn btn-primary" value="看更多"></a>
												</div>
											</div>
										</div>
									</div>
									<div id="club" class="tab-pane fade">
										<div class="container-fluid bg-3 text-center reward">
											<div class="row">
												<div class="col-sm-4">
													<img
														src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memVO.memID}"
														alt="Image" class="img-responsive thumbnail">
													<p>Lorem ipsum..</p>
												</div>
												<div class="col-sm-4">
													<img
														src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memVO.memID}"
														alt="Image" class="img-responsive thumbnail">
													<p>Lorem ipsum..</p>
												</div>
												<div class="col-sm-4">
													<img
														src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memVO.memID}"
														alt="Image" class="img-responsive thumbnail">
													<p>Lorem ipsum..</p>
												</div>
												<div class="row">
													<a href="http://www.google.com"><input type="button"
														class="btn btn-primary" value="看更多"></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<hr class="colorgraph">
							</div>
							<!-- END CONTENT -->
							<!--********************首頁********************-->
							<hr class="colorgraph">
							<!--**************************留言板**************************-->
							<h2>留言板</h2>
							<div class="comments wow fadeInRight" data-wow-delay=".05s"
								data-wow-duration=".1">
								<c:forEach items="${memMBList}" var="memMBList"
									varStatus="status">
									<div class="media">
										<c:if
											test="${mbMemNameList[status.index].memID != memVO.memID}">
											<a
												href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${memMBList.memID}"
												class="pull-left"> <img
												src="<%=request.getContextPath()%>/front-end/member/guestHome/avatar.do?memID=${memMBList.memID}"
												alt="" class="media-object">
												<p>${mbMemNameList[status.index].memName}</p>
											</a>
										</c:if>
										<c:if
											test="${mbMemNameList[status.index].memID == memVO.memID}">
											<a
												href="<%=request.getContextPath()%>/front-end/member/memberHome.do?memID=${memMBList.memID}"
												class="pull-left"> <img
												src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${memMBList.memID}"
												alt="" class="media-object">
												<p>${mbMemNameList[status.index].memName}</p>
											</a>
										</c:if>
										<div class="media-body">
											<p>${memMBList.mbContent}</p>
											<span><fmt:formatDate value="${memMBList.mbDate}"
													pattern="yyyy/MM/dd hh:mm" /></span>
											<div class="col-sm-12 text-right">
												<c:if
													test="${mbMemNameList[status.index].memID != memVO.memID}">
													<input type="button" value="檢舉"
														class="btn btn-danger btn-xs">
												</c:if>
											</div>
										</div>
									</div>
								</c:forEach>
								<!--**************************留言功能**************************-->
								<c:if test="${account != null}">
								<div class="post-comment padding-top-40">
									<h3>留言</h3>
									<form role="form" method="post"
										action="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${guestVO.memID}">
										<div class="form-group">
											<h4>內容</h4>
											<textarea class="form-control" rows="2" name="comment"></textarea>
										</div>
										<p>
											<button class="btn btn-primary" type="submit">提交</button>
											<input type="hidden" name="action" value="comment">
										</p>
									</form>
								</div>
							</div>
							</c:if>
							<!--**************************留言版喔喔**************************-->
						</div>
					</div>
				</c:if>
				<!--**************************隱私權判斷範圍**************************-->
			</div>
		</div>
		<br> <br>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.9.0/sweetalert2.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		//***************************追隨按鈕***************************
		$('#follow').on('click', function() {
			swal({
				title : '追隨',
				text : "您將追隨該名會員!",
				type : 'info',
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : '確定',
				cancelButtonText : '取消',
				confirmButtonClass : 'btn btn-warning',
				cancelButtonClass : 'btn btn-danger',
				buttonsStyling : false
			}).then(function() {
				swal('追隨成功!', '您已成功追隨該會員.', 'success')
				resuit("follow");
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		})
		//***************************好友邀請按鈕***************************
		$('#friend').on('click', function() {
			swal({
				title : '好友',
				text : "您將邀請該名會員成為好友!",
				type : 'info',
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : '確定',
				cancelButtonText : '取消',
				confirmButtonClass : 'btn btn-success',
				cancelButtonClass : 'btn btn-danger',
				buttonsStyling : false
			}).then(function() {
				swal('邀請成功!', '您已成功邀請該會員,靜候佳音.', 'success')
				resuit("friend");
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		})
		//***************************站內信按鈕***************************
		$('#email').on(
				'click',
				function() {
					swal.setDefaults({
						input : 'text',
						confirmButtonText : 'Next &rarr;',
						showCancelButton : true,
						animation : false,
						progressSteps : [ '1', '2' ]
					})

					var steps = [ {
						title : '站內信',
						text : '請輸入站內信標題'
					}, '請輸入站內信內容' ]

					swal.queue(steps)
							.then(
									function(result) {
										swal.resetDefaults()
										swal({
											title : '您已寄信成功!',
											html : 'Your answers: <pre>'
													+ JSON.stringify(result)
													+ '</pre>',
											confirmButtonText : '完成'
										})
										email = result;
										resuit("email");
									}, function() {
										swal.resetDefaults()
									})
				})
		//***************************設為黑名單按鈕***************************
		$('#blacklist').on('click', function() {
			swal({
				title : '黑名單',
				text : "您將把該會員加入黑名單",
				type : 'warning',
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : '確定',
				cancelButtonText : '取消',
				confirmButtonClass : 'btn btn-danger',
				cancelButtonClass : 'btn',
				buttonsStyling : false
			}).then(function() {
				swal('加入成功!', '您已將該會員加入黑名單中.', 'success')
				resuit("blacklist");
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		})
		//***************************檢舉按鈕***************************
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
											title : '您已檢舉成功!',
											html : 'Your answers: <pre>'
													+ JSON.stringify(result)
													+ '</pre>',
											confirmButtonText : '完成'
										})
										report = result;
										resuit("report");
									}, function() {
										swal.resetDefaults()
									})
				})

		function resuit(e) {
			if (e == "follow") {
				// alert("追隨");
				setTimeout(function() {
					form1.submit();
				}, 1000);
			}
			if (e == "friend") {
				// alert("好友");
				setTimeout(function() {
					form1.submit();
				}, 1000);
			}
			if (e == "email") {
				// alert("郵件資料為" + email);
				setTimeout(function() {
					form1.submit();
				}, 1000);
			}
			if (e == "blacklist") {
				// alert("黑名單");
				setTimeout(function() {
					form1.submit();
				}, 1000);
			}
			if (e == "report") {
				// alert("檢舉資料為" + report);
				setTimeout(function() {
					form1.submit();
				}, 1000);
			}
		}

		function init() {
			var email = null;
			var reoprt = null;
			document.getElementById
		}
		window.onload = init;
	</script>

</body>
<!-- END BODY -->

</html>