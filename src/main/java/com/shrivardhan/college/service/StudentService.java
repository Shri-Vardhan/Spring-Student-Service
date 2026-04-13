package com.shrivardhan.college.service;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.repository.StudentRepository;
import com.shrivardhan.college.exception.StudentAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student retrieveStudent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student insertStudent(Student student) {
        if (repository.existsById(student.getId())) {
            throw new StudentAlreadyExistsException("Student already exists");
        }
        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    public Student updateStudent(Long id, String name, Integer age) {
        Student student = retrieveStudent(id);
        student.setName(name);
        student.setAge(age);
        return repository.save(student);
    }

    //Method-Name
    public List<Student> getStudentsByNameAndAge(String name, int age) {
        return repository.findByNameAndAgeGreaterThan(name, age);
    }

   //JPQL
    public List<Student> getStudentsJPLquery(String name, int age) {
        return repository.findStudents(name, age);
    }

    //Native-SQL
    public List<Student> getStudentsNative(String name, int age) {
        return repository.findStudentNative(name, age);
    }
}