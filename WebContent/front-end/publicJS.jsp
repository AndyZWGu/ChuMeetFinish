<!-- BEGIN CORE PLUGINS (REQUIRED FOR ALL PAGES) -->
  <!--[if lt IE 9]>
    <script src="assets/plugins/respond.min.js"></script>
    <![endif]-->
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.wow.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.smooth-scroll.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/back-to-top-outer.js " type="text/javascript "></script>
  <!-- END CORE PLUGINS -->

  <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
  <!--@@@@@@@@@@@@@@@@@@@@@@@@@ 頁面專屬JS，JS擺在最後有益身心健康，可以刪改 @@@@@@@@@@@@@@@@@@@@@@@@-->
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
  <!-- pop up -->
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/layout.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/pages/scripts/bs-carousel.js" type="text/javascript"></script>
  <script type="text/javascript">
    //	下面這部分可以增加，別刪感恩
    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();
        Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
        Layout.initNavScrolling();
    });
  </script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/components/wow.min.js" type="text/javascript"></script>