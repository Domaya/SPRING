<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P><br>
<a href="${pageContext.request.contextPath}/file/1">FILE DOWNLOAD</a><br>
<a href="${pageContext.request.contextPath}/pagestat/rank">EXCEL DOWNLOAD</a><br>
<a href="${pageContext.request.contextPath}/pagestat/rankreport">Open PDF VIEWER</a><br>
<a href="${pageContext.request.contextPath}/locale/changeLanguage?lang=en">로케일</a>
</body>
</html>
