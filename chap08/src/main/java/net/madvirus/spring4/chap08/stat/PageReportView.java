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

/*
스프링은 iText API를 이용해서 PDF를 생성할 수 있는 AbstractPdfView 클래스를 제공함
iText를 사용하려면 의존성을 pom.xml에 추가해야 함

<!-- iText -->
<dependency>
    <groupId>com.lowagie</groupId>
    <artifactId>itext</artifactId>
    <version>2.1.7</version>
    <exclusions>
    	<exclusion>
    		<groupId>bouncycastle</groupId>
    		<artifactId>bcmail-jdk14</artifactId>
    	</exclusion>
    	<exclusion>
    		<groupId>bouncycastle</groupId>
    		<artifactId>bcprov-jdk14</artifactId>
    	</exclusion>
    	<exclusion>
    		<groupId>bouncycastle</groupId>
    		<artifactId>bctsp-jdk14</artifactId>
    	</exclusion>
    </exclusions>
</dependency>

<exclusions> 부분은 iText를 사용하기 위해 추가로 필요한 모듈 중에서 사용하지 않을 모듈을 지정한 것 (암호화 관련 기능 제외)

------------------------------

AbstractPdfView 클래스를 상속 받은 클래스는 buildPdfDocument 메서드를 재정의 해서 PDF를 생성하면 됨
com.lowagie.text.Document 클래스는 iText가 제공하는 클래스로서, Document 객체에 PDF 문서를 생성하는데 필요한 데이터를 추가하므로써
PDF문서를 생성할 수 있음

------------------------------------------------

<bean id="pageReport" class="net.madvirus.spring4.chap08.stat.PageReportView"></bean>
PageReportView 를 스프링 빈으로 등록하고 컨트롤러에 해당 뷰를 사용하도록 구현
실제 PageReportView 가 생성한 PDF 결과 화면은 

-----------------------------------------------

아래는 AbstractPdfView 클래스를 상속받아 PDF 문서를 생성하는 뷰클래스임

*/

public class PageReportView extends AbstractPdfView {
	private String fontPath = "c:\\windows\\Fonts\\malgun.ttf";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			                        Document document, PdfWriter writer, 
			                        HttpServletRequest request,
			                        HttpServletResponse response) throws Exception 
	{
		List<PageRank> pageRanks = (List<PageRank>) model.get("pageRankList");
		Table table = new Table(2, pageRanks.size() + 1);
		table.setPadding(5);

		BaseFont bfKorean = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		Font font = new Font(bfKorean);
		Cell cell = new Cell(new Paragraph("순위", font));
		cell.setHeader(true);
		table.addCell(cell);
		cell = new Cell(new Paragraph("페이지", font));
		table.addCell(cell);
		table.endHeaders();

		for (PageRank rank : pageRanks) {
			table.addCell(Integer.toString(rank.getRank()));
			table.addCell(rank.getPage());
		}
		document.add(table);
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
}
