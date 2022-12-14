<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Emp myBatis 실습</title>

</head>
<body>
	<h2>EMP</h2>
	<a href="newEmp.htm">사원등록</a>
	<hr>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">사번</th>
				<th scope="col">사원명</th>
				<th scope="col">직종</th>
				<th scope="col">사수</th>
				<th scope="col">입사일자</th>
				<th scope="col">연봉</th>
				<th scope="col">성과급</th>
				<th scope="col">부서번호</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="n">
							<tr>
								<td>${n.empno}</td>
								<td><a href="empDetail.htm?empno=${n.empno}">${n.ename}</a></td>
								<td>${n.job}</td>
								<td>${n.mgr}</td>
								<td>${n.hiredate}</td>
								<td>${n.sal}</td>
								<td>${n.comm}</td>
								<td>${n.deptno}</td>
							</tr>
							</c:forEach>
		</tbody>
	</table>

</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</html>