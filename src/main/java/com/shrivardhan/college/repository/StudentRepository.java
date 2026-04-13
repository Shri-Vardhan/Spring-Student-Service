package com.shrivardhan.college.repository;

import com.shrivardhan.college.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Method Name
    List<Student> findByNameAndAgeGreaterThan(String name, int age);

    // JPLQUERY
    @Query("SELECT s FROM Student s WHERE s.name = :name AND s.age > :age")
    List<Student> findStudents(@Param("name") String name,
                               @Param("age") int age);
    // NativeSQL
    @Query(value = "SELECT * FROM student WHERE name = :name AND age > :age", nativeQuery = true)
    List<Student> findStudentNative(@Param("name") String name, @Param("age") int age);

}