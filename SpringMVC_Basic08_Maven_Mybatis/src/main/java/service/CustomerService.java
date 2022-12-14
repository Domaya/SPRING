package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Service
public class CustomerService {

	// Mybatis 작업
	private SqlSession sqlsession;

	@Autowired
	public CustomerService(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	// 글 목록 보기 서비스
	public List<Notice> getNotices(String pg, String f, String q) {

		// default 값 설정
		int page = 1;
		String field = "TITLE";
		String query = "%%";

		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		if (f != null && !f.equals("")) {
			field = f;
		}

		if (q != null && !q.equals("")) {
			query = q;
		}

		// DAO 작업
		List<Notice> list = null;
		try {
			// 동기화/////////////////////////////////
			NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
			////////////////////////////////////////
			list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		// Spring 적용

	}

	// 글 상세 보기 서비스
	public Notice noticesDetail(String seq) {
		Notice notice = null;
		try {
			// 동기화//////////////////////////////////////////////////////////////
			NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
			/////////////////////////////////////////////////////////////////////
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	
	// 글쓰기 처리 서비스
	public String noticeReg(Notice n , HttpServletRequest request) {

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
		    
		    // List<String> filenames = new ArrayList<String>(); //파일명 관리
			//파일명 (DTO)
			n.setFileSrc(filenames.get(0));
			n.setFileSrc2(filenames.get(1));
			try {
				// 동기화//////////////////////////////////////////////////////////////
				NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
				/////////////////////////////////////////////////////////////////////
				noticedao.insert(n);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  return "redirect:notice.htm";
	}
	
	// 글 삭제하기 처리 서비스
	public String noticeDel(String seq) {
		NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
		try {
			noticedao.delete(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:notice.htm";
	}
	
	// 글 수정하기 서비스
	public Notice noticeEdit(String seq) {
		Notice notice=null;
		try {
			// 동기화//////////////////////////////////////////////////////////////
			NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
			/////////////////////////////////////////////////////////////////////
			notice = noticedao.getNotice(seq);
		} catch( Exception e) {
				e.printStackTrace();
		} 
		
		return notice;
	}
	//글 수정 처리
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
				// 동기화//////////////////////////////////////////////////////////////
				NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
				/////////////////////////////////////////////////////////////////////
				noticedao.update(n);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		//처리가 끝나면 상세 페이지로 : redirect  글번호를 가지고 ....
		return "redirect:noticeDetail.htm?seq="+n.getSeq();    //서버에게 새 요청 ....
	}
	

}