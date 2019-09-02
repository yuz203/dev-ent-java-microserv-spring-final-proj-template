package com.hinkmond.jdbcconnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;

@SpringBootApplication
public class JDBCApplication implements CommandLineRunner {
    private static final String KEYFILE = "keyFile.key";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String jdbcQueryForObject(String query) {
        return (jdbcTemplate.queryForObject("SHOW TABLES", String.class));
    }

    /*
     * Solves problem with latest spring boot (with jdbc starter and Hikari)
     * To avoid this error:
     *      java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties getDatasourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource getDatasource() {
        String encryptedPassword = getDatasourceProperties().getPassword().replace("ENCRYPTED(", "")
                .replace(")", "");
        String decryptedPassword = Aesservice.decrypt(encryptedPassword, new File(KEYFILE));
        //System.out.println("decryptedPassword = " + decryptedPassword);
        return getDatasourceProperties().initializeDataSourceBuilder()
                .password(decryptedPassword)
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
        String sqlResult = this.jdbcQueryForObject("SHOW TABLES");
        System.out.println("sqlResult: " + sqlResult);
    }

    // Main method
    public static void main(String[] args) {
        SpringApplication.run(JDBCApplication.class, args);
    }
}
