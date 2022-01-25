-<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
	<title>Chating</title>
	<style>
		*{
			margin:0;
			padding:0;
		}
		.container{
			width: 500px;
			margin: 0 auto;
			padding: 25px
		}
		.container h1{
			text-align: left;
			padding: 5px 5px 5px 15px;
			color: #FFBB00;
			border-left: 3px solid #FFBB00;
			margin-bottom: 20px;
		}
		.chating{
			background-color: #000;
			width: 500px;
			height: 500px;
			overflow: auto;
		}
		.chating .me{ /* 나의 채팅은 노란색, 오른쪽에 위치함 */
			color: #F6F6F6;
			text-align: right;
		}
		.chating .others{ /* 상대방의 채팅은 흰색, 왼쪽에 위치함 */
			color: #FFE400;
			text-align: left;
		}
		input{
			width: 330px;
			height: 25px;
		}
		#yourMsg{ /* 메시지 입력란은 show하기 전까지 안 보임 */
			display: none;
		}
		#yourNameDel{ /* 사용자명 삭제는 show하기 전까지 안 보임 */
			display: none;
		}
	</style>
</head>

<script type="text/javascript">
	var ws; // 웹소켓을 저장할 변수 선언

	function wsOpen(){
		console.log("wsOpen  location.host: " + location.host);
        var wsUri  = "ws://" + location.host + "/oBootMybatis03/chating"; // index.jsp로 들어가야 작동이 되고, 내장 톰캣(8189)이용시에는 /oBootMybatis03 빼줘야 함
        // WebSocket 프로토콜을 사용하여 통신하기 위해서는 WebSocket객체를 생성.(객체 선언하듯에 new로 생성) 
        // 객체는 자동으로 서버로의 연결
 		ws = new WebSocket(wsUri); // 이걸 부름으로써 연결이 됨
		wsEvt();
	}
		
	function wsEvt() {
		console.log("wsEvt  start... ");
		
		//소켓이 열리면 동작
		ws.onopen = function(data){
			console.log("wsEvt  소켓이 열리면 초기화 세팅하기..");
		}
		
		// onmessage는 메시지를 받으면 동작(발동)
		// 이름 등록시,메시지 전송시,사용자 삭제시 핸들러에서 jsonObj에 put했던 msg,youName,sessionId,type,userName등이 String형태로 data에 들어감
		ws.onmessage = function(data) { 
			
			var msg = data.data;
			var memberSave = false;
			alert("ws.onmessage->"+msg)
			if(msg != null && msg.trim() != ''){
				memberSave = false;
				// JSON 오브젝트를 자바스크립트 오브젝트로 변환
			    var jsonMsg = JSON.parse(msg); // JSON.parse() : JSON 문자열의 구문을 분석하고, 그 결과에서 JavaScript 값이나 객체를 생성
				// msg가 배열이고, 2개이상의 Count이면 , member 등록 대상 
                if (Array.isArray(jsonMsg)) {
                	if (Object.keys(jsonMsg).length > 1) {
                    	memberSave = true;
                       	alert("JSONArray jsonMsg Count->"+ Object.keys(jsonMsg).length);
                	}
                }
				// 파싱한 객체의 type값을 확인하여 getId값이면 초기 설정된 값이므로 채팅창에 값을 입력하는게 아니라
				// 추가한 태그 sessionId에 값을 세팅
				if(jsonMsg.type == "getId"){
					var sid = jsonMsg.sessionId != null ? jsonMsg.sessionId : "";
					if(sid != ''){
						$("#sessionId").val(sid); 
						// session User 등록 수행
						sendUser();
					}
				}else if(jsonMsg.type == "message"){
					// type이 message인 경우엔 채팅이 발생한 경우.
	                // 상대방과 자신을 구분하기 위해 여기서 sessionId값을 사용
	                // 최초 이름을 입력하고 연결되었을때, 발급받은 sessionId값을 비교하여 같다면 자기 자신이 발신한
	                // 메시지이므로 오른쪽으로 정렬하는 클래스를 처리하고 메시지를 출력.     
	                // 비교하여 같지 않다면 타인이 발신한 메시지이므로 왼쪽으로 정렬하는 클래스를 처리하고 메시지를 출력
	                // 보낸 사람의 sessionId를 가지고 비교함
					if(jsonMsg.sessionId == $("#sessionId").val()){ // 보낸 사람의 화면에서 처리 방법
						$("#chating").append("<p class='me'>나 :" + jsonMsg.msg + "</p>");	
					}else{ // 받는 사람의 화면에서 처리 방법
						$("#chating").append("<p class='others'>" + jsonMsg.userName + " :" + jsonMsg.msg + "</p>");
					}
				}else if(memberSave = true){ // "getId"도 아니고 "message"도 아니면 "userSave"만 남음 -> 멤버를 저장할 때
					alert("userSave");
					$('#member_sub').remove(); // 기존 멤버들 삭제함(새로 등록된 멤버들꺼까지 다 조회해서 다시 등록할 예정)
					//  memberSave = true 면  -->	User 등록일경우
					// div로 감싸주면 재정의시 삭제(Refresh)후 다시 생성 
					//var str = " <div id='member_sub' class='member_sub'> ";
					var str = " ";
					str  += " <select name='member' id='member_sub' class='member_sub'> ";
					str  += " <option value='ALL'>전체 </option> "; 
					$(jsonMsg).each( // 등록된 멤버가 없어지기 전까지 계속 돌림
						function(){
							var str2 = "";
				            // User를 선택하여 message전송 가능토록 member에 등록 
							if(jsonMsg.sessionId == $("#sessionId").val()){
								alert("내꺼임"+ $("#sessionId").val())
							} else {  // 타인 session일 경우 추가 등록 
								str2 += " <option value='"+this.sessionId + "'> "+this.userName  + "</option> "; 
								str  += str2 ;
							}
						}
					);
					str += " </select>"
					str += " </div><p>"
					$('#member').append(str);	
					memberSave = false;
					
				}else{
						console.warn("unknown type!");
				}
			}
		} // ws.onmessage = function(data) 끝

		document.addEventListener("keypress", function(e){ // 메시지를 보낼 때 보내기 버튼을 누르지 않고 엔터만으로도 전송이 되도록 처리 
			if(e.keyCode == 13){ //enter press (13 : enter 아스키코드)
				send();
			}
		});
	}

	function chatName(){ // 이름 등록하는 순간
		// 김준수
		var userName = $('#userName').val();
		console.log("chatName userName : " + userName);
		if(userName == null || userName.trim() == ""){
			alert("사용자 이름을 입력해주세요");
			$("#userName").focus();
		}else{
			wsOpen();
			$("#meName").append('나의이름 : ' + userName);
			$("#yourName").hide(); 		// 이름 등록하는 input들 숨겨놓기
			$("#yourMsg").show();  		// 숨겨져 있던 메시지 입력란 보이기
			$("#yourNameDel").show();	// 숨겨져 있던 사용자명 삭제란 보이기
		}
	}
	
    // 삭제시 처리 
	function chatNameDelete(){
    	var userName = $("#userName").val();
    	var sessionId = $("#sessionId").val();
    	console.log("chatNameDelete userName : " + userName);
    	console.log("chatNameDelete sessionId : " + sessionId);
    	if(userName == null || userName.trim() == ""){
    		alert("사용자 이름을 입력해주세요.");
    		$("#userName").focus();
    	}else{
    		wsDeleteUser(sessionId);
    		window.close();
    	}
	}
    
	// User Delete  Message 전송 
	function wsDeleteUser(sessionId) {
		var option={
				type: "userDelete",
				sessionId: sessionId
		}
		// 자바스크립트의 값을 JSON 문자열로 변환
		ws.send(JSON.stringify(option));
	}
	
	// User 등록  Message 전송 
	function sendUser() {
		var userOption ={
				type: "userSave",
				sessionId: $("#sessionId").val(),
				userName: $("#userName").val()
		}
		alert("sendUser start...");
		// 자바스크립트의 값을 JSON 문자열로 변환
		ws.send(JSON.stringify(userOption)); // 서버(핸들러)가 메시지를 받음
		// $('#chatting').val("");
	}
	
	// 전체 Message 전송 
	function send() {
		var option ={
			type: "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			yourName : $("#member_sub").val(),
			msg : $("#chatting").val()
		}
		// 자바스크립트의 값을 JSON 문자열로 변환
		ws.send(JSON.stringify(option));
		$('#chatting').val(""); // 메시지 영역 클리어(다시 작성할 수 있는 채팅 입력란을 만들어 줌)
	}
</script>
<body>
 <h1>chat View</h1>
	<div id="container" class="container">
		<h1>채팅</h1>
		<input type="hidden" id="sessionId" value="">
		<div id="meName"></div> <!-- 채팅화면 위에 나의이름: 사용자명이 뜨는 부분 -->
		<div id="chating" class="chating"> <!-- 채팅 글자가 보이는 검정색 네모  -->
		</div>
		<div id="member" class="member"> <!-- 사용자명 select-option으로 바뀌는 부분 -->
		</div>
		
		<div id="yourName">
			<table class="inputTable">
				<tr>
					<th>사용자명</th>
					<th><input type="text" name="userName" id="userName"></th>
					<th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
				</tr>
			</table>
		</div>
		<div id="yourNameDel">
			<table class="deleteTable">
				<tr>
					<th>사용자명 삭제</th>
					<th><input type="text" name="userName" id="userName"></th>
					<th><button onclick="chatNameDelete()" id="startBtn">이름 삭제</button></th>
				</tr>
			</table>
		</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">보내기</button></th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>