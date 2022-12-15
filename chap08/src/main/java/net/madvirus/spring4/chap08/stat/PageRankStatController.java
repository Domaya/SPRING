package net.madvirus.spring4.chap08.stat;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageRankStatController {
	
	/*
	PageRankStatController 는 아래와 같이 'PageRankView'에서 필요로 하는 데이터를 모델에 담고
	사용할 뷰 이름으로 'pageRank'를 리턴함으로써, 'PageRankView'를 뷰로 사용할 수 있게 됨
	
	/pagestat/rank 요청으로
	실제 컨트롤러를 통해서 PageRankView를 사용하게 되면 rank.xls 엑셀파일을 받을 수 있음
	
	*/

	@RequestMapping("/pagestat/rank")
	public String pageRank(Model model) {
		List<PageRank> pageRanks = Arrays.asList(
				new PageRank(1, "/board/humor/1011"),
				new PageRank(2, "/board/notice/12"),
				new PageRank(3, "/board/phone/190")
				);
		model.addAttribute("pageRankList", pageRanks);
		return "pageRank";
	}

	@RequestMapping("/pagestat/rankreport")
	public String pageRankReport(Model model) {
		List<PageRank> pageRanks = Arrays.asList(
				new PageRank(1, "/board/humor/1011"),
				new PageRank(2, "/board/notice/12"),
				new PageRank(3, "/board/phone/190")
				);
		model.addAttribute("pageRankList", pageRanks);
		return "pageReport";
	}
}
