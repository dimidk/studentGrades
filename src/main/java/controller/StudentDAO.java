package controller;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    public Student isValidUser(String name) throws SQLException;
    public List<StudentCourses> getStudCourses(int id) throws SQLException;
    public List<StudentGrades> getGrades(int id) throws SQLException;
    public float getAvgCourse(String course) throws SQLException;
    public float getMaxCourse(String course);
    public float getMinCourse(String course);

}