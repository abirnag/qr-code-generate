package com.abirnag.qrcode_generator.com.abirnag.qrcodes_generator.controllers;

import com.abirnag.qrcode_generator.dto.QRResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qr")
public class QRGeneratorController {

}
