<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>

<%@ page import="com.clubAlbum.model.*"%>

<%
ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
//找尋單一社團成員的資料
%>

<!-- Head BEGIN -->

<head>
    	<!-- 共用Header -->
  <c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
  <!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
  <!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
  <!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
  <!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/slider.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/HTML/src/club/css/clubfirst.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/HTML/src/club/css/act.css" rel="stylesheet">

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
<div class="content">
        <!--主頁面要修改的都在這下面-->
        <div class="container">
            <div class="row">
                <div class=" col-xs-offset-0 col-md-12 col-sm-12">
                    <div class="panel panel-primary" style="margin:20px;">
                        <div class="panel-heading">
                            <h3 class="panel-title">新增相簿</h3>
                        </div>
                        <div class="panel-body">
                        
                        
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

                        
                            <FORM METHOD="post" ACTION="clubAlbum.do" name="form2" enctype="multipart/form-data">
							<!--建立人ID=進入的會員參數 -->
							

                       	        <div class="form-group">
                                    <input type="hidden" class="form-control input-sm" id="memID" name="memID" 
                                    value="${memVO.memID}" /> 
                                </div>


                                							<!--建立人ID=進入的會員參數 -->
                       	        <div class="form-group">
                                    <input type="hidden" class="form-control input-sm" id="clubID" name="clubID" 
                                    value="${clubVO.clubID}" /> 
 
                                </div>
                       	
                       	

                                <div class="form-group col-md-12 col-sm-12">
                                    <label for="clubAlbumName">社團相簿名稱*</label>
                                    <input type="text" class="form-control input-sm" id="clubAlbumName" name="clubAlbumName" 
                                    value="${clubAlbumVO.ClubAlbumName}" /> 
                                </div>


<!-- 應該從這也可以上傳相片,等基本做完再來用 -->
<!--                                 <div class="form-group col-md-12 col-sm-12"> -->
<!--                                     <label for="photo">社團照片*</label> -->
<!--                                     <input type="file" id="photo" name="clubPic"> -->
<!--                                     <p class="help-block">請上傳一張社團照片</p> -->
<!--                                 </div> -->


                                
                                <div class="form-group col-md-12 col-sm-12 text-center">
                                    <input type="submit" value="新增" class="btn btn-primary">
                                    <input type="hidden" name="action" value="getAlbumAdd">
                                </div>
                                <div class="form-group col-md-12 col-sm-12 text-right"><input type="button" value="傳說小按鈕" class="btn btn-danger btn-sm" id="lenged"></div>
                            </form>
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

        <!-- END PAGE LEVEL JAVASCRIPTS -->
        <!-- 傳說小按鈕 -->
        <script type="text/javascript">
            function magic() {
                document.getElementById('Email').value = 'ChuMeetMaster@gmail.com';
                document.getElementById('Name').value = '揪咪大師';
                document.getElementById('Pw').value = 'ChuMeet';
                document.getElementById('Pw2').value = 'ChuMeet';
                document.getElementById('Phone').value = '3345678';
                document.getElementById('LocBorn').value = '台南';
                document.getElementById('LocLive').value = '台北';
                document.getElementById('BD').value = '2017-09-05';
                document.getElementById('memInt').value = '馬克·艾略特·祖克柏（英語：Mark Elliot Zuckerberg，1984年5月14日－）出生於美國紐約州白原市，是知名的社群網站Facebook的創始人、董事長兼執行長，同時也是一名軟體設計師。Facebook是由他和哈佛大學的同學達斯汀·莫斯科維茲、愛德華多·薩維林、克里斯·休斯於2004年共同創立，被譽爲Facebook教主[5][6]。2010年12月，祖克柏被《時代雜誌》評選為「2010年年度風雲人物」[7]。2014年10月24日，他以352億美元成功打入富比士全球富豪榜2014的世界第10大富豪，成為歷史上最年輕打入世界前10大的億萬富豪。2016年5月27日，他以516億美元成功打入富比士全球富豪榜2016的世界第5大富豪，成為歷史上最年輕打入世界前5大的億萬富豪。祖克柏持有400萬股Facebook公司的A股，另有4.223億股B股。B股控股權為前者十倍；兩者合共佔Facebook控股權54%';
            }

            function init() {
                var btn = document.getElementById("lenged");
                btn.addEventListener("click", magic, false);
            }
            window.onload = init;
        </script>

    </body>
    <!-- END BODY -->

</html>