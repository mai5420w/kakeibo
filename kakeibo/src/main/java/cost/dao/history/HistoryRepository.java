package cost.dao.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cost.dao.DataTable;

@Repository
public interface HistoryRepository extends JpaRepository<DataTable, Integer>{



}
