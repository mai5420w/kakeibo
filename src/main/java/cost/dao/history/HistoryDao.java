package cost.dao.history;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import cost.dao.DataTable;

public interface HistoryDao<T> extends Serializable {


	public List<T> getData(int DataId);

	public DataTable updateData(DataTable data, BigDecimal newExpense);
}