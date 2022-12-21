package kosa.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosa.dao.EmpDao;
import kosa.vo.Emp;

@Service
public class AjaxService {

   // Mybatis 작업
   private SqlSession sqlsession;

   @Autowired
   public void setSqlsession(SqlSession sqlsession) {
      this.sqlsession = sqlsession;
   }

   
   // Emp 전체 조회
   public List<Emp> allList() {

      // DAO 작업
      List<Emp> list = null;
         // 동기화/////////////////////////////////////////////////////
         EmpDao empdao = sqlsession.getMapper(EmpDao.class);
         /////////////////////////////////////////////////////////////
         list = empdao.empAll();
   
      return list;
   }
   
   //상세보기 서비스
      public Emp empDetail(int empno) {
		Emp emp = null;

		// 동기화/////////////////////////////////////////////////////
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		///////////////////////////////////////////////////////////
		emp = empdao.getEmp(empno);

         return emp;      
      }
   
   // 삽입
   public String insert(Emp emp) {

      try {
         EmpDao empdao = sqlsession.getMapper(EmpDao.class);
         empdao.insertEmp(emp);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   
   // 수정
   public int empEdit(Emp emp) {
         // 동기화/////////////////////////////////////////////////////
         EmpDao empdao = sqlsession.getMapper(EmpDao.class);
         ///////////////////////////////////////////////////////////
         empdao.updateEmp(emp);

      return 0;
   }

   
   // 수정 처리 서비스
   public String empEdit(Emp emp, HttpServletRequest request) {
         // 동기화/////////////////////////////////////////////////////
         EmpDao empdao = sqlsession.getMapper(EmpDao.class);
         ///////////////////////////////////////////////////////////
         empdao.updateEmp(emp); // DB insert

      return null;
   }

   
   // 삭제
   public String empDel(int empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);

		empdao.deleteEmp(empno);

      return null;
   }

}