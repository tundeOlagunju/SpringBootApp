package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

	DBFile findFirstByOrderByIdDesc();

}

