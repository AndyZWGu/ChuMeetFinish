<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="<%=request.getContextPath()%>/HTML/src/member/css/login.css"
	rel="stylesheet">
	
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	
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
			<div class="row">
				<div class="col-md-offset-3 col-md-6">

					<form class="form-horizontal" method="post"
						ACTION="<%=request.getContextPath()%>/front-end/member/memberLogin.do">
						<span class="heading">登入</span>
						<div class="form-group">
							<input type="email" class="form-control" id="Email"
								placeholder="註冊時信箱" name="memEmail" value="${param.memEmail}">
							<i class="fa fa-user"></i>
						</div>
						<div class="form-group help">

							<input type="password" class="form-control" id="Pw"
								placeholder="密碼" name="memPw" value="${param.memPw}"> <i
								class="fa fa-lock"></i>
								<a href="memForget.html" class="fa fa-question-circle"></a>
						</div>
						<div class="form-group">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font color='red'> <c:forEach var="message"
									items="${errorMsgs}">
									<h5>${message}</h5>
								</c:forEach>
							</font>
						</c:if>
							<!-- <button type="submit" class="btn btn-default">登入(假)</button> -->
							<!-- <a href="memHome.html" class="btn btn-default">登入(假)</a> -->
								<!-- 						<div class="g-recaptcha"
								data-sitekey="6Lcm6y8UAAAAAGNzeX0Yxst8zgh1ntogvV4U1er_"></div> -->
							<input type="submit" value="登入" class="btn btn-default">
							<input type="hidden" name="action" value="login"> 
						</div>
					</form>
					<div class="">
						<input type="button" class="btn btn-sm" id="mem1" value="Master">
						<input type="button" class="btn btn-sm" id="mem2" value="健生中醫">
						<input type="button" class="btn btn-sm"  id="mem3" value="裕彰哥">
						<input type="button" class="btn btn-sm"  id="mem4" value="路過的阿顧">
						<input type="button" class="btn btn-sm"  id="mem5" value="小敏">
						<input type="button" class="btn btn-sm"  id="mem6" value="猛男">
					</div>
					<div class="">
						<input type="button" class="btn btn-sm"  id="mem7" value="印帥">
						<input type="button" class="btn btn-sm"  id="mem8" value="股票達人阿揚">
						<input type="button" class="btn btn-sm"  id="mem9" value="帥氣楷楷">
						<input type="button" class="btn btn-sm"  id="mem10" value="吳JAVA神">
						<input type="button" class="btn btn-sm"  id="mem11" value="剛哥隔壁老王">
						<input type="button" class="btn btn-sm"  id="mem12" value="MMD">
					</div>
					<div class="">
						
						<input type="button" class="btn btn-sm"  id="mem13" value="KAPPA">
					</div>
				</div>
			</div>
		</div>


	</div>

	<!-- BEGIN FOOTER -->
	<c:import url="/front-end/footer.jsp">
	</c:import>
	<!-- END FOOTER -->

	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
 
  	        <!-- 傳說小按鈕 -->
        <script type="text/javascript">
        function mem1() {
            document.getElementById('Email').value = 'adm@gmail.com';
            document.getElementById('Pw').value = 'Master';
        }
        function mem2() {
            document.getElementById('Email').value = 'admin@gmail.com';
            document.getElementById('Pw').value = 'admin';
        }
        function mem3() {
            document.getElementById('Email').value = 'ChuMeet@gmail.com';
            document.getElementById('Pw').value = 'ChuMeet';
        }
        function mem4() {
            document.getElementById('Email').value = 'member01@gmail.com';
            document.getElementById('Pw').value = 'member01';
        }
        function mem5() {
            document.getElementById('Email').value = 'member02@gmail.com';
            document.getElementById('Pw').value = 'member02';
        }
        function mem6() {
            document.getElementById('Email').value = 'member03@gmail.com';
            document.getElementById('Pw').value = 'member03';
        }
        function mem7() {
            document.getElementById('Email').value = 'member04@gmail.com';
            document.getElementById('Pw').value = 'member04';
        }
        function mem8() {
            document.getElementById('Email').value = 'member05@gmail.com';
            document.getElementById('Pw').value = 'member05';
        }
        function mem9() {
            document.getElementById('Email').value = 'member06@gmail.com';
            document.getElementById('Pw').value = 'member06';
        }
        function mem10() {
            document.getElementById('Email').value = 'member07@gmail.com';
            document.getElementById('Pw').value = 'member07';
        }
        function mem11() {
            document.getElementById('Email').value = 'member08@gmail.com';
            document.getElementById('Pw').value = 'member08';
        }
        function mem12() {
            document.getElementById('Email').value = 'member09@gmail.com';
            document.getElementById('Pw').value = 'member09';
        }
        function mem13() {
            document.getElementById('Email').value = 'member10@gmail.com';
            document.getElementById('Pw').value = 'member10';
        }

        function init() {
            var member01= document.getElementById("mem1");
            var member02= document.getElementById("mem2");
            var member03= document.getElementById("mem3");
            var member04= document.getElementById("mem4");
            var member05= document.getElementById("mem5");
            var member06= document.getElementById("mem6");
            var member07= document.getElementById("mem7");
            var member08= document.getElementById("mem8");
            var member09= document.getElementById("mem9");
            var member10= document.getElementById("mem10");
            var member11= document.getElementById("mem11");
            var member12= document.getElementById("mem12");
            var member13= document.getElementById("mem13");
            member01.addEventListener("click", mem1, false);
            member02.addEventListener("click", mem2, false);
            member03.addEventListener("click", mem3, false);
            member04.addEventListener("click", mem4, false);
            member05.addEventListener("click", mem5, false);
            member06.addEventListener("click", mem6, false);
            member07.addEventListener("click", mem7, false);
            member08.addEventListener("click", mem8, false);
            member09.addEventListener("click", mem9, false);
            member10.addEventListener("click", mem10, false);
            member11.addEventListener("click", mem11, false);
            member12.addEventListener("click", mem12, false);
            member13.addEventListener("click", mem13, false);
        }
        window.onload = init;
        </script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  	
</body> 
  	
</body>
<!-- END BODY -->

</html>