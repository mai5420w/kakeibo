package cost.dao.history;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import cost.dao.DataTable;

@SuppressWarnings("serial")
public class HistoryDaoImpl implements HistoryDao<DataTable>{

	private EntityManager entityManager;

	public HistoryDaoImpl(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	//idに対応する1レコード分取得
	@SuppressWarnings("unchecked")
	@Override
	public List<DataTable> getData(int dataId) {
		final List<DataTable> record =
				entityManager
				.createNativeQuery("SELECT * FROM datatbl WHERE data_id = :data_id ", DataTable.class)
				.setParameter("data_id", dataId)
				.getResultList();
		return record;
	}

	/**
	 * データをupdateして返す
	 */
	@Override
	public DataTable updateData(DataTable data, BigDecimal newExpense){ 
		if(data == null){
			System.out.println("金額が入力されていません。");
		}else{

			BigDecimal expense = new BigDecimal(0); //¥0を含めたすべての金額(decimal)を格納する

			if(expense.add(data.getFood()).compareTo( new BigDecimal(0)) != 0){
				data.setFood(newExpense);
			}
			else if(expense.add(data.getDaily()).compareTo( new BigDecimal(0)) != 0) {
				data.setDaily(newExpense);
			}
			else if(expense.add(data.getEntame()).compareTo( new BigDecimal(0)) != 0){
				data.setEntame(newExpense);	
			}
			else if(expense.add(data.getTraffic()).compareTo( new BigDecimal(0)) != 0){
				data.setTraffic(newExpense);
			}
			else if(expense.add(data.getAmusement()).compareTo( new BigDecimal(0)) != 0){
				data.setAmusement(newExpense);
			}
			else if(expense.add(data.getFashion()).compareTo( new BigDecimal(0)) != 0){
				data.setFashion(newExpense);
			}
			else if(expense.add(data.getGoods()).compareTo( new BigDecimal(0)) != 0){
				data.setGoods(newExpense);
			}
			else if(expense.add(data.getMedical()).compareTo( new BigDecimal(0)) != 0){
				data.setMedical(newExpense);
			}
			else if(expense.add(data.getPublicfee()).compareTo( new BigDecimal(0)) != 0){
				data.setPublicfee(newExpense);
			}
			else if(expense.add(data.getRent()).compareTo( new BigDecimal(0)) != 0){
				data.setRent(newExpense);
			}
			else if(expense.add(data.getOthers()).compareTo( new BigDecimal(0)) != 0){
				data.setOthers(newExpense);
			}
		}
		return data;
	}
}
