<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<html>
<!-- Head BEGIN -->
<head>
	<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css" rel="stylesheet">

 <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actStart.css" media="screen" rel="stylesheet" type="text/css">
      
    <style type="text/css" media="screen">
/*      .map_canvas { float: left; }*/
      form { width: 100%;}
      fieldset { width: 320px; margin-top: 20px}
      fieldset label { display: block; margin: 0.5em 0 0em; }
      fieldset input { width: 95%; }
    </style>
    <script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/nicEdit.js" type="text/javascript"></script>
    <script type="text/javascript">
			bkLib.onDomLoaded(function() {
			new nicEditor({fullPanel : true,iconsPath : 'act_assets/js/nicEditorIcons.gif',buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image']}).panelInstance('actCntTA');
		});
	</script>

  
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBktVb6UZUJi0f3ySJlXHz3wqWL4nMI6Us&libraries=places" async defer>
	</script>
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="chumeet">
<!-- userHeader Start -->
  <c:import url="/front-end/userHeader.jsp">
</c:import>
<!-- userHeader Start -->
  <!-- Header Start -->
  <c:import url="/front-end/header.jsp">
</c:import>
  <!-- Header END -->
<!--主頁面要修改的都在這下面-->



<div class="main">
<!-- BEGIN CONTENT -->
<!-- BEGIN LEFT SIDEBAR -->            
      <div class="container">
    
      <div class="wd80">

      
<form  action="<%=request.getContextPath()%>/front-end/act/act.do?action=insert" method="get" enctype="multipart/form-data">
   <input type="hidden" value="insert" name="action">
<!--     		start of row-->
<!--     		======================actName HERE-->
      		<div class="row" id="step1">
      			<div class="col-md-1"><img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/start/stick-man.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 1/6</div>
      				<div class="startTitle"><h3>先幫活動取個好聽的名字吧！</h3></div>
      				<div class="warningText" id="w1">　</div>
      				<input type="text"  class="form-control" id="actName" name="actName" value="啾啾揪揪團"  onkeydown="if(event.keyCode==13)return false;">

      			</div>
      			
      		</div>
      		<hr class="stratLine" id="line1"/>
<!--      		end of row-->
   		
    		<div id="step2" >
     		<!--     		start of row-->
<!--     		======================actLocID actLong actLat actLocName actAdr HERE-->
      		<div class="row">
      			<div class="col-md-1"><img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/start/location.png"></div>
      			<div class="col-md-6">
      				<div class="startHead">step 2/6</div>
      				<div class="startTitle"><h3>舉辦地點在哪兒呢？</h3></div>
      				
				<div class="warningText" id="w2">　</div>
  			  
  			      <div class="input-group">
    				<input id="geocomplete" class="form-control" type="text" placeholder="請輸入地址" value="台灣桃園市中壢區TibaMe" />
					  <span class="input-group-btn">
						<button class="btn btn-info" type="button" id="find">定位</button>
					  </span>
					</div><!-- /input-group -->


           </div>
            <div class="col-md-5">
          <div class="map_canvas">
          	
          </div>

			<div id="tg2"></div>
      <fieldset>

        <input class="adruse" name="name" id="prename" type="hidden" value="">
        <input class="adruse" name="lat" id="prelat" type="hidden" value="">
        <input class="adruse" name="lng" id="prelong" type="hidden" value="">
        <input class="adruse" name="formatted_address" id="preadr" type="hidden" value="">
        <input class="adruse" name="postal_code" id="prepost" type="hidden" value="">

      </fieldset>

     				

      			</div>
      		</div>
      		<hr class="stratLine"/>
<!--      		end of row-->
     		</div>
     		<div id="step3" >
     		<!--     		start of row-->
<!--     		======================POI HERE-->
      		<div class="row">
      			<div class="col-md-1"><img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/start/gift-tag.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 3/6</div>
      				<div class="startTitle"><h3>活動分類是？</h3></div>
      				<div class="warningText" id="w3">　</div>

      					      <div class="margin-top-10">
       					      <span class="catadiv">
								<input id="sports" class="checkbox-custom" name="pois"  value="1" type="checkbox">
								<label for="sports">音樂</label>
							  </span>
       					      <span class="catadiv">
								<input id="learning" class="checkbox-custom" name="pois"  value="3" type="checkbox">
								<label for="learning">舞蹈</label>
							  </span>
       					      <span class="catadiv">
								<input id="eat" class="checkbox-custom" name="pois"  value="4" type="checkbox">
								<label for="eat">親子</label>
							  </span>
       					      <span class="catadiv">
								<input id="arts" class="checkbox-custom" name="pois"  value="2" type="checkbox">
								<label for="arts">戲劇</label>
							  </span>
       					      <span class="catadiv">
								<input id="movie" class="checkbox-custom" name="pois" value="8" type="checkbox">
								<label for="movie">電影</label>
							  </span>
       					      <span class="catadiv">
								<input id="game" class="checkbox-custom" name="pois" value="18" type="checkbox">
								<label for="game">餐聚</label>
							  </span>
       					      <span class="catadiv">
								<input id="outdoors" class="checkbox-custom" name="pois" value="6" type="checkbox">
								<label for="outdoors">展覽</label>
							  </span>
       					      <span class="catadiv">
								<input id="pets" class="checkbox-custom" name="pois" value="17" type="checkbox">
								<label for="pets">演唱會</label>
							  </span>
       					      <span class="catadiv">
								<input id="others" class="checkbox-custom" name="pois" value="15" type="checkbox">
								<label for="others">其他</label>
							  </span>
      					
       					
       					
     				</div>

      			</div>
      		</div>
      		<hr class="stratLine"/>
<!--      		end of row-->
    		</div>

    		<div id="step4" >
<!--     		======================actStartDate actEndDate actSignStartDate actSignEndDate actTimeID actITVType HERE-->
     		<!--     		start of row-->
      		<div class="row">
      			<div class="col-md-1"><img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/start/cal.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 4/6</div>
      				<div class="startTitle"><h3>活動時間設定</h3></div>
      				<div class="warningText" id="w4">　</div>
      				<div>
						<table class="table">
							  <tr>
								<th class="col-md-3">活動舉辦時間</th>
								<td class="col-md-3">
									<input type="datetime-local" name="actStartDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-20T15:00:00">
								</td>
								<th class="col-md-3">活動結束時間</th>
								<td class="col-md-3">
									<input type="datetime-local" name="actEndDate" onkeydown="if(event.keyCode==13)return false;" value="2017-10-21T15:00:00">
								</td>
							  </tr>
							
						  </table>

      				</div>

      			</div>
      		</div>
      		<hr class="stratLine"/>
      		</div>
<!--      		end of row-->
   		
    		<div id="step5" >
     		<!--     		start of row-->
<!--     		======================actMemMax actMemMin actContent actImg actPriID HERE-->
      		<div class="row">
      			<div class="col-md-1"><img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/start/electronic.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 5/6</div>
      				<div class="startTitle"><h3>活動詳細說明</h3></div>
      				<div class="warningText" id="w5">　</div>
      				<div>
						<table class="table table-striped">
							  <tr>
							  <th colspan="4">詳細內容
							  <div class="margin-top-10">
							  	<textarea class="form-control" name="actContent" rows="15" cols="50" id="actCntTA"></textarea>
							  	
							  </div>
							  </th>
							  </tr>
								<tr>
							  	 <th>上傳活動照片
						  	 		</th>
						  	 		<td>
<input class="btn btn-default" id="file" Name="actIMG" type="file" accept="image/png,image/gif,image/jpeg,image/jpg" >
						  	 		</td>

							  </tr>
						  </table>

      				</div>

      			</div>
      		</div>
      		<hr class="stratLine"/>
      		</div>
<!--      		end of row-->
		
		
			<div id="step6" >　
<!--     		start of row-->
      		<div class="row">
      			<div class="col-md-1"><img src="<%=request.getContextPath()%>/front-end/act/act_assets/img/start/people.png"></div>
      			<div class="col-md-11">
      				<div class="startHead">step 6/6</div>
      				<div class="startTitle"><h3>揪咪是一個友善熱情的網站，請同意我們的使用條款</h3></div>
      				<input id="ckfinal" type="checkbox"> 我同意<a href="#"> 《使用條款》 </a><p />
      				<div style="height: 8em;">
      				<button id="goSubmit" class="btn btn-danger actNext" type="submit">開啟活動囉！</button>
      				</div>
      			</div>
      		</div>
<!--      		end of row-->
     		</div>
        </form>
      </div>
        <!-- END SIDEBAR & CONTENT -->

</div>
</div>
<!--主頁面要修改的都在這上面-->




  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->
	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
  	<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.cropit.js" type="text/javascript"></script> 
<script src="jquery.geocomplete.js"></script>
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.cropit.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.geocomplete.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
            Layout.init();    
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
            Layout.initNavScrolling();
			$(".image-editor").cropit();
			$("#imguploader").on('change', function() {
				$("#imgprev").show();
		 	});
			$("#geocomplete").geocomplete({
						  map: ".map_canvas",
						  details: "form",
						  types: ["geocode", "establishment"],
						});
		
				$("a.toscroll").on('click',function(e) {
				var url = e.target.href;
				var hash = url.substring(url.indexOf("#")+1);
				$('html, body').animate({
					scrollTop: $('#'+hash).offset().top
				}, 500);
				return false;
			})

	});
	</script>


<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
