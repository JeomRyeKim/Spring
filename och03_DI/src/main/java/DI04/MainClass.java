package DI04;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX4.xml";
		AbstractApplicationContext ac = new GenericXmlApplicationContext(configLocation);
		
		CollectionBean collectionBean = ac.getBean("collectionBean", CollectionBean.class);
		Map<String, String> addressList = (Map<String, String>)collectionBean.getAddressList();
		System.out.println("홍길동 주소:" + addressList.get("홍길동")); //키를 부르면 값을 받을 수 있음
		System.out.println("중앙 주소:" + addressList.get("중앙"));
	}

}
