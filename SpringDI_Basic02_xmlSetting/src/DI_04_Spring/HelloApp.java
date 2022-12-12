package DI_04_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		/*
		 * //java 코드 MessageBeanImpl messagebean = new MessageBeanImpl("hong");
		 * messagebean.setGreeting("greeting"); messagebean.sayHello(null);
		 */
		
		//spring
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_04_Spring/DI_04.xml");
		//컨테이너 만들고 xml read 해서 객체 생성하고 주입 작업 END
		MessageBean messagebean = context.getBean("m1", MessageBean.class);
		messagebean.sayHello();
		
	}
	

}
