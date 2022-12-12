package DI_Annotation_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //DI_Config.xml과 같은 역할
public class ConfigContext {
	
	//xml <bean id="user" class="DI_Annotation_04_User">
	//자바 코드에서는 함수를 통해서 객체를 리턴
	@Bean //너는 빈 객체를 생성하는 함수야
	public User user() {
		return new User();
	}
	
	@Bean
	public User2 user2() {
		return new User2();
	}
}
