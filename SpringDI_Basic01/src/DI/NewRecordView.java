package DI;

//점수를 출력하는 클래스
public class NewRecordView {
	//NewRecordView는 점수를 받기 위해서 NewRecord가 필요합니다
	
	private NewRecord record; //포함관계(복합연관)
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	
	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}
}
