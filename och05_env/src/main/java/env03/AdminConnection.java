package env03;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class AdminConnection implements InitializingBean, DisposableBean {
	private String adminId;
	private String adminPw;
	private String sub_adminId;
	private String sub_adminPw;

	public void destroy() throws Exception { //객체 삭제(시스템 종료)되면서 마지막에 실행되는 메소드
		System.out.println("AdminConnection destroy() 소멸자 소멸전...");
	}

	public void afterPropertiesSet() throws Exception { //기본생성자 생성 직후 바로 실행되는 메소드
		System.out.println("AdminConnection afterPropertiesSet() 생성자 생성이후...");
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	public String getSub_adminId() {
		return sub_adminId;
	}

	public void setSub_adminId(String sub_adminId) {
		this.sub_adminId = sub_adminId;
	}

	public String getSub_adminPw() {
		return sub_adminPw;
	}

	public void setSub_adminPw(String sub_adminPw) {
		this.sub_adminPw = sub_adminPw;
	}
	
}
