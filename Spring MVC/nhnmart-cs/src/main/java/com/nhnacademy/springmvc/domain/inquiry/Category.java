package com.nhnacademy.springmvc.domain.inquiry;

import lombok.Getter;

@Getter
public enum Category {
    COMPLAINT("complaint","불만 접수"),
    SUGGESTION("suggestion","제안"),
    REFUND_EXCHANGE("refund_exchange","환불/교환"),
    COMPLIMENT("compliment","칭찬해요"),
    ETC("etc","기타 문의");

    private String name;
    private String category;

    Category(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
