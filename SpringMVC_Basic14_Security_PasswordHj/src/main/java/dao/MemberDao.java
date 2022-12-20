package dao;

import dto.Member;

public interface MemberDao {

   //회원가입
   public int insertMember(Member member);  //암호화
   
   public int idCheck(String userid);
   
   public int loginCheck(String username, String password);
   
   public Member getMember(String userid);
   
   //회원수정
   public int updateMember(Member member);  //암호화

}