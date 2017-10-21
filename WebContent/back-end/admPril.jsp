<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*"%>
<%@ page import="com.admPrilType.model.*"%>
<%
session.setAttribute("page", "admin");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
	AdmPrilTypeVO admPrilTypeVO=(AdmPrilTypeVO) request.getAttribute("admPrilTypeVO");
%>
<%
	AdmPrilTypeService admPrilTypeSvc=new AdmPrilTypeService();
	List<AdmPrilTypeVO> list= admPrilTypeSvc.statusname();
	pageContext.setAttribute("list", list);
%>

<html>

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/favicon.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>ChuMeet-Manage</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--  Material Dashboard CSS    -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/material-dashboard.css" rel="stylesheet" />
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/demo.css" rel="stylesheet" />
    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>
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
    .cclabel:active:after {
        width: 20px;
    }
    .div{
    border-radius:10px
}
th{
 text-align: left;
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
				
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#actMain">管理員新增</a>
					</li>
					
				</ul>

				<div style="margin-left:auto; margin-right-auto; width: 80%">
                <div class="tab-content" >
                    <div class="tab-pane fade in active">

                    <FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/admin.do">
							<div class="col-md-6">
                        <table class="table">
                        <tr><th>姓名</th><td><td><input type="TEXT" name="adminName" size="25"></td></td></tr>
                        <tr><th>帳號</th><td><td><input type="TEXT" name="adminMail" size="25"></td></td></tr>
                        <tr><th>信箱</th><td><td><input type="TEXT" name="adminEmail" size="25"></td></td></tr> 
                        </table>
                        </div>
        
<div class="col-md-8">
                                <table  class="table table-hover">
                                <thead>
                                    <tr class="bg-danger">
                                        <th>權限管理</th>                                 
                                        <th><span class="pull-left">權限</span></th>
                                    </tr>
                                </thead>
                                <tbody>
											 <tr>
                                                <td>管理員管理</td>
                                                <td>
                                                    <div class="checkbox" style="text-align: center">
                                                        <label>
                                                            <input type="checkbox" name="adminPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>網站管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="infoPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>App推播管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="appPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>會員管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="memberPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>成就與獎賞管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="achPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>檢舉管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="reportPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>活動社團分類管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="poiPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>活動管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="actPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>

                                                <td>社團管理</td>

                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="clubPrivCheckbox">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            
                                    
                                    
                                </tbody>
                            </table>
                            <span class="text-center" style="margin-left:auto; margin-right: auto">
                            <input type="hidden" name="action" value="insert"> <input
							type="submit" value="新增" class="btn btn-success">
							</span>
							</div>
						</FORM>
                        </div>
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