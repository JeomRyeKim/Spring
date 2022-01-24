<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = request.getContextPath();
%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script> <!-- 다운로드 방식 -->
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> <!-- CDN방식 -->

<script type="text/javascript">
    var contextPath='${pageContext.request.contextPath}';
	var src='${pageContext.request.contextPath}/images/';
	var contextPath='${pageContext.request.contextPath}';
	var i=2;
	var str="";
	var str2="";
	
	/* EmpController */
	function getDeptName(Vdeptno){
		console.log(Vdeptno);
		//alert("Vdeptno->" + Vdeptno);
		
		<%-- <%=context%> 안 해줘도 되는데 해줘야 더 정확함(views안에 여러 폴더 왔다갔다하면 위치를 잃는 경우가 있어서 필요)  --%>
		<%-- url : url을 찾아감 --%>
		<%-- data : 파라미터 --%>
		<%-- dataType : 리턴 타입 (객체는 json, String으로 받으면 text 두 개 주로 사용됨)--%>
		<%-- success:function(data) : 리턴값(결과값) <- data에 Strng 부서명이 들어감 --%>
		/* $ : 제이쿼리 함수 / # : id / . : class */
		
		$.ajax(	
				{ url:"<%=context%>/getDeptName", 
				  data:{deptno : Vdeptno},  
				  dataType:'text',  
				  success:function(data){   
					  //alert("success ajax Data -> " + data);
					  $('#deptName').val(data);  /* input Tag */
					  $('#msg').html(data);		 /* span Tag */
				  }
				}
		);
	}
	
	
	/* RestController TEST */
	function getDept(Vdeptno){ // getDept(10) 파라미터가 들어감 그래서 없으면 mno = 10이 안 나옴!
		alert("Vdeptno -> " + Vdeptno); // Vdeptno -> 10
		$.ajax(	
				{ url:"<%=context%>/sample/sendVO2", 
				  data:{deptno : Vdeptno},  
				  dataType:'json',  
				  success:function(data){ // data에 return된 vo객체가 들어가는 것 
					  //alert(".ajax getDept Data -> " + data);
					  str = data.firstName + " " + data.lastName + " " + data.mno;
					  alert(".ajax getDept str -> " + str); // .ajax getDept str -> 길동 홍 10
					  $('#RestDept').val(str);  /* input Tag */
				  }
				}
		);
	}

 </script>
</head>
<body>
<h2>회원 정보</h2>
<table>
	<tr><th>사번</th><th>이름</th><th>업무</th><th>부서</th><th>근무지</th></tr>
	<c:forEach var="empDept" items="${listEmp}">
		<tr><td>${empDept.empno }</td><td>${empDept.ename }</td>
			<td>${empDept.job }</td>
			<td>${empDept.deptno} 							  <!-- onmouseover : 마우스를 버튼 위에 올리면 -->
			    <input type="button" id="btn_idCheck" value="부서명" onmouseover="getDeptName(${empDept.deptno })">
			</td>
			<td>${empDept.loc }</td>
		</tr>
	</c:forEach>
</table>
	 
	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>

    RestController sendVO2: <input type="text" id="RestDept"          readonly="readonly"><p>
	RestController sendVO2: sendVO2<input type="button" id="btn_Dept"     value="부서명"  onclick="getDept(10)"><p>


</body>
</html>