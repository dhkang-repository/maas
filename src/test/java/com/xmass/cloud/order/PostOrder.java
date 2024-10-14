//package com.xmass.cloud.order;
//
//
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//    String accessKey = System.getenv("UPBIT_OPEN_API_ACCESS_KEY");
//    String secretKey = System.getenv("UPBIT_OPEN_API_SECRET_KEY");
//    String serverUrl = System.getenv("UPBIT_OPEN_API_SERVER_URL");
//
//    HashMap<String, String> params = new HashMap<>();
//    params.put("market", "KRW-BTC");
//    params.put("side", "bid");
//    params.put("volume", "0.01");
//    params.put("price", "100");
//    params.put("ord_type", "limit");
//
//    ArrayList<String> queryElements = new ArrayList<>();
//    for(Map.Entry<String, String> entity : params.entrySet()) {
//        queryElements.add(entity.getKey() + "=" + entity.getValue());
//    }
//
//    String queryString = String.join("&", queryElements.toArray(new String[0]));
//
//    MessageDigest md = MessageDigest.getInstance("SHA-512");
//    md.update(queryString.getBytes("UTF-8"));
//
//    String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
//
//    Algorithm algorithm = Algorithm.HMAC256(secretKey);
//    String jwtToken = JWT.create()
//            .withClaim("access_key", accessKey)
//            .withClaim("nonce", UUID.randomUUID().toString())
//            .withClaim("query_hash", queryHash)
//            .withClaim("query_hash_alg", "SHA512")
//            .sign(algorithm);
//
//    String authenticationToken = "Bearer " + jwtToken;
//
//    try {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost request = new HttpPost(serverUrl + "/v1/orders");
//        request.setHeader("Content-Type", "application/json");
//        request.addHeader("Authorization", authenticationToken);
//        request.setEntity(new StringEntity(new Gson().toJson(params)));
//
//        HttpResponse response = client.execute(request);
//        HttpEntity entity = response.getEntity();
//
//        System.out.println(EntityUtils.toString(entity, "UTF-8"));
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}
