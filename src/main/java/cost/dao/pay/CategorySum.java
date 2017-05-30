package cost.dao.pay;

import java.math.BigDecimal;

public class CategorySum {
	private int dataId;
	private String category; //String　カテゴリー名
	private BigDecimal dailySum; //一つ一つの支出金額 decimal

	public CategorySum(String category, BigDecimal dailySum) {
		this.category = category;
		this.dailySum = dailySum;
	}
	public CategorySum() {
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

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
}