package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
@Component
public class DownloadView extends AbstractView {

	public DownloadView() {
		setContentType("application/download; charset=utf-8");
	}
	
	/*
	 * 파일 다운로드와 관련된 헤더를 알맞게 설정한 뒤에는 response의 OutputStream에 파일을 출력하면 된다.
	 * 위 코드에서는 스프링이 제공하는 유틸리티 클래스인 FileCopyUtils 클래스를 이용해서
	 * FileInputStream으로부터 데이터를 읽어와 response의 OutputStream을 통해서 출력하였다.
	*/

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = (File) model.get("downloadFile");

		response.setContentType(getContentType());	//파일 다운로드를 위해 컨텐트 타입을 “application/download”로 설정하고 있다.
		response.setContentLength((int) file.length()); //다운로드 되는 파일의 크기를 설정해준다

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),
					"iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\";"); //Content-Disposition 헤더를 이용해서 전송되는 파일의 이름을 명시하고 있다.
		response.setHeader("Content-Transfer-Encoding", "binary"); // 전송되는 데이터의 인코딩이 바이너리 타입이라는 것을 명시했다.
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
				}
		}
		out.flush();
	}

}