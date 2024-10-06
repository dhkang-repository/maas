package com.xmass.cloud.util;

import com.xmass.cloud.infrastructure.vo.SecretKeyInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ExternalAPIUtil {
    private final SecretKeyInfo secretKeyInfo;
    private final WebClient webClient;

    // Query hash 생성 메서드
    public String generateHash(String queryString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes(StandardCharsets.UTF_8));
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }
    }

    // JWT 생성 메서드
    public String generateJwtToken(String queryHash) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    secretKeyInfo.getSecretKey().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            return Jwts.builder()
                    .claim("access_key", secretKeyInfo.getAccessKey())
                    .claim("nonce", UUID.randomUUID().toString())
                    .claim("query_hash", queryHash)
                    .claim("query_hash_alg", "SHA512")
                    .signWith(SignatureAlgorithm.HS256, secretKeySpec.getEncoded())
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    // Query string 생성 메서드
    public String generateQueryString(Map<String, String> params, Map<String, List<String>> map) {
        List<String> queryElements = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryElements.add(entry.getKey() + "=" + entry.getValue());
        }
        if(Objects.nonNull(map) && !map.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                List<String> valueList = entry.getValue();
                for(String value : valueList) {
                    queryElements.add(key+"[]=" + value);
                }
            }
        }
        return String.join("&", queryElements.toArray(new String[0]));
    }


    public Mono get(String path,
                    HashMap<String, String> params,
                    HashMap<String, List<String>> map,
                    ParameterizedTypeReference retunType) {
        // Query string 생성
        String queryString = generateQueryString(params, map);

        // Query hash 생성
        String queryHash = generateHash(queryString);

        // JWT 생성
        String jwtToken = generateJwtToken(queryHash);

        String authenticationToken = "Bearer " + jwtToken;

        // WebClient를 통한 비동기 요청
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .query(queryString)
                        .build())
                .header("Content-Type", "application/json")
                .header("Authorization", authenticationToken)
                .retrieve()
                .bodyToMono(retunType);
    }

}
