package cost.dao.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DataBox {
	String id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date date;
	String cotegory;
	Integer data;

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getCotegory() {
		return cotegory;
	}

	public void setCotegory(String cotegory) {
		this.cotegory = cotegory;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}

