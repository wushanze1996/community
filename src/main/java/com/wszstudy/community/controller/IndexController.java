package com.wszstudy.community.controller;

import com.wszstudy.community.dto.PaginationDTO;
import com.wszstudy.community.dto.QuestionDTO;
import com.wszstudy.community.entity.Question;
import com.wszstudy.community.entity.User;
import com.wszstudy.community.mapper.QuestionMapper;
import com.wszstudy.community.mapper.UserMapper;
import com.wszstudy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")

    public String Index(HttpServletRequest request, Model model, @RequestParam(name = "page",defaultValue = "1") Integer page,@RequestParam(name = "size",defaultValue = "5")Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String cookieValue = cookie.getValue();
                    User user = userMapper.findByToken(cookieValue);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }

        }else {
            return "index";
        }
        PaginationDTO pagination =questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return "index";


    }
}
