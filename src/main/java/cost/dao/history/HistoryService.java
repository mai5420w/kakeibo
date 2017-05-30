package cost.dao.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cost.dao.DataTable;

@Service
@Transactional
public class HistoryService {
	@Autowired
	HistoryRepository historyrepository;
	
	public List<DataTable> findAll() {
        return historyrepository.findAll();
    }

    public DataTable findOne(int data_id) {
        return historyrepository.findOne(data_id);
    }

    public DataTable create(DataTable dt) {
        return historyrepository.save(dt);
    }

    public DataTable update(DataTable dt) {
        return historyrepository.save(dt);
    }

    public void delete(int data_id) {
        historyrepository.delete(data_id);
    }
}