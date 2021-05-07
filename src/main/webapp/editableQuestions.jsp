<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<title>Questions</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous">
    </script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=RocknRoll+One&display=swap"
	rel="stylesheet">
    <link rel="stylesheet" href="/style.css">

</head>

<body>
    <%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("username")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("login.jsp");
%>
	<div class="container mt-5">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 text-center">
				<h1 style="text-align: center">Manage Election Machine Questions</h1>
				<br>
				<a class='btn btn-info' href='../addForm.jsp'>Add new question</a>
				
			</div>
			<div class="col-3"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="row">
					<c:forEach var="question" items="${requestScope.questionlist }">
					<div style="height:2px; width: 100%; background-color: black;"></div>
					<div class='row question-box pt-3 pb-4 mt-5 d-flex justify-content-center'>
						<div class='col-12 row d-flex align-items-center'>
							<div class='col-3'>QUESTION_ID: ${question.QUESTION_ID}</div>
							<h5 class='col-6 mt-2 mb-3 text-center'>
								${question.QUESTION}
								<br>
								<br>
								<a class='btn btn-danger' href='../deletequestion?id=${question.QUESTION_ID}'>Delete</a> 
								<a class='btn btn-info' href='../readtoupdatequestion?id=${question.QUESTION_ID}'>Update</a>
							</h5>
							<div class='col-3'></div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
</body>
</html>