package com.abirnag.qrcode_generator.documents;


import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document("users")
@Data
@NoArgsConstructor
public class User {
    @MongoId
    private String id;
    @Nonnull
    private String username;
    private String name;
    private String email;
    @Nonnull
    private String password;
    private boolean isVerifiedEmail=Boolean.FALSE;
    private String roles;
    private LocalDateTime joinedOn;
    private LocalDateTime updatedOn;
}
