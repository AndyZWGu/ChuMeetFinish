<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubNews.model.*"%>

<%


ClubVO clubVO = (ClubVO) request.getAttribute("clubVO");
ClubNewsService clubNewsSvc = new ClubNewsService();
ClubNewsVO clubNewsVO = (ClubNewsVO) request.getAttribute("clubNewsVO");

%>


<!DOCTYPE html>
<html>

<!-- Head BEGIN --><head>
<meta charset="utf-8">
<title>ChuMeet! 活動分類</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="../chumeet_icon.ico">

<!-- Fonts START -->
<!--  <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">-->
<!--  <link href="http://fonts.googleapis.com/earlyaccess/notosanstc.css" rel="stylesheet" type="text/css">-->


<!-- Fonts END -->
<!-- 全站統一CSS 不要亂動，動了要跟全組說 -->

<!-- Global styles START -->
<link href="../assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="../assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/pages/css/base.css" rel="stylesheet">
<!-- Global styles END -->

<!-- Page level plugin styles START -->
<link href="../assets/plugins/owl.carousel/assets/owl.carousel.css" rel="stylesheet">
<link href="../assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet">
<link href="../assets/pages/css/animate.css" rel="stylesheet">
<!-- Page level plugin styles END -->

<!-- Theme styles START -->
<link href="../assets/pages/css/components.css" rel="stylesheet">
<link href="../assets/corporate/css/style.css" rel="stylesheet">
<link href="../assets/corporate/css/style-responsive.css" rel="stylesheet">
<link href="../assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color">
<!-- Theme styles END -->

<!--  my styles  -->
<!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
<link href="../src/club/css/act.css" rel="stylesheet">
<link href="../src/club/css/clubAlbum.css" rel="stylesheet">
<!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
<!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->

<link href="../src/act/css/actPOI.css" rel="stylesheet">
</head>



<!--主頁面要修改的都在這下面-->

<!-- BEGIN CONTENT -->

 <div class="content">
<div  class="col-xs-12 col-sm-6">      
<fORM METHOD="post" ACTION="clubNews.do" name="form3">
      <div class="post-comment padding-top-40">
        <h3>更新公告</h3>
        
								<!-- 社團ID -->
								<div class="form-group col-md-12 col-sm-12">
                                    <input type="hidden" class="form-control input-sm" id="clubID" name="clubNewsID" 
                                    value="${clubNewsVO.clubNewsID}" /> 
                                </div>  
								
                       	        <div class="form-group col-md-12 col-sm-12">
                                    <input type="hidden" class="form-control input-sm" id="clubID" name="clubID" 
                                    value="${clubVO.clubID}" /> 
                                </div>  
                                
                                <div class="form-group col-md-12 col-sm-12">
                                    <input type="hidden" class="form-control input-sm" id="memID" name="memID" 
                                    value="${memVO.memID }" /> 
                                </div>       
        
					          <div class="form-group">
					          	公告標題:<input type="text"  name="clubNewsTitle" value="${clubNewsVO.clubNewsTitle}"><br><br>
					            <textarea class="form-control" rows="8" name=clubNewsContent >${clubNewsVO.clubNewsContent}</textarea>
					          </div>
          
							  <div>
								<input type="hidden"  name="clubID" value="${clubVO.clubID}">
								<input type="hidden"  name="action" value="getOneNews_For_Update">
								<input type="submit" value="送出" class="btn btn-block btn-primary">						
							 </div>
    
    </div>    
</fORM>
</div>      
</div>
        


<!--主頁面要修改的都在這上面-->


   

<!-- Load javascripts at bottom, this will reduce page load time --> 
<!-- BEGIN CORE PLUGINS (REQUIRED FOR ALL PAGES) --> 
<!--[if lt IE 9]>
    <script src="assets/plugins/respond.min.js"></script>
    <![endif]--> 
<!--<script src="../assets/plugins/jquery.wow.min.js" type="text/javascript"></script>-->
<!--<script src="../assets/plugins/components/wow.min.js" type="text/javascript"></script>-->
<script src="../assets/plugins/jquery.min.js" type="text/javascript"></script> 
<!--<script src="../assets/plugins/jquery-migrate.min.js" type="text/javascript"></script> -->
<script src="../assets/plugins/jquery.wow.min.js" type="text/javascript"></script> 
<script src="../assets/plugins/jquery.smooth-scroll.js" type="text/javascript"></script> 
<script src="../assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="../assets/corporate/scripts/back-to-top.js" type="text/javascript"></script> 
<!-- END CORE PLUGINS --> 

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) --> 
<!--@@@@@@@@@@@@@@@@@@@@@@@@@ 頁面專屬JS，JS擺在最後有益身心健康，可以刪改 @@@@@@@@@@@@@@@@@@@@@@@@-->
<script>
//我的社團頁面
// 	$(document).ready(function() {
// 	$('.thumbnail').click(function(){
//     $('.modal-body').empty();
//   	var title = $(this).parent('a').attr("title");
//   	$('.modal-title').html(title);
//   	$($(this).parents('div').html()).appendTo('.modal-body');
//   	$('#myModal').modal({show:true});
// });
// });
	
	
	
	
	
</script>
<!--<script src="../assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script> pop up  -->
<script src="../assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script> 
<script src="../assets/corporate/scripts/layout.js" type="text/javascript"></script> 
<script type="text/javascript">
        jQuery(document).ready(function() {
            Layout.init();    
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
            Layout.initNavScrolling();
        });
    </script> 
<script src="../assets/plugins/components/wow.min.js" type="text/javascript"></script> 

<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>