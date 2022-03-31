package com.test001.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy'年'MM'月'dd'日' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

    }

}
