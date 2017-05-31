package cost.dao.pay;

import org.springframework.format.annotation.DateTimeFormat;

public class PayBox {
	String id;
	@DateTimeFormat(pattern = "yyyyMMdd")
	public String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


}
