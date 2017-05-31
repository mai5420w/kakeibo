package cost.dao.pay;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datatbl")
public class Pay {

	private String id;
	@Id
	private int data_id;
	private Date date;
	private BigDecimal food;
	private BigDecimal daily;
	private BigDecimal entame;
	private BigDecimal traffic;
	private BigDecimal amusement;
	private BigDecimal fashion;
	private BigDecimal goods;
	private BigDecimal medical;
	private BigDecimal publicfee;
	private BigDecimal rent;
	private BigDecimal others;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getData_id() {
		return data_id;
	}
	public void setData_id(int data_id) {
		this.data_id = data_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getFood() {
		return food;
	}
	public void setFood(BigDecimal food) {
		this.food = food;
	}
	public BigDecimal getDaily() {
		return daily;
	}
	public void setDaily(BigDecimal daily) {
		this.daily = daily;
	}
	public BigDecimal getEntame() {
		return entame;
	}
	public void setEntame(BigDecimal entame) {
		this.entame = entame;
	}
	public BigDecimal getTraffic() {
		return traffic;
	}
	public void setTraffic(BigDecimal traffic) {
		this.traffic = traffic;
	}
	public BigDecimal getAmusement() {
		return amusement;
	}
	public void setAmusement(BigDecimal amusement) {
		this.amusement = amusement;
	}
	public BigDecimal getFashion() {
		return fashion;
	}
	public void setFashion(BigDecimal fashion) {
		this.fashion = fashion;
	}
	public BigDecimal getGoods() {
		return goods;
	}
	public void setGoods(BigDecimal goods) {
		this.goods = goods;
	}
	public BigDecimal getMedical() {
		return medical;
	}
	public void setMedical(BigDecimal medical) {
		this.medical = medical;
	}
	public BigDecimal getPublicfee() {
		return publicfee;
	}
	public void setPublicfee(BigDecimal publicfee) {
		this.publicfee = publicfee;
	}
	public BigDecimal getRent() {
		return rent;
	}
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	public BigDecimal getOthers() {
		return others;
	}
	public void setOthers(BigDecimal others) {
		this.others = others;
	}

}
