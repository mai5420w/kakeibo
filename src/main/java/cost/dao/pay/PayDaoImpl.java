package cost.dao.pay;

import java.util.List;

import javax.persistence.EntityManager;


import cost.dao.DataTable;

public class PayDaoImpl implements PayDao<DataTable>{

	// データベースに対してエンティティを登録したり，削除したりするためのインタフェースを持つオブジェクト
	private EntityManager entityManager;

	public PayDaoImpl(){
		super();
	}

	public PayDaoImpl(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataTable> getAll(String inputId) {
		final List<DataTable> paylist = entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE id =:inputId AND MONTH(now()) = MONTH(date)",DataTable.class)
				.setParameter("inputId", inputId)
				.getResultList();
		return paylist;
	}

	/**
	 * 今月分の支出をSELECTする
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataTable> getThismonth(String id) {
		final List<DataTable> paylist =
				entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE id =:id AND MONTH(now()) = MONTH(date) ORDER BY date ASC",DataTable.class)
				.setParameter("id", id)
				.getResultList();
		return paylist;
	}

	/**
	 * 先月分の支出をSELECTする
	 */
	@Override
	public List<DataTable> getLastmonth(String id) {
		final List<DataTable> paylist =
				entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE id =:id AND MONTH(now())-1 = MONTH(date) ORDER BY date ASC",DataTable.class)
				.setParameter("id", id)
				.getResultList();
		return paylist;
	}	

	/**
	 * 日付指定してとってくる。detailsで使用
	 * 日付を入力値にすれば完成
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataTable> getDate(String id,String date) {//TODO もしかしたらDate dateかも?
		final List<DataTable> pays =
				entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE id =:id AND date = :date ", DataTable.class)//TODO and where date = ''で日付指定
				.setParameter("id", id).setParameter("date", date)
				.getResultList();
		return pays;
	}
}