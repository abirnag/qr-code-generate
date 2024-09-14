package com.abirnag.qrcode_generator.annotations;

import com.abirnag.qrcode_generator.handlers.JwtHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticationProcessor {

    private final JwtHandler jwtHandler;

    @Before("@annotation(com.abirnag.qrcode_generator.annotations.Authenticated)")
    public void authenticated(JoinPoint joinPoint) throws Throwable {
        Object[] methodArguments = joinPoint.getArgs();
        Optional<Object> bearerToken = Arrays.stream(methodArguments).filter(arg -> arg instanceof String && ((String) arg).startsWith("Bearer")).findAny();
       if(bearerToken.isEmpty()) {
           log.error("Bearer token not found");
           throw new RuntimeException();
       }
       String[] parts = bearerToken.get().toString().split("\\s+");
       if(parts.length != 2) {
           log.error("Invalid bearer token");
           throw new RuntimeException();
       }
       Map<String, Object> userMap = jwtHandler.verifyToken(parts[1]);
       if(CollectionUtils.isEmpty(userMap)) {
           log.error("Token verification failed");
           throw new RuntimeException();
       }
       if(!userMap.containsKey("loggedIn") || !Boolean.TRUE.equals(userMap.get("loggedIn"))) {
           log.error("Not logged in");
           throw new RuntimeException("Invalid bearer token");
       }
    }
}
