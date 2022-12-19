package net.madvirus.spring4.chap08.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleChangeController {

	private LocaleResolver localeResolver;

	@RequestMapping("/changeLanguage")
	public String change(@RequestParam("language") String language,
			HttpServletRequest request, HttpServletResponse response) {
		Locale locale = new Locale(language);   // en 파라미터 받음
		
		localeResolver.setLocale(request, response, locale);
		return "redirect:/index.jsp";
	}

	//이 함수 이용해서 Locale을 변경하면 이후 요청에 대해서는 지정한 Locale로 메시지 등 로딩함
	public void setLocaleResolver(LocaleResolver localeResolver) {  
		this.localeResolver = localeResolver;
	}
	// Locale변경기능 지원않는  LocalResolver에서 setLocale실행하면 익셉션 발생 -> 지원하는가를 확인할 것
}
//그런데 Locale을 변경하기 위해 컨트롤러까지 만드는 것은 귀찮음 -> 웹요청 파라미터를 통해 손쉽게 Locale을 변경할 수 있는 방법이 있다!?