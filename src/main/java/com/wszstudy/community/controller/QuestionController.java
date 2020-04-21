package com.wszstudy.community.controller;

import com.wszstudy.community.dto.CommentCreateDTO;
import com.wszstudy.community.dto.CommentDTO;
import com.wszstudy.community.dto.QuestionDTO;
import com.wszstudy.community.mapper.CommentMapper;
import com.wszstudy.community.mapper.UserMapper;
import com.wszstudy.community.model.Comment;
import com.wszstudy.community.model.User;
import com.wszstudy.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @GetMapping("/question/{id}")

    public String question(@PathVariable(name = "id") int id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<Comment> comments = commentMapper.selectByQuestionId(id);
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment :comments){
            List<User> user = userMapper.selectById(questionDTO.getCreator());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);

            commentDTOList.add(commentDTO);
        }






        questionService.inView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("commentsAll",commentDTOList);

        return "question";
    }

}
