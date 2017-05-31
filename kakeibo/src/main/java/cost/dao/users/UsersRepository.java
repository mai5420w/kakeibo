package cost.dao.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Springの機能を使う準備　insert 親（JpaRepository）の機能が使える　エンティテー、主キーの型
public interface UsersRepository extends JpaRepository<Users, String> 
{
}