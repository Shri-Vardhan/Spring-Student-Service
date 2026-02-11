package com.shrivardhan.college.service;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.repository.StudentRepository;
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

    public void insertStudent(Student student) {
        repository.save(student);
    }

    public void updateStudentName(Long id, String name) {
        repository.updateName(id, name);
    }

    public void deleteStudent(long id) {
        repository.deleteById(id);
    }

}
