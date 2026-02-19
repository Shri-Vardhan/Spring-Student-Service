package com.shrivardhan.college.repository;

import com.shrivardhan.college.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAll() {
        String sql = "SELECT id, name, age FROM student";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
                    Student s = new Student();
                    s.setId(rs.getLong("id"));
                    s.setName(rs.getString("name"));
                    s.setAge(rs.getInt("age"));
                    return s;
                }
        );
    }

    public Student getStudent(Long id) {
        String sql = "SELECT id, name, age FROM student WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    Student s = new Student();
                    s.setId(rs.getLong("id"));
                    s.setName(rs.getString("name"));
                    s.setAge(rs.getInt("age"));
                    return s;
                },
                id
        );
    }

    public int save(Student student) {
        String sql = "INSERT INTO STUDENT (id, name, age) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAge());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM STUDENT WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateName(Long id, String name) {
        String sql = "UPDATE STUDENT SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, id);
    }

    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM student WHERE id = ?";
        List<Integer> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getInt(1),
                id
        );
        return !result.isEmpty();
    }


}

