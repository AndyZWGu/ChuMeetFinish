<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<title>ChuMeet_Back</title>

<!-- Google Fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/animate.css">
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>



	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo"></span>
			</h1>
		</div>
		<form class="form-horizontal" method="post"
			ACTION="<%=request.getContextPath()%>/back-end/backLogin.do">
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>後端登入</h2>
			</div>
			<div class="form-group">
				<label for="username">帳號</label>
				<input type="text" placeholder ="帳號" name="adminMail">
			</div>
			
			<div class="form-group help">
				<label for="password">密碼</label>
				<input type="password" placeholder ="密碼" name="adminPW">
				
			</div>
			<div>
			

				<c:if test="${not empty errorMsgs}">
					<font color='red'><c:forEach var="message"
							items="${errorMsgs}">
						</c:forEach> </font>
					<H5>${message}</H5>
				</c:if>
				<input type="submit" value="登入">
				

				<br />
			</div>
		</div>
		</form>

	</div>
</body>

<script>
	$(document).ready(function() {
		$('#logo').addClass('animated fadeInDown');
		$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>

</html>