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
							<input type="email" class="form-control" id="inputEmail3"
								placeholder="註冊時信箱" name="memEmail" value="${param.memEmail}">
							<i class="fa fa-user"></i>
						</div>
						<div class="form-group help">

							<input type="password" class="form-control" id="inputPassword3"
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
  	
</body>
<!-- END BODY -->

</html>