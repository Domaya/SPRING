package DI3;

public class Program {

	public static void main(String[] args) {

		// NewRecordView view = new NewRecordView(100, 50, 40);
		// view.print();

		NewRecordView view = new NewRecordView();
		// NewRecord 객체가 필요해지면
		NewRecord record = new NewRecord(100, 50, 40);
		view.setRecord(record); // 의존 객체 주입 (setter 함수)
	
		view.input();
		view.print();
		
	}

}
