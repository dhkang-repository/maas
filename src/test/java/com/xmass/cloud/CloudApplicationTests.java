package com.xmass.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {
                "spring.profiles.active=local",
        },
        classes = CloudApplication.class

)
public class CloudApplicationTests {
    @Test
    void contextLoads() {
    }
}
