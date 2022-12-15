package file;

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

	//위 코드는 단순히 fileId값이 “1”이면 /WEB-INF/files 디렉토리에 있는
	//“객체지향JOO14회.zip”파일을 “downloadFile”이라는 모델 이름으로 뷰에 전달한다.
	// -> 실습을 위해, 'airplane.jpg'로 변경
	//사용하는 뷰 이름은 “download”이다
	
	private WebApplicationContext context = null;



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
	
	
	@RequestMapping("file/{fileId}")
	public ModelAndView download(@PathVariable String fileId, HttpServletResponse response) throws IOException {
		File downloadFile = getFile(fileId);
		if (downloadFile == null) { //다운로드 파일이 없으면 null 리턴
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return new ModelAndView("download", "downloadFile", downloadFile);
	}

	private File getFile(String fileId) {
		String baseDir = context.getServletContext().getRealPath(
				"/WEB-INF/files");
		System.out.println(baseDir);
		if (fileId.equals("1")) {
			return new File(baseDir, "airplane.jpg");
		}
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}

}