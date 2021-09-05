package com.ohmynone.gateway.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class KeyGeneratorConfig {

//    @Value("${jwt.auth-url}")
//    private String authUrl;
//
//    @Bean
//    public PublicKey generatePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        RestTemplate restTemplate = new RestTemplate();
//        AuthRealm response = restTemplate.getForObject(authUrl, AuthRealm.class);
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        X509EncodedKeySpec pubKeySpecX509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(response.publicKey));
//        return kf.generatePublic(pubKeySpecX509EncodedKeySpec);
//    }
//
//    private static class AuthRealm implements Serializable {
//        @JsonProperty(value = "public_key")
//        public String publicKey;
//    }

}
