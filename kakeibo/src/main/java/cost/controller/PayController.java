package cost.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cost.dao.DataTable;
import cost.dao.data.DataBox;
import cost.dao.pay.CategorySum;
import cost.dao.pay.DataSum;
import cost.dao.pay.PayBox;
import cost.dao.pay.PayDaoImpl;
import cost.dao.users.Users;

@Controller
public class PayController {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //日付DateTimeフォーマット指定
	@PersistenceContext
	private EntityManager entityManager;
	
	private PayDaoImpl dao;
	
	private String id;
	
	
	@PostConstruct 
	private void init() {
		dao = new PayDaoImpl(entityManager); 
	}
	 
	/**
	 * 日毎の支出表示　例: 2017/05/08 支出¥200
	 * @return
	 */
	
	@RequestMapping(value = "/top",method = RequestMethod.GET)

	public String loginTop(@ModelAttribute Users users, Model model){
		model.addAttribute("dataBox", new DataBox());
		BigDecimal expense = new BigDecimal(0);
		final List<DataTable>dataList = dao.getThismonth(id);
	 

	for(int i = 0 ; i < dataList.size(); i++){
		expense = expense.add(dataList.get(i).getFood());
		expense = expense.add(dataList.get(i).getDaily());
		expense = expense.add(dataList.get(i).getEntame());
		expense = expense.add(dataList.get(i).getTraffic());
		expense = expense.add(dataList.get(i).getAmusement());
		expense = expense.add(dataList.get(i).getFashion());
		expense = expense.add(dataList.get(i).getGoods());
		expense = expense.add(dataList.get(i).getMedical());
		expense = expense.add(dataList.get(i).getPublicfee());
		expense = expense.add(dataList.get(i).getRent());
		expense = expense.add(dataList.get(i).getOthers());

	}

	model.addAttribute("sum", expense);
	model.addAttribute("id", id);

	return "top";

	}
	
	@RequestMapping(value = "/history", params="id", method = RequestMethod.GET)
	public String data(Model model,@RequestParam("id") final String id) {
		
		this.id = id;
		
		DataTable p = new DataTable();
		List<String> date = new ArrayList<String>(); //Date => Stringへ変換
		final List<DataTable> paylist = dao.getThismonth(id);
		
		for(int i = 0; i < paylist.size(); i++) {
			p = paylist.get(i); //レコード１行全て取り出し
			date.add(sdf.format(p.getDate())); //date型で格納
		}
		BigDecimal expense = new BigDecimal(0);
		ArrayList<BigDecimal> dayExpense = new ArrayList<BigDecimal>();
		for(int i = 0; i < paylist.size(); i++){
			// 最初は箱に入れる
			if(i == 0){
				expense = expense.add(paylist.get(i).getFood());
				expense = expense.add(paylist.get(i).getDaily());
				expense = expense.add(paylist.get(i).getEntame());
				expense = expense.add(paylist.get(i).getTraffic());
				expense = expense.add(paylist.get(i).getAmusement());
				expense = expense.add(paylist.get(i).getFashion());
				expense = expense.add(paylist.get(i).getGoods());
				expense = expense.add(paylist.get(i).getMedical());
				expense = expense.add(paylist.get(i).getPublicfee());
				expense = expense.add(paylist.get(i).getRent());
				expense = expense.add(paylist.get(i).getOthers());
				
				if(i == paylist.size()-1){ //最後のとき
					dayExpense.add(expense);
				}
				
			// 最初と同じ日にちなら入れる
			}else if(date.get(i).equals(date.get(i-1))){
				
				expense = expense.add(paylist.get(i).getFood());
				expense = expense.add(paylist.get(i).getDaily());
				expense = expense.add(paylist.get(i).getEntame());
				expense = expense.add(paylist.get(i).getTraffic());
				expense = expense.add(paylist.get(i).getAmusement());
				expense = expense.add(paylist.get(i).getFashion());
				expense = expense.add(paylist.get(i).getGoods());
				expense = expense.add(paylist.get(i).getMedical());
				expense = expense.add(paylist.get(i).getPublicfee());
				expense = expense.add(paylist.get(i).getRent());
				expense = expense.add(paylist.get(i).getOthers());
				if(i == paylist.size()-1){ //最後のとき
					dayExpense.add(expense);
				}
				// 最初の日付と異なるとき
			}else{
				dayExpense.add(expense);
				expense = new BigDecimal(0);//０に戻す
				expense = expense.add(paylist.get(i).getFood());
				expense = expense.add(paylist.get(i).getDaily());
				expense = expense.add(paylist.get(i).getEntame());
				expense = expense.add(paylist.get(i).getTraffic());
				expense = expense.add(paylist.get(i).getAmusement());
				expense = expense.add(paylist.get(i).getFashion());
				expense = expense.add(paylist.get(i).getGoods());
				expense = expense.add(paylist.get(i).getMedical());
				expense = expense.add(paylist.get(i).getPublicfee());
				expense = expense.add(paylist.get(i).getRent());
				expense = expense.add(paylist.get(i).getOthers());
				if(i == paylist.size()-1){ //最後のとき、
					dayExpense.add(expense);
				}
			}
		}
		//日付
		HashSet<String> set = new HashSet<>(date);
		List<String> hashData = new ArrayList<>(set);
		Collections.sort(hashData);
		
		// 日付と金額のリスト
		ArrayList<DataSum> dsList = new ArrayList<DataSum>();
		for(int i = 0; i<hashData.size(); i++){
			dsList.add(new DataSum(hashData.get(i), dayExpense.get(i)));
		}
		model.addAttribute("id",id);
		model.addAttribute("ds",dsList); //日付けと支出を渡す
		return "history";
	}

	/**
	 * 日ごとの支出詳細表示
	 * 例 3月12日
	 *    食費 ¥220
	 *    食費 ¥300
	 *    交通費　¥200
	 */
	@RequestMapping(value = "/details_day", method = RequestMethod.GET)
	public String details(Model model , @RequestParam("date") final String date){
		final List<DataTable> paylist = dao.getDate(id,date);  //データ取得
		ArrayList<BigDecimal> expenseAll = new ArrayList<BigDecimal>(); //¥0を含めたすべての金額(decimal)を格納する
		ArrayList<CategorySum> dsList = new ArrayList<CategorySum>(); //Details型のリスト
		//String date = (sdf.format(paylist.get(0).getDate()));
		for(int i = 0; i < paylist.size(); i++){
			
			expenseAll.add(paylist.get(i).getFood()); //1
			expenseAll.add(paylist.get(i).getDaily()); //2
			expenseAll.add(paylist.get(i).getEntame()); //3
			expenseAll.add(paylist.get(i).getTraffic()); //4
			expenseAll.add(paylist.get(i).getAmusement()); //5
			expenseAll.add(paylist.get(i).getFashion()); //6
			expenseAll.add(paylist.get(i).getGoods()); //7
			expenseAll.add(paylist.get(i).getMedical()); //8
			expenseAll.add(paylist.get(i).getPublicfee()); //9
			expenseAll.add(paylist.get(i).getRent()); //10
			expenseAll.add(paylist.get(i).getOthers()); //11
		
			for(int k = 0; k<11; k++) {//food ~ othersが11項目のため
				CategorySum ds  = new CategorySum();//インスタンス生成
				if(expenseAll.get(k).compareTo( new BigDecimal("0")) != 0){

					switch(k){ //カテゴリの名前をセット
					case 0: 
						ds.setCategory(" 食費"); 
						break;
					case 1: 
						ds.setCategory(" 日用品");
						break;
					case 2: 
						ds.setCategory(" 交際費");
						break;
					case 3: 
						ds.setCategory(" 交通費");
						break;
					case 4: 
						ds.setCategory(" 娯楽");
						break;
					case 5: 
						ds.setCategory(" 衣服・美容");
						break;
					case 6: 
						ds.setCategory(" 雑貨");
						break;
					case 7:
						ds.setCategory(" 医療費");
						break;
					case 8:
						ds.setCategory(" 公共料金");
						break;
					case 9:
						ds.setCategory(" 家賃");
						break;
					case 10:
						ds.setCategory(" その他");
						break;
					}
					
					ds.setDailySum(expenseAll.get(k)); //金額をセット
					
					ds.setDataId(paylist.get(i).getDataId());
					dsList.add(ds);
				}
			}
			expenseAll = new ArrayList<BigDecimal>(); //初期化
		}

		model.addAttribute("dsList",dsList);
		model.addAttribute("date", date);
		return "details_day";
	}
	
	/**
	* カテゴリー別に月の支出表示
	* 
	*/
		@RequestMapping(value = "/details_month", params = "id", method = RequestMethod.GET)
		public String detail_month(Model model , @RequestParam("id") final String id) {
		List<CategorySum> csLastMonth = new ArrayList<CategorySum>();
		List<DataTable> lastManthList = dao.getLastmonth(id);
		
		/* 先月のカテゴリー別支出額*/
		BigDecimal foodSum = new BigDecimal(0);
		BigDecimal dailySum = new BigDecimal(0);
		BigDecimal entameSum = new BigDecimal(0);
		BigDecimal trafficSum = new BigDecimal(0);
		BigDecimal amusementSum = new BigDecimal(0);
		BigDecimal fashionSum = new BigDecimal(0);
		BigDecimal goodsSum = new BigDecimal(0);
		BigDecimal medicalSum = new BigDecimal(0);
		BigDecimal publicfeeSum = new BigDecimal(0);
		BigDecimal rentSum = new BigDecimal(0);
		BigDecimal othersSum = new BigDecimal(0);
		BigDecimal totalLastSum = new BigDecimal(0); //先月の合計値
		
		for (int i = 0; i < lastManthList.size(); i++) {
		foodSum = foodSum.add(lastManthList.get(i).getFood());
		dailySum = dailySum.add(lastManthList.get(i).getDaily());
		entameSum = entameSum.add(lastManthList.get(i).getEntame());
		trafficSum = trafficSum.add(lastManthList.get(i).getTraffic());
		amusementSum = amusementSum.add(lastManthList.get(i).getAmusement());
		fashionSum = fashionSum.add(lastManthList.get(i).getFashion());
		goodsSum = goodsSum.add(lastManthList.get(i).getGoods());
		medicalSum = medicalSum.add(lastManthList.get(i).getMedical());
		publicfeeSum = publicfeeSum.add(lastManthList.get(i).getPublicfee());
		rentSum = rentSum.add(lastManthList.get(i).getRent());
		othersSum = othersSum.add(lastManthList.get(i).getOthers());
		}
		
		totalLastSum = foodSum.add(dailySum.add(entameSum.add(trafficSum.add(amusementSum
		.add(fashionSum.add(goodsSum.add(medicalSum.add(publicfeeSum.add(rentSum.add(othersSum))))))))));
		
		csLastMonth.add(new CategorySum("食費",foodSum));
		csLastMonth.add(new CategorySum("日用品",dailySum));
		csLastMonth.add(new CategorySum("交際費",entameSum));
		csLastMonth.add(new CategorySum("交通費",trafficSum));
		csLastMonth.add(new CategorySum("娯楽",amusementSum));
		csLastMonth.add(new CategorySum("衣服・美容",fashionSum));
		csLastMonth.add(new CategorySum("雑貨", goodsSum));
		csLastMonth.add(new CategorySum("医療費",medicalSum));
		csLastMonth.add(new CategorySum("公共料金",publicfeeSum));
		csLastMonth.add(new CategorySum("家賃",rentSum));
		csLastMonth.add(new CategorySum("その他",othersSum));
		
		model.addAttribute("csLastMonth", csLastMonth);
		model.addAttribute("totalLastSum", totalLastSum);

		
		/*今月のカテゴリー別支出額*/
		List<CategorySum> csThisMonth = new ArrayList<CategorySum>();
		List<DataTable> thisManthList = dao.getThismonth(id);
		foodSum = new BigDecimal(0);
		dailySum = new BigDecimal(0);
		entameSum = new BigDecimal(0);
		trafficSum = new BigDecimal(0);
		amusementSum = new BigDecimal(0);
		fashionSum = new BigDecimal(0);
		goodsSum = new BigDecimal(0);
		medicalSum = new BigDecimal(0);
		publicfeeSum = new BigDecimal(0);
		rentSum = new BigDecimal(0);
		othersSum = new BigDecimal(0);
		BigDecimal totalThisSum = new BigDecimal(0); //今月の合計値

		
		for (int i = 0; i < thisManthList.size(); i++) {
		foodSum = foodSum.add(thisManthList.get(i).getFood());
		dailySum = dailySum.add(thisManthList.get(i).getDaily());
		entameSum = entameSum.add(thisManthList.get(i).getEntame());
		trafficSum = trafficSum.add(thisManthList.get(i).getTraffic());
		amusementSum = amusementSum.add(thisManthList.get(i).getAmusement());
		fashionSum = fashionSum.add(thisManthList.get(i).getFashion());
		goodsSum = goodsSum.add(thisManthList.get(i).getGoods());
		medicalSum = medicalSum.add(thisManthList.get(i).getMedical());
		publicfeeSum = publicfeeSum.add(thisManthList.get(i).getPublicfee());
		rentSum = rentSum.add(thisManthList.get(i).getRent());
		othersSum = othersSum.add(thisManthList.get(i).getOthers());
		}
		totalThisSum = foodSum.add(dailySum.add(entameSum.add(trafficSum.add(amusementSum
		.add(fashionSum.add(goodsSum.add(medicalSum.add(publicfeeSum.add(rentSum.add(othersSum))))))))));
		
		csThisMonth.add(new CategorySum("食費",foodSum));
		csThisMonth.add(new CategorySum("日用品",dailySum));
		csThisMonth.add(new CategorySum("交際費",entameSum));
		csThisMonth.add(new CategorySum("交通費",trafficSum));
		csThisMonth.add(new CategorySum("娯楽",amusementSum));
		csThisMonth.add(new CategorySum("衣服・美容",fashionSum));
		csThisMonth.add(new CategorySum("雑貨", goodsSum));
		csThisMonth.add(new CategorySum("医療費",medicalSum));
		csThisMonth.add(new CategorySum("公共料金",publicfeeSum));
		csThisMonth.add(new CategorySum("家賃",rentSum));
		csThisMonth.add(new CategorySum("その他",othersSum));
		
		model.addAttribute("csThisMonth", csThisMonth); 
		model.addAttribute("totalThisSum", totalThisSum);
		
		BigDecimal detailsMonth = new BigDecimal(0);
		model.addAttribute("details_month", detailsMonth);
		return "details_month";
		}
}