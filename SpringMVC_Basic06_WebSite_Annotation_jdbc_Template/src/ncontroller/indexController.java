package ncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	@RequestMapping("/index.htm")
	public String index() {
		return "index.jsp";
	}
}
