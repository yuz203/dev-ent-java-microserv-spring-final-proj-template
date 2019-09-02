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
        String decryptedPassword = AesServiceUtils.decrypt(encryptedPassword, new File(KEYFILE));
        //System.out.println("decryptedPassword = " + decryptedPassword);
        return getDatasourceProperties().initializeDataSourceBuilder()
                .password(decryptedPassword)
                .build();
    }
    // --- END: SpringBootApplication Bean

    // Use this in during Application run()
    @Bean
    public JDBCApplication schedulerRunner(JdbcTemplate jdbcTemplate) {
        JDBCConnector jdbcConnector = new JDBCConnector();
        jdbcConnector.setJdbcTemplate(jdbcTemplate);

        return new JDBCApplication();
    }

    // Main method
    public static void main(String... args) {
        SpringApplication.run(JDBCApplication.class, args);
    }
}
