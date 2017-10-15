<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*"%>
<%
session.setAttribute("page", "act");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/favicon.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>ChuMeet-Manage</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta name="viewport" content="width=device-width" />
    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--  Material Dashboard CSS    -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/material-dashboard.css" rel="stylesheet" />
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/demo.css" rel="stylesheet" />
    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons" rel="stylesheet" type="text/css">
    <style>
    .table,
    .table th,
    .table td,
    .table select {
        text-align: center;
        vertical-align: middle;
        line-height: 1em;
    }

    .table>tbody>tr>td {
        line-height: 1em;
        padding: .3em;
    }

    .table>thead>tr>th {
        padding: .5em;
        font-weight: bold;
        line-height: 1.5em;
    }

    .radio i {
        color: red;
    }

    .input[type='radio']:checked i {
        color: yellow;
    }


    input[type=checkbox] {
        height: 0;
        width: 0;
        visibility: hidden;
    }


    .cclabel {
        cursor: pointer;
        text-indent: -9999px;
        width: 40px;
        height: 20px;
        background: grey;
        display: block;
        border-radius: 20px;
        position: relative;
        margin-left: auto;
        margin-right: auto;
    }

    .cclabel:after {
        content: '';
        position: absolute;
        top: 0px;
        left: 2px;
        width: 20px;
        height: 20px;
        background: #fff;
        border-radius: 20px;
        transition: 0.3s;
    }

    input:checked+.cclabel {
        background: #bada55;
    }

    input:checked+.cclabel:after {
        left: calc(100% - 2px);
        transform: translateX(-100%);
    }

    .cclabel:active:after {
        width: 20px;
    }
    </style>
</head>

<body>
     <c:if test="${adminVO!=null}">
	<div class="wrapper">
		
		<!-- Sidebar -->
		<c:import url = "/back-end/backEndSidebar.jsp">
		</c:import>
		
		<div class="main-panel">
				<!-- Navbar -->
				<c:import url = "/back-end/backEndNavbar.jsp">
				</c:import>
            <!--/////////////CONTENT///////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <div class="container">
             
               
                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active">
                      
                        <table class="table  table-hover">
                            <thead>
                                <tr class="bg-danger">
                                    <th class="col-md-1">代碼</th>
                                    <th class="col-md-2">舉辦人</th>
                                    <th class="col-md-1">建立時間</th>
                                    <th class="col-md-3">活動名稱</th>
                                    <th class="col-md-1">狀態</th>
                                    <th class="col-md-1">設定</th>
                                    <th class="col-md-1">HOT</th>
                                    <th class="col-md-1">地區</th>
                                    <th class="col-md-1">詳細</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>actID</td>
                                    <td>memID</td>
                                    <td>actCreateDate</td>
                                    <td>actName</td>
                                    <td>未開始</td>
                                    <td>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="optradio" value="0" checked><i class="fa fa-check-square" aria-hidden="true"></i></label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="optradio" value="4"><i class="fa fa-ban" aria-hidden="true"></i></label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="optradio" value="5"><i class="fa fa-question-circle" aria-hidden="true"></i></label>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="checkbox" id="switch" />
                                        <label class="cclabel" for="switch">推</label>
                                    </td>
                                    <td>actAddr</td>
                                    <td>
                                        <button class="btn btn-info btn-sm">Detail</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>001</td>
                                    <td>breadcan@gmail.com</td>
                                    <td>2017/1/1</td>
                                    <td>老樹根魔法木工坊DIY體驗</td>
                                    <td>未開始</td>
                                    <td>
                                        <span class="btn-group">
                        <button type="button" class="btn btn-danger btn-sm statbtn" name="actStatID" value="5">停權</button>
                        <button type="button" class="btn btn-danger btn-sm statbtn" name="actStatID" value="6">暫停</button>
                      </span>
                                        <button class="btn btn-sm btn-warning hotbtn">熱推</button>
                                    </td>
                                    <td>台中市南區</td>
                                    <td>
                                        <button class="btn btn-info btn-sm">Detail</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <button class="btn btn-success">儲存變更</button>
                    </div>
                    
                </div>
            </div>
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
        </div>
    </div>
    </c:if>
</body>
<!--   Core JS Files   -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
<!-- Material Dashboard javascript methods -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material-dashboard.js"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/demo.js"></script>
<script type="text/javascript">
$(document).ready(function() {

    // Javascript method's body can be found in assets/js/demos.js
    demo.initDashboardPageCharts();

});
</script>

</html>