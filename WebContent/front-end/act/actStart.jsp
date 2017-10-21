<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="java.util.*"%>

<html><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../chumeet_icon.ico">
    <title>ChuMeetStart</title>
    
<!-- 共用Header -->

<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">
 <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/base.css" rel="stylesheet">
  <!-- Global styles END -->

  <!-- Page level plugin styles START -->
  <link href="<%=request.getContextPath()%>/HTML/assets/plugins/owl.carousel/assets/owl.carousel.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/animate.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/jquery-ui-1.12.1.custom/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/dp/jquery-ui.multidatespicker.css" rel="stylesheet">

 <!-- Theme styles START -->
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/components.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/corporate/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/corporate/css/style-responsive.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color">
  <!-- Theme styles END -->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Custom styles -->
 <link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/mtf.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- Fonts START -->
<!-- Theme styles END -->
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/act/act_assets/trumbowyg/ui/trumbowyg.min.css">
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actStart.css" media="screen" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBiHKk-5rL3ARP2SmZvtcQ5poVS97N_7A&libraries=places"></script>
						   <style>
						   .group{
							   text-align: left;
						   }
						   .group .h4title{
							   color: #2E86AB;
							   font-size: 1.4em;
						   }
						   .group .ipcnt{
							   border: 0; 
							   border-bottom: 1px solid #C2E812;
							   padding-left: 1em;
							   margin-top: .5em;
						   }
						   
						   .group .ipcomp{
							   padding-left: 1em;
							   padding-top: .5em;
						   }
						   .err{
							   color:#F93943;
							   
						   }
					   </style>		
</head>
<!-- Body BEGIN -->
<body class="corporate">

<  <!-- Header Start -->
  <c:import url="../../front-end/header.jsp">
</c:import>
  <!-- Header END -->


<!--主頁面要修改的都在這下面-->




<div class="center-block" style="max-width: 1150px; text-align: center;">
<!-- BEGIN CONTENT -->
<!-- MultiStep Form -->
<form id="msform"  action="<%=request.getContextPath()%>/front-end/act/act.do?action=insert" method="post" enctype="multipart/form-data">
            <!-- progressbar -->
            <ul id="progressbar">
                <li class="active">基本資料</li>
                <li>地點與分類</li>
                <li>時間</li>
                <li>詳細內容</li>

                <li>創建活動</li>
            </ul>
            
            
            
            <div class="container" style="min-height: 750px;">
  

                                               
 


<!--1st page start  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            -->            
            <fieldset>
				<div class="startRight">
               <img src="act_assets/img/start/bg3.jpg" class="img-responsive">
               </div>
				<div class="startLeft">
                <h1 class="fs-title">活動基本資料</h1>
                <h3 class="fs-subtitle">Your presence on the social network</h3>
                <div class="wd80">
						<div class="wrapper ">
								<div class="group">
								
								<span class="h4title"><i class="fa fa-caret-right" aria-hidden="true"></i> 活動名稱：</span>
						  		<span id="error1" class="err"></span>
							  	<input class="ipcnt" size="45em" id="aName" type="text" required="required"/>

								

<!--cover start-->
<br><br>

<span class="h4title"><i class="fa fa-caret-right" aria-hidden="true"></i> 選擇活動封面：</span><br><br>
<input type="file" id="files" name="actIMG"/>
<img id="image"  class="img-responsive margin-bottom-30 img-rounded"/>
						  <!--						  end of cover-->
					</div>
               </div></div></div>
      	   <input type="button" name="next" class="next action-button" value="下一步"/>
                
            </fieldset>
            
            
<!--1st page end  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            -->



            
            <!-- fieldsets -->
<!--2nd page start@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
            <fieldset>
				<div class="startRight">
               <img src="act_assets/img/start/bg4.jpg" class="img-responsive">
               </div>
				<div class="startLeft">
                <h1 class="fs-title">地點與分類</h1>
                <h3 class="fs-subtitle">Your presence on the social network</h3>
                								

				   <div class="wrapper">     
 
								<div class="group margin-bottom-20">
								<span class="h4title"><i class="fa fa-caret-right" aria-hidden="true"></i> 舉辦地點為</span><span id="error3" class="err"></span>
							  	<input class="ipcnt" size="45em" id="showAddress" type="text" required="required" readonly/>

							  	
									<div class="ipcomp margin-top-10;" style="color: dimgrey"><small>請選擇：</small>
							  	<span id="onlinebtn" class="btn btn-sm btn-default">線上活動</span>
									<a href="#myModal" class="trigger-btn" data-toggle="modal"><span class="btn btn-sm btn-default">實體活動</span></a>
									</div>
								</div>				

			
<!-- Modal HTML -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog modal-login">
		<div class="modal-content">
			<div class="modal-header">
                <div class="avatar"><img src="act_assets/img/start/map.png" class="img-responsive"></div>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">



      <input id="geocomplete" style="opacity: 0" type="text" placeholder="xxx" />
	<div class="input-group">
      <input id="inputmap" class="form-control ui-front" type="text" placeholder="請輸入地址" value="TibaMe" />
         <div class="input-group-btn">
      			<input id="find" class="form-control" type="button" value="定位" />
   		 </div>
	</div>
         <div class="map_canvas"></div>
         
         <button id="mapok" type="button" class="btn btn-info margin-top-10">地點OK</button>
         
<!--         actLocName-->
        <input id="locName" name="name" type="hidden"value="">				
        <input name="point_of_interest" type="hidden"value="">
        <input id="lat" name="lat" type="hidden"value="">
        <input id="lng" name="lng" type="hidden"value="">


        <input name="location" type="hidden"value="">
        <input name="location_type" type="hidden"value="">
<!--        actAdr-->
        <input id="loc" name="formatted_address" type="hidden"value="">
        <input name="bounds" type="hidden"value="">
        <input name="viewport" type="hidden"value="">
        <input name="route" type="hidden"value="">
        <input name="street_number" type="hidden"value="">
<!--post-->
        <input id="post" name="postal_code" type="hidden"value="">
        <input name="locality" type="hidden"value="">
        <input name="sublocality" type="hidden"value="">
        <input name="country" type="hidden"value="">
        <input name="country_short" type="hidden"value="">
        <input name="administrative_area_level_1" type="hidden"value="">
        <input name="place_id" type="hidden"value="">
        <input name="id" type="hidden"value="">
        <input name="reference" type="hidden"value="">
        <input name="url" type="hidden"value="">
        <input name="website" type="hidden"value="">
</div>		
				
			</div>
		</div>
	
 
							
</div>	   

						   	<br>
						   	<div class="group">
								<span class="h4title"><i class="fa fa-caret-right" aria-hidden="true"></i> 活動分類有...</span><span id="error4" class="err"></span>
							  	<div id="poiappend" class="event-tags" style="text-align: left; margin-top: 0"></div>
							  	</div>
								<hr>
							   <div id="showcat" class="event-tags_st" style="text-align: left;">
					   				<input type="hidden"id="poilists">
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>音樂</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>戲劇</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>親子</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>展覽</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>講座</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>電影</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>運動</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>學習</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>餐聚</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>研習課程</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>藝文</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>電競</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>線上活動</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>戶外</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>寵物</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>其他</small></span></a></li></span>
    					
						  
						
              		  
						   </div>	
		
		
              		  </div></div>

                <input type="button" name="next" class="next action-button" id="page2" value="下一步"/>
            </fieldset>
<!--2nd page end@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->       




                                                                

     

<!--3rd page start@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->
            <fieldset>
				<div class="startRight">
               		<img src="act_assets/img/start/bg2.jpg" class="img-responsive">
               </div>
				<div class="startLeft">
 				  <h1 class="fs-title">時間設定</h1>
                <h3 class="fs-subtitle">Your presence on the social network</h3>
						<div class="group">
								<span class="h4title"><i class="fa fa-caret-right" aria-hidden="true"></i> 舉辦時間</span>
							<p></p>
							<div style="margin-left: 1em;">
								<div class="form-inline">
								  <div class="form-group">
									<input type="datetime-local" min="2017-10-19" id="aStartDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-20T15:00:00">
									  </div>
									<div class="form-group">
									至<br><input type="datetime-local" min="2017-10-19" id="aEndDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-20T15:00:00">
									</div>
									</div>
							</div>
            		 	 	<br>
								<span class="h4title"><i class="fa fa-caret-right" aria-hidden="true"></i> 重複設定</span><span id="error5" class="err"></span>
            		 	 	<p />
             		 	 	<span class="margin-left-20"><button type="button" class="btn btn-sm btn-default" id="onetime">一次</button>
             		 	 	<button class="btn btn-sm btn-default" type="button" data-toggle="modal" data-target="#timeModal" id="rept">重複</button></span>
							
							<!-- Modal -->
							<div id="timeModal" class="modal fade" role="dialog">
							  <div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">請選擇重複日</h4>
								  </div>
								  <div class="modal-body">
									<p><input id="mdp-demo"></p>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reptclose">關閉</button>
								  </div>
								</div>

							  </div>
							</div>
          					 <p></p>
							<div id="showdates"></div>
              		 	 </div>
              		  
              		  
              		  </div>

                <input type="button" name="next" id="page3" class="next action-button" value="下一步"/>
            </fieldset>
<!--3rd page end@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->
















<!--4rh page start@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->
            <fieldset>
               <div class="startRight">
               <img src="act_assets/img/start/bg8.jpg" class="img-responsive">
               </div>
				<div class="startLeft">
                <h2 class="fs-title">活動說明</h2>
                <h3 class="fs-subtitle">Your presence on the social network</h3>

							  <div class="margin-top-10">
									<div id="editor">

										<h2>碰碰好可愛</h2>
										<p>呼叫所有的狗狗愛好者們！我的拉不拉多狗狗─碰碰非常想跟其他狗狗見面喔！<br>
										我們將在每周一的傍晚在巴克禮公園舉辦狗狗聚會，歡迎所有的狗主人、毛狗、喜歡狗狗的人參加。<br>
										現場還有寵物送領養的活動；<br>
										晚間將到【咖啡豆生活家】聚餐，有提供給狗狗與主人飲料與食物，可以讓我們聚餐到十點。<br>
											
										</p>
									</div>
							  </div></div>

                <input type="button" id="conf" name="next" class="next action-button" value="下一步"/>
            </fieldset>
            
            
            <fieldset>

              <input type="hidden"name="action" value="insert">
               <img src="act_assets/img/dogs.jpg" class="img-responsive">
               <h2 class="fs-title">活動內容確認</h2>
                <h3 class="fs-subtitle">Your presence on the social network</h3>
<div class="wd80">
                <table class="table">
                <tr>
                <th class="col-md-3">活動圖片</th>
					<td class="col-md-9"><img id="image2"  style="max-width: 400px" class="img-responsive margin-bottom-30 img-rounded"/></td>
                </tr>

                <tr>
                <th>活動名稱：</th>
					<td><span id="actNameW"></span><input type="hidden" Name="actName" id="actName"></td>
                </tr>
                <tr>
                
                <th>活動地點：</th>
                <td><span id="actAdrW"></span><input type="hidden" Name="actAdr" id="actAdr">
               <input type="hidden" Name="actLocName" id="actLocName">
                <input type="hidden"Name="actPost" id="actPost">
                <input type="hidden" Name="actLat" id="actLat">
                <input type="hidden" Name="actLong" id="actLong"></td>
                  </tr>
                  
                <tr>
                <th>活動時間：</th>
					<td><span id="actStartDateW"></span><input type="hidden" Name="actStartDate" id="actStartDate"></td>
                </tr>
                
                <tr>
                <th>活動結束時間：</th>
                <td><span id="actEndTimeW"></span><input type="hidden" Name="actEndDate" id="actEndDate"></td>
                </tr>
                
                <tr>
                <th>活動重複設定：</th>
                <td><span id="actTimeTypeCntW"></span><input type="hidden" Name="actTimeTypeCnt" id="actTimeTypeCnt"></td>
                </tr>      

                <tr>
                <th>活動分類：</th>
					<td><span id="actPOIsW"></span><input type="hidden" Name="actPOIs" id="actPOIs"></td>
                </tr>
                
                 <tr>
                <th>活動內容：</th>
                <td><span id="actContentW"></span><input type="hidden"Name="actContent" id="actContent"></td>
                </tr>      
                         
                </table>
                
   </div>             
                
                
                <script>
						 jQuery(document).ready(function() {
							 $("#onlinebtn").click(function(){
								 $("#locName").val("線上")
								 $("#lng").val(999);
								 $("#lat").val(999);
								 $("#post").val(999);
								 $("#loc").val("");
							 })
							 $("#conf").click(function(){
									$("#actName").val($("#aName").val());
									$("#actAdr").val($("#loc").val());
									$("#actPost").val($("#post").val());
									$("#actStartDate").val($("#aStartDate").val());
									$("#actEndDate").val($("#aEndDate").val());
					
									$("#actPOIs").val($("#poilists").val());										
									$("#actContent").val($("#editor").text());	
									$("#actTimeTypeCnt").val($("#showdates").text());	
									$("#actContent").val($("#editor").html());
									$("#actLocName").val($("#locName").val());
									$("#actLong").val($("#lng").val());
									$("#actLat").val($("#lat").val());
								 	
								 $("#actNameW").text($("#aName").val());
								 $("#actAdrW").text($("#loc").val());
								 $("#actStartDateW").text($("#aStartDate").val().replace("T", " "));
								 $("#actEndTimeW").text($("#aEndDate").val().replace("T", " "));
								 $("#actTimeTypeCnt").text($("#showdates").text());	
								 $("#actPOIsW").text($("#poilists").val());	
								 $("#actContentW").text($("#editor").text());								 
								 
								 
								 
								});
						 });

				
				
				</script>
                


                <h3>揪咪是一個熱情友善的網站。請同意我們的使用條款</h3>
      			<input id="ckfinal" type="checkbox"> 我同意<a href="#"> 《使用條款》 </a><p />
               
                <input type="submit" name="submit" class="submit action-button" value="建立活動"/>
     </form>
            </fieldset>
           
            
            
            
       </div>



</div>


<!-- /.MultiStep Form -->



<!--主頁面要修改的都在這上面-->



<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
   
  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->
	<!-- 共用Js -->


<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-3.2.1.min.js"><\/script>')</script>
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/trumbowyg/trumbowyg.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/act/BS-MultiStepForm/assets/bootstrap/js/bootstrap.min.js"></script>

<script src="<%=request.getContextPath()%>/front-end/act/act_assets/modal/js/bootstrap-modal.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/front-end/act/BS-MultiStepForm/assets/multistepform/js/msform.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=request.getContextPath()%>/front-end/act/act_assets/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/act/act_assets/dp/jquery-ui.multidatespicker.js"></script>

 <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.wow.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/jquery.smooth-scroll.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>

<script src="act_assets/js/actStart/jquery.cropit.js" type="text/javascript"></script> 
<script src="act_assets/js/actStart/jquery.geocomplete.js"></script>
<!-- END CORE PLUGINS --> 

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) --> 
<!--@@@@@@@@@@@@@@@@@@@@@@@@@ 頁面專屬JS，JS擺在最後有益身心健康，可以刪改 @@@@@@@@@@@@@@@@@@@@@@@@-->
<script src="<%=request.getContextPath()%>/HTML/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/trumbowyg/plugins/colors/trumbowyg.colors.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/trumbowyg/plugins/upload/trumbowyg.upload.min.js"></script>

<script src="<%=request.getContextPath()%>/front-end/act/act_assets/trumbowyg/langs/zh_tw.min.js"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/corporate/scripts/layout.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/HTML/assets/pages/scripts/bs-carousel.js" type="text/javascript"></script>

<script type="text/javascript">
        jQuery(document).ready(function() {
//		$('.date').datepicker({
//			multidate: true
//		});
//
//		$('.date').datepicker('setDates', [new Date(2017, 10, 11), new Date(2017, 10, 15)])
			$('#mdp-demo').multiDatesPicker({
				maxPicks: 6,
				minDate: 0, // today
				maxDate: 60 // +30 days from today
			});

			
			$('#editor').trumbowyg({
				
	//			lang: 'zh_tw',
				btnsDef: {
					// Create a new dropdown
					image: {
						dropdown: ['insertImage', 'upload'],
						ico: 'insertImage'
					}
				},
    // Redefine the button pane
				btns: [
					['viewHTML'],
					['formatting'],
					['strong', 'em', 'del'],
					['superscript', 'subscript'],
					['link'],
					['image'], // Our fresh created dropdown
					['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
					['unorderedList', 'orderedList'],
					['horizontalRule'],
					['removeformat'],
					['foreColor', 'backColor'],
				],
				plugins: {
					// Add imagur parameters to upload plugin for demo purposes
					upload: {
						serverPath: 'https://api.imgur.com/3/image',
						fileFieldName: 'image',
						headers: {
							'Authorization': 'Client-ID xxxxxxxxxxxx'
						},
						urlPropertyName: 'data.link'
					}
				}
				
				
			});

$("#page3").click(function(){
	var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
	$("#error6").html('');
	var l3=$("#showdates").html();
	
	if (l3.length<1){
		$("#error5").html("error5");
	}else{
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
        'transform': 'scale('+scale+')',
        'position': 'absolute'
      });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
	}
	animating = true;
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
        'transform': 'scale('+scale+')',
        'position': 'absolute'
      });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
	});
	
			
$("#page2").click(function(){
	var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
	$("#error3").html('');
	$("#error4").html('');
	var l1=$("#showAddress").val();
	var l2=$("#poilists").val();
	if(l1.length<1){
		$("#error3").html("error3");
	};
	if (l2.length<1){
		$("#error4").html("error4");
	}
	if (l1.length>0 && l2.length>0){

	if(animating) return false;
	animating = true;
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
        'transform': 'scale('+scale+')',
        'position': 'absolute'
      });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
	}
	
	
				

})
});		


document.getElementById("files").onchange = function () {
    var reader = new FileReader();

    reader.onload = function (e) {
        // get loaded data and render thumbnail.
     document.getElementById("image").src = e.target.result;
		document.getElementById("image2").src = e.target.result;
    };
    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
};
	
	
	
	
			//jQuery time


function nextbtn(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
        'transform': 'scale('+scale+')',
        'position': 'absolute'
      });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
}

function prebtn(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();
	
	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
	
	//show the previous fieldset
	previous_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
}

			
			
			
			
			
			
			
			
			

	
	

	
</script>

   
   <!--map-->
      
    <script>
      $(function(){
		$(".map_canvas").css("display","none");
		$(".map_canvas").css("visibility","hidden");
		$("#onlinebtn").click(function(){
				$("#showAddress").val("線上活動");	
			   $("#showAddress").css("visibility","visible");
			   $("#showAddress").css("display","");
							  })  
		  
		  
		  
		$("#inputmap").geocomplete({
			  types: ["geocode", "establishment"],
			});
		  
		$("#inputmap").on("keyup paste change", function() {
			$("#geocomplete").val($("#inputmap").val());			  
		});
		  
		  $('#inputmap').keyup(function(e) {
    		 if(e.keyCode == 13) {
          		$("#find").click();
    		}
		  });
		  
		  
        $("#find").click(function(){
			$("#geocomplete").geocomplete({
			  map: ".map_canvas",
			  details: "form",
			  types: ["geocode", "establishment"],
			});
			
			$("#geocomplete").trigger("geocode");
			$("#myModal").css({width:'auto'});
			$(".map_canvas").css("display","");
			$(".map_canvas").css("visibility","visible");
			
        });
		  
		  $("#myModal").on("shown.bs.modal", function () {
			 $(this).find('.modal-content').css({
              width:'auto', //probably not needed
              height:'auto', //probably not needed 
              'max-height':'100%'
       });
		});
		  
		   $("#mapok").click(function(){
			  $("#showAddress").val($("#geocomplete").val());
			   $("#showAddress").css("visibility","visible");
			   $("#showAddress").css("display","");
			   $("#myModal").modal('hide')
		   });
		  
		  $('input[type="file"]').change(function(){

			  var f = this.files[0];  
			  var name = f.name;

			  $("#showpath").text(name);

			});
		  
		  
		  
      });
		
		

		
		
    </script>

	<!--map end-->
	
	<script>

		function removeTag(){
			$("#poiappend").detach(this);
		}
		
		var poiList=new Array();
		$(".selectPOIs").click(function(){
			if(poiList.length<6){
				var str=$(this).find('.poicnt').text();
				var str2="<span onClick='removeTag();'><li><a href='#'><small><i class='fa fa-tags'></i>"+str+"</small></a></li></span>"
				$("#poiappend").append(str2);
				$(this).css("display","none");
				poiList.push(str);
				$(poilists).val(poiList);
			}else{
				alert("最多選擇六項")
			}
//			$("#showpois").val(" ");
		});
		
		$("#reptclose").click(function() {
			$("#showdates").html($("#mdp-demo").val());
		});
		
		$("#onetime").click(function(){
			$("#showdates").html("無重複設定");
		});
		
	</script>


</body>
</html>
