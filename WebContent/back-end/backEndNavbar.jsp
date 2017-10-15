<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*"%>
<% 
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>
<nav class="navbar navbar-transparent ">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						
						<li><a href="#pablo" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="material-icons">person</i>
								<p class="hidden-lg hidden-md">Profile</p>
						</a>
													<ul class="dropdown-menu">
								<li class="text-center">
								<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/admin.do">
												<input type="submit" value="修改資料"
													class="btn-link"> <input
													type="hidden" name="action" value="getOne_For_Update"> <input
													type="hidden" name="adminID" value="${adminVO.adminID}">
											</FORM>
								</li>
								<li class="text-center">
								<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back-end/admin.do">
												<input type="submit" value="登出"
													class="btn-link"> <input
													type="hidden" name="action" value="logout"> <input
													type="hidden" name="adminID" value="${adminVO.adminID}">
											</FORM>
								</li>
								
							</ul>
						</li>
					</ul>
				</div>
			</div>
			</nav>