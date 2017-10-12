<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%
MemberVO memVO = (MemberVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<fmt:formatDate value="${memVO.memBD}" pattern="yyyy-MM-dd HH:mm:ss" />
<html>
<head>
<title>會員資料	Demo.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>會員編號</th>
		<th>會員信箱</th>
		<th>會員密碼</th>
		<th>會員驗證種類</th>
		<th>會員等級</th>
		<th>會員經驗</th>
		<th>會員揪點</th>
		<th>會員名稱</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員手機</th>
		<th>會員大頭照</th>
		<th>會員加入揪咪日期</th>
		<th>會員登入次數</th>
		<th>會員出生地</th>
		<th>會員居住地</th>
		<th>會員自我介紹</th>
		<th>會員精度</th>
		<th>會員緯度</th>
		<th>會員隱私權設定</th>
		<th>會員驗證狀態</th>
		
	</tr>
	<tr align='center' valign='middle'>
		<td>${memVO.memID}</td>
		<td>${memVO.memEmail}</td>
		<td>${memVO.memPw}</td>
		<td>${memVO.memberType}</td>
		<td>${memVO.memLv}</td>
		<td>${memVO.memExp}</td>
		<td>${memVO.memPt}</td>
		<td>${memVO.memName}</td>
		<td>${memVO.memGender}</td>
		<td>${memVO.memBD}</td>
		<td>${memVO.memPhone}</td>
		<td>${memVO.memAvatar}</td>
		<td>${memVO.memJoinDate}</td>
		<td>${memVO.memLoginNum}</td>
		<td>${memVO.memLocBorn}</td>
		<td>${memVO.memLocLive}</td>
		<td>${memVO.memInt}</td>
		<td>${memVO.memLong}</td>
		<td>${memVO.memLat}</td>
		<td>${memVO.memPriv}</td>
		<td>${memVO.memStatus}</td>
	</tr>
</table>

</body>
</html>