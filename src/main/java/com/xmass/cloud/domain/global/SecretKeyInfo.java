package com.xmass.cloud.domain.global;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Getter
@Component
public class SecretKeyInfo {

    /**
     * @see String serverUrl: upbit server api url
     * @see String secretKey: upbit secret key
     * @see String accessKey: upbit access key
     */
    private final String serverUrl;
    private final String secretKey;
    private final String accessKey;

    public SecretKeyInfo(@Value("${api.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        try (FileInputStream secretStream = new FileInputStream("src/main/resources/secretkey");
             FileInputStream accessStream = new FileInputStream("src/main/resources/accesskey")) {
            secretKey = new String(secretStream.readAllBytes());
            accessKey = new String(accessStream.readAllBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
