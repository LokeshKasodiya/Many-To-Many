package org.jsp.studentbatchapp.controller;

import java.util.List;

import org.jsp.studentbatchapp.dto.ResponseStructure;
import org.jsp.studentbatchapp.dto.Student;
import org.jsp.studentbatchapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/students/{batch_id}")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student,
			@PathVariable int batch_id) {
		return studentService.saveStudent(student, batch_id);
	}

	@PutMapping("/students")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}

	@GetMapping("/students/find-by-batch-id/{batch_id}")
	public ResponseEntity<ResponseStructure<List<Student>>> findByBatchId(@PathVariable int batch_id) {
		return studentService.fineByBatchId(batch_id);
	}

	@GetMapping("/students/find-by-batch-code/{batch_code}")
	public ResponseEntity<ResponseStructure<List<Student>>> findByBatchCode(@PathVariable String batch_code) {
		return studentService.fineByBatchCode(batch_code);
	}

	@GetMapping("/students/find-by-subject/{subject}")
	public ResponseEntity<ResponseStructure<List<Student>>> findBySubject(@PathVariable String subject) {
		return studentService.fineBySubject(subject);
	}

	@GetMapping("/students/find-by-trainer-name/{name}")
	public ResponseEntity<ResponseStructure<List<Student>>> findByTrainerName(@PathVariable String name) {
		return studentService.fineByTrainerName(name);
	}

	@PutMapping("/students/addStudents/{studentId}/batch/{batchId}")
	public ResponseEntity<ResponseStructure<Student>> addStudentToBatch(@PathVariable int studentId,
			@PathVariable int batchId) {
		return studentService.addStudentToBatch(studentId, batchId);
	}
}
