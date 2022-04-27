package controller;

//import model.*;
//import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "LoginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {

    private StudentDAO std;

    private String message;
    public LoginServlet() throws IOException {
        super();
    }

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String studentId ="";
        HttpSession session  = request.getSession();


        std = ConfigDatabase.getStudentDAO();

        studentId = request.getParameter("studentId");

        System.out.println("checking for " + studentId);

        String name;
        String message;

        Student stud = null;
        try {
            stud = std.isValidUser(studentId);
            if (stud.getId() == -1) {
                request.setAttribute("name","User no exists");
                RequestDispatcher view = request.getRequestDispatcher("errorLogin.jsp");
                view.forward(request,response);
            }
            else {
                request.setAttribute("name", stud.getFullName());
                List<StudentCourses> studCourses = std.getStudCourses(stud.getId());
                List<StudentGrades> studGrades = std.getGrades(stud.getId());


                request.setAttribute("name",stud.getFullName());
                request.setAttribute("studCourses",studCourses);
                request.setAttribute("studGrades",studGrades);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher view = request.getRequestDispatcher("studCourses.jsp");
        view.forward(request,response);

    }

    public void destroy() {
    }
}