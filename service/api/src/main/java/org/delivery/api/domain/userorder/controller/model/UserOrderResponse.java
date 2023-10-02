package org.delivery.api.domain.userorder.controller.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderResponse {

    private Long id;

    private Long userId;

    private UserOrderStatus status;

    private BigDecimal amount;

    private LocalDateTime orderedAt; // 주문시간

    private LocalDateTime acceptedAt; // 주문 받는 시간

    private LocalDateTime cookingStartedAt; // 요리 시간

    private LocalDateTime deliveryStartedAt; // 배달 시작 시간

    private LocalDateTime receivedAt; // 음식 받는 시간
}
