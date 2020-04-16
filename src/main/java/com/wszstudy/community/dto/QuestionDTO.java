package com.wszstudy.community.dto;

import com.wszstudy.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private int view_count;
    private int like_count;
    private int creator;
    private String tag;
    private User user;
}
