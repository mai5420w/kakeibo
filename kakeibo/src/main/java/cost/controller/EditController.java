package cost.controller;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cost.dao.DataTable;
import cost.dao.history.HistoryDaoImpl;
import cost.dao.history.HistoryService;
import cost.dao.pay.CategorySum;

@Controller
public class EditController {

	@Autowired
	private HistoryService historyService;
	private int dataId;

	@PersistenceContext
	private EntityManager entityManager;
	private HistoryDaoImpl dao;

	@PostConstruct //[memo]噛み砕くとspringが作ってくれた後にこれをやりたいです、というアドテーション
	private void init() {
		dao = new HistoryDaoImpl(entityManager);//springが作ってくれたdaoマネージャー
	}

	@RequestMapping("/edittop")
	public String history(@RequestParam("dataId") final int dataId){
		this.dataId = dataId; 
		return "edit";
	}

	/*
	 *  編集(UPDATE)メソッド
	 */
	@RequestMapping(value = "/editup" , method = RequestMethod.GET)
	public String update(Model model,@RequestParam("expense") final int expense){
		BigDecimal newExpense = new BigDecimal(expense); //TODO htmlから金額を取得
		DataTable d = new DataTable();
		d = historyService.findOne(dataId); //対応するdata_idのレコードを取得
		historyService.update(dao.updateData(d, newExpense)); //ここでupdateしている

		model.addAttribute("message", newExpense + "円に更新しました");
		return "edit";
	}

	/*
	 *  削除(DELETE)メソッド
	 */
	@RequestMapping(value = "/editdel", method = RequestMethod.POST)
	public String deleteTask(@ModelAttribute CategorySum cs,Model model) {
		//プライマリーキーであるData_IDの取得
		int id = cs.getDataId(); //TODO ここで編集ボタンを押された入力された値のdata_idを取得したい
		historyService.delete(id);
		model.addAttribute("id", id);
		return "delete";
	}

}