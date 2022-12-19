package net.madvirus.spring4.chap08.stat;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageRankStatController {

	@RequestMapping("/pagestat/rank")
	public String pageRank(Model model) {
		List<PageRank> pageRanks = Arrays.asList(
				new PageRank(1, "/board/humor/1011"),
				new PageRank(2, "/board/notice/12"),
				new PageRank(3, "/board/phone/190")
				);
		model.addAttribute("pageRankList", pageRanks);
		return "pageRank";   //뷰에서 필요로 하는 데이터 값들을 DTO객체로 모델에 담아서 View로 보내준다
	}

	@RequestMapping("/pagestat/rankreport")
	public String pageRankReport(Model model) {
		List<PageRank> pageRanks = Arrays.asList(
				new PageRank(1, "/board/humor/1011"),
				new PageRank(2, "/board/notice/12"),
				new PageRank(3, "/board/phone/190")
				);
		model.addAttribute("pageRankList", pageRanks);
		return "pageReport";  //뷰에서 필요로 하는 데이터 값들을 DTO객체로 모델에 담아서 View로 보내준다
	}
}
