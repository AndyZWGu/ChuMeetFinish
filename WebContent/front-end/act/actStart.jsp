<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="java.util.*"%>

<html lang="en"><head>
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
</head>
<!-- Body BEGIN -->
<body class="corporate">

<  <!-- Header Start -->
  <c:import url="../../front-end/header.jsp">
</c:import>
  <!-- Header END -->



<div class="center-block" style="max-width: 1150px; text-align: center;">
<!-- BEGIN CONTENT -->
<!-- MultiStep Form -->

        <form id="msform" action="<%=request.getContextPath()%>/front-end/act/act.do" method="get">
            <!-- progressbar -->
            <ul id="progressbar">
                <li class="active">基本資料</li>
                <li>地點與分類</li>
                <li>時間</li>
                <li>詳細內容</li>

                <li>創建活動</li>
            </ul>
            
        	<input name="action" type="hidden" value="insert">    
            
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
						<div class="wrapper inputSp">
								<div class="group">

							  	<input class="is" type="text" required="required" id="aName"/>
								<span class="highlight"></span><span class="bar"></span>
							  	<label class="la"><i class="fa fa-caret-right pull-left" aria-hidden="true"></i>活動主題是...</label>
								</div>

<!--cover start-->



								<div class="image-editor" style="text-align: center;">
					  	 		
				  	 			  <label for="file-upload" class="custom-file-upload">
										選擇活動封面
									</label>
									<input id="file-upload" type="file" accept="image/png,image/gif,image/jpeg,image/jpg" class="cropit-image-input pull-left"/>
							<div class="row">
							<div class="col-md-11">
										<div id="imgprev" style="width: 360px; text-align: center;margin-left: auto; margin-right: auto">
										<div class="cropit-preview" style="margin-left: auto; margin-right: auto"></div>
							</div>  	 			  
							<div class="col-md-1">
										<input type="range" class="cropit-image-zoom-input">
										<input type="hidden" name="image-data" id="actImg" class="hidden-image-data" />
								</div>
								</div>
							</div>
						  	 	</div>
						  <!--						  end of cover-->
					
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
                <h1 class="fs-title">活動分類</h1>
                <h3 class="fs-subtitle">Your presence on the social network</h3>
                								

				   <div class="wrapper inputSp">     
       		        
													
								<div class="group margin-bottom-20">

							  	<input id="showAddress" class="is hns" type="text" required="required"/>
								<span class="highlight"></span><span class="bar"></span>
							  	<label class="la"><i class="fa fa-caret-right pull-left" aria-hidden="true"></i>舉辦地點為...</label>
							  	
							  	
									<div class="pull-left gms margin-top-10;" style="color: dimgrey"><small>請選擇：</small>
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
        <input id="locName" name="name" type="hidden" value="">				
        <input name="point_of_interest" type="hidden" value="">
        <input id="lat" name="lat" type="hidden" value="">
        <input id="lng" name="lng" type="hidden" value="">


        <input name="location" type="hidden" value="">
        <input name="location_type" type="hidden" value="">
<!--        actAdr-->
        <input id="loc" name="formatted_address" type="hidden" value="">
        <input name="bounds" type="hidden" value="">
        <input name="viewport" type="hidden" value="">
        <input name="route" type="hidden" value="">
        <input name="street_number" type="hidden" value="">
<!--post-->
        <input id="post" name="postal_code" type="hidden" value="">
        <input name="locality" type="hidden" value="">
        <input name="sublocality" type="hidden" value="">
        <input name="country" type="hidden" value="">
        <input name="country_short" type="hidden" value="">
        <input name="administrative_area_level_1" type="hidden" value="">
        <input name="place_id" type="hidden" value="">
        <input name="id" type="hidden" value="">
        <input name="reference" type="hidden" value="">
        <input name="url" type="hidden" value="">
        <input name="website" type="hidden" value="">
</div>		
				
			</div>
		</div>
	
 
							
</div>	   

						   	<br>
						   									<div class="group">

							  	<input id="showpois" class="is hns" type="textarea" required="required" />
							  	<div id="poiappend" class="event-tags" style="text-align: left; margin-top: 0"></div>
							  								  	
								<span class="highlight"></span><span class="bar"></span>
							  	<label class="la"><i class="fa fa-caret-right pull-left" aria-hidden="true"></i>活動分類有...</label>
							  	</div>
								
							   <div id="showcat" class="event-tags_st" style="text-align: left;">
					   				<input type="hidden" id="poilists">
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>音樂</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>戲劇</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>舞蹈</small></span></a></li></span>
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
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>讀書會</small></span></a></li></span>
								   <span class="selectPOIs"><li><a href="#"><span class="poicnt"><small>其他</small></span></a></li></span>
    					
						  
						
              		  
						   </div>	
		
		
              		  </div></div>
                <input type="button" name="previous" class="previous action-button-previous" value="上一步"/>
                <input type="button" name="next" class="next action-button" value="下一步"/>
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
						<div style="text-align: left">
							<h3><i class="fa fa-caret-right pull-left" aria-hidden="true"></i>預定時間為...</h3>
							<p></p>
							<div style="margin-left: 1em;">
								<div class="form-inline">
								  <div class="form-group">
									<input type="datetime-local" min="2017-10-19" id="aStartDate" class="form-control" onkeydown="if(event.keyCode==13)return false;" value="2017-10-20T15:00:00">
									  </div>
									<div class="form-group">
									至<input type="time" id="aEndTime" class="form-control" onkeydown="if(event.keyCode==13)return false;" value="17:00:00">
									</div>
									</div>
							</div>
            		 	 	<br>
             		 	 	<h3><i class="fa fa-caret-right pull-left" aria-hidden="true"></i>重複設定</h3>
             		 	 	<span class="margin-left-20"><button class="btn btn-sm btn-default" id="onetime">一次</button>
             		 	 	<button class="btn btn-sm btn-default" data-toggle="modal" data-target="#timeModal" id="rept">重複</button></span>
							
							<!-- Modal -->
							<div id="timeModal" class="modal fade" role="dialog">
							  <div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Modal Header</h4>
								  </div>
								  <div class="modal-body">
									<p><input id="mdp-demo"></p>
								  </div>
								  <div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="reptclose">Close</button>
								  </div>
								</div>

							  </div>
							</div>
          					 <p></p>
							<div id="showdates"></div>
              		 	 </div>
              		  
              		  
              		  </div>
                <input type="button" name="previous" class="previous action-button-previous" value="上一步"/>
                <input type="button" name="next" class="next action-button" value="下一步"/>
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
										<p>
											我覺得我做不完了QwQ
										</p>
									</div>
							  </div></div>
                 <input type="button" name="previous" class="previous action-button-previous" value="上一步"/>
                <input type="button" id="conf" name="next" class="next action-button" value="下一步"/>
            </fieldset>
            
            
            <fieldset>
                <h2 class="fs-title">活動內容確認</h2>
                <h3 class="fs-subtitle">Your presence on the social network</h3>
                
                <table>
                <tr>
                <th>活動名稱：</th>
                <td><input Name="actName" id="actName"></td>
                </tr>
                <tr>
                
                <th>活動地點：</th>
                <td><input Name="actAdr" id="actAdr"></td>
                </tr>
                
				<tr>
                <th>Name：</th>
                <td><input Name="actLocName" id="actLocName"></td>
                </tr>
                
                
                <tr>
                <th>POST：</th>
                <td><input Name="actPost" id="actPost"></td>
                </tr>
                
                <tr>
                <th>LAT：</th>
                <td><input Name="actLat" id="actLat"></td>
                </tr>
                
                
                <tr>
                <th>LONG：</th>
                <td><input Name="actLong" id="actLong"></td>
                </tr>
                
                <tr>
                <th>活動時間：</th>
                <td><input Name="actStartDate" id="actStartDate"></td>
                </tr>
                
                <tr>
                <th>活動結束時間：</th>
                <td><input Name="actEndTime" id="actEndTime"></td>
                </tr>
                
                <tr>
                <th>活動重複設定：</th>
                <td><input Name="actTimeTypeCnt" id="actTimeTypeCnt"></td>
                </tr>      
                       
                
                
                <tr>
                <th>活動分類：</th>
                <td><input Name="actPOIs" id="actPOIs"></td>
                </tr>
                
                 <tr>
                <th>活動內容：</th>
                <td><input type="textarea" Name="actContent" id="actContent"></td>
                </tr>      
                         
                </table>
                
                
                
                
                <script>
						 jQuery(document).ready(function() {
							 $("#onlinebtn").click(function(){
								 $("#locName").val("線上")
							 })
							 $("#conf").click(function(){
									$("#actName").val($("#aName").val());
									$("#actAdr").val($("#loc").val());
									$("#actPost").val($("#post").val());
									$("#actStartDate").val($("#aStartDate").val());
									$("#actEndTime").val($("#aEndTime").val());
									$("#actEndTime").val($("#aEndTime").val());									
									$("#actPOIs").val($("#poilists").val());										
									$("#actContent").val($("#editor").text());	
									$("#actTimeTypeCnt").val($("#showdates").text());	
									$("#actContent").val($("#editor").text());
									$("#actLocName").val($("#locName").val());
									$("#actLong").val($("#lng").val());
									$("#actLat").val($("#lat").val());
								});
						 });

				
				
				</script>
                
                
                <input type="button" name="previous" class="previous action-button-previous" value="上一步"/>
                <input type="button" name="next" class="next action-button" value="NEXT"/>
            </fieldset>
            <fieldset>
               	<img src="act_assets/img/dogs.jpg" class="img-responsive">
                <h3 class="fs-title">就快完成囉！</h3>
                <h3 class="fs-subtitle">揪咪是一個熱情友善的網站。請同意我們的使用條款</h3>
      			<input id="ckfinal" type="checkbox"> 我同意<a href="#"> 《使用條款》 </a><p />
                <input type="button" name="previous" class="previous action-button-previous" value="上一步"/>
                <button type="submit" name="submit" class="action-button">建立活動</button>

            </fieldset>
           
            
            
            
       </div>
     </form>
    

</div>


<!-- /.MultiStep Form -->





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
//	$('.date').datepicker({
//		multidate: true
//	});
//
//	$('.date').datepicker('setDates', [new Date(2017, 10, 11), new Date(2017, 10, 15)])
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

        Layout.init();    
        Layout.initOWL();
        Layout.initTwitter();
        Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
});




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
//		$("#showpois").val(" ");
	});
	
	$("#reptclose").click(function() {
		$("#showdates").html($("#mdp-demo").val());
	});
	
</script>

<script src="<%=request.getContextPath()%>/HTML/assets/plugins/components/wow.min.js" type="text/javascript"></script>

</body>
</html>
