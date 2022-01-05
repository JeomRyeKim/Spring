package DI07;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //annotation(@) <- 전통적인 방식과 같고 더 많이 사용됨
public class ApplicationConfig {
	@Bean
	public Student student1() {
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("팝뮤직");
		hobbies.add("피아노");
		
		Student student = new Student("안예은", 27, hobbies);
		student.setHeight(160);
		student.setWeight(50);
		return student;
	}
	@Bean
	public Student student2() {
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("뮤지컬");
		hobbies.add("음악감상");
		
		Student student = new Student("김준수", 35, hobbies);
		student.setHeight(178);
		student.setWeight(70);
		return student;
	}
	
}
