<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="com.poi.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	Act_Service actS = new Act_Service();
	ActPOIService apS = new ActPOIService();
	List<ActFVO> list = new ArrayList<ActFVO>();
	List<ActFVO> poiRDx2lists= new ArrayList<ActFVO>();

	Integer poiIDPage = (Objects.isNull(request.getAttribute("poiID"))) ? 0 : (Integer) (request.getAttribute("poiID")); //POI款
			System.out.println("poiIDPage="+poiIDPage);
	String pageName;

	
	poiRDx2lists=actS.getRDx2ByPOIID();
	
	if (request.getParameter("action") == null || (request.getParameter("action") .equals("ori"))) {
		pageName = "ori";
	} else {
		pageName = request.getParameter("action");
	}

	System.out.println("pageName=" + pageName);
	if (pageName.equals("ori")) {
		list = actS.getAll();
		pageContext.setAttribute("list", list); //基本款
	} else {
			list = (List<ActFVO>) request.getAttribute("list");
			pageContext.setAttribute("list", list);
	}
	

	if (Objects.isNull(list) || list.size() == 0) { //拿不到東西怎辦 

	}

	com.gen.tool.tools toolman=new com.gen.tool.tools();
	com.gen.tool.actCodeTrans transman=new com.gen.tool.actCodeTrans();
	
	System.out.println("HI! list.size="+list.size());
	
	MemberVO memVO = (MemberVO)session.getAttribute("memVO");
%>
<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ -->
<%
Integer memNow=999999;

if(memVO!=null){
	memNow=memVO.getMemID();
	}	


%>

<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ -->


<html>
<!-- Head BEGIN -->
<head>
<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
<!-- 共用Header -->
<!--  my styles  -->
<link
	href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css"
	rel="stylesheet">
<style>
.btn-is-disabled {
  pointer-events: none;
  /* Disables the button completely. Better than just cursor: default; */
  filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=70);
  opacity: 0.7;
}


</style>
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

		<!--     end of top contain-->
		<div class="container">
			<!--      start of top top row (with select)-->
			<div class="row">
				<div class="col-md-3">
					<div id="bread">
						<ul class="breadcrumb">
							<li><a href="<%=request.getContextPath()%>/index.html">ChuMeet!</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front-end/act/actList.jsp">活動列表</a></li>
							<li class="active">揪咪推薦</li>
						</ul>
					</div>
				</div>
				<!--        <div class="col-md-9">-->

				<!--    start of search group 	-->
				<!-- BEGIN TOP SEARCH -->
				<!--					  <div class="input-group">
							<span class="input-group-btn">
								 <button class="btn btn-primary btnSearch" onClick="searchStart();">
									<i class="fa fa-search" aria-hidden="true"></i>
								</button>
							</span>							<input type="text" placeholder="Search" id="searchInput" class="form-control">

					  </div>-->
				<!-- END TOP SEARCH -->
				<!--	</div>-->

				<!--end of search group-->


				<!-- BEGIN SIDEBAR & CONTENT -->
				<div class="row margin-bottom-40">

					<!-- BEGIN CONTENT -->
					<div class="col-md-12 col-sm-12">
						<div class="row">
							<div class="col-md-8">
								<h1>揪咪推薦</h1>
								<h1></h1>
							</div>
<!-- 							<div class="col-md-4 padding-top-10"> -->
<!-- 								<div class="actFilter pull-right"> -->
<!-- 									<select> -->
<!-- 										<option>依距離排列</option> -->
<!-- 										<option selected>依時間排列</option> -->
<!-- 																<option>依人數排列</option> -->
<!-- 																<option>依熱門度排列</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>

						<div class="content-page">
							<div class="row">



								<div class="col-md-3 col-sm-3">
									<ul class="tabbable actl-tabbable">
										<li><a href="<%=request.getContextPath()%>/front-end/act/actStart2.jsp" data-toggle="tab">開個揪揪團</a></li>
										<li data-toggle="collapse" data-target="#myAct"
											class="collapsed"><a <%if (memNow>9999){ %>class="btn-is-disabled" <%} %>href="#tab_1" data-toggle="tab">我的活動
												<span class="arrow collapsed"></span>
										</a></li> 
										<ul class="sub-menu <%
												System.out.println("left Menu Start");
										if(!pageName.equals("getMyAct2") && !pageName.equals("getMyAct5") && !pageName.equals("getMyAct1")){ %> collapse<%} %>" id="myAct">
											<li <%if(pageName.equals("getMyAct2")){%> class="active" <%} %>>
											<a <%if (memNow>9999){ %>class="btn-is-disabled" <%} %> href="<%=request.getContextPath()%>/front-end/act/act.do?action=getMyAct2">參加中</a></li>
											<li <%if(pageName.equals("getMyAct1")){%> class="active" <%} %>>
											<a <%if (memNow>9999){ %>class="btn-is-disabled" <%}%> href="<%=request.getContextPath()%>/front-end/act/act.do?action=getMyAct1">我舉辦的活動</a></li>
											<li <%if(pageName.equals("getMyAct5")){%> class="active" <%} %>>
											<a <%if (memNow>9999){ %>class="btn-is-disabled" <%}%> href="<%=request.getContextPath()%>/front-end/act/act.do?action=getMyAct5">追蹤中</a></li>
											<!-- 								<li><a href="#">邀請中</a></li> -->
											<!-- 								<li><a href="#">社團活動</a></li> -->
											<!-- 								<li><a href="#">好友活動</a></li> -->
										</ul>
										<li <%if (pageName.equals("ori")) {%> class="active" <%}%>><a
											href="<%=request.getContextPath()%>/front-end/act/actList.jsp">揪咪推薦</a></li>

										<li <%if (pageName.equals("QueryWks")) {%> class="active" <%}%>><a
											href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryWks">周末特調</a></li>
										<li data-toggle="collapse" data-target="#actPOI"
											class="collapsed"><a href="#tab_2" data-toggle="tab">活動分類
												<span class="arrow"></span>
										</a></li>
										<ul class="sub-menu<%
												System.out.println("left Menu POIStart");
										if (poiIDPage <= 0) {%> collapse" <%}%>
											id="actPOI">
											<li <%if (poiIDPage == 9) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=9">運動</a></li>
											<li <%if (poiIDPage == 1) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=1">音樂</a></li>
											<li <%if (poiIDPage == 2) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=2">戲劇</a></li>
											<li <%if (poiIDPage == 3) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=3">舞蹈</a></li>
											<li <%if (poiIDPage == 6) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=6">展覽</a></li>
											<li <%if (poiIDPage == 7) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=7">講座</a></li>
											<li <%if (poiIDPage == 8) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=8">電影</a></li>
											<li <%if (poiIDPage == 8) {%> class="active" <%}%>><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=24">寵物</a></li>
											<li><a
												href="<%=request.getContextPath()%>/front-end/act/actPOIs.jsp">其他</a></li>
												<%												System.out.println("left Menu END"); %>
										</ul>
										<!-- 					<li><a href="#tab_2" data-toggle="tab">揪揪地圖</a></li> -->
									</ul>

									<!-- BEGIN RECENT NEWS -->
									<p />
									<h3>注目活動</h3>
									<div class="recent-news margin-bottom-10">

<%for (ActFVO actfforpoi:poiRDx2lists) {%>
										<!--                  card start -->
										<div class="wow fadeInUp" data-wow-duration=".3"
											data-wow-delay=".2s">
											<div class="card">
													<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=<%=actfforpoi.getActVO().getActID()%>">
														<img class="img-responsive img-rounded"
														src="<%=request.getContextPath()%>/img/showIMG?colName=actIMG&table=ACT&pk=actID&imgFrom=<%=actfforpoi.getActVO().getActID()%>">
													</a>
												<div class="card-block">
													<h4 class="card-title"><%=actfforpoi.getActVO().getActName() %></h4>
													<small style="color: gray;">
													<i class="fa fa-map-marker"></i><%=transman.actPosttoString(actfforpoi.getActVO().getActPost()) %> 
													<i	class="fa fa-calendar"></i><%=toolman.tsToActStr(actfforpoi.getActVO().getActStartDate()) %></small>
														<%Integer limit=0; %>
														<% if(actfforpoi.getActCnt().length()<70){ limit=actfforpoi.getActCnt().length();} else{ limit=70;} %>
													<span class="card-text margin-top-10" style="font-size=14px;"><%=actfforpoi.getActCnt().substring(0, limit) %>...</span>
													<div class="card-text cardmore">
														<a href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=<%=actfforpoi.getActVO().getActID()%>" class="btn btn-sm btn-default" >詳細資訊</a>
													</div>
												</div>
											</div>
										</div>
										<!--                      card over-->
										<%} %>
									</div>
									<!-- END RECENT NEWS -->
								</div>
								<!-- BEGIN LEFT SIDEBAR -->
								<div class="col-md-9 col-sm-9 event-posts margin-bottom-50">
								<% POI_service pois=new POI_service();
								List<POIVO> listp=pois.getAll();
								for(POIVO poiv:listp){
									if(poiv.getPOIID()!=16){
								%>
						                    <div class="col-md-3">
											<div class="POI">
												<div class="cef">
												<a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=<%=poiv.getPOIID()%>">
												  	<p>
												  	<img class="img-responsive img-rounded"
														src="<%=request.getContextPath()%>/img/showIMG?colName=POIIMG&table=POI&pk=POIID&imgFrom=<%=poiv.getPOIID()%>">
												  	
												  	</p>
													<p class="h3"><%=poiv.getPOINameC() %></p>
												</a>
												</div>
										   </div>
					                    	</div>
					                    	<%} }%>
								</div>

								<!-- END LEFT SIDEBAR -->

							</div>
						</div>
					</div>
					<!-- END CONTENT -->
				</div>
				<!-- END SIDEBAR & CONTENT -->
			</div>
		</div>
		<!--      end of top top row (with select)-->
	</div>

	<!--主頁面要修改的都在這上面-->



	<input type="hidden" name="action" value="showOne">
	</form>
	<br>
	<br>
	<br>
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
</body>
<!-- END BODY -->
</html>