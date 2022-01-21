<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	/* 실무에서 배포할 때에 쓰여질 건 아님 */
	// response.sendRedirect("list");
	// response.sendRedirect("listEmpDept"); /* join */
	// response.sendRedirect("writeDeptIn"); /* Procedure 호출 Test */
	// response.sendRedirect("writeDeptCursor"); /* Procedure cursor Test */
	// response.sendRedirect("upLoadFormStart"); /* UpLoad Test */
	// JPA
	// response.sendRedirect("members"); /* Jpa와 Mybatis를 섞어서 함께 쓸 수 있다 */
	// response.sendRedirect("interCeptorForm"); /* 인터셉터 : 중간에 채가는것 */
	// response.sendRedirect("sendVO3"); /* RestController Test (VO) */
	response.sendRedirect("listEmpAjax"); /* Ajax -> getDeptName listEmpAjax */
	// response.sendRedirect("listEmpajax2");
	// response.sendRedirect("chat");
%>
</body>
</html>