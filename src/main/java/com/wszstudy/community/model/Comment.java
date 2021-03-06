package com.wszstudy.community.model;

import lombok.Data;

@Data
public class Comment {
    private long id;
    private long parent_id;
    private Integer type;
    private Integer commentator;
    private long gmt_create;
    private long gmt_modified;
    private long like_count;
    private String content;

}
