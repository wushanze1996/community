package com.wszstudy.community.mapper;

import com.wszstudy.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);
    @Select("select count(1) from question")
    int count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") int userId,@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    int countByUserId(@Param("userId") int userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") int id);

    @Update("update question set title = #{title},description=#{description},gmt_modified=#{gmt_modified},tag=#{tag} where id = #{id}")
    void update(Question question);


    @Update("update question set title = #{title},description=#{description},gmt_modified=#{gmt_modified},tag=#{tag},view_count =#{view_count} where id = #{id}")
    void updateAndViewCount(Question question);
}
