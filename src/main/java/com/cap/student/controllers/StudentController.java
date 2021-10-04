package com.cap.student.controllers;

import com.cap.student.entity.Student;
import com.cap.student.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

  @Autowired
  StudentRepository studentRepository;

  private List<Student> studentList = createList();

  @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Student> getAllStudents() {
    studentRepository.findAll().forEach(studentList::add);
    return studentList;
  }

  @GetMapping(value = "/students/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Student> getStudentById(long id) {
    Optional<Student> student = studentRepository.findById(id);
    if (student.isPresent()) {
      return new ResponseEntity<>(student.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/students")
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    try {
      Student _student = studentRepository.
          save(new Student(student.getId(), student.getName(), student.getStatus()));
      return new ResponseEntity<>(_student, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @DeleteMapping("/students/{id}")
  public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
    try {
      studentRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private static List<Student> createList() {

    List<Student> studentList = new ArrayList<>();
    Student student1 = new Student();
    student1.setName("Andrew");
    student1.setStatus("pending");
    student1.setId(1);
    studentList.add(student1);

    Student student2 = new Student();
    student2.setName("John");
    student2.setStatus("approved");
    student2.setId(2);
    studentList.add(student2);
    return studentList;
  }
}
