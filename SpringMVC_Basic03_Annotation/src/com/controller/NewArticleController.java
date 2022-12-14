package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
-클라이언트가 요청을 보냄
1.화면 좀 보여줘
2.처리해주세요(글쓰기 입력 처리, 로그인 완료 처리) : writeok.do

요청 주소가 : write.do >> 화면
요청 주소가 : writeok.do >> 처리 .....

-클라이언트 요청
-1개의 주소를 가지고 판단
-요청 주소 하나로(화면, 처리) 판단 > 근거 > 전송 방식(GET, POST)
>> http://localhost:8090/SpringMVC_Basic03_Annotation/article/newArticle.do
같은 주소인데
전송 방식이 GET이라면 >> 화면 보여달라는거구나 >> VIEW 제공
전송 방식이 POST라면 >> 데이터 처리해달라는거구나 >> 서비스 처리 후 화면 제공
*/

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	private ArticleService articleservice ;
	
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}

	//@RequestMapping(method=RequestMethod.GET) //화면 보여주세요
	@GetMapping
	public String form() {
		//Spring함수의 return 타입이 String view의 주소를 의미 ....
		System.out.println("GET");
		return "article/newArticleForm";
		// /WEB-INF/views  + article/newArticleForm + .jsp
	}
	
	//1.데이터 받는 가장 전통적인 방법 >> HttpServletRequest request >> 코드량 증가 >> spring 용
	//@RequestMapping(method = RequestMethod.POST) //처리해주세요
	/* 이거는 옛날 방식
	@PostMapping
	public ModelAndView submit(HttpServletRequest request) {
		
	      System.out.println("POST");
	      
	      NewArticleCommand article = new NewArticleCommand();
			article.setParentId( Integer.parseInt(request.getParameter("parentId")));
			article.setTitle(request.getParameter("title"));
			article.setContent(request.getParameter("content"));
			
			//NewArticleController 가 service 필요해 
			this.articleservice.writeArticle(article);
			//처리완료
			
			ModelAndView mv = new  ModelAndView();
			mv.addObject("newArticleCommand", article);
			mv.setViewName("article/newArticleSubmitted");
			
	      return mv;
	}
	*/
	//2. Spring에서 parameter를 DTO 객체로 받기
	//2.1 자동화 >> 전제조건 >> input name="" 같이 DTO 객체의 member field명과 동일
	/* 이것보다 더 자동할거임ㅋ
	@PostMapping
	public ModelAndView submit(NewArticleCommand command) {
		//1. 자동 DTO 객체 생성 NewArticleCommand = new NewArticleCommand();
		//2. 넘어온 파라미터 값을 DTO에 setter함수를 사용해서 자동 주입
		//3. NewArticleCommand command 객체 IOC 컨테이너 안에 자동 생성 id=""
		this.articleservice.writeArticle(command);
		
		//처리완료
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", command);
		mv.setViewName("article/newArticleSubmitted");
		return mv;
	}
	*/
	@PostMapping
	public String submit(@ModelAttribute("Articledata") NewArticleCommand command) {
		this.articleservice.writeArticle(command);
		
		/*ModelAttribute어노테이션이 아래 역할을 대체한 것
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", command);
		
		그리고 return어쩌고가 setview대신함
		 */
		
		return "article/newArticleSubmitted";
	}
	
}
