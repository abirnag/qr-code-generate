package com.abirnag.qrcode_generator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocketRequest {
    private String heading;
    private String body;
    private Boolean qrRequired;
}
