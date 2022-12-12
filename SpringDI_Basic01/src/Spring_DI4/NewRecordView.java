package Spring_DI4;

import java.util.Scanner;

// 점수를 출력하는 클래스
public class NewRecordView implements RecordView {

	// NewRecordView는 점수를 받기 위해 NewRecord가 필요
	private NewRecord record; // 포함 관계로 사용
	
	//Interface 사용한 POINT
	// setter 함수를 통해 필요한 객체의 주소 받기
	// 주소가 필요할 때 setter 함수를 통해 주입 받으면 되겠ㅈ ㅣ...
	public void setRecord(Record record) { //interface 사용해서 다형성(부모타입 ..._)
		this.record = (NewRecord)record; //다운캐스팅
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

	@Override
	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("kor");
		record.setKor(sc.nextInt());

		System.out.println("eng");
		record.setEng(sc.nextInt());
		
		System.out.println("math");
		record.setMath(sc.nextInt());
		
	}
	
}
