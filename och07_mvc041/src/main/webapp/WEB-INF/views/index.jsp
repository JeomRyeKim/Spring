<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function chk(){
		if(!frm.id.value){
			alert("id 입력하세요!!!");
			frm.id.focus();
			return false;
		}
		return true;
	}
</script>
<body>
	<form name="frm" action="student" method="post" onsubmit="return chk()"> <!-- index에는 post방식으로 되어있는데 컨트롤러가 get방식으로 되어있어서 오류남 -->
<!-- 	<form action="student" method="get"> 기본이 get방식이라 method 생략해도 됨 -->
		student id : <input type="text" name="id"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>