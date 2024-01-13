package org.jsp.studentbatchapp.repository;

import java.util.List;

import org.jsp.studentbatchapp.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Query("select b.students from Batch b where b.batchCode=?1")
	public List<Student> findByBatchCode(String batchCode);

	@Query("select b.students from Batch b where b.trainer=?1")
	public List<Student> findByTrainerName(String trainer_name);

	@Query("select b.students from Batch b where b.subject=?1")
	public List<Student> findBySubject(String subject_name);

	@Query("select b.students from Batch b where b.id=?1")
	public List<Student> findByBatchId(int id);
}
