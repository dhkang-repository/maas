package com.xmass.cloud.infrastructure.util;

import com.xmass.cloud.domain.global.SecretKeyInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    private String generateHash(String queryString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes(StandardCharsets.UTF_8));
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }
    }

    private String generateJwtToken(String queryHash) {
        return Jwts.builder()
                .claim("access_key", secretKeyInfo.getAccessKey())
                .claim("nonce", UUID.randomUUID().toString())
                .claim("query_hash", queryHash)
                .claim("query_hash_alg", "SHA512")
                .signWith(SignatureAlgorithm.HS256, secretKeyInfo.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    private String generateQueryString(Map<String, String> params, Map<String, List<String>> map) {
        List<String> queryElements = new ArrayList<>();
        params.forEach((key, value) -> queryElements.add(key + "=" + value));
        if (map != null) {
            map.forEach((key, values) -> values.forEach(value -> queryElements.add(key + "[]=" + value)));
        }
        return String.join("&", queryElements);
    }

    public <T> Mono<T> post(String path, Map<String, String> params, Map<String, List<String>> map, Class<T> responseType) {
        String queryString = generateQueryString(params, map);
        String queryHash = generateHash(queryString);
        String jwtToken = generateJwtToken(queryHash);

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(path).query(queryString).build())
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .bodyValue(params)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> get(String path, Map<String, String> params, Map<String, List<String>> map, ParameterizedTypeReference<T> responseType) {
        String queryString = generateQueryString(params, map);
        String queryHash = generateHash(queryString);
        String jwtToken = generateJwtToken(queryHash);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).query(queryString).build())
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .retrieve()
                .bodyToMono(responseType);
    }
}
