 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <div class="main">

        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
                
                    <form class="form-horizontal" method="post" ACTION="<%=request.getContextPath()%>/member/memberLogin.do">
                        <span class="heading">登入</span>
                        <div class="form-group">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="註冊時信箱" name="memEmail" value="${param.memEmail}">
                            <i class="fa fa-user"></i>
                        </div>                        <div class="form-group help">

                            <input type="password" class="form-control" id="inputPassword3" placeholder="密碼" name="memPw"  value="${param.memPw}">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group">
                            <!-- <button type="submit" class="btn btn-default">登入(假)</button> -->
                            <!-- <a href="memHome.html" class="btn btn-default">登入(假)</a> -->
                            <input type="submit" value="送出">
                            <input type="hidden" name="action" value="login">
                            <p>忘記密碼</p>
                            <a href="memForget.html" class="fa fa-question-circle"></a>
                        </div>
                        <!-- <div class="g-recaptcha" data-sitekey="6Lcm6y8UAAAAAGNzeX0Yxst8zgh1ntogvV4U1er_"></div> -->
                    </form>
                </div>
            </div>
        </div>


    </div>