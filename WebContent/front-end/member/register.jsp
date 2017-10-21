<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<!-- Head BEGIN -->
	<!-- 共用Header -->
  <c:import url="/front-end/head.jsp">
</c:import>
<title>ChuMeet! 註冊頁面</title>
	<!-- 共用Header -->
<!--  my styles  -->
<!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
<!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
<!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
<link href="<%=request.getContextPath()%>/HTML/src/member/css/register.css"
	rel="stylesheet">
	
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="">
	<c:import url="/front-end/userHeader.jsp">
	</c:import>
	<!-- Header Start -->
	<c:import url="/front-end/header.jsp">
	</c:import>
	<!-- Header END -->

	<!--主頁面要修改的都在這下面-->
	<div class="">

        <div class="container">
            <div class="row">
                <div class=" col-xs-offset-0 col-md-12 col-sm-12">
                    <div class="panel panel-primary" style="margin:20px;">
                        <div class="panel-heading">
                            <h3 class="panel-title">加入揪咪</h3>
                        </div>
                        <div class="panel-body">
                            <form onsubmit="return check()" method="post" action="<%=request.getContextPath()%>/front-end/member/memberRegister.do" enctype="multipart/form-data" data-toggle="validator" role="form">
                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="Email">帳號*</label>
                                    <input type="email" class="form-control input-sm" id="Email" name="memEmail" placeholder="填寫註冊信箱" required>
                                </div>
                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="Name">暱稱*</label>
                                    <input type="text" class="form-control input-sm" id="Name" name="memName" placeholder="作為使用者名稱" required>
                                </div>
                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="Pw">密碼*</label>
                                    <input type="password" class="form-control input-sm" id="Pw" name="memPw" placeholder="密碼" required>
                                </div>

                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="Phone">手機</label>
                                    <input type="text" class="form-control input-sm" id="Phone" name="memPhone" placeholder="手機" required>
                                </div>

                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="Pw2">確認密碼*</label>
                                    <input type="password" class="form-control input-sm" id="Pw2" name="memPw2" placeholder="確認密碼" data-match="#Pw" required>
                                </div>

                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="LocBorn">出生地</label>
                                    <select class="form-control" id="LocBorn" name="memLocBorn">
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

                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="photo">大頭照*</label>
                                    <input type="file" id="photo" name="memAvatar" required>
                                    <p class="help-block">請上傳一張照片作為您的個人大頭照</p>
                                </div>

                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="LocLive">居住地</label>
                                    <select class="form-control" id="Loclive" name="memLocLive" required>
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
                                <div class="form-group  col-md-6 col-sm-6">
                                    <label for="BD">生日</label>
                                    <input type="date" class="form-control input-sm" id="BD" name="memBD" placeholder="生日" required>
                                </div>
                                <div class="form-group  col-xs-offset-6 col-md-6 col-sm-6">
                                    <label for="memGender">性別</label>
                                    <hr>
                                    <label class="radio-inline">
                                  <input type="radio" name="memGender" value="1" checked required> 男生<br>
                                </label>
                                    <label class="radio-inline">
                                  <input type="radio" name="memGender" value="0" required> 女生<br>
                                </label>
                                </div>
                                <div class="form-group col-md-12 col-sm-12">
                                    <label for="memInt">自我介紹</label>
                                    <textarea class="form-control input-sm" id="memInt" name="memInt" rows="8"></textarea>
                                </div>
                                <div class="form-group col-md-12 col-sm-12 text-center">
                                   	<h4>揪咪是一個友善熱情的網站，請同意我們的使用條款</h4>
                                    <input id="ckfinal" type="checkbox" required> 我同意<a href="#"> 《使用條款》 </a>
                                    <p />
                                	<input type="submit" value="註冊" class="btn btn-primary">
									<input type="hidden" name="action" value="register"> 
                                    <div class="form-group col-md-12 col-sm-12 text-center"><a href="<%=request.getContextPath()%>/front-end/member/memberHome.do">已經有帳號?</a></div>

                                </div>
                                <div class="form-group col-md-12 col-sm-12 text-right"><input type="button" value="傳說小按鈕" class="btn btn-danger btn-sm" id="lenged"></div>
                            </form>
                        <%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font color='red'> <c:forEach var="message"
									items="${errorMsgs}">
									<h5>${message}</h5>
								</c:forEach>
							</font>
						</c:if>
                        </div>
                    </div>
                    <!--=============================================================================================-->
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
  	<script src="<%=request.getContextPath()%>/HTML/src/member/js/validator.min.js"></script>
  	        <!-- 傳說小按鈕 -->
        <script type="text/javascript">
        function magic() {
            document.getElementById('Email').value = 'AGu@gmail.com';
            document.getElementById('Name').value = '顧宅';
            document.getElementById('Pw').value = 'Agu531328';
            document.getElementById('Pw2').value = 'Agu531328';
            document.getElementById('Phone').value = '0988666666';
            document.getElementById('BD').value = '1993-09-05';
            document.getElementById('memInt').value = '第一次加入啾咪,希望可以認識大家呀！';
        }

        function check() {
            if (!document.getElementById('ckfinal').checked) {
                swal("請先同意我們的使用條款在註冊,感謝！");
                return;
            }
        }

        function init() {
            var lengedBtn = document.getElementById("lenged");
            var submitBtn = document.getElementById("register");
            lengedBtn.addEventListener("click", magic, false);
            submitBtn.addEventListener("click", check, false);
        }
        window.onload = init;
        </script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  	
</body>
<!-- END BODY -->

</html>