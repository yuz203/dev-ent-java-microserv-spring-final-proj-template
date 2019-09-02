package com.hinkmond.jdbcconnector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class JDBCController {
    @CrossOrigin
    @RequestMapping(value="/helloworld", method= RequestMethod.GET)
    public String printCryptTest() {
        AesServiceUtils aesServiceUtils = new AesServiceUtils();

        String encryptedStr = aesServiceUtils.encrypt("Hello World!", new File("keyFile.key"));
        File keyFile = new File("keyFile.key");
        return("Decrypt = " + aesServiceUtils.decrypt(encryptedStr, keyFile));
    }
}
