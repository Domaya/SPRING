package DI_06_Spring;

public class ArticleService {
	//ArticleService는 ArticleDao 의존
	//주입
	//생성자
	//setter
	
	private ArticleDao articledao;
	public ArticleService(ArticleDao articledao) {
		this.articledao = articledao;
		System.out.println("ArticleService 생성자 호출");
	}
	
	//글쓰기 서비스
	public void write(Article article) {
		this.articledao.insert(article);
	}
}
