package net.madvirus.spring4.chap08.stat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class PageRankView extends AbstractExcelView {
																//AbstractExcelView 클래스가 실제로 웹브라우저에 데이터를 보낸다
																//  (buildExcelDocument 클래스가 엑셀 생성 처리 마무리 하면)
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,         //HSSFWorkbook: 엑셀파일 생성위해 필요한 객체 생성 (POI가 제공)
			HttpServletResponse response) 
								throws Exception {
								response.setHeader("Content-Disposition", "attachment; filename=\"pagerank.xls\";");  //엑셀파일다운시 이름설정
								/*
								Content-Disposition :
								HTTP Response 헤더의 종류. 브라우저에 Content가 
								inline으로 표시될지 attachment로 표시될지 여부를 나타냄
								
								inline : Web Page로, 혹은 Web Page 내에서 표시 (기본값)
								attachment : 로컬에 다운로드 & 저장 (대부분의 브라우저에서는 바로 다운로드가 되거나, “Save As” 다이얼로그가 표시됨(저장하는 창))
								filename : 파일 이름을 지정할 수 있음
								 */
		HSSFSheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet);

		List<PageRank> pageRanks = (List<PageRank>) model.get("pageRankList");
		int rowNum = 1;
		for (PageRank rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);
		}
	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();  //새 엑셀파일 만들기
		workbook.setSheetName(0, "페이지 순위");			//시트 이름 만들기
		sheet.setColumnWidth(1, 256 * 20);					//시트 크기 설정
		return sheet;
	}

	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cell = firstRow.createCell(0);  //해당열의 1열
		cell.setCellValue("순위");  //값 넣기

		cell = firstRow.createCell(1); //해당열의 2열 
		cell.setCellValue("페이지");	//값 넣기 
	}

	private void createPageRankRow(HSSFSheet sheet, PageRank rank,
			int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);  // 행(row) 생성
		HSSFCell cell = row.createCell(0);					//해당열의 1열
		cell.setCellValue(rank.getRank());					//값 넣기  (컨트롤러에서 설정한)

		cell = row.createCell(1);									//해당열의 2열
		cell.setCellValue(rank.getPage());					//값 넣기   (컨트롤러에서 설정한)
	}

}
