package sdlc02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX02.xml");
		Student student1 = ctx.getBean("student", Student.class); //같은 bean을 사용할 때 같은 주소값을 사용함
		System.out.println("student1 이름 : " + student1.getName()); //같은 bean을 다른 객체로 사용할 때 scope="prototype"로 사용하면 됨
		System.out.println("student1 나이 : " + student1.getAge());
		
		System.out.println("==================================");
		Student student2= ctx.getBean("student", Student.class);
		student2.setName("강유");
		student2.setAge(55);
		
		System.out.println("student2 이름 : " + student2.getName());
		System.out.println("student2 나이 : " + student2.getAge());
		
		System.out.println("==================================");
		System.out.println("student1 이름 : " + student1.getName());
		System.out.println("student1 나이 : " + student1.getAge());
		
		if(student1.equals(student2)) {
			System.out.println("student1 == student2");
		}else {
			System.out.println("student1 != student2");
			
		}
		ctx.close();
	}

}
