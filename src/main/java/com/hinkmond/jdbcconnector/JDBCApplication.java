package com.hinkmond.jdbcconnector;

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
public class JDBCApplication {
    private static final String KEYFILE = "keyFile.key";
    private JdbcTemplate jdbcTemplate;

    private String jdbcQueryForObject(JdbcTemplate jdbcTemplate, String query) {
        return (jdbcTemplate.queryForObject("SHOW TABLES", String.class));
    }

    /*
     * Solves problem with latest spring boot (with jdbc starter and Hikari)
     * To avoid this error:
     *      java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.
     */
    // --- BEGIN: SpringBootApplication Bean
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
    // --- END: SpringBootApplication Bean

    // Use this in during Application run()
    @Bean
    public JDBCApplication schedulerRunner(JdbcTemplate jdbcTemplate) {
        JDBCApplication jdbcApplication = new JDBCApplication();
        jdbcApplication.jdbcTemplate = jdbcTemplate;
        String sqlResult = this.jdbcQueryForObject(jdbcTemplate, "SHOW TABLES");
        System.out.println(">>>>>>> sqlResult: " + sqlResult);
        System.out.println(">>>>>>> jdbcTemplate: " + jdbcApplication.jdbcTemplate.toString());
        return new JDBCApplication();
    }

    // Main method
    public static void main(String... args) {
        SpringApplication.run(JDBCApplication.class, args);
    }
}
