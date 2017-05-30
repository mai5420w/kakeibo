package cost.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// table名を書く。
@Table(name = "datatbl")
public class DataTable {

	@Id
	@GeneratedValue
	@Column(name ="data_id")
	private  Integer dataId;

	private String id;
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

	public DataTable() {
	}

	public DataTable(String id,Date data, BigDecimal food, BigDecimal daily, BigDecimal entame,
			BigDecimal traffic, BigDecimal amusement, BigDecimal fashion, BigDecimal goods, BigDecimal medical,
			BigDecimal publicfee, BigDecimal rent, BigDecimal others) {
		super();
		this.dataId = null;
		this.id = id;
		this.date = data;
		this.food = food;
		this.daily = daily;
		this.entame = entame;
		this.traffic = traffic;
		this.amusement = amusement;
		this.fashion = fashion;
		this.goods = goods;
		this.medical = medical;
		this.publicfee = publicfee;
		this.rent = rent;
		this.others = others;
	}



	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setData(Date date) {
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