package cost.dao.data;

import java.io.Serializable;
import java.util.List;

import cost.dao.DataTable;

public interface DataDao extends Serializable{
	public List<DataTable> getAll(String id);
	public List<DataTable> getThismonth(String inputId);

}
