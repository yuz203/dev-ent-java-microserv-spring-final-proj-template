package com.hinkmond.jdbcconnector;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

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
        String queryTmp = "SHOW TABLES;";
        List<String> sqlResult = this.jdbcTemplate.queryForList(queryTmp, String.class);
        System.out.println(">>>>>>> sqlResult: " + sqlResult);
        try {
            if (jdbcTemplate.getDataSource() != null) {
                System.out.println(">>>>>>> jdbcTemplate login timeout: " + jdbcTemplate.getDataSource()
                        .getLoginTimeout());
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
