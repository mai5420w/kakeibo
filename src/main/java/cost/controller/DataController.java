package cost.controller;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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
import cost.dao.data.DataDaoImpl;
import cost.dao.data.DataRepository;
import cost.dao.users.UsersRepository;



@Controller
@EnableAutoConfiguration

public class DataController {
	@PersistenceContext
	private EntityManager entityManager;
	private DataDaoImpl dao;
	
	@PostConstruct
	public void init(){
		dao = new DataDaoImpl(entityManager);
	}
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	DataRepository dataRepository;
	
	@RequestMapping(value = "/top/input", method = RequestMethod.POST)
	public String Sum(Model model, @ModelAttribute DataBox data) throws ParseException {
		
		Date inputDate = data.getDate();
		String inputId = data.getId();
		
	    BigDecimal inputFood = new BigDecimal(0.0);
	    BigDecimal inputDaily= new BigDecimal(0.0);
	    BigDecimal inputEntame= new BigDecimal(0.0);
	    BigDecimal inputTraffic= new BigDecimal(0.0);
	    BigDecimal inputAmusement= new BigDecimal(0.0);
	    BigDecimal inputFashion= new BigDecimal(0.0);
	    BigDecimal inputGoods= new BigDecimal(0.0);
	    BigDecimal inputMedical= new BigDecimal(0.0);
	    BigDecimal inputPublicfee= new BigDecimal(0.0);
	    BigDecimal inputRent= new BigDecimal(0.0);
	    BigDecimal inputOthers= new BigDecimal(0.0);
	    
	    
	    if(data.getCotegory().equals("food")){
	    	inputFood = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("daily")){
	    	inputDaily = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("entame")){
	    	inputEntame = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("traffic")){
	    	inputTraffic = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("amusement")){
	    	inputAmusement = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("fashion")){
	    	inputFashion = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("goods")){
	    	inputGoods = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("medical")){
	    	inputMedical = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("publicfee")){
	    	inputPublicfee = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("rent")){
	    	inputRent = BigDecimal.valueOf(data.getData());
	    }else if(data.getCotegory().equals("others")){
	    	inputOthers = BigDecimal.valueOf(data.getData());
	    }

		DataTable dataTable = new DataTable(data.getId(),inputDate,inputFood, inputDaily, inputEntame, inputTraffic, inputAmusement, inputFashion,inputGoods, inputMedical, inputPublicfee, inputRent, inputOthers);
		dataRepository.save(dataTable);
		
		BigDecimal expense = new BigDecimal(0); //BigDecimal型
		final List<DataTable> datalist = dao.getThismonth(inputId); //リストをゲットしてくる
		
		for(int i = 0; i < datalist.size(); i++){
			dataTable = datalist.get(i);
			expense = expense.add(dataTable.getFood());
			expense = expense.add(dataTable.getDaily());
			expense = expense.add(dataTable.getEntame());
			expense = expense.add(dataTable.getTraffic());
			expense = expense.add(dataTable.getAmusement());
			expense = expense.add(dataTable.getFashion());
			expense = expense.add(dataTable.getGoods());
			expense = expense.add(dataTable.getMedical());
			expense = expense.add(dataTable.getPublicfee());
			expense = expense.add(dataTable.getRent());
			expense = expense.add(dataTable.getOthers());
			
			model.addAttribute("sum",expense);
			model.addAttribute("id",inputId);
		}
		return "top";
	}
}