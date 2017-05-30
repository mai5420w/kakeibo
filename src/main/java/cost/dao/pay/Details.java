package cost.dao.pay;

import java.math.BigDecimal;

public class Details {
	private String category;
	private BigDecimal dailySum; //一つ一つの支出金額

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public BigDecimal getDailySum() {
		return dailySum;
	}


	public void setDailySum(BigDecimal dailySum) {
		this.dailySum = dailySum;
	}

	public String toString(){
		return category + " ¥" + dailySum;
	}

}
