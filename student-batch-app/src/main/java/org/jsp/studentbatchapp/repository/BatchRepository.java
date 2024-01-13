package org.jsp.studentbatchapp.repository;

import java.util.List;

import org.jsp.studentbatchapp.dto.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
	 @Query("select s.batch from Student s where s.id=?1" )
	    List<Batch> findByStudentId(int studentId);

	@Query("select s.batch from Student s where s.phone=?1")
	public List<Batch> findByStudentPhone(long phone);
	
}
