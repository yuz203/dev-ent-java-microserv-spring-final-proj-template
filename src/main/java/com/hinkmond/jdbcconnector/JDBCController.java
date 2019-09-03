package com.hinkmond.jdbcconnector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JDBCController {
    private final static String KEYFILEPATH = "./keyFile.key";

    @CrossOrigin
    @RequestMapping(value="/helloworld", method= RequestMethod.GET)
    public String printCryptTest() {
        AESUtils aesUtils = new AESUtils();

        String encryptedStr = aesUtils.encrypt("Hello World!", KEYFILEPATH);
        return("Decrypt = " + aesUtils.decrypt(encryptedStr, KEYFILEPATH));
    }
}
