<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<div class="container">

		<form id="searchForm" action="${ctx }/" method="post">
			<input id="pageNo" type="hidden" name="pageNo" /> <input
				id="pageSize" type="hidden" name="pageSize" />
		</form>

		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>电话</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${page.result }">
					<tr>
						<td>${entity.id }</td>
						<td>${entity.name }</td>
						<td>${entity.phone }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pull-right">${page } </div>
		

	</div>
</body>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</html>