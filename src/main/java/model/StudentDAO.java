package model;

import java.util.HashMap;
import java.util.List;

public interface StudentDAO {

    public Student isValidUser(String name);
    public List<StudentCourses> getStudCourses(int id);
    public List<StudentGrades> getGrades(int id);
    public float getAvgCourse(String course);
    public float getMaxCourse(String course);
    public float getMinCourse(String course);

}
