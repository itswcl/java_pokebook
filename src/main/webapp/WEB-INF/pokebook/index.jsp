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
<title>PokeBook</title>
</head>
<body>

	<div class="container">
		<h1>PokeBook</h1>
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th>Expense</th>
						<th>Vendor</th>
						<th>Amount</th>
						<th>Option</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="expense" items="${ expenses }">
						<tr>
							<th><a href="/pokebook/${expense.id}"><c:out value="${ expense.name }" /></a></th>
							<th><c:out value="${ expense.vendor }" /></th>
							<th>$<c:out value="${ expense.amount }" /></th>
							<th>
								<div class="d-flex">
									<a class="btn" href="/pokebook/${expense.id}/edit">Edit</a>
									
									<form action="/pokebook/${expense.id}" method="post">
										<input type="hidden" name="_method" value="delete">
										<input class="btn"type="submit" value="Delete">
									</form>
								</div>
							</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<h1>Track an expense</h1>
			<form:form action="/pokebook" method="post" modelAttribute="expense">
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
	</div>

</body>
</html>