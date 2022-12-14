package dao;

import java.sql.SQLException;
import java.util.List;

import vo.Member;

public interface MemberDao {
	public Member getMember(String uid) throws ClassNotFoundException, SQLException;
	public int insert(Member member) throws ClassNotFoundException, SQLException;
}
