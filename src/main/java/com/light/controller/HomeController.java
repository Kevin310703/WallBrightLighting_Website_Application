package com.light.controller;

import com.light.dto.CategoryDTO;
import com.light.dto.FeedbackDTO;
import com.light.model.Category;
import com.light.model.Feedback;
import com.light.model.Product;
import com.light.model.User;
import com.light.repositories.CategoryRepository;
import com.light.repositories.FeedbackRepository;
import com.light.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommonService commonService;

    @GetMapping("/")
    public String index(Model model) {
        User currentUser = commonService.getCurrentUser();
        List<FeedbackDTO> feedbacks = feedbackRepository.findAll()
                .stream()
                .filter(f -> f.getRate() == 3)
                .limit(3)
                .map(this::toFeedbackDTO)
                .collect(Collectors.toList());
        List<CategoryDTO> categories = categoryRepository.findAll()
                .stream()
                .filter(c -> c.getIsActive() == 1)
                .map(this::toCategoriesDTO)
                .collect(Collectors.toList());


        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("categories", categories);
        model.addAttribute("user", currentUser);

        return "index";
    }

    private CategoryDTO toCategoriesDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getCategoryName(), category.getDescription(),
                category.getProducts()
                        .stream()
                        .map(Product::getImage)
                        .findFirst()
                        .orElse(null));
    }

    private FeedbackDTO toFeedbackDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getName(), feedback.getComment());
    }
}
