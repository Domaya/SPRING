<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>사원 등록</title>
</head>
<body>

	<h3>사원 등록</h3>
	<hr>
	<form action="" method="post">
		<div class="form-group">
			<label for="empno">사원번호:</label> <input type="number" name="empno"
				class="form-control" id="empno">
		</div>
		<div class="form-group">
			<label for="ename">사원명:</label> <input type="text" name="ename"
				class="form-control" id="ename">
		</div>
		<div class="form-group">
			<label for="job">직종:</label> <input type="text" name="job"
				class="form-control" id="job">
		</div>
		<div class="form-group">
			<label for="mgr">사수:</label> <input type="text" name="mgr"
				class="form-control" id="mgr">
		</div>
		<div class="form-group">
			<label for="sal">임금:</label> <input type="text" name="sal"
				class="form-control" id="sal">
		</div>
		<div class="form-group">
			<label for="comm">성과급:</label> <input type="text" name="comm"
				class="form-control" id="comm">
		</div>
		<div class="form-group">
			<label for="deptno">부서번호:</label> <input type="text" name="deptno"
				class="form-control" id="deptno">
		</div>
		
		
		<button type="submit" class="btn btn-default">Submit</button>
	</form>

</body>
</html>