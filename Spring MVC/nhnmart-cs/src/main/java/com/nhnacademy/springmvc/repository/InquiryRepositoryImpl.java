package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.inquiry.Category;
import com.nhnacademy.springmvc.domain.inquiry.Inquiry;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    private final Map<Long, Inquiry> inquiryMap = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return this.inquiryMap.containsKey(id);
    }

    @Override
    public Inquiry register(String title, String content, Category category, String customerId) {
        long id = inquiryMap.isEmpty() ? 1 : Collections.max(inquiryMap.keySet()) + 1;

        Inquiry inquiry = new Inquiry(id, title, category, content, customerId);
        inquiryMap.put(id, inquiry);

        return inquiry;
    }

    @Override
    public Inquiry getInquiry(long id) {
        return exists(id) ? inquiryMap.get(id) : null;
    }

    @Override
    public Collection<Inquiry> findAll() {
        return inquiryMap.values();
    }

    @Override
    public List<Inquiry> findAllByCategory(Category category) {
        return findAll().stream().filter(inquiry -> inquiry.getCategory().equals(category))
                .sorted(Comparator.comparing(Inquiry::getRegisterTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Inquiry> findAllByCustomerId(String customerId) {
        return findAll().stream().filter(inquiry -> Objects.equals(inquiry.getCustomerId(), customerId))
                .sorted(Comparator.comparing(Inquiry::getRegisterTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Inquiry> findAllByNoAnswer() {
        return findAll().stream().filter(inquiry -> inquiry.getAnswer() != null).collect(Collectors.toList());
    }
}
