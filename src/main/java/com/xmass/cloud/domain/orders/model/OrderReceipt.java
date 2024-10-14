package com.xmass.cloud.domain.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class OrderReceipt {
    @JsonProperty("uuid")
    private String uuid; // 주문의 고유 아이디 String
    @JsonProperty("side")
    private String side; // 주문 종류	String
    @JsonProperty("rd_type")
    private String rdType; // 주문 방식 String
    @JsonProperty("price")
    private String price; // 주문 당시 화폐 가격 NumberString
    @JsonProperty("state")
    private String state; // 주문 상태 String
    @JsonProperty("market")
    private String market; // 마켓의 유일키 String
    @JsonProperty("created_at")
    private String createdAt; // 주문 생성 시간 String
    @JsonProperty("volume")
    private String volume; // 사용자가 입력한 주문 양 NumberString
    @JsonProperty("remaining_volume")
    private String remainingVolume; //	체결 후 남은 주문 양 NumberString
    @JsonProperty("reserved_fee")
    private String reservedFee; //	수수료로 예약된 비용 NumberString
    @JsonProperty("remaining_fee")
    private String remainingFee; // 남은 수수료 NumberString
    @JsonProperty("paid_fee")
    private String paidFee; // 사용된 수수료 NumberString
    @JsonProperty("locked")
    private String locked; // 거래에 사용중인 비용 NumberString
    @JsonProperty("executed_volume")
    private String executedVolume; // 체결된 양 NumberString
    @JsonProperty("trades_count")
    private Integer tradesCount; // 해당 주문에 걸린 체결 수 Integer
    @JsonProperty("time_in_force")
    private String timeInForce; // IOC, FOK 설정 String
}
