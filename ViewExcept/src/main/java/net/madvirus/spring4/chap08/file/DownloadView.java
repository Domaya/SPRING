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

	public DownloadView() {
		setContentType("application/download; charset=utf-8");  //다운로드를 하기 위한 View 클래스의 타입 설정 (application/download)
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = (File) model.get("downloadFile");  //컨트롤러에서 모델로 보내준 데이터 File타입으로 다운캐스팅해서 file에 담는다

		response.setContentType(getContentType());
		response.setContentLength((int) file.length());  //다운로드하는 파일의 크기 재기

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;					//브라우저 설정
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),
					"iso-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\";");
		/*
		Content-Disposition :
		HTTP Response 헤더의 종류. 브라우저에 Content가 
		inline으로 표시될지 attachment로 표시될지 여부를 나타냄
		
		inline : Web Page로, 혹은 Web Page 내에서 표시 (기본값)
		attachment : 로컬에 다운로드 & 저장 (대부분의 브라우저에서는 바로 다운로드가 되거나, “Save As” 다이얼로그가 표시됨(저장하는 창))
		filename : 파일 이름을 지정할 수 있음 (사용자)
		 */
		response.setHeader("Content-Transfer-Encoding", "binary");  //이진법 형태로 데이터 인코딩
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);        //스프링이 제공하는 FileCopyUtils클래스
		} finally {										  //지정한 Input스트림 내용을 지정한 Output 스트림에 복사하고 스트림을 닫는다
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
				}
		}
		out.flush(); //삭제한다
	}

}
