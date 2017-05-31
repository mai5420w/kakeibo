package cost.dao.pay;

import java.math.BigDecimal;

public class DataSum {

	private String data;
	private BigDecimal sum;


	public DataSum(String data, BigDecimal sum) {
		this.data = data;
		this.sum = sum;
	}

	public String getDate() {
		return data;
	}

	public void setDate(String date) {
		this.data = data;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

}