package kr.or.kosa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kosa.service.EmpService;
import kr.or.kosa.vo.Emp;

@Component
@Controller
//@RequestMapping("/emp/")
public class EmpController {

	private EmpService service;

	@Autowired
	public void setCustomerservice(EmpService service) {
		this.service = service;
	}

	@RequestMapping("/emp/list.htm")   //  
	public String notices(String pg , String f , String q , Model model) {
		//System.out.println("list 매핑");
		List<Emp> list = service.getEmps();
		model.addAttribute("list", list);  //자동으로 notice.jsp forward 
		//System.out.println(list);
		return  "emp/list";
	}
	
	@RequestMapping("/emp/empDetail.htm")
	public String detail(@RequestParam(value="empno") String empno, Model model) {
		//System.out.println("사원 상세조회 매핑... 내가 받은 파라미터 사원번호 : " + empno);
		Emp emp = service.getEmp(Integer.parseInt(empno));
		//System.out.println(emp);
		model.addAttribute("emp", emp);
		return "emp/empDetail";
	}
	
	@GetMapping("/emp/newEmp.htm")
	public String newEmp() {
		System.out.println("사원등록 매핑 ");
		return "emp/empForm";
	}
	@PostMapping("/emp/newEmp.htm")
	public String newEmpOk(Emp emp, HttpServletRequest request) {
		System.out.println("form submit 매핑");
		System.out.println("emp : " + emp);
		try {
			service.insertEmp(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list.htm";
	}
	
	@GetMapping("/emp/empEdit.htm")
	public String edit(@RequestParam(value="empno") String empno, Model model) {
		System.out.println("사원수정 매핑");
		Emp emp = service.getEmp(Integer.parseInt(empno));
		model.addAttribute("emp", emp);
		return "emp/empEdit";
	}
	
	@PostMapping("/emp/empEdit.htm")
	public String edit(Emp emp, Model model) {
		System.out.println("사원수정 매핑");
//		Emp emp = service.getEmp(Integer.parseInt(empno));
		service.updateEmp(emp);
		model.addAttribute("emp", emp);
		return "emp/empDetail";
	}
	@RequestMapping("/emp/empDelete.htm")
	public String update(@RequestParam(value="empno")String empno) {
		System.out.println("사원 삭제 매핑");
		service.deleteEmp(Integer.parseInt(empno));
		return "redirect:list.htm";
	}
}
