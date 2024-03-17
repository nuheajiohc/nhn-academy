package com.nhnacademy.springmvc.domain.inquiry;

import com.nhnacademy.springmvc.domain.answer.Answer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Inquiry {

    private final long id;
    private final String title;
    private final Category category;
    private final String content;
    private LocalDateTime registerTime;
    private final String customerId;
    private List<String> files;
    @Setter
    private Answer answer;

    public Inquiry(long id, String title, Category category, String content, String customerId) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.customerId = customerId;

//        this.files = files;
        this.registerTime = LocalDateTime.now();
    }
}
