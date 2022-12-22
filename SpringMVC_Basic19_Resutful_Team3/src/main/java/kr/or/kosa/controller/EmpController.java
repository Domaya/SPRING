package kr.or.kosa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.dto.Emp;
import kr.or.kosa.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {
	
	//controller는 서비스 의존
	
	EmpService service;
	@Autowired
	public void setEmpservice(EmpService service) {
		this.service = service;
	}
	
	/*
	 * Restful 방식의 annotation도 존재합니다
	 * @GetMapping
	 * @PostMapping
	 * @DeleteMapping
	 * @PutMapping
	 * @PatchMappig
	 */
	
	/* 가장 쉬운 방식
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<Emp> emplist(){
		return service.selectAllEmp();
	}
	*/
	//전체조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Emp>> emplist(){
		List<Emp> list = new ArrayList<>();
		try {
			list = service.selectAllEmp();
			return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Emp>>(list, HttpStatus.BAD_REQUEST);
		}
	}
	
	//조건조회
	@RequestMapping(value="{empno}", method=RequestMethod.GET)
	public Emp EmpByEmpno(@PathVariable("empno") int empno) {
		return service.selectEmp(empno);
	}
	
	//insert
	//데이터(empno=3000, ename=아무개, sal=10) 서버로 전달
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<List<Emp>> insert(@RequestBody Emp emp){
		List<Emp> list = new ArrayList<>();
		try {
			System.out.println("insert실행");
			service.insert(emp);
			list = service.selectAllEmp();
			return new ResponseEntity<List<Emp>>(list ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Emp>>(list ,HttpStatus.BAD_REQUEST);
		}
	}
	
	//update
	//데이터(empno=3000, ename=아무개, sal=10) 서버로 전달
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<List<Emp>> update(@RequestBody Emp emp){
		List<Emp> list = new ArrayList<>();

		try {
			System.out.println("update실행");
			service.update(emp);
			list = service.selectAllEmp();
			return new ResponseEntity<List<Emp>>(list ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Emp>>(list ,HttpStatus.BAD_REQUEST);
		}
	}
	
	//삭제
	@RequestMapping(value="{empno}", method=RequestMethod.DELETE)
	public ResponseEntity<List<Emp>> delete(@PathVariable("empno") int empno) {
		List<Emp> list = new ArrayList<>();

		try {
			System.out.println("delete실행");
			service.delete(empno);
			list = service.selectAllEmp();
			return new ResponseEntity<List<Emp>>(list ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Emp>>(list ,HttpStatus.BAD_REQUEST);
		}
	}
}
