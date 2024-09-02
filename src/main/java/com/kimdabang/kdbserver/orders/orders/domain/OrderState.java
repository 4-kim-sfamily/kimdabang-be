package com.kimdabang.kdbserver.orders.orders.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState {

    State_Payment_completed("결제완료"),
    State_Preparing_Delivery("배송준비중"),
    State_In_Delivery("배송중"),
    State_Delivery_Completed("배송완료");

    private final String orderState;

    @JsonValue
    public String getOrderState() {
        return orderState;
    }

    @JsonCreator
    public static OrderState fromString(String value) {
        for (OrderState orderState : OrderState.values()) {
            if (orderState.orderState.equals(value)) {
                return orderState;
            }
        }
        throw new IllegalArgumentException("UnKnown value: " + value);
    }
}
