<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.club.model.*"%>
<%@ page import="com.clubMem.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
	ClubVO clubVO = (ClubVO) session.getAttribute("clubVO");
	
	//找社團會員
	ClubMemService clubMemSvc = new ClubMemService();
	List<ClubMemVO> clubMemlist = (List) session.getAttribute("clubMemlist");
	pageContext.setAttribute("clubMemlist",clubMemlist);
	// 找會員名字
	MemberService memSvc=new MemberService();
	List<MemberVO> mbMemNameList=new ArrayList<MemberVO>();
	for(ClubMemVO list:clubMemlist){
		mbMemNameList.add(memSvc.getOneMember(list.getMemID()));
	}
	request.setAttribute("mbMemNameList",mbMemNameList);
%>

<html>

<!-- Head BEGIN -->
<!-- 共用Header -->
<c:import url="/front-end/head.jsp">
</c:import>
<!-- 共用Header -->
<!--  my styles  -->
<!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
<!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
<!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
<link href="<%=request.getContextPath()%>/HTML/src/act/css/act.csss"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/HTML/src/member/css/memHome.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/HTML/src/member/css/memberChatRoom.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.9.0/sweetalert2.min.css"
	rel="stylesheet">
	
	<style>
.mymessage {
    background-color:#b8f693;
}
</style>
	
</head>

<body class="chumeet">
	<c:import url="/front-end/userHeader.jsp">
	</c:import>

	<!-- Header Start -->
	<c:import url="/front-end/header.jsp">
	</c:import>
	<div class="main">
		<div class="container">
			<div class="row">
<div class="col-md-12">
	<div class="row profile-content blog-item">
			<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>/front-end/member/memberHome.do">首頁</a></li>
			<li><a href="<%=request.getContextPath()%>/front-end/club/ClubAll.jsp">社團推薦</a></li>
			<li>${clubVO.clubName}</li>
			<li class="active">社團專屬聊天室</li>
		</ul>
	<hr class="colorgraph">
<script src="https://use.fontawesome.com/45e03a14ce.js"></script>
<div class="main_section">
   <div class="container">
      <div class="chat_container">
		 
         <div class="col-sm-9 message_section">
		 <div class="row">
		 <div class="new_message_head">
		 <div class="pull-left"><button><i class="fa fa-plus-square-o" aria-hidden="true"></i>
		 <c:if test="${guestName==null}">
		 	等待聊天中
		 </c:if>
		 <c:if test="${guestName!=null}">
		 	當前聊天對象:${guestName}
		 </c:if>
		 </button></div><div class="pull-right"><div class="dropdown">
  <button class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <i class="fa fa-cogs" aria-hidden="true"></i>  Setting
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
    <li><a href="#">Action</a></li>
    <li><a href="#">Profile</a></li>
    <li><a href="#">Logout</a></li>
  </ul>
</div></div>
		 </div><!--new_message_head-->
		 
		 <div class="chat_area">
		 <ul class="list-unstyled" id="messageContent" onload="connect();" onunload="disconnect();">
		 	<input id="userName" class="text-field" type="hidden" placeholder="使用者名稱" value="${memVO.memName}"/>
		    <input id="userID" class="text-field" type="hidden" placeholder="使用者ID" value="${memVO.memID}"/>
		    <input id="guestID" class="text-field" type="hidden" placeholder="對方ID" value="${memVO.memID}"/>
		    <input id="guestName" class="text-field" type="hidden" placeholder="對方名稱" value="1"/>
		    <input type="button" id="connect"  class="hidden" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="hidden" value="離線" onclick="disconnect();"/>		 
		 
		 </ul>
		 </div><!--chat_area-->
          <div class="message_write">
    	 <textarea rows="5" class="form-control" rows="1" cols="50" placeholder="輸入訊息" id="message"></textarea>
    	 <a href="#" class="pull-right btn btn-success" id="sendMessage" onclick="sendMessage();">送出</a>
		 </div>
		 </div>
         </div> <!--message_section-->
         
                  <div class="col-sm-2 chat_sidebar">
    	 <div class="row">
            <div id="custom-search-input">
               <div class="input-group col-md-12">
                  <input type="text" class="  search-query form-control" placeholder="Conversation" />
                  <button class="btn btn-danger" type="button">
                  <span class=" glyphicon glyphicon-search"></span>
                  </button>
               </div>
            </div>
            <div class="dropdown all_conversation">
               <button class="dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               <i class="fa fa-weixin" aria-hidden="true"></i>
               社團成員列表
               <span class="caret pull-right"></span>
               </button>
            </div>
                        <div class="member_list">
               <ul class="list-unstyled">
               		<c:forEach items="${clubMemlist}" var="clubMemlist" varStatus="status">
                  	<li class="left clearfix">
                     	<span class="chat-img pull-left">
                     	<img src="<%=request.getContextPath()%>/front-end/member/memberHome/avatar.do?memID=${clubMemlist.memID}" alt="User Avatar" class="img-circle">
                     	</span>
                     	<div class="chat-body clearfix">
                        	<div class="header_sec">
                        	<p>${mbMemNameList[status.index].memName}<p id="${clubMemlist.memID}"></p></p>
                        	</div>                        	
                     	</div>
                  	</li>
               		</c:forEach>
               </ul>
            </div></div>
         </div>
         <!--chat_sidebar-->
      </div>
   </div>
</div>
<hr class="colorgraph">
	</div>
</div>


			</div>
		</div>
		<br> <br>
	</div>

	<!-- BEGIN FOOTER -->
	<c:import url="/front-end/footer.jsp">
	</c:import>
	<!-- END FOOTER -->

	<!-- 共用Js -->
	<c:import url="/front-end/publicJS.jsp">
	</c:import>
	<!-- 共用Js -->
	<script
		src="<%=request.getContextPath()%>/HTML/src/member/js/validator.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.9.0/sweetalert2.min.js"
		type="text/javascript"></script>

<script>
	
    var userID= document.getElementById("userID").value;
    var guestID= document.getElementById("guestID").value;
    var userName= document.getElementById("userName").value;
    
    var MyPoint = "/MyWebSocketServer/"+userID+"/"+guestID;
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		/* alert(endPointURL); */
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			firstMessage();
			
			updateStatus("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        /* alert(jsonObj.message); */ 
	        //新增
	        
	        //加圖寫法
	        
	        //
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        var userMessage = jsonObj.message + "\r\n";

	        //新增

			var userID = jsonObj.userID;
			/* alert(userID); */
			/* alert(e); */
			/* alert(message); */
			var messageContent = document.getElementById("messageContent");
			var li = document.createElement("LI");
			li.setAttribute("class", "left clearfix");
			var span = document.createElement("SPAN");
	        span.setAttribute("class", "chat-img1 pull-right"); 
			var img = document.createElement("IMG");
			var imgPath = "/ChuMeetWebsiteFinish/front-end/member/memberHome/avatar.do?memID="+userID;
			img.setAttribute("src", imgPath); 
			span.appendChild(img); 
			var div = document.createElement("DIV");
			div.setAttribute("class", "chat-body1 clearfix"); 
			var p = document.createElement("PRE");
			var userMessageTextNode = document.createTextNode(userMessage);
			p.appendChild(userMessageTextNode);
			var timeDiv = document.createElement("DIV");
			timeDiv.setAttribute("class", "chat_time pull-left"); 
			var today=new Date();
			var nowTime = document.createTextNode(today.toLocaleTimeString());
			timeDiv.appendChild(nowTime);
			div.appendChild(p);
			div.appendChild(timeDiv);
			li.appendChild(span);
			li.appendChild(div); 
			messageContent.appendChild(li);
			messageContent.scrollTop = messageContent.scrollHeight;
			
			//上線狀態
			
	        
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
			//下線狀態
			
			
		};
			
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	var inputUserID = document.getElementById("userID");
	inputUserID.focus();
	var inputReceiverID = document.getElementById("guestID");
	inputReceiverID.focus();
	
	function sendMessage() {
		var userID = inputUserID.value.trim();
		var receiverID = inputReceiverID.value.trim();
	    var userName = inputUserName.value.trim();
	    
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }

	    	var inputMessage = document.getElementById("message");
	    	var message = inputMessage.value.trim();
	    
	    if (receiverID === ""){
	        /* alert ("對方請勿空白!"); */
	        inputReceiverID.focus();	
			return;
	    }
	    
	    if (message === ""){
	        /* alert ("訊息請勿空白!"); */
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userID" : userID, "receiverID" : receiverID, "userName" : "club", "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	        myMessage(message);
	    }
	}
	
	function firstMessage(){
		alert("加入社團專屬聊天室");
		var userID = inputUserID.value.trim();
		var receiverID = inputReceiverID.value.trim();
	    var userName = inputUserName.value.trim();
	    
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }

	    	var message = userName+"加入聊天室";
	    	/* alert(userID+"/"+receiverID+"/"+userName+"/"+message); */
	    
	    if (receiverID === ""){
	        /* alert ("對方請勿空白!"); */
	        inputReceiverID.focus();	
			return;
	    }
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userID" : userID, "receiverID" : receiverID, "userName" : "club", "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	        myMessage(message);
	    }
	}
	
	function quitMessage(){
		var userID = inputUserID.value.trim();
		var receiverID = inputReceiverID.value.trim();
	    var userName = inputUserName.value.trim();
	    
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }

	    	var message = userName+"已經退出聊天室";
	    	/* alert(userID+"/"+receiverID+"/"+userName+"/"+message); */
	    
	    if (receiverID === ""){
	        alert ("對方請勿空白!");
	        inputReceiverID.focus();	
			return;
	    }
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userID" : userID, "receiverID" : receiverID, "userName" : "club", "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	        myMessage(message);
	    }
	}
	


	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
	function myMessage(e){
		var userID = document.getElementById("userID").value.trim();
		/* alert(userID); */
		/* alert(e); */
		/* alert(message); */
		var messageContent = document.getElementById("messageContent");
		var li = document.createElement("LI");
		li.setAttribute("class", "left clearfix");
		var span = document.createElement("SPAN");
        span.setAttribute("class", "chat-img1 pull-left"); 
		var img = document.createElement("IMG");
		var imgPath = "/ChuMeetWebsiteFinish/front-end/member/memberHome/avatar.do?memID="+userID;
		img.setAttribute("src", imgPath); 
		span.appendChild(img); 
		var div = document.createElement("DIV");
		div.setAttribute("class", "chat-body1 clearfix"); 
		var p = document.createElement("PRE");
		p.setAttribute("class", "mymessage"); 
		var userMessageTextNode = document.createTextNode(e);
		p.appendChild(userMessageTextNode);
		var timeDiv = document.createElement("DIV");
		timeDiv.setAttribute("class", "chat_time pull-left"); 
		var today=new Date();
		var nowTime = document.createTextNode(today.toLocaleTimeString());
		timeDiv.appendChild(nowTime);
		div.appendChild(p);
		div.appendChild(timeDiv);
		li.appendChild(span);
		li.appendChild(div); 
		messageContent.appendChild(li);
		messageContent.scrollTop = messageContent.scrollHeight;
	}
	
	window.onload = function() {
		  /* alert("gg"); */
		  connect();
		  /* 加入聊天 */
		 myMessage("您已加入聊天室");
			//上線狀態
			
		  /* firstMessage(); */
		};
		
	window.onbeforeunload = function(){
		quitMessage();
		webSocket.close();
		/* disconnect(); */
	};
		
</script>

</body>

</html>