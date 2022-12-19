<%@ page contentType="text/html; charset=utf-8" %>
<html>
<script>
	function alert(){
		if(lang == "en"){
			alert("English");
		}else{
			alert("한국어");
		}
	}
</script>
<body>
<ul>

<li><h3>커스텀 뷰:</h3>
	<ul>
	<!-- 파일 아이디가 1일 때 ->> 다운로드컨트롤러로 가서 정해주는 파일 다운로드 시킴 -->
	<li><a href="file/1">/file/1</a>: 파일 다운로드 <small>DownloadView, DownloadController.download()</small></li><hr>
	<li><a href="pagestat/rank">/pagestat/rank</a>: 엑셀 다운로드 <small>PageRankView, PageRankStatController.pageRank()</small></li><hr>
	<li><a href="pagestat/rankreport">/pagestat/rankreport</a>: PDF 다운로드 <small>PageReportView, PageRankStatController.pageRankReport()</small></li>
	</ul>
</li>
 <li>로케일 변경:
	<ul>
	<li><a href="changeLanguage?language=en">/changeLanguage?lang=en</a>: LocaleResolver 예, LocaleChangeController.change(), lang 파라미터로 변경</li>
	<li><a href="changeLanguage?language=ko">/changeLanguage?lang=ko</a></li>
	<li><a href="auth/login?language=en">/auth/login?lang=en</a>: LocaleChangeInterceptor 예, lang으로 변경 후, lang 없이 확인</li>
	<li><a href="auth/login">/auth/login</a></li>
	</ul>
</li>
</ul>

</body>
</html>