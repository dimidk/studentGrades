package controller;

//import model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentDAO {

    //@Autowired
    //private final JdbcTemplate jdbc;
    private DataSource ds;

    public StudentService(DataSource dataSource) throws IOException {

        //this.jdbc = new JdbcTemplate(dataSource);
        this.ds = dataSource;
    }

    public Student isValidUser(String name) throws SQLException {

        System.out.println("i am in isValideUser");

        Student std = new Student();
        Connection con = ds.getConnection();
        String sql = "select * from student where fullname = ?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1,name);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            std.setId(rs.getInt("id"));
            std.setFullName(rs.getString("fullname"));
        }

       //std = jdbc.queryForObject("select * from student where fullname = ? ", new studMapper(), name.trim());
       return std;

    }

    @Override
    public List<StudentCourses> getStudCourses(int id) throws SQLException {

        List<StudentCourses> listRes = new ArrayList<>();
        Connection con = ds.getConnection();
        String sql = "select studentCourses.studid,courses.coursename " +
                "from studentCourses inner join courses on courses.id = studentCourses.courseid "
                +  " and studentCourses.studid = ?";

        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){
            StudentCourses std = new StudentCourses();
            std.setStudentId(rs.getInt("studid"));
            std.setCourseName(rs.getString("coursename"));
            listRes.add(std);
        }

        return listRes;
    }

    @Override
    public List<StudentGrades> getGrades(int id) throws SQLException {

        List<StudentGrades> listRes = new ArrayList<>();
        Connection con = ds.getConnection();
        String sql = "select studentGrades.studid,courses.coursename,studentGrades.grade " +
                "from studentGrades inner join courses on courses.id = studentGrades.courseid "
                +  " and studentGrades.studid = ?";

        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){
            StudentGrades std = new StudentGrades();
            std.setStudentId(rs.getInt("studid"));
            std.setCourseName(rs.getString("coursename"));
            std.setGrade(rs.getFloat("grade"));
            listRes.add(std);
        }

        return listRes;
    }

    @Override
    public float getAvgCourse(String course) throws SQLException {

        float avgGrade = 0;

        Connection con = ds.getConnection();
        String sql = "select avg(grade) as avgGrade from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1,course);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            avgGrade = rs.getInt("avgGrade");
        }

        return avgGrade;

    }

    @Override
    public float getMaxCourse(String course) {
        return 0;
    }

    @Override
    public float getMinCourse(String course) {
        return 0;
    }

    /*@Override
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
    }*/

}


