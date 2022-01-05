package DI08;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public Student student1() {
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("수영");
		hobbies.add("물내리기");
		
		Student student = new Student("을지문덕", 55, hobbies);
		student.setHeight(170);
		student.setWeight(70);
		
		return student;
	}
}
