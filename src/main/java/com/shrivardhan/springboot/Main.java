package com.shrivardhan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private final JdbcTemplate jdbcTemplate;
    private final Environment environment;


    public Main(JdbcTemplate jdbcTemplate, Environment environment) {
        this.jdbcTemplate = jdbcTemplate;
        this.environment = environment;
        String sql = "SELECT * FROM TAB";

        System.out.println("URL = " + environment.getProperty("spring.datasource.url"));
        System.out.println("Username = " + environment.getProperty("spring.datasource.username"));
        System.out.println("Password = " + environment.getProperty("spring.datasource.password"));

        try {  
            jdbcTemplate.execute(sql);
        } catch (Exception exception) {
            System.out.println("Connection error");
        }
    }

}
