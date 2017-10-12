<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

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
	href="<%=request.getContextPath()%>/HTML/src/member/css/memHome.css"
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

	<!--主頁面-->
	<div class="main">
		<div class="container">
			<div class="row profile">
				<c:import url="/front-end/member/memberHomeSidebar.jsp">
				</c:import>
				<!-- 首頁 -->
				<c:if test="${checkedSidbar == 'memHome'}">
					<c:import url="/front-end/member/memberHomeContent.jsp">
					</c:import>
				</c:if>
				<!-- 行事曆 -->
				<c:if test="${checkedSidbar == 'memCalendar'}">
					<c:import url="/front-end/member/memberCalendarContent.jsp">
					</c:import>
				</c:if>
				<!-- 收件夾 -->
				<c:if test="${checkedSidbar == 'memMail'}">
					<c:import url="/front-end/member/memberMailContent.jsp">
					</c:import>
				</c:if>
				<c:if test="${checkedSidbar == 'memMailDetail'}">
					<c:import url="/front-end/member/memberMailDetailContent.jsp">
					</c:import>
				</c:if>
				<!-- 我的動態 -->
				<c:if test="${checkedSidbar == 'memNF'}">
					<c:import url="/front-end/member/memberNFContent.jsp">
					</c:import>
				</c:if>
				<c:if test="${checkedSidbar == 'memNFDetail'}">
					<c:import url="/front-end/member/memberNFDetailContent.jsp">
					</c:import>
				</c:if>
				<!-- 社群管理 -->
				<c:if test="${checkedSidbar == 'memCommunity'}">
					<c:import url="/front-end/member/memberCommunityContent.jsp">
					</c:import>
				</c:if>
				<!-- 等級 -->
				<c:if test="${checkedSidbar == 'memLv'}">
					<c:import url="/front-end/member/memberLvContent.jsp">
					</c:import>
				</c:if>
				<!-- 成就 -->
				<c:if test="${checkedSidbar == 'memAch'}">
					<c:import url="/front-end/member/memberAchContent.jsp">
					</c:import>
				</c:if>
				<!-- 獎賞 -->
				<c:if test="${checkedSidbar == 'memReward'}">
					<c:import url="/front-end/member/memberRewardContent.jsp">
					</c:import>
				</c:if>
				<!-- 評價 -->
				<c:if test="${checkedSidbar == 'memStar'}">
					<c:import url="/front-end/member/memberStarContent.jsp">
					</c:import>
				</c:if>
				<!-- 基本資訊 -->
				<c:if test="${checkedSidbar == 'memInfo'}">
					<c:import url="/front-end/member/memberInfoContent.jsp">
					</c:import>
				</c:if>
				<!-- 個人頁面設定 -->
				<c:if test="${checkedSidbar == 'memSetting'}">
					<c:import url="/front-end/member/memberSettingContent.jsp">
					</c:import>
				</c:if>


			</div>
		</div>
		<br> <br>
	</div>
	<!--主頁面->

	<!-- BEGIN FOOTER -->
	<c:import url="/front-end/footer.jsp">
	</c:import>
	<!-- END FOOTER -->

	<!-- 共用Js -->
	<c:import url="/front-end/publicJS.jsp">
	</c:import>
	<!-- 共用Js -->
	<script
		src="<%=request.getContextPath()%>/HTML/src/member/js/validator.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.9.0/sweetalert2.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		//***************************確認按鈕***************************
		//暫時失效
		$('#ＸＸＸsubmitBtnＸＸＸ').on('click', function() {
			swal({
				title : '修改確認',
				text : "您將修改會員資料!",
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
				swal('成功!', '您已成功修改會員資料', 'success')
				resuit("changeInfo");
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		})
		//***************************確認按鈕***************************

		function resuit(e) {
			if (e == "changeInfo") {
				// alert("追隨");
				setTimeout(function() {
					/* memInfoForm.submit(); */
				}, 1000);
			}
		}
		//AJAX , 以下兩個用在信箱驗證與手機驗證

		/* ajax */
		var req;
		var isIE;
		function doCompletion() {
			console.log("ajax!!!!");
			var url = "memberInfo.do?verify=email";
			req = initRequest();
			req.open("GET", url, true);
			/* req.onreadystatechange = callback; */
			req.send(null);
		}

		function initRequest() {
			if (window.XMLHttpRequest) {
				if (navigator.userAgent.indexOf('MSIE') != -1) {
					isIE = true;
				}
				return new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				isIE = true;
				return new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		/* ajax */
		function verifyEMail() {
			swal({
				title : '郵件驗證',
				text : "您將驗證當前信箱!",
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
				swal('已寄送!', '請至信箱查收驗證信.', 'success')
				doCompletion();
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		}
		
		function memInfoChange() {
			var memInfoForm = document.getElementById("memInfoForm");
			swal({
				title : '修改確認',
				text : "您將修改當前會員資料!",
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
				memInfoForm.submit();
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		}
		
		function memSettingChange() {
			var memSettingForm = document.getElementById("memSettingForm");
			swal({
				title : '修改確認',
				text : "您將修改當前會員資料!",
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
				memSettingForm.submit();
			}, function(dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal('已取消', '點擊OK返回 :)', 'error')
				}
			})
		}

		function init() {
			var memInfoForm = document.getElementById("memInfoForm");
		}

		window.onload = init;
	</script>
</body>
<!-- END BODY -->

</html>