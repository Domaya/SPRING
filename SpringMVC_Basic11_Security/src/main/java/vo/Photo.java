package vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
DB에 테이블 있다고 가정
create table photo(
	name
	age
	image -> 업로드한 파일의 이름 들어감 -> 1.jpg , 2.png 등
)
 */
public class Photo {
	private String name;
	private int age;
	private String image;
	////////////////////////////////////요기 DB컬럼
	
	/////별도의 파일을 담을 수 있는 객체...
	//POINT
	private CommonsMultipartFile file;  //업로드한 파일을 받는 객체  여러파일을 업로드 하고 싶다면 List에 제네릭으로 <CommonsMultipartFile> 타입이 들어가면 된다
	//사진 : <input type="text" name="file">  이름이 같아야 함

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	//getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Photo [name=" + name + ", age=" + age + ", image=" + image + ", file=" + file + "]";
	}
	
	
}
