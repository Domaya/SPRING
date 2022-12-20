package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import dto.Notice;

@Service
public class CustomerService {

	//Mybatis 작업
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	//글목록보기 서비스 (DB)
	public List<Notice> notices(String pg , String f , String q) {
		
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
		
		if(f != null   && ! f.equals("")) {
			field = f;
		}

		if(q != null   && ! q.equals("")) {
			query = q;
		}
		
		//DAO 작업
		List<Notice> list = null;
		try {
				//동기화/////////////////////////////////////////////////////
			 	NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
				///////////////////////////////////////////////////////////
			 	list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
					e.printStackTrace();
		} catch (SQLException e) {
					e.printStackTrace();
		}
		return list;
	}
	
	//글상세보기 서비스
	public Notice noticesDetail(String seq) {
		Notice notice = null;
		try {
				//동기화/////////////////////////////////////////////////////
			 	NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
				///////////////////////////////////////////////////////////	
			    notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
						e.printStackTrace();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		return notice;		
	}
	
	
	//글쓰기처리 서비스
	public String noticeReg(Notice n , HttpServletRequest request, Principal principal) {
	  
	    List<CommonsMultipartFile> files = n.getFiles();
	    List<String> filenames = new ArrayList<String>(); //파일명 관리
	 
	    if(files != null  && files.size() > 0) {  //1개라 업로드된 파일이 존재하면
			for(CommonsMultipartFile  mutifile  : files) {
				String filename =  mutifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
				String fpath = path + "\\" + filename;
				System.out.println(fpath);
				
				if(!filename.equals("")) {  //실 파일 업로드 (웹서버)
					FileOutputStream fs =null;
					try {
						     fs = new FileOutputStream(fpath);
						     fs.write(mutifile.getBytes());
		     
						     filenames.add(filename);  //db에 입력될 파일명
						     
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						 try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
	    }
	    //
	    //	Spring Security 인증처리하기
	    // (Spring Security 생성한 객체 정보를 가지고 와서 필요한 정보 추출)
	    //필요한 정보 : 로그인한 ID, 권한정보(여러개: ROLE_ADMIN or ROLE_USER)
	    
//	    SecurityContext context = SecurityContextHolder.getContext();
//	    Authentication auth = context.getAuthentication(); //인증 관련한 것만
//	    
//	    UserDetails userinfo = (UserDetails) auth.getPrincipal(); //인증된 사용자 정보만
//	    
//	    System.out.println("권한 정보 : " + userinfo.getAuthorities());
//	    System.out.println("사용자ID : " + userinfo.getUsername());
	    /*
	     체인방법으로 기술하는 것이 좋아요
	     
	     */
	    //Session.getAttribute("userid")
//	    n.setWriter(userinfo.getUsername());
	    
	    //가장 간단한 방법
	    //Principal 인터페이스를 정의하면...인증 성공되면 정보를 담은 객체의 주소를 받을 수 있다 자동으로
	    n.setWriter(principal.getName().trim());
	    
		//파일명 (DTO)
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));
		try {
				//동기화/////////////////////////////////////////////////////
			 	NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
				///////////////////////////////////////////////////////////	
			    noticedao.insert(n);  //DB insert
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	  return "redirect:notice.htm";
}
	
	
	
	
	//글삭제하기 처리 서비스
	public String noticeDel(String seq) {
		NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
		try {
				noticedao.delete(seq);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:notice.htm";
	}
	
	
	
	//글수정하기 서비스
	public Notice noticeEdit(String seq) {
		Notice notice=null;
		try {
				//동기화/////////////////////////////////////////////////////
			 	NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
				///////////////////////////////////////////////////////////	
				notice =  noticedao.getNotice(seq);
		} catch( Exception e) {
				e.printStackTrace();
		} 
		return notice;
	}
	
	
	//글수정하기 처리 서비스
	public String noticeEdit(Notice n , HttpServletRequest request) {
 
		 List<CommonsMultipartFile> files = n.getFiles();
		 List<String> filenames = new ArrayList<String>();  //파일명 관리
		
		if(files != null  && files.size() > 0) {  //1개라 업로드된 파일이 존재하면
				for(CommonsMultipartFile  mutifile  : files) {
					String filename =  mutifile.getOriginalFilename();
					String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
					String fpath = path + "\\" + filename;
					System.out.println(fpath);
					
					if(!filename.equals("")) {  //실 파일 업로드 (웹서버)
						FileOutputStream fs =null;
						try {
							     fs = new FileOutputStream(fpath);
							     fs.write(mutifile.getBytes());
			     
							     filenames.add(filename);  //db에 입력될 파일명
							     
						} catch (Exception e) {
							e.printStackTrace();
						}finally {
							 try {
								fs.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
		}
		 
		 
			//파일업로드 2개 한다는 전제
			//파일명 (DTO)
			n.setFileSrc(filenames.get(0));
			n.setFileSrc2(filenames.get(1));
			try {
					//동기화/////////////////////////////////////////////////////
				 	NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
					///////////////////////////////////////////////////////////				
					noticedao.update(n);  //DB insert
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		//처리가 끝나면 상세 페이지로 : redirect  글번호를 가지고 ....
		return "redirect:noticeDetail.htm?seq="+n.getSeq();    //서버에게 새 요청 ....
	}
	
   
	// 파일 다운로드 서비스 함수
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response)
		throws IOException {

			String fname = new String(f.getBytes("euc-kr"), "8859_1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");

			String fullpath = request.getServletContext().getRealPath("/customer/" + p + "/" + f);
			System.out.println(fullpath);
			FileInputStream fin = new FileInputStream(fullpath);

			ServletOutputStream sout = response.getOutputStream();
			byte[] buf = new byte[1024]; // 전체를 다읽지 않고 1204byte씩 읽어서
			int size = 0;
			while ((size = fin.read(buf, 0, buf.length)) != -1) {
				sout.write(buf, 0, size);
			}
			fin.close();
			sout.close();
		}
	
}
