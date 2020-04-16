package com.wszstudy.community.controller;

import com.wszstudy.community.dto.QuestionDTO;
import com.wszstudy.community.mapper.QuestionMapper;
import com.wszstudy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")

    public String question(@PathVariable(name = "id") int id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        questionService.inView(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }

}
