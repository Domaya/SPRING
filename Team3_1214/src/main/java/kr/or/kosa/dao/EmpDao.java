package kr.or.kosa.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.kosa.vo.Emp;

public interface EmpDao {
	//사원전체목록
	public List<Emp> getEmps() throws ClassNotFoundException, SQLException;

	//사원상세
	public Emp getEmp(int empno) throws ClassNotFoundException, SQLException;
	
	//사원 삽입
	public int insertEmp(Emp emp) throws ClassNotFoundException, SQLException;
	
	//사원 수정
	public int updateEmp(Emp emp) throws ClassNotFoundException, SQLException;
	
	//사원 삭제
	public int deleteEmp(int empno) throws ClassNotFoundException, SQLException;
	
}
