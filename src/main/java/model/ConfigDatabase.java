package model;

import controller.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ConfigDatabase {

    @Bean
    public static DataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/GradesDB");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        return dataSource;
    }

    public static StudentDAO getStudentDAO() throws IOException {

        return new StudentService(getDataSource());
    }
}
