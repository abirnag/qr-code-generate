package com.abirnag.qrcode_generator.com.abirnag.qrcodes_generator.controllers;

import com.abirnag.qrcode_generator.dto.JwtRequest;
import com.abirnag.qrcode_generator.dto.JwtTokenResponse;
import com.abirnag.qrcode_generator.handlers.JwtHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class JwtController {
    private final JwtHandler jwtHandler;

    @PostMapping()
    public Mono<JwtTokenResponse> generateToken(@RequestBody JwtRequest request) {
       return jwtHandler.generateToken(request);
    }
}
