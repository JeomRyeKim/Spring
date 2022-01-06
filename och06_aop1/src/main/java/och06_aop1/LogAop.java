package och06_aop1;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop { //횡단 관심사
	// Around Advice에서 사용할 공통기능 메서드는,대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출
	// Proxy
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable { //joinPoint : 핵심 관심사와 종단 관심사가 주 프로그램의 어디에 횡단할 것인지를 나타내는 위치
		//signatureStr : 핵심관심사 메소드 명
		String signatureStr = joinPoint.getSignature().toString();
		// 핵심 관심사의 수행 메소드
		System.out.println(signatureStr + " is start.");
		long st = System.currentTimeMillis(); //수행 시간 1/1000초 단위로 얻음
		
		try {
			// 핵심관심사 수행(대행) : Proxy역할
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis(); //성능을 측정하는 것
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + "경과시간 : " + (et - st));
		}
		
	}
	
}
