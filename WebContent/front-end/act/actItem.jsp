<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.act.actMem.model.*"%>
<%@ page import="com.act.model.*"%>
<%-- <%@ page import="com.act.controller.*"%> --%>
<%@ page import="com.act.actMem.model.AmFaceVO"%>
<%@ page import="com.act.act.model.*"%>
<%@ page import="com.act.actPOI.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%
	ActFVO actfVO = (ActFVO) request.getAttribute("actfVO");
// 	System.out.println("getActID=" + actfVO.getActVO().getActID());
// 	System.out.println("req ActID=" + request.getAttribute("actID"));
	Act_Service actS = new Act_Service();
	ActPOIService apS = new ActPOIService();
	List<Integer> pois = apS.getPOIByActID(actfVO.getActVO().getActID());
	List<ActFVO> poix2lists = new ArrayList<ActFVO>();
	try {
		poix2lists = actS.getx2ByPOIID(apS.getPOIByActID(actfVO.getActVO().getActID()).get(0));
	} catch (Exception e) {
		poix2lists = actS.getx2ByPOIID(1);
	} finally {
	}
%>
<%
	ActMemService ams = new ActMemService();
	Set<Integer> whosInNo = new HashSet<Integer>();
	Set<Integer> whosTNo = new HashSet<Integer>();
	// System.out.println("actID="+(Integer.parseInt(request.getParameter("actID"))));
	List<AmFaceVO> whosinList = ams.whosIn(Integer.parseInt(request.getParameter("actID")));
	pageContext.setAttribute("whosinList", whosinList);
	if (whosinList.size() > 0) {
		for (AmFaceVO amf : whosinList) {
			System.out.println("add " + amf.getMemID());
			whosInNo.add(amf.getMemID());
		}
	}

	List<AmFaceVO> whosTList = ams.whosT(Integer.parseInt(request.getParameter("actID")));
	pageContext.setAttribute("whosTList", whosTList);
	if (whosTList.size() > 0) {
		for (AmFaceVO amf : whosTList) {
			whosTNo.add(amf.getMemID());
		}
	}
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<!-- @@@@@@@@@@@@@@@@@@@@@@@@@ -->
<%
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
<link
	href="<%=request.getContextPath()%>/front-end/act/act_assets/css/actMain.css"
	rel="stylesheet">

<style>
#map {
	width: 100%;
	height: 250px;
	background-color: grey;
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

					<div class="content-page">
						<div class="row">
							<jsp:useBean id="toolman" class="com.gen.tool.tools" />
							<jsp:useBean id="transman" class="com.gen.tool.actCodeTrans" />
							<!-- BEGIN LEFT SIDEBAR -->

							<div class="col-md-9 col-sm-9 blog-item">
								<h1>${actfVO.actVO.actName}</h1>
								<div class="row">
									<div class="col-md-5">
										<img
											src="<%=request.getContextPath()%>/img/showIMG?colName=actIMG&table=ACT&pk=actID&imgFrom=${actfVO.actVO.actID}"
											class="img-responsive margin-bottom-30 img-rounded"
											style="margin-left: auto; margin-right: auto;" alt="">
									</div>

									<div class="col-md-7">
										<table class="table table-hover">
											<tr>
												<th class="text-danger topstat"><i
													class="fa fa-smile-o"></i></th>
												<th>我的狀態</th>
												<td><span> <%
 	if (memNow == actfVO.getActVO().getMemID()) {
 %> 活動發起人 <%
 	} else if (whosInNo.contains(memNow)) {
 %>已參加 <%
 	} else if (whosTNo.contains(memNow)) {
 %>已追蹤 <%
 	} else {
 %>尚未參加<%
 	}
 %> <c:choose>
															<c:when
																test="${toolman.nowTimestamp() < actfVO.actVO.actStartDate}">，等待活動開始 </c:when>
															<c:when
																test="${toolman.nowTimestamp() >= actfVO.actVO.actStartDate && toolman.nowTimestamp() <= actfVO.actVO.actEndDate}">，活動進行中 </c:when>
															<c:when
																test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}">，活動已結束</c:when>
															<c:otherwise>WAT</c:otherwise>
														</c:choose>

												</span></td>
											</tr>
											<tr>
												<th class="text-danger topstat"><i class="fa fa-user"></i></th>
												<th>活動發起人</th>
												<td>
											<c:if test="${memVO.memID!=actfVO.actVO.memID}">
											<a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${actfVO.actVO.memID}"> 
											</c:if>
											<c:if test="${memVO.memID==actfVO.actVO.memID}">
											<a href="<%=request.getContextPath()%>/front-end/member/memberHome.do"> 
											</c:if>
												
												<span>${transman.whoRU(actfVO.actVO.memID)}</span></a></td>
											</tr>
											<tr>
												<th class="text-danger topstat"><i
													class="fa fa-calendar"></i></th>
												<th>活動時間</th>
												<td><span>
														${toolman.tsToActStr(actfVO.actVO.actStartDate)}起至
														${toolman.tsToActStrOT(actfVO.actVO.actEndDate)}</span></td>
											</tr>
											<c:if test="${actfVO.actVO.actType==1}">
												<tr>
													<th class="text-danger topstat"><i class="fa fa-users"></i></th>
													<th>目前人數</th>
													<td><span><%=whosinList.size()%></span></td>
												</tr>
											</c:if>
										</table>
										<div class="event-tags">
											<%
												for (Integer poiID : pois) {
											%>
											<li style="font-size: 14px"><a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=QueryPOI&poiID=<%=poiID%>">
													<i class="fa fa-tags"></i> <%=transman.poitoString(poiID)%></a></li>
											<%
												}
											%>
										</div>


									</div>
								</div>
								<hr class="colorgraph">
								<h2>活動詳情</h2>
								<div style="margin-left: 1em; margin-right: 1em;">
									<p>
										<c:if test="${actfVO.actVO.actType==2}">

											<table style="border-width: 0px">
												<tr>
													<th>主辦單位：</th>
													<td>${actfVO.actVO.actMasterUnit}</td>
												</tr>
												<tr>
													<th>是否售票：</th>
													<td><c:choose>
															<c:when test="${actfVO.actVO.actOnSale}=='Y'">是</td>
												</tr>
												<tr>
													<th>售票網址：</th>
													<td><a href="${actfVO.actVO.actWebSales}">${actfVO.actVO.actWebSales}</a></td>
												</tr>
												<tr>
													<th>票價：</th>
													<td>${actfVO.actVO.actPrice}/td>
												</tr>
												</c:when>
												<c:otherwise>免費</td>
													</tr>
												</c:otherwise>
												</c:choose>
											</table>
											<hr>
										</c:if>
										${actfVO.actVO.actContent}

									</p>
								</div>
								<hr class="colorgraph">
								<h2>留言區</h2>
								<div class="comments">

									<%
										ActMBService ambS = new ActMBService();
										Integer actID = Integer.parseInt(request.getParameter("actID"));
										System.out.print(actID);
										List<ActMBVO> listmbs = ambS.getAll(actID);
									%>

									<%
										if (listmbs.size() > 0) {
											for (ActMBVO amb : listmbs) {
									%>

									<!--                   start mb1 -->
									<div class="media">
										<a href="javascript:;" class="pull-left"> <img
											class="media-object"
											src="<%=request.getContextPath()%>/img/showIMG?colName=MEMAVATAR&table=MEMBER&pk=MEMID&imgFrom=<%=amb.getMemID()%>">
										</a>
										<div class="media-body">
											<h4 class="media-heading">
												<%=com.gen.tool.actCodeTrans.whoRU(amb.getMemID())%>
												<span><%=com.gen.tool.tools.tsToActStr(amb.getActMBDate())%>
													<%
														if (memNow == amb.getMemID()) {
													%> <a
													href="<%=request.getContextPath()%>/front-end/act/actmb.do?action=deleteMB&actID=<%=amb.getActID()%>&actMBID=<%=amb.getActMBID()%>">
														[delete]</a></span>
												<%
													}
												%>
											</h4>
											<p><%=amb.getActMBContent()%></p>
										</div>
									</div>
									<!--end media-->

									<%
										}
										}
									%>


								</div>



								<div class="post-comment padding-top-40">
									<h3>活動留言</h3>
									<form id="MBform" role="form"
										action="<%=request.getContextPath()%>/front-end/act/actmb.do">
										<div class="form-group">
											<textarea name="actMBContent" class="form-control" rows="8"></textarea>
											<input type="hidden" name="action" value="insertMB">
											<input type="hidden" name="actID" value=${actfVO.actVO.actID}>
											<input type="hidden" name="memID" value=<%=memNow%>>
											<input type="hidden" name="requestURL"
												value=<%=request.getParameter("requestURL")%>>
										</div>
										<p>
											<button class="btn btn-primary" id="MBarea">提交</button>
										</p>
										<script>
                      $("#MBarea").click(function(){
                    	  $("#MBform").submit();
                      });
                      </script>
									</form>
								</div>
							</div>
							<!-- END LEFT SIDEBAR -->

							<!-- BEGIN RIGHT SIDEBAR -->
							<div class="col-md-3 col-sm-3 blog-sidebar">

								<form action="actm.do" method="post">
									<div>

										<%
											if (memNow == actfVO.getActVO().getMemID()) {
										%>
										<button id="actMng"
											<c:if test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}"> disabled </c:if>
											class="btn btn-block btn-info">管理活動</button>
										<%
											} else if (whosInNo.contains(memNow)) {
										%>
										<button type="submit" name="action" value="delete"
											<c:if test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}"> disabled </c:if>
											class="btn btn-block btn-warning">退出活動</button>
										<%
											} else if (whosTNo.contains(memNow)) {
										%>
										<button type="submit" name="action" value="update2"
											<c:if test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}"> disabled </c:if>
											class="btn btn-block btn-primary">參加活動</button>
										<button type="submit" name="action" value="delete"
											<c:if test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}"> disabled </c:if>
											class="btn btn-block btn-warning">取消追蹤</button>
										<%
											} else {
										%>
										<button type="submit" name="action" value="insert2" <%if (memNow>9999){%> disabled<%} %>
											<c:if test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}"> disabled </c:if>
											class="btn btn-block btn-primary">我要參加</button>
										<button type="submit" name="action" value="insert5" <%if (memNow>9999){%> disabled<%} %>
											<c:if test="${toolman.nowTimestamp() > actfVO.actVO.actEndDate}"> disabled </c:if>
											class="btn btn-block btn-success">追蹤活動</button>
										<%
											}
										%>
									</div>
									<input type="hidden" name="actID" value="${actfVO.actVO.actID}">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>">
								</form>
								<p />
								<!-- BEGIN map -->
								<div class="blog-photo-stream margin-bottom-20">
									<h2>活動地點</h2>
									<p>${actfVO.actVO.actAdr}</p>
									<c:choose>
										<c:when test="${actfVO.actVO.actPost==999}">
											<img
												src="<%=request.getContextPath()%>/front-end/act/act_assets/img/online.jpg"
												alt="" class="img-rounded">
										</c:when>
										<c:otherwise>
											<div id="map"></div>
											<script>
						  function initMap() {
							var uluru = {lat:${actfVO.actVO.actLat}, lng:  ${actfVO.actVO.actLong}};
							var map = new google.maps.Map(document.getElementById('map'), {
							  zoom: 17,
							  center: uluru
							});
							var marker = new google.maps.Marker({
							  position: uluru,
							  map: map
							});
						  }
						</script>
											<script async defer
												src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBiHKk-5rL3ARP2SmZvtcQ5poVS97N_7A&callback=initMap">
						</script>
										</c:otherwise>
									</c:choose>
								</div>
								<p />
								<!-- END BLOG PHOTOS STREAM -->
								<!-- BEGIN BLOG PHOTOS STREAM -->
								<div class="blog-photo-stream margin-bottom-20">
									<h2>已參加的成員</h2>
									<ul class="list-unstyled">
										<c:forEach var="whosinList" items="${whosinList}">
											<li>
											<c:if test="${memVO.memID!=whosinList.memID}">
											<a href="<%=request.getContextPath()%>/front-end/member/guestHome.do?memID=${whosinList.memID}"> 
											</c:if>
											<c:if test="${memVO.memID==whosinList.memID}">
											<a href="<%=request.getContextPath()%>/front-end/member/memberHome.do"> 
											</c:if>
											
											<img
													src="<%=request.getContextPath()%>/img/showIMG?colName=MEMAVATAR&table=MEMBER&pk=MEMID&imgFrom=${whosinList.memID}"
													title="${whosinList.memName}">
											</a></li>
										</c:forEach>
									</ul>
								</div>
								<!-- END BLOG PHOTOS STREAM -->


								<!-- BEGIN RECENT NEWS -->
								<h2>類似的活動</h2>
								<div class="recent-news margin-bottom-10">

									<%
										for (ActFVO actfforpoi : poix2lists) {
									%>
									<!--                  card start -->
									<div class="wow fadeInUp" data-wow-duration=".3"
										data-wow-delay=".2s">
										<div class="card">
											<a
												href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=<%=actfforpoi.getActVO().getActID()%>">
												<img class="img-responsive img-rounded"
												src="<%=request.getContextPath()%>/img/showIMG?colName=actIMG&table=ACT&pk=actID&imgFrom=<%=actfforpoi.getActVO().getActID()%>">
											</a>
											<div class="card-block">
												<h4 class="card-title"><%=actfforpoi.getActVO().getActName()%></h4>
												<small style="color: gray;"> <i
													class="fa fa-map-marker"></i><%=transman.actPosttoString(actfforpoi.getActVO().getActPost())%>
													<i class="fa fa-calendar"></i><%=toolman.tsToActStr(actfforpoi.getActVO().getActStartDate())%></small>
												<%
													Integer limit = 0;
												%>
												<%
													if (actfforpoi.getActCnt().length() < 70) {
															limit = actfforpoi.getActCnt().length();
														} else {
															limit = 70;
														}
												%>
												<span class="card-text margin-top-10" style=""><%=actfforpoi.getActCnt().substring(0, limit)%>...</span>
												<div class="card-text cardmore">
													<a
														href="<%=request.getContextPath()%>/front-end/act/act.do?action=showOne&actID=<%=actfforpoi.getActVO().getActID()%>"
														class="btn btn-sm btn-default">詳細資訊</a>
												</div>
											</div>
										</div>
									</div>
									<!--                      card over-->
									<%
										}
									%>
								</div>
								<!-- END RECENT NEWS -->



							</div>
							<!-- END RIGHT SIDEBAR -->
						</div>
					</div>
				</div>
				<!-- END CONTENT -->
			</div>
		</div>
	</div>
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

	<script
		src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.cropit.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/act/act_assets/js/actStart/jquery.geocomplete.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
            Layout.init();    
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
            Layout.initNavScrolling();

		 	});
</script>


	<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
