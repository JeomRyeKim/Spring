<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <% %>으로 인해 서블릿이 되어버림  --%>
	<!-- context가 없어도 문제는 없음. 보통때는 적용 안 해도 되지만, sub폴더가 많아질 때 자기 위치를 잘 못 찾아갈 때가 생길 때 context를 넣어주기만 하면 해결되는 경우가 많음  -->
	<!-- context에 index.jsp의 진짜 경로를 넣음 -->
	<%
		String context = request.getContextPath();
	%>
	context : <%=context%> <br> <!-- /mvc042 -->
<%-- 	<form action="<%=context%>/studentView1" method="post"> --%>
	<form action="<%=context%>/studentView2" method="post"> <!-- 여기서 선언한 name과 DTO의 변수명이 같으면 form에서 입력한 값들이 DTO로 자동 저장됨! -->
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br>
		학년 : <input type="text" name="grageNum"><br>
		반 :  <input type="text" name="classNum"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>