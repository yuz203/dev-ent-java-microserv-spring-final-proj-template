package com.hinkmond.finalproj;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;


@RestController
public class JDBCController {
    private final static String KEYFILEPATH = "./keyFile.key";

    @CrossOrigin
    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String printCryptTest() {
        AESUtils aesUtils = new AESUtils();

        String encryptedStr = aesUtils.encrypt("Hello World!", KEYFILEPATH);
        return ("Decrypt = " + aesUtils.decrypt(encryptedStr, KEYFILEPATH));
    }

    @CrossOrigin
    @RequestMapping(value = "/printAllCustomers", method = RequestMethod.GET)
    public String printAllCustomers() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from customer;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("user_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("addr")).append(", ")
                    .append(sqlRowSet.getString("phone")).append(", ")
                    .append(sqlRowSet.getString("email")).append(", ")
                    .append(sqlRowSet.getString("created_at"))
                    .append("\n");
        }
        return ("SELECT * from customer:\n" + resultStr);
    }

    @CrossOrigin
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@RequestBody AddCustomerData addCustomerData) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "INSERT INTO customer (first_name, last_name, addr, email) " +
                "VALUES (" +
                "'" + addCustomerData.getFirstName() + "'," +
                "'" + addCustomerData.getLastName() + "'," +
                "'" + addCustomerData.getAddress() + "'," +
                "'" + addCustomerData.getEmail() + "'" +
                ");";
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return ("Rows updated: " + rowsUpdated);
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    public String deleteCustomer(@RequestBody DeleteCustomerData deleteCustomerData) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "DELETE FROM customer WHERE first_name = " +
                "'" + deleteCustomerData.getFirstName() + "' " +
                "AND last_name = " +
                "'" + deleteCustomerData.getLastName() + "';";
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return ("Rows updated: " + rowsUpdated);
    }


}
