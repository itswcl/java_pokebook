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
		<h1>Edit Expense</h1>
		<a href="/">Home</a>
		<form:form action="/pokebook/${expense.id}" method="post"
			modelAttribute="expense">
			<input type="hidden" name="_method" value="put">
			<p>
				<form:label path="name">Expense Name</form:label>
				<form:errors path="name" />
				<form:input path="name" />
			</p>
			<p>
				<form:label path="vendor">Vendor</form:label>
				<form:errors path="vendor" />
				<form:input path="vendor" />
			</p>
			<p>
				<form:label path="amount">Amount</form:label>
				<form:errors path="amount" />
				<form:input type="double" path="amount" />
			</p>
			<p>
				<form:label path="description">Description</form:label>
				<form:errors path="description" />
				<form:textarea path="description" />
			</p>
			<input type="submit" value="Submit" />
		</form:form>
	</div>

</body>
</html>