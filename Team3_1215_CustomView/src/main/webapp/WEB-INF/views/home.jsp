<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
<a href="${pageContext.request.contextPath}/pagestat/rankreport">Open PDF VIEWER</a>
</body>
</html>
