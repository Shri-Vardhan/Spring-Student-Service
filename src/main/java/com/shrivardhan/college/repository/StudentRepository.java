package com.shrivardhan.college.repository;

import com.shrivardhan.college.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}