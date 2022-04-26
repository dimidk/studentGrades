package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DataServlet", urlPatterns = "/data-servlet")
public class DataServlet extends HttpServlet {

    private StudentDAO std;

    public DataServlet(){}

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");

        String course = request.getParameter("course");

        std = ConfigDatabase.getStudentDAO();

        float max =0;
        float min = 0;
        float avg = 0;
        try {
            max = std.getMaxCourse(course);
            min = std.getMinCourse(course);
            avg = std.getAvgCourse(course);

            request.setAttribute("course",course);
            request.setAttribute("max",max);
            request.setAttribute("min",min);
            request.setAttribute("avg",avg);

        } catch (SQLException e) {
            e.printStackTrace();
        }




        RequestDispatcher view = request.getRequestDispatcher("allGrades.jsp");
        view.forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }
}
