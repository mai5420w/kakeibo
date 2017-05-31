package cost.dao.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cost.dao.DataTable;

@Repository
//Springの機能を使う準備　insert 親（JpaRepository）の機能が使える　エンティテー、主キーの型
public interface DataRepository extends JpaRepository<DataTable, Integer> 
{
}