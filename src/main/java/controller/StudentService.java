package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentService implements StudentDAO {

    @Autowired
    private final JdbcTemplate jdbc;

    public StudentService(DataSource dataSource) throws IOException {

        this.jdbc = new JdbcTemplate(dataSource);
    }

    public Student isValidUser(String name) {

        System.out.println("i am in isValideUser");

        Student std;
       std = jdbc.queryForObject("select * from student where fullname = ? ", new studMapper(), name.trim());
       return std;

    }

    @Override
    public List<StudentCourses> getStudCourses(int id) {

        List<StudentCourses> stdCourses;

        stdCourses = jdbc.query("select studentCourses.studid,courses.coursename " +
                "from studentCourses inner join courses on courses.id = studentCourses.courseid "
                +  " and studentCourses.studid = ?", new studCoursesMapper(),id);

        return stdCourses;
    }

    @Override
    public List<StudentGrades> getGrades(int id) {

        List<StudentGrades> stdGrades;

        stdGrades =  jdbc.query("select studentGrades.studid,courses.coursename,studentGrades.grade " +
                        "from studentGrades inner join courses on courses.id = studentGrades.courseid "
                        +  " and studentGrades.studid = ?", new studGradesMapper(),id);

        return stdGrades;
    }

    @Override
    public float getAvgCourse(String course) {

        float avgGrade;

        avgGrade = jdbc.queryForObject("select avg(grade) from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?",Float.class,course);
        return avgGrade;
    }

    @Override
    public float getMaxCourse(String course) {

        float maxGrade;

        maxGrade = jdbc.queryForObject("select max(grade) from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?",Float.class,course);
        return maxGrade;
    }

    @Override
    public float getMinCourse(String course) {

        float minGrade;

        minGrade = jdbc.queryForObject("select min(grade) from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?",Float.class,course);
        return minGrade;
    }

    private static final class studMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student td = new Student();
            td.setSemester(rs.getInt("semester"));
            td.setCardID(rs.getString("cardID"));
            td.setFullName(rs.getString("fullName"));
            td.setId(rs.getInt("id"));
            return td;
        }
    }

    private static final class studCoursesMapper implements RowMapper<StudentCourses> {

        @Override
        public StudentCourses mapRow(ResultSet rs, int index) throws SQLException {
            StudentCourses td = new StudentCourses();
            td.setCourseName(rs.getString("coursename"));
            td.setStudentId(rs.getInt("studid"));
            return td;
        }
    }

    private static final class studGradesMapper implements RowMapper<StudentGrades> {

        @Override
        public StudentGrades mapRow(ResultSet rs, int index) throws SQLException {
            StudentGrades td = new StudentGrades();
            td.setGrade(rs.getInt("grade"));
            td.setStudentId(rs.getInt("studentId"));
            td.setCourseName(rs.getString("coursename"));

            return td;
        }
    }

}


