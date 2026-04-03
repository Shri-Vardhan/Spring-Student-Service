package com.shrivardhan.college.service;

import com.shrivardhan.college.exception.StudentAlreadyExistsException;
import com.shrivardhan.college.exception.StudentNotFoundException;
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

    public List<Student> getStudents(int offset, int size) {
        return repository.findAll(offset, size);
    }

    public int getTotalCount() {
        return repository.count();
    }

    public int insertStudent(Student student) {
        if (repository.existsById(student.getId())) {
            throw new StudentAlreadyExistsException("Student with ID " + student.getId() + " already exists");
        } else {
            return repository.save(student);
        }
    }

    public void updateStudentName(Long id, String name){
        repository.updateName(id , name);
    }

    public int deleteStudent(long id) {
        return repository.deleteById(id);
    }

    public Student retrieveStudent(long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student with ID " + id + " not found"
            );
        } else {
            return repository.getStudent(id);
        }
    }

    public int updateStudent(Student student) {
        return repository.updateStudent(student);
    }

}