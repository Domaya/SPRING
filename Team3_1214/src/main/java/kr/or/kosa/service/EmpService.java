package kr.or.kosa.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.vo.Emp;

@Service
public class EmpService {
	// Mybatis 작업
	private SqlSession sqlsession;

	@Autowired
	public EmpService(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	//사원전체목록
	public List<Emp> getEmps(){
		
		List<Emp> list = null;
		try {
			// 동기화/////////////////////////////////
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			////////////////////////////////////////
			list = dao.getEmps();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//사원상세
	public Emp getEmp(int empno){
		Emp emp = null;
		try {
			// 동기화/////////////////////////////////
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			////////////////////////////////////////
			emp = dao.getEmp(empno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	//사원 삽입
	public int insertEmp(Emp emp){
		try {
			// 동기화/////////////////////////////////
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			////////////////////////////////////////
			dao.insertEmp(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//redirect로 바꾸기
		return 0;
	}	
	//사원 수정
	public Emp updateEmp(int empno){
		Emp emp = null;
		try {
			// 동기화/////////////////////////////////
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			////////////////////////////////////////
			emp = dao.getEmp(empno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}	
	
	//사원 수정 처리
	public String updateEmp(Emp emp) {
		try {
			// 동기화/////////////////////////////////
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			////////////////////////////////////////
			dao.updateEmp(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//리다이렉트로 뷰 수정
		return null;
	}
	
	//사원 삭제
	public String deleteEmp(int empno){
		try {
			// 동기화/////////////////////////////////
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			////////////////////////////////////////
			dao.deleteEmp(empno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//리다이렉트
		return null;
	}	
	
}
