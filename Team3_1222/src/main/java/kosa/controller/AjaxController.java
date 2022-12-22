package kosa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kosa.service.AjaxService;
import kosa.vo.Emp;

@RestController //@controller이면서 함수(@ResponseBody)
public class AjaxController {
	
	@Autowired
	AjaxService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Emp>> emplist(){
		List<Emp> list = new ArrayList<>();
		try {
			System.out.println("정상실행");
			list = service.allList();
			return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Emp>>(list, HttpStatus.BAD_REQUEST);
		}
	}
	
	//조건조회
		@RequestMapping(value="{empno}", method=RequestMethod.GET)
		public Emp EmpByEmpno(@PathVariable("empno") int empno) {
			return service.empDetail(empno);
		}
	
		//insert
		//데이터(empno=3000, ename=아무개, sal=10) 서버로 전달
		@RequestMapping(value="", method=RequestMethod.POST)
		public ResponseEntity<List<Emp>> insert(@RequestBody Emp emp){
			List<Emp> list = null;
			try {
				System.out.println("insert실행");
				service.insert(emp);
				list = service.allList();
				return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<Emp>>(list, HttpStatus.BAD_REQUEST);
			}
		}
		
		//update
		//데이터(empno=3000, ename=아무개, sal=10) 서버로 전달
		@RequestMapping(value="", method=RequestMethod.PUT)
		public ResponseEntity<List<Emp>> update(@RequestBody Emp emp){
			List<Emp> list = null;
			try {
				System.out.println("update실행");
				service.empEdit(emp);
				list = service.allList();
				return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<Emp>>(list, HttpStatus.BAD_REQUEST);
			}
		}
		
		//삭제
		@RequestMapping(value="{empno}", method=RequestMethod.DELETE)
		public ResponseEntity<List<Emp>> delete(@PathVariable("empno") int empno) {
			List<Emp> list = null;
			try {
				System.out.println("delete실행");
				service.empDel(empno);
				list = service.allList();
				return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<Emp>>(list, HttpStatus.BAD_REQUEST);
			}
		}
		
	/*  기존 코드
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
	*/
} 