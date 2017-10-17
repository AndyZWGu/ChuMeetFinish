<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="java.util.*"%>
<html>
<%

//廣告
AdService ad = new AdService();
List <AdVO> adList = (List<AdVO>)ad.getAll();
pageContext.setAttribute("adList", adList);

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
  <link href="<%=request.getContextPath()%>/HTML/src/index/index-byAGu.css" rel="stylesheet">
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

  <!--主頁面要修改的都在這下面-->
      <!--主頁面要修改的都在這下面-->
<div class="content">


    <div class="main">
      <div class="container">
        <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
            <li>網站資訊</li>
            <li class="active">廣告優惠</li>
        </ul>
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
        <!-- BEGIN SIDEBAR -->
          <div class="sidebar col-md-3 col-sm-3">
            <ul class="list-group margin-bottom-25 sidebar-menu">
            <c:forEach items="${adList}" var="adList">
            	<li class="list-group-item clearfix"><a href="javascript:;"><i class="fa fa-angle-right"></i>${adList.adName}</a></li>
            	
            	<h1>${adList.adName}</h1>
            <div class="content-page">
              <p>${adVO.adContent}</p>
              <h3>廣告加入時間:<fmt:formatDate value="${adVO.adDate}" pattern="yyyy/MM/dd hh:mm" /></h3>
            </div>
            
            
            </c:forEach>
            </ul>
          </div>
          <!-- END SIDEBAR -->

          <!-- BEGIN CONTENT -->
			<div class="col-md-9 col-sm-9">
            <img src="<%=request.getContextPath()%>/front-end/info/adImg.do?adID=1" class="img-responsive" alt="">
          </div>
          <!-- END CONTENT -->
        </div>
        <!-- BEGIN SIDEBAR & CONTENT -->
      </div>
    </div>


</div>
    <!--======================================================================================================-->

    <!--======================================================================================================-->

    <!--/#food-menu-->
    <!--======================================================================================================-->


    <!-- BEGIN RECENT WORKS -->

    <!-- END RECENT WORKS -->

    <!-- END BLOCKQUOTE BLOCK -->
    <!--======================================================================================================-->
    <!-- BEGIN TABS AND TESTIMONIALS -->
    
    <!-- END TABS AND TESTIMONIALS -->
    <!--======================================================================================================-->
    <!--================================================================-->

  
  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->

	<!-- 共用Js -->
<!-- BEGIN CORE PLUGINS (REQUIRED FOR ALL PAGES) -->
  <!--[if lt IE 9]>
    <script src="assets/plugins/respond.min.js"></script>
    <![endif]-->
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.wow.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.smooth-scroll.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/back-to-top-outer.js " type="text/javascript "></script>
  <!-- END CORE PLUGINS -->

  <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
  <!--@@@@@@@@@@@@@@@@@@@@@@@@@ é é¢å°å±¬JSï¼JSæºå¨æå¾æçèº«å¿å¥åº·ï¼å¯ä»¥åªæ¹ @@@@@@@@@@@@@@@@@@@@@@@@-->
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
  <!-- pop up -->
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/layout.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/pages/scripts/bs-carousel.js" type="text/javascript"></script>
  <script type="text/javascript">
    //	ä¸é¢éé¨åå¯ä»¥å¢å ï¼å¥åªææ©
    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();
        Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
        Layout.initNavScrolling();
    });
  </script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/components/wow.min.js" type="text/javascript"></script>

  	<!-- 共用Js -->
  	
</body>
<!-- END BODY -->

</html>
