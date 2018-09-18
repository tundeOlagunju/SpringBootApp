package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.DBText;


public interface DBTextRepository extends JpaRepository<DBText,String>{
	
	DBText findFirstByOrderByIdDesc();
	
}
