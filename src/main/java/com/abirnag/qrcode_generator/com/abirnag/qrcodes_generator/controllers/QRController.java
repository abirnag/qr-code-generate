package com.abirnag.qrcode_generator.com.abirnag.qrcodes_generator.controllers;

import com.abirnag.qrcode_generator.dto.QRRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class QRController {
    @GetMapping("/hello")
    public Mono<String> getQRCode(){
        return Mono.just("Hello World");
    }

}
