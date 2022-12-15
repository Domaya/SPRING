package net.madvirus.spring4.chap08.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware {
	
	/*
	 * 웹 어플리케이션은 JSP 이외의 기능으로 
	 * 파일 다운로드 기능 제공과 (파일 다운로드를 위해 커스텀 View 클래스 구현하는 방법)
	 * 동적으로 엑셀이나 PDF 파일을 생성하는 경우도 있다. (AbstracExcelView 클래스와 AbstractPdfView 클래스를 이용해 엑셀과 PDF를 동적 생성 방법)
	 */

	private WebApplicationContext context = null;
	
	@RequestMapping("file/{fileId}")
	public String downloadV2(@PathVariable String fileId, Model model, HttpServletResponse response) throws IOException {
		// 단순히 fileId 값이 '1'이면 /WEB-INF/files 디렉토리에 있는
		// "객체지향JCO14회.zip" 파일을 "downloadFile"이라는 모델 이름으로 view에 전달함
		File downloadFile = getFile(fileId);
		if (downloadFile == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		model.addAttribute("downloadFile", downloadFile);

		// 사용하는 view 이름은 "download"
		return "download";
	}

	//@RequestMapping("/file/{fileId}")
	public ModelAndView download(@PathVariable String fileId, HttpServletResponse response) throws IOException {
		// 단순히 fileId 값이 '1'이면 /WEB-INF/files 디렉토리에 있는
		// "객체지향JCO14회.zip" 파일을 "downloadFile"이라는 모델 이름으로 view에 전달함
		File downloadFile = getFile(fileId);
		if (downloadFile == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// 사용하는 view 이름은 "download"
		return new ModelAndView("download", "downloadFile", downloadFile);
	}

	private File getFile(String fileId) {
		String baseDir = context.getServletContext().getRealPath("/WEB-INF/files");
		if (fileId.equals("1"))
			return new File(baseDir, "객체지향JCO14회.zip");
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
	
	/*
	(JSP는 파일 다운로드와 같은 기능을 구현하기 보다는 HTML과 같은 결과를 보여주기에 적합한 view 구현 기술임) 
	보통 HTML 응답이 아닌 경우에는 그에 알맞은 전용 뷰 클래스를 구현함!! 
	또한 BeanNameViewResolver를 이용해서 커스텀 뷰 클래스를 사용할 수 있도록 알맞게 설정해야함 
	
	<!-- DownloadController bean 등록 --> 
	<bean class="net.madvirus.spring4.chap08.file.DownloadController"> </bean> 
	<!-- DownloadView bean 등록 --> 
	<bean class="net.madvirus.spring4.chap08.file.DownloadView" id="download"></bean>	 
	
	<!-- 
		<bean class="org.springframework.web.servlet.view.BeanNameViewResolver"> 
		    <property name="order" value="1"/> 
	     </bean>  
	--> 
	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver"/>
	*/
}
