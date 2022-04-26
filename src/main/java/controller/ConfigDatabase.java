package controller;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;


//@Configuration
public class ConfigDatabase {

 //   @Bean
    public static DataSource getDataSource() {

        //DriverManagerDataSource dataSource = new DriverManagerDataSource();
       // DataSource dataSource = null;
        MysqlDataSource dataSource = new MysqlDataSource();
      //  dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/GradesDB");
        //dataSource.setUsername("admin");
        dataSource.setUser("admin");
        dataSource.setPassword("admin");

        return dataSource;
    }

    public static StudentDAO getStudentDAO() throws IOException {

        return new StudentService(getDataSource());
    }
}
