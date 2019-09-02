package com.hinkmond.jdbcconnector;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

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
        try {
            if (this.jdbcTemplate.getDataSource() != null) {
                System.out.println(">>>>>>> jdbcTemplate login timeout: " + this.jdbcTemplate.getDataSource()
                        .getLoginTimeout());
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
