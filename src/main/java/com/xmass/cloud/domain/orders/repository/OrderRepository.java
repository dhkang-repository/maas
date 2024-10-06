package com.xmass.cloud.domain.orders.repository;

import com.xmass.cloud.domain.account.model.Account;
import com.xmass.cloud.domain.orders.model.OrderChance;
import com.xmass.cloud.infrastructure.vo.SecretKeyInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final WebClient webClient;
    private final SecretKeyInfo secretKeyInfo;

    public Mono<OrderChance> getChance(String market) {
        // 1. Query parameters 설정
        Map<String, String> params = new HashMap<>();
        params.put("market", "KRW-" + market);

        // 2. Query string 생성
        String queryString = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((param1, param2) -> param1 + "&" + param2)
                .orElse("");

        // 3. Query hash 생성
        String queryHash = generateHash(queryString);

        // 4. JWT 생성
        String jwtToken = generateJwtToken(queryHash);

        // 5. Authorization 토큰 설정
        String authenticationToken = "Bearer " + jwtToken;

        // 6. WebClient를 통한 비동기 요청
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/orders/chance")
                        .queryParam("market", "KRW-" + market)
                        .build())
                .header("Content-Type", "application/json")
                .header("Authorization", authenticationToken)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<OrderChance>() {});
    }

    // Query hash 생성 메서드
    private String generateHash(String queryString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes(StandardCharsets.UTF_8));
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }
    }

    // JWT 생성 메서드
    private String generateJwtToken(String queryHash) {
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
}
