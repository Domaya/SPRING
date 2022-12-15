package net.madvirus.spring4.chap08.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/*
스프링이 제공하는 <spring:message> 커스텀 태그는 웹 요청과 관련된 언어 정보를 이용해서 알맞은 메시지를 출력함

실제로, 스프링 MVC는 LocaleResolver 를 이용해서 웹 요청과 관련된 Locale을 추출하고,
이 Locale 객체를 이용해서 알맞은 언어의 메시지를 선택함
스프링이 제공하는 LocaleResolver를 사용해서 Locale을 변경하는 방법 아라보쟈

5.1 LocaleResolver 인터페이스
o.s.web.servlet.LocaleResolver 인터페이스는 'resolveLocale'과 'setLocale' 추상 메서드로 구성되어 있음

'resolveLocale' 메서드는 요청과 관련된 Locale을 반환함, 'DispatcherServlet'은 등록되어 있는 LocaleResolver의 'resolveLocale' 메서드를
호출해서 웹요청을 처리할 때 사용할 Locale을 구함

'setLocale' 메서드는 Locale을 변경할 때 사용함, 예를 들어 쿠키나 HttpSession에 Locale 정보를 저장할 때에 이 메서드가 사용됨

5.2 LocaleResolver의 종류
1. AcceptHeaderLocaleResolver
2. CookieLocaleResolver
3. SessionLocaleResolver
4. FixedLocaleResolver

5.3 LocaleResolver 등록
'DispatcherServlet'은 이름이 ******'localeResolver'****** 인 빈을 LocaleResolver로 사용함

컨트롤러 클래스는 다음과 같이 LocaleResolver의 setLocale() 메서드를 호출해서 클라이언트의 웹 요청을 위한 Locale을 변경할 수 있음
LocaleResolver를 이용해서 Locale을 변경하면, 이후 요청에 대해서는 지정한 Locale을 이용해서 메시지를 로딩함

-----------------------------------------
RequestContextUtils 클래스는 웹 요청과 관련된 LocaleResolver를 구현할 수 있는 메서드를 제공하고 있으므로
LocaleChangeController 는 LocaleChangeController2로 변경될 수 있음

*/

// 컨트롤러 클래스는 다음과 같이 LocaleResolver의 setLocale() 메서드를 호출해서 클라이언트의 웹 요청을 위한 Locale을 변경할 수 있음
@Controller
public class LocaleChangeController {

	private LocaleResolver localeResolver;

	@RequestMapping("/changeLanguage")
	public String change(@RequestParam("lang") String language,
						 HttpServletRequest request, 
						 HttpServletResponse response) 
	{
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		return "redirect:/index.jsp";
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
}
