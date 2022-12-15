package net.madvirus.spring4.chap08.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	
	/*
	파일 다운로드를 구현하려면 컨텐트 타입을 application/octet-stream과 같이 다운로드로 위한 타입으로 설정해야함
	다운로드 받는 파일 이름을 알맞게 설정하려면 Content-Disposition 헤더의 값을 알맞게 설정해야함
	*/

	public DownloadView() {
		setContentType("application/download; charset=utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			                               HttpServletRequest request, 
			                               HttpServletResponse response) throws Exception 
	{
		File file = (File) model.get("downloadFile");

		// 파일 다운로드를 위해 컨텐트 타입을 "application/download"로 설정하고 있음
		response.setContentType(getContentType());
		// 다운로드 되는 파일의 크기 설정
		response.setContentLength((int) file.length());

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
		}
		
		// Content-Disposition 헤더를 이용해서 전송되는 파일의 이름을 명시
		//다운로드 받는 파일 이름을 알맞게 설정하려면 Content-Disposition 헤더의 값을 알맞게 설정해야함
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		// 전송되는 데이터의 인코딩이 "binary" 타입이라는 것을 명시
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// response 의 OutputStream 에 파일을 출력하는 과정
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			// 스프링이 제공하는 유틸리티 클래스인 FileCopyUtils 클래스를 이용해서 위에 선언한 FileInputStream fis로부터
			// 데이터를 읽어와 response 의 OutputStream 을 통해 출력
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ex) {
				}
			}
		}
		out.flush();
	}
}
