package ncontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dto.Member;
import service.MemberService;

@Controller
@RequestMapping("/mypage/")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	   @GetMapping("mypage.htm")
	   public String mypage(Principal principal, Model model) {
		   System.out.println(principal.getName());
		  Member m = service.getMember(principal.getName());
		  model.addAttribute("m", m);
	      return "mypage/mypage";
	   }
	   

	   @GetMapping("pwCheck.htm")
	   public String confirm(Principal principal, Model model) {
		   
	      return "mypage/pwCheck";
	   }
	   
	   @PostMapping("pwCheck.htm")
	   public String confirm(@RequestParam("password") String rawPassword,	Principal principal) {
			String viewpage="";
			
			//회원정보
			Member member = service.getMember(principal.getName());
			
			
			//DB에서 가져온 암호화된 문자열
			String encodedPassword = member.getPwd();
			boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);

			if(result){
				System.out.println("이동 전 .. : " + member);
				viewpage= "redirect:mypageEdit.htm";
			}else{
				viewpage="redirect:pwCheck.htm";
			}

	      return viewpage;
	   }
	   
	   @GetMapping("mypageEdit.htm")
	   public String edit(Principal principal, Model model) {
		   Member member = service.getMember(principal.getName());
		   model.addAttribute("m", member);
		   return "mypage/mypageEdit";
	   }
	   
	   @PostMapping("mypageEdithtm")
	   public String editok(Member member, Principal pricipal, Model model) {
		   System.out.println("member : " + member);
		   member.setPwd((bCryptPasswordEncoder.encode(member.getPwd())));
		   service.updateMember(member);
		   model.addAttribute("m", member);
		   return "mypage/mypage";
	   }
}
