package com.nhnacademy.springmvc.controller.customer;

import com.nhnacademy.springmvc.domain.inquiry.Category;
import com.nhnacademy.springmvc.domain.inquiry.Inquiry;
import com.nhnacademy.springmvc.domain.inquiry.InquiryRegisterRequest;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cs/customer")
public class InquiryRegisterController {
    private final InquiryRepository inquiryRepository;

    public InquiryRegisterController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @ModelAttribute("categories")
    public Category[] getCategory(){
        return Category.values();
    }

    @GetMapping("register")
    public String viewInquiry(Model model) {
//        model.addAttribute("categories", Category.values());
        return "customer/inquiry";
    }

    @PostMapping("register")
    public String registerInquiry(@Valid @ModelAttribute InquiryRegisterRequest inquiryRegisterRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException();
        }

        this.inquiryRepository.register(inquiryRegisterRequest.getTitle(),
                inquiryRegisterRequest.getContent(), inquiryRegisterRequest.getCategory(),
                inquiryRegisterRequest.getCustomerId());
       return "redirect:/cs/customer/list";
    }
}
