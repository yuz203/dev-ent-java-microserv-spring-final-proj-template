package com.hinkmond.finalproj;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class JDBCConnector {
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        JDBCConnector.jdbcTemplate = jdbcTemplate;
        String queryTmp = "SHOW TABLES;";
        List<String> sqlResult = JDBCConnector.jdbcTemplate.queryForList(queryTmp, String.class);
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
