package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

   public static void main(String[] args) {

      /*
      MyBean mybean = new MyBean();
      MyBean mybean2 = new MyBean("hong");
      MyBean mybean3 = new MyBean();
      // new할 때마다 각각 생성
      System.out.println(mybean); // DI_05_Spring.MyBean@21bcffb5
      System.out.println(mybean2); // DI_05_Spring.MyBean@380fb434
      System.out.println(mybean3); // DI_05_Spring.MyBean@668bc3d5
      
      System.out.println("----------------------------");
      
      Singleton single = Singleton.getInstance();
      Singleton single2 = Singleton.getInstance();
      Singleton single3 = Singleton.getInstance();
      // DI_05_Spring.Singleton@79b4d0f 주소 동일
      System.out.println(single);
      System.out.println(single2);
      System.out.println(single3);
      */
      
      ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml");
      MyBean m = context.getBean("mybean", MyBean.class);
      MyBean m2 = context.getBean("mybean", MyBean.class);
      MyBean m3 = context.getBean("mybean", MyBean.class);
      // m, m2, m3 전부 같은 주소 DI_05_Spring.MyBean@41a2befb
      // getBean 함수는 new가 아니라 가져오는 것이므로 주소가 동일
      
      Singleton single = context.getBean("single", Singleton.class);
      Singleton single2 = context.getBean("single", Singleton.class);
      // DI_05_Spring.Singleton@6ff29830
      System.out.println(single);
      System.out.println(single2);
      
      System.out.println(single + " , " + single2);
      
      /*
      getBean()
      1. 컨테이너 안의 객체를 리턴 (new 아님 !!)
      2. return type Object (타입에 맞는 casting)
      3. 스프링 컨테이너 안의 객체들은 default로 Singleton
      4. 예외적으로 getBean()이 new할 수도 있음 (거의 사용 X)
      */
   }

}