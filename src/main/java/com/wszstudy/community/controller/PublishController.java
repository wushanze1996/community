package com.wszstudy.community.controller;

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

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        return "publish";


    }
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            @RequestParam(value = "id")Integer id,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title==null || title=="")
        {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null || description=="")
        {
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if (tag==null || tag =="")
        {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
            if (user==null){
                model.addAttribute("error","用户未登录");
                return "publish";

            }

        Question question = new Question();
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);
        question.setCreator(user.getId());
//        question.setGmt_create(System.currentTimeMillis());
//        question.setGmt_modified(question.getGmt_create());
        question.setId(id);
        questionService.createOrUpdate(question);
        //questionMapper.create(question);
        return "redirect:/";
    }
}

