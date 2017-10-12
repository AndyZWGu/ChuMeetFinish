<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubAlbum.model.*"%>

<%
ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");

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

    <!-- Body BEGIN -->

    <body class="chumeet">

        <!-- BEGIN TOP BAR 2-->
        <div class="pre-header">
            <div class="container">
                <div class="row">
                    <!-- BEGIN TOP BAR LEFT PART -->
                    <div class="col-md-6 col-sm-6 additional-shop-info">
                        <ul class="list-unstyled list-inline">
                            <li><span><strong>ChuMeet</strong></span></li>
                            <li><span>Nice to meet you <i class="fa fa-smile-o" aria-hidden="true"></i></span> </li>
                            <li><a href="act/actStart.html"><span class="topst"> 開始揪團吧！ </span></a></li>
                        </ul>


                    </div>

                    <!-- END TOP BAR LEFT PART -->
                    <!-- BEGIN TOP BAR MENU -->
                    <div class="col-md-6 col-sm-6 additional-nav">
                        <ul class="list-unstyled list-inline pull-right">
                            <li><span>哈囉，剛哥！　</span> <a href="../member/setting.html"><i class="fa fa-cog" aria-hidden="true"></i>設定</a></li>
                            <li><a href="member/logout.html"><i class="fa fa-sign-out" aria-hidden="true"></i>登出</a></li>
                            <li><a href="member/mail.html"><i class="fa fa-envelope-o" aria-hidden="true"></i>消息</a></li>
                        </ul>
                    </div>
                    <!-- END TOP BAR MENU -->
                </div>
            </div>
        </div>
        <!-- END TOP BAR 2-->





        <!-- BEGIN HEADER -->
        <div class="header">
            <div class="container">
                <a class="site-logo" href="../index.html"><img src="../assets/LOGO/ChuMeet_NavLogo_25.png" alt="ChuMeet"></a>

                <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>


                <!-- BEGIN NAVIGATION -->
                <div class="header-navigation pull-right font-transform-inherit">
                    <ul>
                        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="member/memNF.html"> 會員中心 </a></li>
                        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="act/act.html"> 活動廣場 </a>
                            <ul class="dropdown-menu">
                                <li><a href="act/act.html">我的活動</a></li>
                                <li><a href="act/act.html">揪咪推薦</a></li>
                                <li><a href="act/act.html">政府藝文活動</a></li>
                                <li><a href="act/act.html">周末特調</a></li>
                                <li><a href="act/act.html">熱門標籤</a></li>
                                <li><a href="act/act.html">活動分類</a></li>
                                <li><a href="act/act.html">揪揪地圖</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="club/club_ALL.html"> 社團大廳 </a>
                            <ul class="dropdown-menu">
                                <li><a href="club/club_ALL.html">我的社團</a></li>
                                <li><a href="club/club_ALL.html">社團推薦</a></li>
                                <li><a href="club/club_ALL.html">社團地圖</a></li>
                            </ul>

                        </li>

                        <!-- BEGIN TOP SEARCH -->
                        <li class="menu-search">
                            <span class="sep"></span>
                            <i class="fa fa-search search-btn"></i>
                            <div class="search-box">
                                <form action="#">
                                    <div class="input-group">
                                        <input type="text" placeholder="Search" class="form-control">
                                        <span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">Search</button>
                    </span>
                                    </div>
                                </form>
                            </div>
                        </li>
                        <!-- END TOP SEARCH -->

                    </ul>
                </div>
                <!-- END NAVIGATION -->
            </div>
        </div>

        <!-- Header END -->

        <!--主頁面要修改的都在這下面-->
        <div class="container">
            <div class="row">
                <div class=" col-xs-offset-0 col-md-12 col-sm-12">
                    <div class="panel panel-primary" style="margin:20px;">
                        <div class="panel-heading">
                            <h3 class="panel-title">新增相片</h3>
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

                        
                            <FORM METHOD="post" ACTION="clubImg.do" name="form2" enctype="multipart/form-data">
							<!--建立人ID=進入的會員參數 -->
							
							    <div class="form-group">
                                    <input type="hidden" class="form-control input-sm" id="clubID" name="clubID" 
                                    value="${clubVO.clubID}" /> 
 
                                </div>
							

                       	        <div class="form-group">
                                    <input type="hidden" class="form-control input-sm" id="memID" name="memID" 
                                    value="${clubVO.clubID}" /> 
                                </div>


                                							
                       	        <div class="form-group">
                                    <input type="hidden" class="form-control input-sm" id="clubAlbumID" name="clubAlbumID" 
                                    value="${clubAlbumVO.clubAlbumID}" /> 
 
                                </div>
                       	

                                <div class="form-group col-md-12 col-sm-12">
                                    <label for="clubImg">社團照片*</label>
                                    <input type="file" id="clubImg" name="clubImg">
                                    <p class="help-block">請上傳一張照片</p>
                                </div>
                                
                                <div class="form-group col-md-12 col-sm-12">
                                    <label for="clubImgContent">相片內容</label>
                                    <input type="text" class="form-control input-sm" id="clubImgContent" name="clubImgContent" 
                                    value="${clubImgVO.ClubImgContent}" /> 
                                </div>


                                
                                <div class="form-group col-md-12 col-sm-12 text-center">
                                    <input type="submit" value="新增" class="btn btn-primary">
                                    <input type="hidden" name="action" value="getImgAdd">
                                </div>
                                <div class="form-group col-md-12 col-sm-12 text-right"><input type="button" value="傳說小按鈕" class="btn btn-danger btn-sm" id="lenged"></div>
                            </form>
                        </div>
                    </div>
                    <!--=============================================================================================-->
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