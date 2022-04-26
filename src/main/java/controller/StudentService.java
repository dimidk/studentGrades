package controller;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentDAO {

    private DataSource ds;

    public StudentService(DataSource dataSource) throws IOException {
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
            avgGrade = rs.getFloat("avgGrade");
        }

        return avgGrade;

    }

    @Override
    public float getMaxCourse(String course) throws SQLException {

        float maxGrade = 0;

        Connection con = ds.getConnection();
        String sql = "select max(grade) as maxGrade from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1,course);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            maxGrade = rs.getFloat("maxGrade");
        }

        return maxGrade;
    }

    @Override
    public float getMinCourse(String course) throws SQLException {

        float minGrade = 0;

        Connection con = ds.getConnection();
        String sql = "select min(grade) as minGrade from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1,course);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            minGrade = rs.getFloat("minGrade");
        }

        return minGrade;

    }
}


