package com.nhnacademy.springmvc.controller.customer;

import com.nhnacademy.springmvc.domain.inquiry.Category;
import com.nhnacademy.springmvc.domain.inquiry.Inquiry;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cs/customer")
public class InquiryController {
    private final InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @ModelAttribute("categories")
    public Category[] getCategory(){
        return Category.values();
    }

    @GetMapping(value ="list")
    public String viewInquiries(HttpServletRequest request, Model model, @RequestParam(value = "category",required = false) Category category) {
        HttpSession session = request.getSession();
        String customerId = (String) session.getAttribute("sessionId");

        List<Inquiry> inquiries = inquiryRepository.findAllByCustomerId(customerId);
        if(category!=null){
            inquiries = inquiryRepository.findAllByCategory(category);
        }

        if (inquiries!=null){
            model.addAttribute("inquiries",inquiries);
        }

        return "customer/inquiries";
    }

    @GetMapping("detail/{inquiryId}")
    public String viewInquiryDetail(@PathVariable long inquiryId, Model model){
        if(!inquiryRepository.exists(inquiryId)){
            throw new InquiryNotFoundException();
        }

        Inquiry inquiry = inquiryRepository.getInquiry(inquiryId);
        model.addAttribute("inquiry",inquiry);
        return "customer/inquiryDetail";
    }
}
