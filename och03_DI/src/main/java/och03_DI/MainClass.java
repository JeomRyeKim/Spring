package och03_DI;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// 전통적
//		MyCalculator myCalculator = new MyCalculator();
//		myCalculator.setCalculator(new Calculator());
//		myCalculator.setFirstNum(10);
//		myCalculator.setSecondNum(2);
//		myCalculator.add();
//		myCalculator.sub();
		
		// DI setting
		String configLocation = "classpath:applicationCTX.xml"; // <- 여기에 값을 넣음
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation); //값이 들어있는 경로
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class); // 경로에 있는 <bean id="myCalculator"를 가져온 것
		//위의 3줄이 MyCalculator myCalculator = new MyCalculator();와 똑같은 것
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}

}
