package net.madvirus.spring4.chap08.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

//fileId 값이 1 일 때 파일을 downloadFile이란 이름 모델로 뷰에 전달 (View이름 : download)
@Controller
public class DownloadController implements ApplicationContextAware {  

	private WebApplicationContext context = null;  //

	@RequestMapping("/file/{fileId}")
	public ModelAndView download(@PathVariable String fileId, HttpServletResponse response) throws IOException {
		File downloadFile = getFile(fileId);
		if (downloadFile == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return new ModelAndView("download", "downloadFile", downloadFile);
	}

	private File getFile(String fileId) {
		String baseDir = context.getServletContext().getRealPath(
				"/WEB-INF/files");  																	//이 폴더에 다운로드가 될 파일이 위치
		if (fileId.equals("1"))																	    //IF문 = 파일 아이디가 1 일 때
			return new File(baseDir, "DO_ThumbsUp.jpg");						//해당 이름의 파일을 찾고 다운로드
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}

}
