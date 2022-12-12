package DI_07_Spring;

import java.util.List;

public class ProtocolHandler {
	private List<MyFilter> filters;
	
	//getter
	public List<MyFilter> getFilters(){
		return this.filters;
	}
	
	//setter
	//filters 객체의 주소가 setter로 주입
	public void setFilters(List<MyFilter> filters) {
		this.filters = filters;
	}
	
	//검증 함수
	public int filter_length() {
		return this.filters.size();
	}
}
