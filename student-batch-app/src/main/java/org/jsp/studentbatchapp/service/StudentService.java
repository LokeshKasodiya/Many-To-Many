package org.jsp.studentbatchapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.studentbatchapp.dao.BatchDao;
import org.jsp.studentbatchapp.dao.StudentDao;
import org.jsp.studentbatchapp.dto.Batch;
import org.jsp.studentbatchapp.dto.ResponseStructure;
import org.jsp.studentbatchapp.dto.Student;
import org.jsp.studentbatchapp.exception.BatchNotFoundException;
import org.jsp.studentbatchapp.exception.StudentAlreadyPresentException;
import org.jsp.studentbatchapp.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private BatchDao batchDao;

	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student, int batch_id) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Batch> recBatch = batchDao.findById(batch_id);
		if (recBatch.isPresent()) {
			Batch batch = recBatch.get();
			 if (student.getBatches() == null) {
		            student.setBatches(new ArrayList<>()); }
			student.getBatches().add(batch);
			batch.getStudents().add(student);
			batchDao.saveBatch(batch);
			structure.setData(studentDao.saveStudent(student));
			structure.setMessage("Student registerd successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.CREATED);
		}
		throw new BatchNotFoundException("Invalid Batch Id");
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Student> recStudent = studentDao.findById(student.getId());
		if (recStudent.isPresent()) {
			Student dbStudent = recStudent.get();
			dbStudent.setId(student.getId());
			dbStudent.setName(student.getName());
			dbStudent.setPhone(student.getPhone());
			dbStudent.setAge(student.getAge());
			dbStudent.setPercentage(student.getPercentage());
			structure.setData(studentDao.saveStudent(dbStudent));
			structure.setMessage("Student updated successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.ACCEPTED);
		}
		throw new StudentNotFoundException("Invalid Student Id");
	}

	public ResponseEntity<ResponseStructure<List<Student>>> fineByBatchId(int batch_id) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<>();
		List<Student> students = studentDao.findByBatchId(batch_id);
		if (students.size() > 0) {
			structure.setData(students);
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
		}
		throw new StudentNotFoundException("Invalid Batch Id");
	}

	public ResponseEntity<ResponseStructure<List<Student>>> fineByBatchCode(String batch_code) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<>();
		List<Student> students = studentDao.findByBatchCode(batch_code);
		if (students.size() > 0) {
			structure.setData(students);
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
		}
		throw new StudentNotFoundException("Invalid Batch Code");
	}

	public ResponseEntity<ResponseStructure<List<Student>>> fineBySubject(String subject) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<>();
		List<Student> students = studentDao.findBySubject(subject);
		if (students.size() > 0) {
			structure.setData(students);
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
		}
		throw new StudentNotFoundException("Invalid Subject");
	}

	public ResponseEntity<ResponseStructure<List<Student>>> fineByTrainerName(String trainer_name) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<>();
		List<Student> students = studentDao.findByTrainerName(trainer_name);
		if (students.size() > 0) {
			structure.setData(students);
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
		}
		throw new StudentNotFoundException("Invalid Trainer Name");
	}
	public ResponseEntity<ResponseStructure<Student>> addStudentToBatch(int studentId,int batchId){
		ResponseStructure<Student> structure=new ResponseStructure<>();
		Optional<Student> recStudent=studentDao.findById(studentId);
		if(recStudent.isPresent()) {
		Student student=recStudent.get();
		Optional<Batch> recBatch=batchDao.findById(batchId);
		if(recBatch.isPresent()) {
			List<Batch> batches=student.getBatches();
			Batch batch=recBatch.get();
			batches=student.getBatches();
			if(batches.contains(batch)){
				throw new StudentAlreadyPresentException("Can't add Student to batch");
			}
			batches.add(batch);
			student.setBatches(batches);
			structure.setData(studentDao.saveStudent(student));
			structure.setMessage("Student added to the batch ");
			structure.setStatusCode(HttpStatus.OK.value());
			System.out.println();
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
			}
		throw new BatchNotFoundException("Batch Not Found With Entered Batch Id");
		}
		throw new StudentNotFoundException("Student Not Found With Entered Student Id");
	}
}
