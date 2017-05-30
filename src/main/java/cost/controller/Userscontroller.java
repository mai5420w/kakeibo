package cost.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cost.dao.DataTable;
import cost.dao.data.DataBox;
import cost.dao.users.Users;
import cost.dao.users.UsersDaoImpl;
import cost.dao.users.UsersRepository;

@Controller
@EnableAutoConfiguration

public class Userscontroller {
	@PersistenceContext
	private EntityManager entityManager;
	private UsersDaoImpl dao;

	@PostConstruct
	public void init(){
		dao = new UsersDaoImpl(entityManager);
	}

	@RequestMapping("/logintop")
	public String loginTop(@ModelAttribute Users users){
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute Users users,Model model) {



		//入力値の取得
		String inputId = users.getId();
		String inputPw = users.getPw();

		//入力id＝DBidのレコードを取得
		List<Users>usersList = dao.getById(inputId);


		//リストが取得される＆passの一致
		if(usersList.equals(null) || usersList.size() != 1){
			model.addAttribute("message","id,passが誤っています。");
			return "login";
		}else if(usersList.size() == 1){

			Users u = usersList.get(0);
			String pw = u.getPw();
			if(inputPw.equals(pw)){
				model.addAttribute("dataBox", new DataBox());
				model.addAttribute("id", inputId);

				DataTable p = new DataTable(); //DataTabelのインスタンス作成
				BigDecimal expense = new BigDecimal(0); //BigDecimal型
				final List<DataTable> dataList = dao.getThismonth(inputId); //リストをゲットしてくる

				for(int i = 0; i < dataList.size(); i++){
					p = dataList.get(i);
					expense = expense.add(p.getFood());
					expense = expense.add(p.getDaily());
					expense = expense.add(p.getEntame());
					expense = expense.add(p.getTraffic());
					expense = expense.add(p.getAmusement());
					expense = expense.add(p.getFashion());
					expense = expense.add(p.getGoods());
					expense = expense.add(p.getMedical());
					expense = expense.add(p.getPublicfee());
					expense = expense.add(p.getRent());
					expense = expense.add(p.getOthers());
				}
				model.addAttribute("sum", expense);
				model.addAttribute("id", inputId);

				return "top";
			}else{
				model.addAttribute("message","id,passが誤っています。");
				return "login";
			}
		}else{
			model.addAttribute("message","id,passが誤っています。");
			return"login";
		}
	}

	@RequestMapping("/new_account")
	public String newAccount(@ModelAttribute Users users){
		return "new_account";
	}

	@Autowired
	UsersRepository usersRepository;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(@ModelAttribute Users users, Model model) {

		//入力値の取得
		String inputId = users.getId();
		String inputPw = users.getPw();

		//入力id＝DBidのレコードを取得
		List<Users>usersList = dao.getById(inputId);

		if(usersList.size() >= 1){
			model.addAttribute("message","既に使用されているidです。別のidで登録してください。");
			return "new_account";
		}else{
			Users user = new Users(inputId,inputPw);
			usersRepository.save(user);
			model.addAttribute("message","新規登録が完了しました。");
			return "login";
		}
	} 	
}