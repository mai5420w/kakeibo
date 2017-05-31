package cost.dao.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import cost.dao.DataTable;


@Repository
public class DataDaoImpl implements DataDao{

	private EntityManager entityManager;

	public DataDaoImpl(){
		super();
	}

	public DataDaoImpl(EntityManager entityManager){
		this.entityManager = entityManager; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataTable> getAll(String id){
		final List<DataTable> dataList = entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE id =:id AND MONTH(now()) = MONTH(date)　ORDER BY date ASC",DataTable.class)
				.setParameter("id", id)
				.getResultList();
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataTable> getThismonth(String inputId){
		final List<DataTable> dataList = entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE id =:inputId AND MONTH(now()) = MONTH(date)",DataTable.class)
				.setParameter("inputId", inputId)
				.getResultList();
		return dataList;
	}

}

