package cn.edu.zut.cs.impl;

import cn.edu.zut.cs.dao.StudentDao;
import cn.edu.zut.cs.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    static Connection conn;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm","root", "123456");


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //列表是当作一个数据库
    List<Student> students;

    public StudentDaoImpl(){

        students = new ArrayList<>();
        Student student1 = new Student("Robert",0);
        Student student2 = new Student("John",1);
        students.add(student1);
        students.add(student2);
    }
    @Override
    public void deleteStudent(Student student) {
        students.remove(student);
        String sql=" delete from student where rollNo = "+ student.getRollNo()+ "";
        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Student: RollNo"+student.getRollNo()+" , deleted from database");
    }

    //从数据库中检索学生名单
    @Override
    public List<Student> getAllStudents() throws SQLException {
        String sql = "select * from student ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        students = new ArrayList<>();
        while(rs.next()) {
            Student st = new Student(rs.getString("name"),rs.getInt("rollNo"));
            students.add(st);
        }
        return students;
    }

    @Override
    public Student getStudent(int rollNo) throws SQLException {
        String sql = "select * from student  where rollNo= "+rollNo + "";
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            return new Student(rs.getString("name"),rs.getInt("rollNo"));
        }
        else{
            return null;
        }
    }

    @Override
    public void updateStudent(Student student) {
        students.get(0).setName(student.getName());
        String sql="update student set name='"+student.getName() +" 'where rollNo="+student.getRollNo()+"";
        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Student: Roll No"+ student.getRollNo()+ ", updated in the database");
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
        String sql = "insert into student (rollNo, name) values("+ student.getRollNo() + " ,'" + student.getName()+"')";
        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}