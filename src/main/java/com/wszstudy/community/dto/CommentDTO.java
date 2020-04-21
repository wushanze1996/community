package com.wszstudy.community.dto;

import com.wszstudy.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private long id;
    private long parent_id;
    private Integer type;
    private Integer commentator;
    private long gmt_create;
    private long gmt_modified;
    private long like_count;
    private String content;
    private User user;


}
