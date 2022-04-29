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

   public Users isValidUser(String name,String passwd) throws SQLException {

        System.out.println("i am in isValideUser");

        Users std = new Users();
        Connection con = ds.getConnection();
        String sql = "select * from users where username = ? and password = ?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,passwd);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next() ) {
            std.setUsername("-1");
            std.setPassword("-1");
        }
        else {
            std.setUsername(rs.getString("username"));
            std.setPassword(rs.getString("password"));
            std.setEnabled(rs.getBoolean("enabled"));
        }

       return std;
    }

    @Override
    public Student getAuthenticatedUser(String username) throws SQLException {

        Student std = new Student();

        Connection con = ds.getConnection();
        String sql = "select * from student where username = ?";
//        System.out.println(username);
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1,username);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){
            std.setFullName(rs.getString("fullname"));
            std.setId(rs.getInt("id"));
            std.setCardID(rs.getString("cardid"));
            std.setSemester(rs.getInt("semester"));
            std.setUsername(rs.getString("username"));
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

    public float getMedianCourse(String course) throws SQLException {

        float medianGrade;

        Connection con = ds.getConnection();

        float max = getMaxCourse(course);
        float min = getMinCourse(course);
        medianGrade = (max + min) / 2;


        return medianGrade;

    }
}


