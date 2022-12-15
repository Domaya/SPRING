package stat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/*
AbstractExcelView 클래스를 이용한 엑셀 다운로드 구현 

월별로 페이지 뷰 통계 정보나 급여 명세서와 같은 정보를 엑셀로 제공해야 하는 경우가 있는데, 
스프링은 엑셀 형식으로 뷰 데이터를 생성할 수 있도록 다음의 두 View 클래스를 제공함 
	- AbstractExcelView : POI API를 이용하여 엑셀 응답을 생성 
	- AbstractJExcelView : JExcel API를 이용하여 엑셀 응답 생성
 
POI API를 이용하여 엑셀 응답을 생성하는 AbstractExcelView 클래를 살펴보자~!

---------------

POI를 이용하기 위해서는 pom.xml 에 아래의 의존성을 추가함! 
<!-- poi --> 
<dependency> 
	<groupId>org.apache.poi</groupId> 
	<artifactId>poi</artifactId> 
	  <version>3.17</version> 
</dependency> 

AbstractExcelView 클래스는 엑셀 응답 결과를 생성하기 위한 기본 기능을 제공하고 있으며, 
이 클래스를 상속받은 뒤 다음의 메서드만 알맞게 재정의하면 됨 
--------------------------
HSSFWorkbook 은 POI API 가 제공하는 엑셀 관련 클레스임
하위 클래스는 이 클래스를 상속받아 엑셀 문서를 생성하면 됨

아래의 클래스는 AbstractExcelView 를 상속 받아 엑셀 문서를 생성하는 클래스의 작성 예시임

*/

// PageRankView 클래스는 HSSFWorkbook, HSSFSheet 등 POI가 제공하는 기능을 이용해서 엑셀 데이터를 생성함
// buildExcelDocument 메서드에서 엑셀 생성을 마무리 지으면, AbstractExcelView 에서 실제로 데이터를 웹 브라우저에 전송하게 됨
// POI를 이용한 엑셀 생성에 대한 내용은 http://poi.apache.org/spreadsheet/ 사이트 참고!!
// PageRankView 가 컨트롤러에서 사용할 수 있도록 빈으로 등록해야함!
/*
<bean id="pageRank" class="net.madvirus.spring4.chap08.stat.PageRankView"></bean>
<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
	<property name="order" value="1"/>
</bean>
*/
public class PageRankView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
									  HSSFWorkbook workbook, 
									  HttpServletRequest request,
			                          HttpServletResponse response) throws Exception 
	{
		//buildExcelDocument() 메소드는 엑셀 파일을 생성하는데 필요한
		//HSSFWorkbook 객체를 파라미터로 받아, 이 객체를 이용해 알맞게 엑셀 데이터를 생성하면  됨
		// 웹 브라우저가 엑셀 파일을 다운로드할 때, 사용할 이름을 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"pagerank.xls\";");

		HSSFSheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet);

		List<PageRank> pageRanks = (List<PageRank>) model.get("pageRankList");
		int rowNum = 1;
		for (PageRank rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);
		}
	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "페이지 순위");
		sheet.setColumnWidth(1, 256 * 20);
		return sheet;
	}

	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("순위");

		cell = firstRow.createCell(1);
		cell.setCellValue("페이지");
	}

	private void createPageRankRow(HSSFSheet sheet, PageRank rank, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getRank());

		cell = row.createCell(1);
		cell.setCellValue(rank.getPage());

	}

}
