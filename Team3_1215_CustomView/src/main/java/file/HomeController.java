package file;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	   @RequestMapping("/")
	   public String rending() {
	      System.out.println("rending");
	      return "home";
	      //return "/WEB-INF/views/index.jsp";
	   }
	
	@RequestMapping("/index.htm")
	public String index() {
		return "home";
	}
	
}
