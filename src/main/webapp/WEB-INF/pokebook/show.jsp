<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- local JS -->
<script type="text/javascript" src="js/app.js"></script>
<!-- Bootstrap JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Starting page</title>
</head>
<body>

	<div class="container">
		<h1>Expense Detail</h1>
		<a href="/">Go back</a>
		<div class="row">
			<p>
				Expense Name:
				<c:out value="${ expense.name }" />
			</p>
			<p>
				Expense Description:
				<c:out value="${ expense.description }" />
			</p>
			<p>
				Expense Vendor:
				<c:out value="${ expense.vendor }" />
			</p>
			<p>
				Amount Spent $
				<c:out value="${ expense.amount }" />
			</p>
		</div>
		<div class="d-flex">
			<a class="btn" href="/pokebook/${expense.id}/edit">Edit</a>

			<form action="/pokebook/${expense.id}" method="post">
				<input type="hidden" name="_method" value="delete"> <input
					class="btn" type="submit" value="Delete">
			</form>
		</div>
	</div>

</body>
</html>