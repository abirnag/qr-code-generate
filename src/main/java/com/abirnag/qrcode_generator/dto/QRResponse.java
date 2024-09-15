package com.abirnag.qrcode_generator.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class QRResponse {
    private String data;
    private String error;
}
