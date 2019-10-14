<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/index2.css" rel="stylesheet">
<script type="text/javascript" src="jquery-3.3.1.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<h1>Redis缓存展示Zset页面</h1>
<table>
<tr>
<td>商品序号</td>
<td>商品名称</td>
<td>商品价格</td>
<td>销售百分比</td>
</tr>
<c:forEach items="${list}" var="g">
<tr>
<td>${g.id}</td>
<td>${g.name}</td>
<td>${g.price}</td>
<td>${g.hunders}</td>
</tr>
</c:forEach>
</table>

<a href="findByList?page=${prePage}">上一页</a>
<a href="findByList?page=${nextPage}">下一页</a>


</body>
</html>