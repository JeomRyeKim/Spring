package env03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ApplicationConfig { //XML역할
	
//	<bean id="adminConnection" class="env02.AdminConnection">
//	<property name="adminId" >
//		<value>${admin.id}</value>
//	</property>
//	<property name="adminPw" >
//		<value>${admin.pw}</value>
//	</property>
//	<property name="sub_adminId" >
//		<value>${sub_admin.id}</value>
//	</property>
//	<property name="sub_adminPw" >
//		<value>${sub_admin.pw}</value>
//	</property>
//  </bean>
	//와 같은 의미가 밑에 @Value들
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPw;
	@Value("${sub_admin.id}")
	private String sub_adminId;
	@Value("${sub_admin.pw}")
	private String sub_adminPw;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
	
	System.out.println("2. Properties Run");
	Resource[] locations = new Resource[2];
	//밑에 두 줄이 <context:property-placeholder location="classpath:admin2.properties, classpath:sub_admin2.properties" />와 같음
	locations[0] = new ClassPathResource("admin3.properties"); // import,include했다고 생각하면 됨
	locations[1] = new ClassPathResource("sub_admin3.properties");
	//System.out.println("PropertySourcesPlaceholderConfigurer1 adminId->" + adminId);
	configurer.setLocations(locations);
	
	return configurer;
	}
	
	@Bean
	public AdminConnection adminConfig() {
		AdminConnection adminConnection = new AdminConnection();
		System.out.println("3. adminConfig Run");
		adminConnection.setAdminId(adminId);
		adminConnection.setAdminPw(adminPw);
		adminConnection.setSub_adminId(sub_adminId);
		adminConnection.setSub_adminPw(sub_adminPw);
		
		return adminConnection;
	}
}
