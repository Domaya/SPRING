package kosa.dao;

import java.util.List;

import kosa.vo.Emp;

public interface EmpDao {
	
	//전체 사원 조회
	public List<Emp> empAll();
	
	//emp CRUD
	//C : insert
	public int insertEmp(Emp emp);
	
	//R : get
	public Emp getEmp(int empno);
	
	//U : update
	public int updateEmp(Emp emp);
	
	//D : delete
	public int deleteEmp(int empno);
}
