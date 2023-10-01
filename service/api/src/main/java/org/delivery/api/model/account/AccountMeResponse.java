package org.delivery.api.model.account;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountMeResponse {
    private String email;
    private String name;
    private LocalDateTime registeredAt;
}
