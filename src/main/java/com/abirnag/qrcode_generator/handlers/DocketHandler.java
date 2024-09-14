package com.abirnag.qrcode_generator.handlers;

import com.abirnag.qrcode_generator.documents.Docket;
import com.abirnag.qrcode_generator.dto.DocketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DocketHandler {

    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<Docket> createDocket(DocketRequest docketRequest) {
        Docket docket = new Docket();
        docket.setHeading(docketRequest.getHeading());
        docket.setBody(docketRequest.getBody());
        docket.setCreatedBy("Admin");
        return mongoTemplate.save(docket);
    }

    public Mono<Docket> getDocket(String id) {
        return mongoTemplate.findById(id,Docket.class);
    }
}
