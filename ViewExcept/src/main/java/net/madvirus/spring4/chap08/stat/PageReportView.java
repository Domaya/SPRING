package net.madvirus.spring4.chap08.stat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PageReportView extends AbstractPdfView {
	private String fontPath = "c:\\windows\\Fonts\\malgun.ttf";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
													Document document, PdfWriter writer, HttpServletRequest request,
																				HttpServletResponse response) throws Exception {
		List<PageRank> pageRanks = (List<PageRank>) model.get("pageRankList");  //컨트롤러에서 모델로 받은 데이터 다운캐스팅
		Table table = new Table(2, pageRanks.size() + 1);  //PDF 파일 내의 테이블 행과 열 설정
		table.setPadding(5);		//테이블 여백 설정

		BaseFont bfKorean = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);  //폰트 설정

		Font font = new Font(bfKorean);
		Cell cell = new Cell(new Paragraph("순위", font));  //셀 + 들어갈 문장 작성
		cell.setHeader(true);	//테이블 헤더로 지정
		table.addCell(cell);     	//테이블에 삽입
		cell = new Cell(new Paragraph("페이지", font));    //셀 + 들어갈 문장 작성
		table.addCell(cell);		//테이블에 삽입
		table.endHeaders();	//헤더 끝

		for (PageRank rank : pageRanks) {    //for문으로 List출력
			table.addCell(Integer.toString(rank.getRank()));
			table.addCell(rank.getPage());
		}
		document.add(table);  
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}

}
