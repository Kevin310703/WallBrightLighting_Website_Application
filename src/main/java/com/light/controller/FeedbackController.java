package com.light.controller;

import com.light.dto.FeedbackDTO;
import com.light.model.Feedback;
import com.light.model.User;
import com.light.repositories.FeedbackRepository;
import com.light.request.FeedbackRequest;
import com.light.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CommonService commonService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(required = false) Map<String, String> message) {
        User currentUser = commonService.getCurrentUser();
        model.addAttribute("message", message);
        model.addAttribute("user", currentUser);
        return "feedback";
    }

    @GetMapping("/all")
    public String all(Model model) {
        User currentUser = commonService.getCurrentUser();
        if (currentUser == null || currentUser.getRole().getId() != 1){
            return "redirect:/auth/login";
        }
        List<FeedbackDTO> feedbacks = feedbackRepository.findAll()
                .stream()
                .map(this::toFeedbackDTO)
                .collect(Collectors.toList());
        model.addAttribute("feedbacks", feedbacks);
        return "allfeedback";
    }

    @PostMapping("/send")
    public String send(FeedbackRequest request) {
        Map<String, String> errorList = getStringStringMap(request);
        if (!errorList.isEmpty()) {
            return "redirect:/feedback/index?" + buildQueryString(errorList);
        }
        Feedback newFeedback = new Feedback();
        newFeedback.setName(request.getName());
        newFeedback.setEmail(request.getEmail());
        newFeedback.setComment(request.getComment());
        newFeedback.setRate(Short.parseShort(request.getRate()));
        newFeedback.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        feedbackRepository.save(newFeedback);
        Map<String, String> success = new HashMap<>();
        success.put("Success", "Your message has been sent. Thank you!");
        return "redirect:/feedback/index?" + buildQueryString(success);
    }

    private static Map<String, String> getStringStringMap(FeedbackRequest request) {
        Map<String, String> errorList = new HashMap<>();
        if (request.getName() == null) {
            errorList.put("NullName", "* Name cannot be empty");
        }
        if (request.getEmail() == null) {
            errorList.put("NullEmail", "* Email cannot be empty");
        }
        if (request.getRate() == null) {
            errorList.put("NullRate", "* Rate cannot be empty");
        }
        if (request.getComment() == null) {
            errorList.put("NullComment", "* Comment cannot be empty");
        }
        return errorList;
    }

    private String buildQueryString(Map<String, String> params) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return queryString.toString();
    }

    private FeedbackDTO toFeedbackDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getId(), feedback.getName(), feedback.getComment(), feedback.getEmail(), feedback.getRate());
    }
}
