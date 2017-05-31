package cost.dao.pay;

import java.io.Serializable;
import java.util.List;
public interface PayDao<T> extends Serializable {
	
	public List<T> getAll(String inputId);

	public List<T> getDate(String id,String date);

	public List<T> getLastmonth(String id);

	public List<T> getThismonth(String id);

}
