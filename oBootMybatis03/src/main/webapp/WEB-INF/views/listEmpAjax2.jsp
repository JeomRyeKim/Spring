<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = request.getContextPath();
    System.out.println("context->"+context);
%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>


<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
   
    var contextPath='${pageContext.request.contextPath}';
	var src='${pageContext.request.contextPath}/images/';
	var contextPath='${pageContext.request.contextPath}';
	var i=2;

	

	/* RestController TEST */
	function getListDept(){
		var str="";
		var str2="";
		
		
		console.log("getListDept Run");
		alert("getListDept Run...");
		$.ajax(	// 파라미터가 없어서 data:가 필요없음
				{
					url:"<%=context%>/sendVO3", 
					dataType:'json',  
					success:function(data){ // data에 deptList를 넣어줌
						var jsondata = JSON.stringify(data); // stringify : json으로 던져준 객체를 String으로 바꿔줌
						alert("jsondata -> " + jsondata);
						$('#Dept_list').append(jsondata);
						str += "<select name='dept'>";
						// .each : 내부에서 객체 내용을 돌려서 반복해서 내보냄
						$(data).each( // 태그들을 통째로 <div>에 넣어주는 것
							function(){ // 이 안에서만 돌릴꺼니까 익명함수로 선언
								// str2 = " '" + this.deptno + "' " + this.dname + "<br>";
								str2 = "<option value='" + this.deptno + "'> " + this.dname + "</option> ";
								str += str2;
							}
						);
						str += "</select><p>"
						$('#Dept_list3').append(str);
					} 
				}
		);
		
	}
	
	function getDeptDelete(Vindex){
		console.log(Vindex);
		alert("empDept ->" + Vindex);
		var selEname = $('#ename'+Vindex).val(); /* value="${empDept.ename }"이기 때문에 해당 이름이 뜸  */
		alert("selEname -> " + selEname);
		// Ajax로 empnoDel
		var selEmpno = $('#empno'+Vindex).val();
		alert("selEmpno -> " + selEmpno);
		$.ajax(
				{
					url:"<%=context%>/empnoDelete",
					data:{empno : selEmpno , ename : selEname}, // 파라미터가 여러개일 때 이렇게 사용하면 됨
					dataType:'text', // 리턴값 delStatusStr가 String이라 text로 받는 것
					success:function(data){
						alert(".ajax getDempDelete data ->" + data);
						if(data == '1'){ // Mybatis Query 성공시 - delete : Delete된 행의 개수 (실패했다면 0) -> 1행이 삭제될꺼니 1이 뜰것이다
							// 성공하면 아래라인 수행 - 위에서 실제 DB에서 삭제처리하고 밑에는 화면에서 삭제처리만 해주는 것(영구적으로 삭제)
							$('#empDept'+Vindex).remove()  /* Delete Tag - 없으면 화면에 바로 반영이 안 됨 새로고침해야 삭제된 데이터가 화면에 반영이 됨 */
						}
					}
				}
		);
	}
		
 </script>
</head>
<body>
<h2>회원 정보</h2>
<table>
	<tr><th>번호</th><th>사번</th><th>이름</th><th>업무</th><th>부서</th><th>근무지</th></tr>
<c:forEach var="empDept" items="${listEmp}" varStatus="status">
	<tr id="empDept${status.index}"> <!-- index : listEmpAjax2 화면에서 뿌려지는 list들 위에부터 0,1,2... 내부적으로 번호를 갖고 있음 -->
		<td>empDept${status.index}</td>
		<td><input type="text" id="empno${status.index}" value="${empDept.empno }">${empDept.empno }</td>
		<td><input type="text" id="ename${status.index}" value="${empDept.ename }">${empDept.ename }</td> <!-- 내부적으로 id가 랜덤하게 계속 변함 -->
		<td>${empDept.job }</td>
		<td>${empDept.deptno } 
		    <input type="button" id="btn_idCheck2" value="부서Row Delete" onclick="getDeptDelete(${status.index})">
		</td>
		<td>${empDept.loc }</td>
	</tr>
</c:forEach>
</table>
	 
	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>

    RestController RestDept1 sendVO2: <input type="text" id="RestDept1"       readonly="readonly"><p>
    RestController RestDept2 sendVO2: <input type="text" id="RestDept2"       readonly="readonly"><p>
	

    <!--  RestController LISTVO3: <input type="text" id="RestDeptList"   readonly="readonly"><p> -->
	RestController LISTVO3: <input type="button" id="btn_Dept3"     value="부서명 LIST"  onclick="getListDept()"><p>
	                        <span id="RestDeptList"></span><p>
	                        			<div id="board_reply">
	Dept_list:	<div id="Dept_list"></div>

	Dept_list3:
	<div id="Dept_list3">

	</div>
</body>
</html>