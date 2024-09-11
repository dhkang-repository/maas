package com.xmass.cloud.infrastructure.vo;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class SecretKey {

    private final String secretKey;

    public SecretKey() {
        try (FileInputStream ipt = new FileInputStream("src/main/resources/secret")) {
            secretKey = new String(ipt.readAllBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
