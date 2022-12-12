package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

   public static void main(String[] args) {
      /*
        NewRecordView view = new NewRecordView(100, 50, 40); view.print();
       
      
      NewRecordView view = new NewRecordView();
      //놀다가
      //NewRecord 객체가 필요하면
      NewRecord record = new NewRecord(100, 50, 50);
      view.setRecord(record); //의존 객체의 주소 주입(setter 함수)
      */
      
      
      /*
       (스프링은 자신만의 메모리 공간이 필요해요)
       1. SpringFramework 가 제공하는 컨테이너에 객체 생성 (메모리 생성 :  IOC(제어의 역전)) 컨테이너)
       >>ApplicationContext context = new ClassPathXmlApplicationContext
       2. 컨테이너를 만들고 그 메모리에 필요한 객체를 생성하고 조립(주입) ... 자동 소멸
             컨테이너가 생성되고 DIConfig.xml read 하고 컴파일 해요 ....
          DIConfig.xml 컴파일하면 bean 객체를 생성하고 필요한 객체를 주입받고 .... 작업이 끝남.
       
       */
      
      ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
      //Spring 필요한 메모리 공간을 생성하고 생성된 메모리에 DIConfig.xml read 해서 객체 생성하는  코드
      
      //컨테이너 안에 필요한 객체가 생성되어 있어요
      //컨테이너 안에 여러개 블럭들이 생성되어 있어요
      RecordView view = (RecordView)context.getBean("view");
      
      view.input();
      view.print();
   }

}