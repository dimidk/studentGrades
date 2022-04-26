package controller;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;

public class ConfigDatabase {

    public static DataSource getDataSource() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/GradesDB");
        dataSource.setUser("admin");
        dataSource.setPassword("admin");

        return dataSource;
    }

    public static StudentDAO getStudentDAO() throws IOException {

        return new StudentService(getDataSource());
    }
}
