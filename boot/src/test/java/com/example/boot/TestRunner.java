package com.example.boot;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestRunner {

    @Karate.Test
    Karate runTests() {
        return Karate.run("classpath:features").relativeTo(getClass());
    }
}
