package com.example.productprice.boot;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRunner {

    private static final String LOCALHOST = "http://localhost:";
    private static final String FEATURES_PATH = "classpath:features";

    @LocalServerPort
    private int port;

    @Karate.Test
    Karate runTests() {
        System.setProperty("karate.base.url", LOCALHOST + port);
        return Karate.run(FEATURES_PATH).relativeTo(getClass());
    }
}
