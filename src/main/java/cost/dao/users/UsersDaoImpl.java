package cost.dao.users;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import cost.dao.DataTable;


@Repository
public class UsersDaoImpl implements UsersDao {

	private EntityManager entityManager;

	public UsersDaoImpl() {
		super();
	}


	public UsersDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;

	}
	@Override
	public List<Users> getById(final String id) {
		final List<Users>usersList  = entityManager
				.createNativeQuery("SELECT * FROM usertbl WHERE id = :id",Users.class)
				.setParameter("id", id)
				.getResultList();
		return usersList;
	}
	@Override
	public List<Users> getByPw(final String pw) {
		final List<Users>usersList  = entityManager
				.createNativeQuery("SELECT * FROM usertbl WHERE pw = :pw",Users.class)
				.setParameter("pw", pw)
				.getResultList();
		return usersList;
	}

	@Override
	public List<Users> getAll() {
		final List<Users>usersList  = entityManager
				.createNativeQuery("SELECT * FROM usertbl", Users.class)
				.getResultList();
		return usersList;
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

