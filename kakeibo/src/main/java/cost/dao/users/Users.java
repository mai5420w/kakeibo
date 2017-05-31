package cost.dao.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// table名を書く。
@Table(name = "usertbl")
public class Users {

	@Id
	private String id;
	private String pw;

	public Users(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public Users(){
		super();
	}	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

}