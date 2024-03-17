package com.nhnacademy.springmvc.domain.inquiry;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class InquiryRegisterRequest {
    @Size(min=2, max=200)
    private final String title;
    private final Category category;
    @Size(max=40000)
    private final String content;

    @NotNull
    private final String customerId;
    private List<String> files;
}
