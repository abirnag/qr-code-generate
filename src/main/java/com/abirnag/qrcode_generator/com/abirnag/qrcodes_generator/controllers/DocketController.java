package com.abirnag.qrcode_generator.com.abirnag.qrcodes_generator.controllers;

import com.abirnag.qrcode_generator.annotations.Authenticated;
import com.abirnag.qrcode_generator.documents.Docket;
import com.abirnag.qrcode_generator.dto.DocketRequest;
import com.abirnag.qrcode_generator.handlers.DocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dockets")
@RequiredArgsConstructor
public class DocketController {
    private final DocketHandler docketHandler;

    @GetMapping("/{id}")
    public Mono<Docket> getDocket(@PathVariable String id) {
        return docketHandler.getDocket(id);
    }
    @PostMapping("")
    @Authenticated
    public Mono<Docket> createDocket(@RequestBody DocketRequest request,@RequestHeader("authorization") String token){
        return docketHandler.createDocket(request);
    }
}
