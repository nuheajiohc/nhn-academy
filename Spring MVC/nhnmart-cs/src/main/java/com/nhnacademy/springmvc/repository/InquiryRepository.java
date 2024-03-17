package com.nhnacademy.springmvc.repository;


import com.nhnacademy.springmvc.domain.inquiry.Category;
import com.nhnacademy.springmvc.domain.inquiry.Inquiry;
import java.util.Collection;
import java.util.List;

public interface InquiryRepository {
    boolean exists(long id);

    Inquiry register(String title, String content, Category category, String customerId);

    Inquiry getInquiry(long id);

    Collection<Inquiry> findAll();

    List<Inquiry> findAllByCategory(Category category);

    List<Inquiry> findAllByCustomerId(String customerId);

    List<Inquiry> findAllByNoAnswer();
}
