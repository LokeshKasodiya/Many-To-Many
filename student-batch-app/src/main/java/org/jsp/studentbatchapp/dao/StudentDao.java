package org.jsp.studentbatchapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.studentbatchapp.dto.Student;
import org.jsp.studentbatchapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public Optional<Student> findById(int id) {
		return studentRepository.findById(id);
	}

	public List<Student> findByBatchId(int batch_id) {
		return studentRepository.findByBatchId(batch_id);
	}

	public List<Student> findByBatchCode(String batchCode) {
		return studentRepository.findByBatchCode(batchCode);
	}

	public List<Student> findBySubject(String subject) {
		return studentRepository.findBySubject(subject);
	}

	public List<Student> findByTrainerName(String trainer) {
		return studentRepository.findByTrainerName(trainer);
	}
}
