package com.example.demo;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	@Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
          .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
          .url("jdbc:sqlserver://10.194.50.39:1433;databaseName=xyz_old_dtsdb_old;encrypt=true;trustServerCertificate=true")
          .username("sa")
          .password("Rain$2023")
          .build();
          }	
    
	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);	}

}
