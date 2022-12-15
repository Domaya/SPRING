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
Locale 변경을 지원하지 않는 LocaleResolver의 setLocale() 메서드를 실행하면 Exception이 발생함
따라서 Locale 변경 기능을 사용할 때에는 사용하는 LocaleResolver가 Locale 변경을 지원하는지 확인해야함

5.5 LocaleChangeInterceptor를 이용한 Locale 변경
Locale을 변경하기 위해 별도의 컨트롤러 클래스를 개발한다는 것은 다소 성가신 일임
이 경우, 스프링이 제공하는 LocaleChangeInterceptor 클래스를 사용하면 웹 요청 파라미터를 이용해서 손쉽게 Locale을 변경할 수 있음
LocaleChangeInterceptor 클래스는 HandlerInterceptor로서 다음과 같이 설정함
아래 설정에서 paramName 프로퍼티는 Locale 언어를 변경할 때 사용될 요청 파라미터의 이름을 지정함

<interceptors>
	<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
		<property name="paramName" value="language" />
	</bean>
</interceptors>

위 코드에서는 paramName 프로퍼티의 값으로 language를 설정했는데, 이 경우 language 요청 파라미터를 사용해서 Locale을 변경할 수 있음
[ http:// ~~~~~~ /auth/login?language=en ]

LocaleChangeInterceptor 는 ParamName 프로퍼티로 설정한 요청 파라미터가 존재할 경우, 파라미터의 값을 이용해서 Locale을 생성한 뒤,
LocaleResolver를 이용해서 Locale을 변경함, 이후 요청에서는 변경된 Locale이 적용됨


*/

@Controller
public class LocaleChangeController2 {

	@RequestMapping("/changeLanguage2")
	public String change(@RequestParam("lang") String language,
			HttpServletRequest request, HttpServletResponse response) {
		Locale locale = new Locale(language);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, locale);
		return "redirect:/index.jsp";
	}
}
