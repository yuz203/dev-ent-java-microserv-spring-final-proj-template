package com.hinkmond.jdbcconnector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class JDBCController {
    @CrossOrigin
    @RequestMapping(value="/helloworld", method= RequestMethod.GET)
    public static String printCryptTest() throws GeneralSecurityException, IOException {
        AesServiceUtils aesserviceUtils = new AesServiceUtils();

        String encryptedStr = aesserviceUtils.encrypt("Hello World!", new File("keyFile.key"));
        File keyFile = new File("keyFile.key");
        return("Decrypt = " + aesserviceUtils.decrypt(encryptedStr, keyFile));
    }
}
