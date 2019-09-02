package com.hinkmond.jdbcconnector;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCConnector {
    private JdbcTemplate jdbcTemplate;

    private String jdbcQueryForObject(String query) {
        if (query == null) {
            return "NULL query";
        }
        return (this.jdbcTemplate.queryForObject(query, String.class));
    }

    void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String queryTmp = "SHOW TABLES";
        String sqlResult = this.jdbcQueryForObject(queryTmp);
        System.out.println(">>>>>>> sqlResult: " + sqlResult);
        System.out.println(">>>>>>> jdbcTemplate: " + this.jdbcTemplate.toString());
    }
}
