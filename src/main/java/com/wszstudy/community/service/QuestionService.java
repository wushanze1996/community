package com.wszstudy.community.service;

import com.wszstudy.community.dto.PaginationDTO;
import com.wszstudy.community.dto.QuestionDTO;
import com.wszstudy.community.entity.Question;
import com.wszstudy.community.entity.User;
import com.wszstudy.community.mapper.QuestionMapper;
import com.wszstudy.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalpage;
        int totalCount = questionMapper.count();
        if(totalCount%size==0){
            totalpage=totalCount/size;
        }else {
            totalpage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalpage){
            page=totalpage;
        }
        paginationDTO.setPagination(totalpage,page);
        Integer offset = (page-1)*5;
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;

    }

    public PaginationDTO list(int userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalpage;
        int totalCount = questionMapper.countByUserId(userId);
        if(totalCount%size==0){
            totalpage=totalCount/size;
        }else {
            totalpage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalpage){
            page=totalpage;
        }
        paginationDTO.setPagination(totalpage,page);
        Integer offset = (page-1)*5;
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }

    public QuestionDTO getById(int id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }


    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);

        }else {
            question.setGmt_modified(question.getGmt_create());

            questionMapper.update(question);
        }
    }
}
