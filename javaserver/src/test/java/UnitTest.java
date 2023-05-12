import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.junit.jupiter.api.Assertions;
import cn.edu.zut.cs.dao.StudentDao;
import cn.edu.zut.cs.impl.StudentDaoImpl;
import cn.edu.zut.cs.model.Student;

import java.sql.SQLException;
@SpringJUnitConfig(locations = "classpath*:javaserver-context.xml")

public class UnitTest {

    public static void main(String[] args) throws SQLException {
        StudentDao studentDao = new StudentDaoImpl();

        //添加学生

        studentDao.addStudent(new Student("test",78));
        studentDao.addStudent(new Student("test2",97));
        //输出所有的学生
        for (Student student : studentDao.getAllStudents()) {
            System.out.println("Student: [RollNo : "
                    +student.getRollNo()+", Name : "+student.getName()+" ]");
        }


        //更新学生
        Student student =studentDao.getStudent(78);
        student.setName("Michael");
        studentDao.updateStudent(student);

        //获取学生
        Student st=studentDao.getStudent(78);
        System.out.println("Student: [RollNo : "
                +st.getRollNo()+", Name : "+st.getName()+" ]");
        //删除学生
        Student de_st=studentDao.getStudent(97);
        studentDao.deleteStudent(de_st);
    }

}
