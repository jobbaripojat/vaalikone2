<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Update an existing question</title>
</head>
<body>
	<div class="container">
		<div class="row align-items-center vh-100">
			<div class="col-4"></div>
			<div class="col-4 text-center">
				<form action='../updatequestion' method='post'>
					<div class="row g-3">
						<label for="question">Edit question number ${requestScope.question.QUESTION_ID}</label>
						<input class="form-control" id='id' type='hidden' name='id' value='${requestScope.question.QUESTION_ID}' readonly>
						<textarea class="form-control" id='question' type='text' name='question' placeholder='New question'>${requestScope.question.QUESTION}</textarea>
						<br>
						<input class="btn btn-primary" type='submit' name='ok' value='Submit'>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>