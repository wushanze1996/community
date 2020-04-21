package com.wszstudy.community.mapper;

import com.wszstudy.community.dto.CommentDTO;
import com.wszstudy.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CommentMapper {

    @Insert("insert into comment (parent_id,type,commentator,gmt_create,gmt_modified,like_count,content) values (#{parent_id},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{like_count},#{content})")
    void insert(Comment comment);


    @Select("select * from comment where parent_id = #{id}")
    List<Comment> selectByQuestionId(int id);
}
