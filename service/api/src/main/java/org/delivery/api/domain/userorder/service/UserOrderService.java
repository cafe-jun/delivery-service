package org.delivery.api.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithOutStatusWithThrow(
            Long id,
            Long userId
    ) {
        return userOrderRepository.findAllByIdAndUserId(id,userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public UserOrderEntity getUserOrderWithThrow(
            Long id,
            Long userId
    ) {
        return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED,userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<UserOrderEntity> getUserOrderList(
            Long userId
    ) {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrderEntity> getUserOrderList(
            Long userId,
            List<UserOrderStatus> statuses
    ) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statuses);
    }

    public List<UserOrderEntity> current(Long userId) {
        return getUserOrderList(userId,
                List.of(
                        UserOrderStatus.REGISTERED,
                        UserOrderStatus.ORDER,
                        UserOrderStatus.ACCEPT,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY
                )
            );
    }
    public List<UserOrderEntity> history(Long userId) {
        return getUserOrderList(userId,
                List.of(
                        UserOrderStatus.RECEIVE
                )
        );
    }
    // 주문
    public UserOrderEntity order(
            UserOrderEntity userOrderEntity
    ) {
        return Optional.ofNullable(userOrderEntity)
                .map(it -> {
                    userOrderEntity.setStatus(UserOrderStatus.ORDER);
                    userOrderEntity.setOrderedAt(LocalDateTime.now());
                    return userOrderRepository.save(userOrderEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 상태 변경
    public UserOrderEntity setState(UserOrderEntity userOrderEntity,UserOrderStatus status) {
        userOrderEntity.setStatus(status);
        return userOrderRepository.save(userOrderEntity);
    }

    public UserOrderEntity accept(UserOrderEntity userOrderEntity) {
        userOrderEntity.setAcceptedAt(LocalDateTime.now());
        return setState(userOrderEntity,UserOrderStatus.ACCEPT);
    }
    public UserOrderEntity cooking(UserOrderEntity userOrderEntity) {
        userOrderEntity.setCookingStartedAt(LocalDateTime.now());
        return setState(userOrderEntity,UserOrderStatus.COOKING);
    }
    public UserOrderEntity delivery(UserOrderEntity userOrderEntity) {
        userOrderEntity.setDeliveryStartedAt(LocalDateTime.now());
        return setState(userOrderEntity,UserOrderStatus.DELIVERY);
    }
    public UserOrderEntity received(UserOrderEntity userOrderEntity) {
        userOrderEntity.setReceivedAt(LocalDateTime.now());
        return setState(userOrderEntity,UserOrderStatus.RECEIVE);
    }

}
