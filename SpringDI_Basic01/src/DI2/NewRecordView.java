package DI2;

// 점수를 출력하는 클래스
public class NewRecordView {

	// NewRecordView는 점수를 받기 위해 NewRecord가 필요
	private NewRecord record; // 포함 관계로 사용
	
	// setter 함수를 통해 필요한 객체의 주소 받기
	// 주소가 필요할 때 setter 함수를 통해 주입 받으면 되겠ㅈ ㅣ...
	public void setRecord(NewRecord record) {
		this.record = record;
	}
	
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	
	/*
	 나는 니가 필요해 ..
	 나는 너의 객체 주소가 필요해 ..
	 
	 1. 생성자를 통해 필요한 객체를 생성 or 주입 >> DI >> 복합 연관, 집합 연관으로 표현 가능
	 2. 함수(setter)를 통해 필요한 객체를 주입 >> DI >> 집합 연관으로 표현 가능
	 */
	
	public NewRecordView() {
		
	}

	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}
	
}
