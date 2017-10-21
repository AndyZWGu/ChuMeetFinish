<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
// 	ActFVO actfVO = (ActFVO) request.getAttribute("actfVO");
// 	System.out.println("getActID=" + actfVO.getActVO().getActID());
// 	System.out.println("req ActID=" + request.getAttribute("actID"));
	Act_Service actS = new Act_Service();
	ActFVO actfVO=actS.getOne(222);
	ActPOIService apS = new ActPOIService();
	Integer actID=actfVO.getActVO().getActID();
	List<Integer> pois = apS.getPOIByActID(actID);

	  
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 
	 SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");// 
		 String str1 = df.format(actfVO.getActVO().getActStartDate());
		 String str2 = df2.format(actfVO.getActVO().getActStartDate()); 
	String strStartdate=str1+"T"+str2;
		 str1 = df.format(actfVO.getActVO().getActEndDate());
		 str2 = df2.format(actfVO.getActVO().getActEndDate());
	String strEnddate=str1+"T"+str2;
	
	
// 	System.out.println("strStartdate="+strStartdate);
// 	System.out.println("strEnddate="+strEnddate);
	
	
	
	
%>
<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ -->
<%
MemberVO memVO = (MemberVO) session.getAttribute("memVO");
	Integer memNow = 999999;
	if (memVO != null) {
		memNow = memVO.getMemID();
	}
%>

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
			<ul class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>/front-end/index.jsp">ChuMeet!</a></li>
				<li><a
					href="<%=request.getContextPath()%>/front-end/act/actList.jsp">活動列表</a></li>
				<li class="active">${actfVO.actVO.actName}</li>
			</ul>
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
          <div class="col-md-12 col-sm-12">
            <h1>更新活動內容：${actfVO.actVO.actName}</h1>
            <div class="content-page">
                               <form action="<%=request.getContextPath()%>/front-end/act/act.do" method="post">
              <div class="row">
                <!-- BEGIN LEFT SIDEBAR -->            
                <div class="col-md-9 col-sm-9 blog-item">


                   <input type="hidden" value="update" name="action">
                   <input type="hidden" value="<%=actID %>" name="actID">
                          		<table class="table table-hover">
                          		<tr><th class="text-danger topstat"><i class="fa fa-smile-o"></i></th><th>活動名稱</th><td><input size="57px" type="text" name="actName" value="<%=actfVO.getActVO().getActName()%>"></td></tr>
		                   		<tr><th class="text-danger topstat"><i class="fa fa-smile-o"></i></th><th>活動圖片</th><td><input type="file" name="actIMG" id="files" />
								 <img id="image"
										src="<%=request.getContextPath()%>/img/showIMG?colName=actIMG&table=ACT&pk=actID&imgFrom=<%=actID %>"
										class="img-responsive margin-bottom-30 img-rounded"
										style="margin-left: auto; margin-right: auto; max-width:400px"" alt="">
					 
						 </td></tr>
								<tr><th class="text-danger topstat"><i class="fa fa-user"></i></th><th>活動起訖時間</th><td>	<div class="form-inline">
								  <div class="form-group">
									<input type="datetime-local" min="2017-10-19" onkeydown="if(event.keyCode==13)return false;" name=actStartDate" value="<%=strStartdate%>">
									 </div>
									<div class="form-group">
									至<input type="datetime-local" name="actEndDate" onkeydown="if(event.keyCode==13)return false;" name=actEndDate"  value="<%=strEnddate%>">
									</div>
                        		
                        		</td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-calendar"></i></th><th>活動舉辦地點</th><td>
									
                <div class="input-group">
    				<input id="geocomplete" type="text"  size="57.5px" placeholder="請輸入地址" value="<%=actfVO.getActVO().getActAdr()%>"/>
					  <span class="input-group-btn">
						<button class="btn btn-info" type="button" id="find">定位</button>
					  </span>
					</div><!-- /input-group -->

          <div class="map_canvas">
          </div>
      <fieldset>

        <input class="adruse" name="name" id="prename" type="hidden" value="<%=actfVO.getActVO().getActLocName()%>">
        <input class="adruse" name="lat" id="prelat" type="hidden" value="<%=actfVO.getActVO().getActLat()%>">
        <input class="adruse" name="lng" id="prelong" type="hidden" value="<%=actfVO.getActVO().getActLong()%>">
        <input class="adruse" name="formatted_address" id="preadr" type="hidden" value="<%=actfVO.getActVO().getActAdr()%>">
        <input class="adruse" name="postal_code" id="prepost" type="hidden" value="<%=actfVO.getActVO().getActPost()%>">
       
      </fieldset>

     				


                        		</td></tr>
                         		<tr><th class="text-danger topstat"><i class="fa fa-users"></i></th><th>活動內容</th><td>
									<div class="margin-top-10">
								  	<textarea class="form-control" name="actContent" rows="15" cols="50" id="actCntTA"><%=actfVO.getActVO().getActContent()%></textarea>
							 	</div>	
                          		
                          		</td></tr>
                          		
                          		<tr>
                          		<th>*</th>
                          		<th>分類設定</th>
                          		<td>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="1" <%if (pois.contains(1)){ %>checked<%} %>>音樂</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="2" <%if (pois.contains(2)){ %>checked<%} %>>戲劇</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="3" <%if (pois.contains(3)){ %>checked<%} %>>舞蹈</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="4" <%if (pois.contains(4)){ %>checked<%} %>>親子</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="5" <%if (pois.contains(5)){ %>checked<%} %>>獨立音樂</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="6" <%if (pois.contains(6)){ %>checked<%} %>>展覽</label>
<br>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="7" <%if (pois.contains(7)){ %>checked<%} %>>講座</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="8" <%if (pois.contains(8)){ %>checked<%} %>>電影</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="9" <%if (pois.contains(9)){ %>checked<%} %>>運動</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="10" <%if (pois.contains(10)){ %>checked<%} %>>手作</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="11" <%if (pois.contains(11)){ %>checked<%} %>>綜藝</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="12" <%if (pois.contains(12)){ %>checked<%} %>>學習</label>
<br>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="13" <%if (pois.contains(13)){ %>checked<%} %>>競賽</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="14" <%if (pois.contains(14)){ %>checked<%} %>>徵選</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="20" <%if (pois.contains(20)){ %>checked<%} %>>藝文</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="17" <%if (pois.contains(17)){ %>checked<%} %>>演唱會</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="18" <%if (pois.contains(18)){ %>checked<%} %>>餐聚</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="19" <%if (pois.contains(19)){ %>checked<%} %>>研習課程</label>
<br>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="21" <%if (pois.contains(21)){ %>checked<%} %>>電競</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="22" <%if (pois.contains(22)){ %>checked<%} %>>線上活動</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="23" <%if (pois.contains(23)){ %>checked<%} %>>戶外</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="24" <%if (pois.contains(24)){ %>checked<%} %>>寵物</label>
<label class="checkbox-inline"><input type="checkbox" name="pois" value="25" <%if (pois.contains(25)){ %>checked<%} %>>讀書會</label> 
<label class="checkbox-inline"><input type="checkbox" name="pois" value="15" <%if (pois.contains(15)){ %>checked<%} %>>其他</label>                          		
                          		
                          		
                          		</td>
                          		</tr>
                          		</table>

                    	
                    </div>	
                    	
                     	
           
                <!-- END LEFT SIDEBAR -->

                <!-- BEGIN RIGHT SIDEBAR -->            
                <div class="col-md-3 col-sm-3 blog-sidebar">
					<div>
					<button class="btn btn-block btn-primary" type="submit">確定更新</button>
				<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=<%=actfVO.getActVO().getActID()%>"><span class="btn btn-block btn-success">取消更新</span></a>
				</div>
                  <!-- BEGIN map -->
                </div>
                <!-- END RIGHT SIDEBAR -->            
              </div>
            </form>
            </div>
          </div>
          <!-- END CONTENT -->
        </div> </div> </div>
        <!-- END SIDEBAR & CONTENT -->

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
			
			document.getElementById("files").onchange = function () {
		    var reader = new FileReader();
				
		    reader.onload = function (e) {
		        // get loaded data and render thumbnail.
		        document.getElementById("image").src = e.target.result;
		    };
		    // read the image file as a data URL.
		    reader.readAsDataURL(this.files[0]);
		};
					
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
