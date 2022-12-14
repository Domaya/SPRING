package ncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	   @RequestMapping("/")
	   public String rending() {
	      System.out.println("rending");
	      return "index";
	      //return "/WEB-INF/views/index.jsp";
	   }
	
	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}
}
