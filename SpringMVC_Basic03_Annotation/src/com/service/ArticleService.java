package com.service;
import org.springframework.stereotype.Service;

import com.model.NewArticleCommand;

/*
 * @Service >> 너는 빈 객체로 생성되어야 해
 * public class ArticleService
 * 
 * xml파일에 <context:component-scan base-package="com.service">
 */

public class ArticleService {
	public ArticleService() {
		System.out.println("ArticleService 생성자 호출");
	}
	
	public void writeArticle(NewArticleCommand command){
		//DAO 있다고 가정하고
		//실제로 INSERT가 됐다고 가정
		System.out.println("글쓰기 작업 완료 : " + command.toString());
	}
}
