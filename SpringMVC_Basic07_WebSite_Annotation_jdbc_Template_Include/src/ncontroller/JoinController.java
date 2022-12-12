package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.NewMemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	//memberDao 의존
	private NewMemberDao memberdao;
	@Autowired
	public void setMemberdao(NewMemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	//GET 요청
	//join.jsp 화면처리
	@GetMapping("join.htm")
	public String join() {
		return "joinus/join";
	}
	
	//POST요청
	//join
	@PostMapping("join.htm")
	public String join(Member member) {
		System.out.println(member.toString());
		try {
			memberdao.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/index.htm";
		//http://locahost:8090/SpringMVC/joinus/join.htm
		//return "redirect:/index.htm";처리하면
		//http://localhost:8090/SpringMVC/index.htm
	}
}
