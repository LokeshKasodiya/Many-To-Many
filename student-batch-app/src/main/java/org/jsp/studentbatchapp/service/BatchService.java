package org.jsp.studentbatchapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.studentbatchapp.dao.BatchDao;
import org.jsp.studentbatchapp.dto.Batch;
import org.jsp.studentbatchapp.dto.ResponseStructure;
import org.jsp.studentbatchapp.exception.BatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
	@Autowired
	private BatchDao batchDao;

	public ResponseEntity<ResponseStructure<Batch>> saveBatch(Batch batch) {
		ResponseStructure<Batch> structure = new ResponseStructure<>();
			structure.setData(batchDao.saveBatch(batch));
			structure.setMessage("Batch registerd successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Batch>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Batch>> updateBatch(Batch batch) {
		ResponseStructure<Batch> structure = new ResponseStructure<>();
		Optional<Batch> recBatch = batchDao.findById(batch.getId());
		if (recBatch.isPresent()) {
			Batch dbBatch = recBatch.get();
			dbBatch.setId(batch.getId());
			dbBatch.setBatchCode(batch.getBatchCode());
			dbBatch.setSubject(batch.getSubject());
			dbBatch.setTrainer(batch.getTrainer());
			structure.setData(batchDao.saveBatch(dbBatch));
			structure.setMessage("Batch registerd successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Batch>>(structure, HttpStatus.ACCEPTED);
		}
		throw new BatchNotFoundException("Invalid Batch Id");
	}

	public ResponseEntity<ResponseStructure<List<Batch>>> findByStudentId(int id) {
		ResponseStructure<List<Batch>> structure = new ResponseStructure<>();
		List<Batch> batches = batchDao.findByStudentId(id);
		if (batches.size() > 0) {
			structure.setData(batches);
			structure.setMessage("Batches Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Batch>>>(structure, HttpStatus.OK);
		}
		throw new BatchNotFoundException("Invalid Student Id");
	}

	public ResponseEntity<ResponseStructure<List<Batch>>> findByStudentPhone(long phone) {
		ResponseStructure<List<Batch>> structure = new ResponseStructure<>();
		List<Batch> batches = batchDao.findByStudentPhone(phone);
		if (batches.size() > 0) {
			structure.setData(batches);
			structure.setMessage("Batches Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Batch>>>(structure, HttpStatus.OK);
		}
		throw new BatchNotFoundException("Invalid Student number");
	}

}
