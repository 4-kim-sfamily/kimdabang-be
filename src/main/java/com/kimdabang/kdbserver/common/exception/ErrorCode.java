package com.kimdabang.kdbserver.common.exception;
import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 번대 에러
    TEST_ERROR_CODE(400, "ERROR-001", "올바르지 않은 입력값입니다."),
    DUPLICATED_LOGIN_ID(409, "USER-001", "중복된 로그인 아이디입니다."),
    USER_NOT_FOUND(400, "USER-002", "해당 회원을 찾을 수 없습니다."),
    BAD_CREDENTIALS(401, "USER-003", "아이디 또는 비밀번호가 일치하지 않습니다."),
    BAD_SOCIAL_CREDENTIALS(401, "USER-004", "소셜 로그인 정보가 없습니다."),
    BAD_USER_REQUEST(402, "USER-005", "잘못된 유저 접근입니다."),

    DATA_NOT_FOUND(400, "DATA-001", "데이터를 찾을 수 없습니다."),

    ADDREESS_IS_DEFAULT(400, "ADDRESS-001", "기본 주소는 삭제, 해제할 수 없습니다."),

    COUPON_NOT_FOUND(400, "COUPON-001", "쿠폰을 찾을 수 없습니다."),
    COUPON_NOT_ENROLL(401, "COUPON-002", "쿠폰이 등록되지 않았습니다."),
    COUPON_ALREADY_ENROLLED(402, "COUPON-003", "이미 발행받은 쿠폰입니다."),

    MOBILEGIFTICON_NOT_FOUND(400, "MOBILEGIFTICON-001", "모바일 상품권을 찾을 수 없습니다."),
    MOBILEGIFTICON_NOT_ENROLL(401, "MOBILEGIFTICON-002", "모바일 상품권이 등록되지 않았습니다."),
    MOBILEGIFTICON_ALREADY_ENROLL(402, "MOBILEGIFTICON-003", "이미 등록된 모바일 상품권입니다."),

    FAVORITE_NOT_FOUND(400, "FAVORITE-001", "좋아요를 찾을 수 없습니다."),

    CHECKBOX_NOT_FOUND(400, "CHECKBOX-001", "체크박스를 찾을 수 없습니다."),

    OPTIONDETAIL_NOT_FOUND(400, "OPTIONDETAIL-001", "해당 옵션 세부정보를 찾을 수 없습니다."),
    PARENTSOPTION_NOT_FOUND(400, "PARENTSOPTION-001", "부모 옵션을 찾을 수 없습니다."),

    PRODUCT_NOT_FOUND(400, "PRODUCT-001", "상품을 찾을 수 없습니다."),
    PRODUCTMEDIA_NOT_FOUND(400, "PRODUCTMEDIA-001", "상품미디어를 찾을 수 없습니다."),
    PRODUCTDETAIL_NOT_FOUND(400, "PRODUCTDETAIL-001", "상품 상세 정보를 찾을 수 없습니다."),
    PRODUCTDETAIL_ALREADY_INIT(401, "PRODUCTDETAIL-002", "상품 상세 정보는 중복 등록이 불가능합니다."),

    SEASON_NOT_FOUND(400, "SEASON-001", "시즌을 찾을 수 없습니다."),
    SEASONMEDIA_NOT_FOUND(400, "SEASONMEDIA-001", "시즌미디어를 찾을 수 없습니다."),
    SEASONPRODUCT_NOT_FOUND(400, "SEASONPRODUCT-001", "시즌상품을 찾을 수 없습니다."),

    PURCHASE_NOT_FOUND(400, "PURCHASE-001", "주문을 찾을 수 없습니다."),
    PURCHASE_PROCESS_FAILED(401, "PURCHASE-002", "주문을 처리하지 못했습니다."),

    PAYMENT_NOT_FOUND(400, "PAYMENT-001", "결제정보를 찾을 수 없습니다."),

    STARBUCKSCARD_NOT_FOUND(400, "STARBUCKSCARD-001", "스타벅스카드를 찾을 수 없습니다."),
    STARBUCKSCARD_NOT_ENROLL(401, "STARBUCKSCARD-002", "스타벅스카드가 등록되지 않았습니다."),
    STARBUCKSCARD_ALREADY_ENROLLED(402, "STARBUCKSCARD-003", "이미 등록된 스타벅스카드입니다."),
    STARBUCKSCARD_NOT_ENOUGH_BALANCE(403,"STARBUCKSCARD-004", "스타벅스카드의 잔액이 부족합니다."),

    REVIWE_NOT_FOUND(400, "REVIWE-001", "리뷰를 찾을 수 없습니다."),
    REVIWE_PROCESS_FAILED(401, "REVIWE-002", "리뷰를 등록하지 못했습니다."),

    MEDIA_UPLODE_FAILED(401, "MEDIA-002", "파일을 업로드하지 못했습니다."),
    MEDIA_PROCESS_FAILED(401, "MEDIA-003", "파일 처리에 실패했습니다."),

    INTERNAL_SERVER_ERROR(500, "COMMON-002", "서버에서 요청을 처리하지 못했습니다.");


    private final int status;
    private final String code;
    private final String Description;

    ErrorCode(int status, String code, String Description) {
        this.status = status;
        this.code = code;
        this.Description = Description;
    }
}