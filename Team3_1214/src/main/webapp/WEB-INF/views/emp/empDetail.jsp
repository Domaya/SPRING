<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>사원 정보 상세</title>
</head>
<body>
	<h3>${emp.ename }</h3>
	<a href="list.htm">돌아가기</a>
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
			<tr>
				<td>${emp.empno}</td>
				<td>${emp.ename}</td>
				<td>${emp.job}</td>
				<td>${emp.mgr}</td>
				<td>${emp.hiredate}</td>
				<td>${emp.sal}</td>
				<td>${emp.comm}</td>
				<td>${emp.deptno}</td>
			</tr>
		</tbody>
	</table>
	<a href="empEdit.htm?empno=${emp.empno}">정보수정</a>&nbsp;&nbsp;||&nbsp;&nbsp;<a href="empDelete.htm?empno=${emp.empno}">삭제</a>
</body>
</html>