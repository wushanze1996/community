package com.wszstudy.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private int view_count;
    private int like_count;
    private int creator;
    private String tag;




}
