package controller;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    //public Student isValidUser(String name) throws SQLException;
    public Users isValidUser(String name,String passwd) throws SQLException;
    public Student getAuthenticatedUser(String username) throws SQLException;
    public List<StudentCourses> getStudCourses(int id) throws SQLException;
    public List<StudentGrades> getGrades(int id) throws SQLException;
    public float getAvgCourse(String course) throws SQLException;
    public float getMaxCourse(String course) throws SQLException;
    public float getMinCourse(String course) throws SQLException;
    public float getMedianCourse(String course) throws SQLException;

}
