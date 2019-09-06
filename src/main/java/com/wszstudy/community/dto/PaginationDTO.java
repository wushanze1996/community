package com.wszstudy.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showNext;
    private boolean showPrevious;

    private int page;//当前页
    private List<Integer> pages = new ArrayList<>();
    private int totalpage = 0;

    public void setPagination(int totalpage, Integer page) {


        this.totalpage=totalpage;
        this.page=page;
        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i <= totalpage){
                pages.add(page+i);
            }
        }
        //显示前一页
        if (page==1){
            showPrevious=false;
        }
        else {
            showPrevious=true;
        }
        //显示后一页
        if (page==totalpage)
        {
            showNext=false;
        }
        else {
            showNext=true;
        }
        //显示第一页
        if (pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //显示最后页
        if (pages.contains(totalpage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
    }
}
