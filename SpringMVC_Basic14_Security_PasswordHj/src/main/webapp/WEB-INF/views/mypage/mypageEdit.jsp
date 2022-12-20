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
	      <link href="join.css" type="text/css" rel="stylesheet" />
<title>마이페이지</title>
</head>
<body>

      <jsp:include page="/WEB-INF/views/inc/header.jsp" />
      <jsp:include page="inc/visual.jsp" />
      <div id="main">
         <div class="top-wrapper clear">
            <div id="content">
	<form action="" method="post">
		<div class="form-group">
			<label for="userid">아이디:</label> <input type="text" name="userid"
				class="form-control" id="userid" value="${m.userid}" readonly>
		</div>
		<div class="form-group">
			<label for="ename">비밀번호:</label> <input type="text" name="pwd"
				class="form-control" id="pwd" >
		</div>
		<div class="form-group">
			<label for="job">이름:</label> <input type="text" name="name"
				class="form-control" id="name" value="${m.name}">
		</div>
		<div class="form-group">
			<label for="mgr">핸드폰번호:</label> <input type="number" name="mgr"
				class="form-control" id="mgr" value="${m.cphone}">
		</div>
		<div class="form-group">
			<label for="sal">이메일:</label> <input type="text" name="sal"
				class="form-control" id="sal" value="${m.email}">
		</div>
		<div class="form-group">
			<label for="comm">취미:</label> <input type="text" name="comm"
				class="form-control" id="comm" value="${m.habit}">
		</div>
		
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>
</div>
</div>
</body>
</html>