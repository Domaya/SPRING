package DI_02;

public class HelloApp {
	public static void main(String[] args) {
		MessageBean_en messagebean_en = new MessageBean_en();
		messagebean_en.sayHello("hong");
		
		MessageBean_kr messagebean_kr = new MessageBean_kr();
		messagebean_kr.sayHello("hong");
	}
}
