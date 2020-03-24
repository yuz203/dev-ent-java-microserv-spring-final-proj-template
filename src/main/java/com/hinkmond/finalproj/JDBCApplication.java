package com.hinkmond.finalproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class JDBCApplication extends SpringBootServletInitializer {
    private static final String KEYFILEPATH = "./keyFile.key";

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
    public DataSource getDatasource(AESUtils aesUtils) {
        String encryptedPassword = getDatasourceProperties().getPassword().replace("ENCRYPTED(", "")
                .replace(")", "");
        String decryptedPassword = aesUtils.decrypt(encryptedPassword, KEYFILEPATH);
        return getDatasourceProperties().initializeDataSourceBuilder()
                .password(decryptedPassword)
                .build();
    }
    // --- END: SpringBootApplication Bean

    // Initialize and call the run method (if class implements CommandLineRunner) during Spring Boot startup
    @Bean
    public JDBCConnector schedulerRunner(JdbcTemplate jdbcTemplate) {
        JDBCConnector jdbcConnector = new JDBCConnector();
        jdbcConnector.setJdbcTemplate(jdbcTemplate);
        return jdbcConnector;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JDBCApplication.class);
    }

    // Main method
    public static void main(String... args) {
        SpringApplication.run(JDBCApplication.class, args);
    }
}
