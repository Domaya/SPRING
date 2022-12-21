package kosa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosa.service.AjaxService;
import kosa.vo.Emp;

@RestController //@controller이면서 함수(@ResponseBody)
public class AjaxController {
	
	@Autowired
	AjaxService service;
	
	//매핑
	@RequestMapping("restcon.ajax")
	public List<Emp> ajaxResponseBody(){
		
		List<Emp> list = new ArrayList<Emp>();
		list = service.allList();
		return list;
	}
	
	@RequestMapping("delete.ajax")
	public List<Emp> deleteEmp(int empno) {
		//System.out.println("delete");
		//System.out.println("EMP : " + service.empDetail(empno));
		service.empDel(empno);
		//근데 삭제하고나서 어케해?
		List<Emp> list = new ArrayList<Emp>();
		list = service.allList();
		return list;
	
	}
	
//	@RequestMapping("update.ajax")
	@GetMapping("update.ajax")
	public Emp updateEmp(Emp emp) {
		System.out.println("update");
		System.out.println("EMP : " + emp.toString());
		Emp data = service.empDetail(emp.getEmpno());
		return data;
	}

	@PostMapping("update.ajax")
	public List<Emp> updateEmpok(Emp myemp) {
		service.empEdit(myemp);
//		Emp emp = service.empDetail(myemp.getEmpno());
//		return emp;
		List<Emp> list = new ArrayList<Emp>();
		list = service.allList();
		return list;
	}
	
	@RequestMapping("insert.ajax")
	public Emp insert(Emp emp) {
		System.out.println("insert");
		service.insert(emp);
		Emp myemp = service.empDetail(emp.getEmpno());
		//비동기 안에 비동기를 하는게 나은지
		//아니면 모든 함수에서 전체 list를 리턴하는게 나은지 ? ?
		return myemp;
	}
	
	@RequestMapping("view.ajax")
	public String ViewPage() {
		System.out.println("view.ajax");
		return "view.ajax 문자열이 반환";
	}
} 