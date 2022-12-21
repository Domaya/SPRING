package kosa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kosa.vo.Employee;
import kosa.vo.TestVO;

@Controller
public class AjaxController {
	
	//View 인터페이스 부모타입
	@Autowired
	public View jsonview;
	
	//command=AjaxTest&name=java&arr="+array
	@RequestMapping("json.kosa")
	public View jsonkosa(String command, String name, String[] arr, ModelMap map) {
		
		System.out.println("command : " + command);
		System.out.println("name : " + name);
		System.out.println("arr : " + arr);
		System.out.println("Arrays.toString() : " + Arrays.deepToString(arr));
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("치킨");
		list.add("맥주");
		list.add("피자");
		
		map.addAttribute("menu", list); //View단에 전달
		//{:}
		//{"menu":Array}
		
		return jsonview;
		//<bean name="jsonview"> 객체와 동일한 이름
	}
	
	@RequestMapping("json2.kosa")
	public View jsonkosa(String command, String name, ModelMap map) {
		System.out.println("command : " + command);
		System.out.println("name : " + name);
		
		List<TestVO> list = new ArrayList<TestVO>();
		
		TestVO vo = new TestVO();
		vo.setBeer("라거");
		vo.setFood("골뱅이");
		
		list.add(vo);
		
		TestVO vo2 = new TestVO();
		vo2.setBeer("카스");
		vo2.setFood("치킨");
		
		list.add(vo2);
		
		map.addAttribute("menu", list);
		//{"menu":[{beer:"라거",food:"골뱅이"}, {beer:"카스",food:"치킨"}]}
		return jsonview;
	}
	
	//Employee 객체 2개 만들어서 List형태로 반환하는 비동기 처리 함수 생성해서 테스트
	@RequestMapping("json3.kosa")
	public View jsonkosa(ModelMap map) {
		
		List<Employee> list = new ArrayList<Employee>();
		
		Employee emp = new Employee();
		emp.setEmail("hjdo0211@naver.com");
		emp.setFirstname("DO");
		emp.setFirstname("HyeonJung");
		list.add(emp);
		
		Employee emp2 = new Employee();
		emp2.setEmail("kosa@smu.ac.kr");
		emp2.setFirstname("SANG");
		emp2.setLastname("MYUNG");
		list.add(emp2);
		
		map.addAttribute("data", list);
		
		return jsonview;
	}
	// Employee 객체 2개 만들어서 List 형태로 반환하는 비동기 처리 함수 생성
	   @RequestMapping("json3.kosa")
	   public View jsonkosa(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
	      
	      List<Employee> list = new ArrayList<Employee>(); // 객체 배열
	      
	      Employee employee = new Employee();
	      employee.setFirstname("firstname1");
	      employee.setLastname("lastname1");
	      employee.setEmail("email1");
	      list.add(employee);
	      
	      Employee employee2 = new Employee();
	      employee2.setFirstname("firstname2");
	      employee2.setLastname("lastname2");
	      employee2.setEmail("email2");
	      list.add(employee2);

	      map.addAttribute("data", list);
	      
	      return jsonview;
	   }
	   
	   @RequestMapping("json4.kosa")
	   public void typeFunction(@RequestParam(value="", required=false) String[] aaa,
	         @RequestParam(value="", required=false) String[] bbb, 
	         String ccc) {
	      
	      for (String str : aaa) {
	         System.out.println(str);
	      }
	      System.out.println(bbb);
	      System.out.println(ccc);
	      
	   }
	
}
