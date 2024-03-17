package com.nhnacademy.springmvc.controller.manager;

import com.nhnacademy.springmvc.domain.inquiry.Category;
import com.nhnacademy.springmvc.domain.inquiry.Inquiry;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cs/manager")
public class AnswerController {

    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    public AnswerController(InquiryRepository inquiryRepository, AnswerRepository answerRepository) {
        this.inquiryRepository = inquiryRepository;
        this.answerRepository = answerRepository;
    }

    @ModelAttribute("categories")
    public Category[] getCategory(){
        return Category.values();
    }

    @GetMapping(value ="list")
    public String viewInquiries( Model model) {
        List<Inquiry> inquiries = inquiryRepository.findAllByNoAnswer();

        if (inquiries!=null){
            model.addAttribute("inquiries",inquiries);
        }

        return "customer/inquiries";
    }

}
