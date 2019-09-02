package com.hinkmond.jdbcconnector;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCConnector {
    private JdbcTemplate jdbcTemplate;

    private String jdbcQueryForObject(JdbcTemplate jdbcTemplate, String query) {
        if (query == null) {
            return "NULL query";
        }
        return (jdbcTemplate.queryForObject(query, String.class));
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        String queryTmp = "SHOW TABLES";
        String sqlResult = this.jdbcQueryForObject(jdbcTemplate, queryTmp);
        System.out.println(">>>>>>> sqlResult: " + sqlResult);
        System.out.println(">>>>>>> jdbcTemplate: " + jdbcTemplate.toString());

        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }
}
