package cost.dao.users;

	import java.io.Serializable;
	import java.util.List;

import cost.dao.DataTable;

	public interface UsersDao<T> extends Serializable{
		public List<T> getAll();
		public List<T> getById(String id);
		public List<T> getByPw(String pw);
		public List<DataTable> getThismonth(String inputId);
}
