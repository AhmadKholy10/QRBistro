package com.example.qrcodedemo.model.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
	
	@Autowired
	private  StudentRepository studentRepository;
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public Student findByID(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student Not Found"));
	}

}
