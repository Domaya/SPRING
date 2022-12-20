package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import dto.Member;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlsession;
	
	public int insertMember(Member member){
		int result = 0;
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		result = dao.insertMember(member);
		return result;
	}
	
	public int idCheck(String userid){
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = 0;
		result = dao.idCheck(userid);
		return result;
	}
	
	public int loginCheck(String username, String password){
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = 0;
		result = dao.loginCheck(username, password);
		return result;
	}
}
