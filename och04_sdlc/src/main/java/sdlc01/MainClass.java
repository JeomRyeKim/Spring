package sdlc01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		System.out.println("1 ctx.load Before");
		ctx.load("classpath:applicationCTX01.xml");
		System.out.println("2 ctx.load After");
		// 실제 Bean 생성
		ctx.refresh();
		System.out.println("2 ctx.refresh After");
		ctx.close();
		System.out.println("2 ctx.close After");
	}

}
