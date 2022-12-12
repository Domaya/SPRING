package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//public class HelloController implements Controller { <- 이렇게 처리하면 요청 하나당 컨트롤러 하나씩 필요함
/*
 * 게시판 : 목록보기 >> listController
 * 				글쓰기 >> WriteController
 *             수정하기 >> EditController
 *  
 */


/*
 * @Controller
 * public class HelloController
 * 
 * method단위로 매핑
 * 하나의 controller 안에 여러개의 함수를 생성 각각 매핑
 * 
 * 게시판 : 목록보기 >> public void list()
 * 				글쓰기 >> public void write()
 * 				수정하기 >> public void edit()
 */

@Controller
public class HelloController {
	public HelloController() {
		System.out.println("HelloController 생성자 호출");
		
	}
	
	@RequestMapping("/hello.do") // <a href="hello.do"></a>요청이 오면 함수 실행
	public ModelAndView hello() {
		System.out.println("[hello.do method call]");
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", getGreeting());
		mv.setViewName("Hello");
		return mv;
	}
	
	private String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String data = "";
		if(hour >= 6 && hour <= 10) {
			data = "학습시간";
		}else if(hour >= 11 && hour <= 13) {
			data = "배고픈 시간";
		}else if(hour >= 14 && hour <= 18) {
			data = "졸린 시간";
		}else {
			data = "go home";
		}
		return data;
	}
}
