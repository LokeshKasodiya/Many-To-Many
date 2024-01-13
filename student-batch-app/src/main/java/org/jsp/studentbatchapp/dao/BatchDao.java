package org.jsp.studentbatchapp.dao;

import java.util.Optional;
import java.util.List;

import org.jsp.studentbatchapp.dto.Batch;
import org.jsp.studentbatchapp.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BatchDao {
	@Autowired
	private BatchRepository batchRepository;

	public Batch saveBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	public Optional<Batch> findById(int id) {
		return batchRepository.findById(id);
	}

	public List<Batch> findByStudentId(int id) {
		return batchRepository.findByStudentId(id);
	}

	public List<Batch> findByStudentPhone(long phone) {
		return batchRepository.findByStudentPhone(phone);
	}
}
