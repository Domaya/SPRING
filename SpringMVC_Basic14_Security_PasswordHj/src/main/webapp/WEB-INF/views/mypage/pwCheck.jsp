<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>index</title>
      <link href="../joinus/join.css" type="text/css" rel="stylesheet" />
   </head>
   <body>
      <jsp:include page="/WEB-INF/views/inc/header.jsp" />
      <jsp:include page="inc/visual.jsp" />
      <div id="main">
         <div class="top-wrapper clear">
            <div id="content">
               <form action="" method="post">
                  <h2>본인 확인</h2><br /><br />
                  비밀번호를 입력해주세요: <input type="password" name="password"/>
                  <input type="submit" value="확인" />
               </form>   
               
            </div>
            <jsp:include page="inc/aside.jsp" />
         </div>
      </div>
      <jsp:include page="../inc/footer.jsp" />
   </body>
</html>