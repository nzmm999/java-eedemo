package cn.edu.zut.cs.dao;

import cn.edu.zut.cs.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    List<Student> getAllStudents() throws SQLException;
    Student getStudent(int rollNo) throws SQLException;
    void updateStudent(Student student);
    void deleteStudent(Student student);

    void addStudent(Student student);
}