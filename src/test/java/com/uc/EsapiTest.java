package com.uc;

import org.junit.Test;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;

public class EsapiTest {

    @Test
    public void testXss() {
        String content = "<script type=\"text/javascript\" src=\"demo_time.js\"></script>";
        String a = ESAPI.encoder().encodeForHTML(content);
        System.out.println(a);


        String sql = "select * from b_region where id = ";
        String param = "1 or 1=1";
        MySQLCodec mySQLCodec = new MySQLCodec(MySQLCodec.Mode.STANDARD);
        String esql = ESAPI.encoder().encodeForSQL(mySQLCodec,param);
        System.out.println(sql + esql);

    }
}
