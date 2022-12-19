package net.madvirus.spring4.chap08.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class LocaleChangeController2 {

	@RequestMapping("/changeLanguage2")
	public String change(@RequestParam("lang") String language,
			HttpServletRequest request, HttpServletResponse response) {
		Locale locale = new Locale(language);
		LocaleResolver localeResolver = RequestContextUtils    //RequestContextUtils 클래스는 웹요청 관련된 LocaleResolver를 
                .getLocaleResolver(request);									  //구할 수 있는 메소드 제공하므로 컨트롤러1의 코드를 이렇게 쓸 수도 있다

		localeResolver.setLocale(request, response, locale);
		return "redirect:/index.jsp";
	}
				// Locale변경기능 지원않는  LocalResolver에서 setLocale실행하면 익셉션 발생 -> 지원하는가를 확인할 것
}



//그런데 Locale을 변경하기 위해 컨트롤러까지 만드는 것은 귀찮음 -> 웹요청 파라미터를 통해 손쉽게 Locale을 변경할 수 있는 방법이 있다!?