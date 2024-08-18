<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
th {
	background-color: #ff9900
}

.message {
	color: #800000
}
</style>
<title>従業員検索ページ</title>
</head>
<body>
	<header>
		<h1>従業員検索ページ</h1>
	</header>
	<main>
		<form action="controller" method="post">
			<p>検索したい従業員IDを入力して下さい</p>
			<p>
				<input type="text" name="paramId"> <input type="hidden"
					name="btn" value="IdSearch"> <input type="submit"
					value="検索">
			</p>
		</form>

		<c:if test="${not empty requestScope.message }">
			<p class="message">${requestScope.message }</p>
		</c:if>
		<c:if test="${not empty requestScope.empList }">
			<h2>検索結果</h2>
			<table>
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>住所</th>
					<th>年齢</th>
					<th>E-MAIL</th>
				</tr>
				<c:forEach var="emp" items="${requestScope.empList }">
					<tr>
						<td><c:out value="${emp.id }" /></td>
						<td><c:out value="${emp.name }" /></td>
						<td><c:out value="${emp.address }" /></td>
						<td><c:out value="${emp.age }" /></td>
						<td><c:out value="${emp.mail }" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</main>
</body>
</html>