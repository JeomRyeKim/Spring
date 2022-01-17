package com.oracle.oBootAPI01.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootAPI01.domain.Member;
import com.oracle.oBootAPI01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import oracle.net.aso.m;

@RestController // Controller + ResponseBody(viewResolver를 찾아가지 않음, 객체면 JsonConverter or String이면 StringConverter)
@RequiredArgsConstructor // 지정된 속성들에 대해서만 생성자를 만들어 줌
public class JpaRestController {
	private final MemberService memberService;

//	public JpaRestController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	// Bad Api - 개인정보유출로 보안성 때문에 이렇게 코드를 짜면 안 됨
	@GetMapping("/restApi/v1/members")
	public List<Member> memberV1(){
		System.out.println("JpaRestController /restApi/v1/members start...");
		return memberService.getListAllMember(); // JsonConverter 때문에 json 값으로 아래처럼 나옴
//		[{"id":1,"name":"안중근","team":{"team_id":1,"name":"대한독립군"},"teamname":null,"teamid":null},
//		 {"id":2,"name":"이순신장군","team":{"team_id":2,"name":"삼도수군통제사"},"teamname":null,"teamid":null},
//		 {"id":21,"name":"강감찬","team":{"team_id":21,"name":"귀주"},"teamname":null,"teamid":null},
//		 {"id":22,"name":"대조영","team":{"team_id":22,"name":"대모산"},"teamname":null,"teamid":null},
//		 {"id":23,"name":"김유신","team":{"team_id":23,"name":"화랑도"},"teamname":null,"teamid":null}]
	}
	
	// Good API
	@GetMapping("/restApi/v2/members")
	public Result memberV2() {
		List<Member> findMembers = memberService.getListAllMember();
		// 자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나 - stream하면 확장한 for문처럼 안에서 여러건수가 돌아서 
		// findMembers.stream()가 m이라고 했을 때(실질적으론<Member>를 의미함) .getName()로 5건의 이름들이 다 각각 객체로 만들어져 있는 것 - new MemberRtnDto(안중근),new MemberRtnDto(이순신장군),new MemberRtnDto(강감찬)..
		// .stream() .map() .collect()가 그냥 식이라고 생각하면 됨! iterator로 돌리는거랑 맹 같지만 좀 더 식이 깔끔하다 생각하면 됨
		List<MemberRtnDto> memberCollect = findMembers.stream().map(m -> new MemberRtnDto(m.getName(), m.getId())).collect(Collectors.toList()); // collect : 묶어서 쓸 때
		// new Result(totCount, data);
		return new Result(memberCollect.size(), memberCollect); // {"totCount":5,"data":[{"name":"안중근","id":1},{"name":"이순신장군","id":2},{"name":"강감찬","id":21},{"name":"대조영","id":22},{"name":"김유신","id":23}]}
		//return new Result(memberCollect); // new Result(data);
	}
	
	// 1. Entity보안
    // 2. 유연성 --> Entity가 API에 의존적 X, 원하는 Data 생성 , 전달(필요한 데이터만 추출해서 사용)
    // T는 인스턴스를 생성할 때 구체적인 타입으로 변경
	@Data
	@AllArgsConstructor
	class Result<T>{ // 요기에 총 값이나 평균값 등 통계내서 받고 싶은 값을 추가로 적어주면 됨
		private int totCount;
		private T data; // 각각 들어있는 객체들이 모아져 있는 것 - 너네가 보내준 데이터 그대로 보내줄게!
	}

	@Data
	@AllArgsConstructor
	class MemberRtnDto{ // 원하는 데이터를 여기에다 선언하면 됨 
		private String name; // {"data":[{"name":"안중근"},{"name":"이순신장군"},{"name":"강감찬"},{"name":"대조영"},{"name":"김유신"}]}
		private Long id;	 // {"data":[{"name":"안중근","id":1},{"name":"이순신장군","id":2},{"name":"강감찬","id":21},{"name":"대조영","id":22},{"name":"김유신","id":23}]}
	}
}
