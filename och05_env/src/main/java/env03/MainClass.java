package env03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("1 Run");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class); //환경작업(@annotation을 1등으로 봄)부터 시작함(생성자먼저 실행X)
		
		System.out.println("4 adminConfig Before Run");
		AdminConnection connection = ctx.getBean("adminConfig", AdminConnection.class); //생성자 유무 확인
		System.out.println("5 adminConfig After Run");
		
		System.out.println("connection.getAdminId() adminID : " + connection.getAdminId());
		System.out.println("connection.getAdminPw() adminPW : " + connection.getAdminPw());
		System.out.println("connection.getSub_adminId() sub_adminID : " + connection.getSub_adminId());
		System.out.println("connection.getSub_adminPw() sub_adminPW : " + connection.getSub_adminPw());
		ctx.close();
	}

}
