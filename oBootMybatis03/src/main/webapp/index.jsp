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
	response.sendRedirect("upLoadFormStart"); /* UpLoad Test */
	// response.sendRedirect("members");
	// response.sendRedirect("interCepterForm"); /* 인터셉터 : 중간에 채가는것 */
	// response.sendRedirect("chat");
	// response.sendRedirect("sendVO3");
	// response.sendRedirect("listEmpAjax");
	// response.sendRedirect("listEmpajax2");
%>
</body>
</html>