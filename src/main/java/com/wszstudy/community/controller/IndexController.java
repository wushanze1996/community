package com.wszstudy.community.controller;
import com.wszstudy.community.dto.PaginationDTO;
import com.wszstudy.community.mapper.UserMapper;
import com.wszstudy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")

    public String Index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){
        PaginationDTO pagination =questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return "index";


    }
}
