package com.abirnag.qrcode_generator.handlers;

import com.abirnag.qrcode_generator.documents.User;
import com.abirnag.qrcode_generator.dto.JwtRequest;
import com.abirnag.qrcode_generator.dto.JwtTokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.micrometer.common.util.StringUtils.isBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtHandler {
    private final ReactiveMongoTemplate mongoTemplate;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Mono<JwtTokenResponse> generateToken(JwtRequest request){
        if(isBlank(request.getPassword()) || isBlank(request.getUsername())){
            log.error("Username or password is blank");
            JwtTokenResponse errorResponse = new JwtTokenResponse();
            errorResponse.setError("Username or password is blank");
            return Mono.just(errorResponse);
        }
        String password = request.getPassword();
        Query query = new Query()
                .addCriteria(Criteria.where("username").is(request.getUsername()))
                .addCriteria(Criteria.where("password").is(password));
        JwtBuilder jwtBuilder = Jwts.builder();
        return mongoTemplate.findOne(query, User.class).flatMap(u->{
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("name",u.getName());
            userMap.put("email",u.getEmail());
            Key key = new SecretKeySpec(jwtSecret.getBytes(),"HmacSHA256");
            String token = jwtBuilder
                    .subject(u.getUsername())
                    .issuedAt(new Date())
                    .claims(userMap)
                    .signWith(key).compact();
            JwtTokenResponse jwt =  new JwtTokenResponse();
            jwt.setToken(token);
            return Mono.justOrEmpty(jwt);
        }).switchIfEmpty(Mono.just(JwtTokenResponse.builder().error("User not found").build()));
    }
    public Map<String,Object> verifyToken(String token){
        try {
            SecretKey key = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
            Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            Claims payload = claimsJws.getPayload();
            Map<String, Object> userMap = new HashMap<>(payload);
            userMap.put("loggedIn",true);
            return userMap;
        } catch (Exception e){
            log.error("Exceptions : ",e);
            return Map.of("loggedIn", false);
        }
    }

}
