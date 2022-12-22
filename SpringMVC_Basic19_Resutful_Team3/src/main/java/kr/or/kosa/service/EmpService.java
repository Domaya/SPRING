package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.Emp;

@Service
public class EmpService {
	
	@Autowired
	private SqlSession sqlsession;
	
	public List<Emp> selectAllEmp(){
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		//인터페이스를 mapper가 구현했다(mybatis)
		List<Emp> list = new ArrayList<Emp>();
		list = dao.select();
		return list;
	}
	
	public Emp selectEmp(int empno) {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		Emp emp = dao.selectByEmpno(empno);
		return emp;
	}
	
	public void insert(Emp emp) {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		dao.insert(emp);
	}
	
	public void update(Emp emp) {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		dao.update(emp);
	}
	
	public void delete(int empno) {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		dao.delete(empno);
	}
	
}
