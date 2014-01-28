<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock</title>
</head>
<body>
	<form:form name="myform" method="post" modelAttribute="gis">
		<div align="center">
			<h1>Welcome to the stock</h1>

			<table>
				<tr>
					<td>Name:<form:input path="name" /></td>
				</tr>
				<tr>
					<td>Amount:<form:input path="amountInstock" /></td>
				</tr>
				<tr align="center">
					<td colspan="5" align="right"><input type="submit" value="add"
						onclick='this.form.action="/GoodsInStock/";' id="add"></td>
					<td colspan="5" align="center"><input type="submit"
						value="delete" onclick='this.form.action="/GoodsInStock/delete";'
						id="del" /></td>
					<td colspan="5" align="left"><input type="submit"
						value="update" onclick='this.form.action="/GoodsInStock/update";'
						id="update" /></td>
				</tr>

			</table>
			<br />
			<table border="1" cellpadding="5" width="100">
				<c:forEach var="entry" items="${goods}">
					<tr>
						<td><c:out value="${entry['name']}" /></td>
						<td><c:out value="${entry['amountInstock']}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>
</body>
</html>