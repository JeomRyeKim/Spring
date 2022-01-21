<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis JPA 회원 등록</title>

</head>
<%
	String context = request.getContextPath();
%>

<body>
<h1>Mybatis JPA 회원 등록</h1>
<div class="container">
    <form action="<%=context%>/memberJpa/save" method="post">
       ID : <input type="text" id="id"   name="id"   required="required"><p>
              이름  : <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
         <button type="submit">등록</button>
    </form>
</div> <!-- /container -->
</body>
</html>