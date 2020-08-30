package com.interview.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Many;

import com.interview.project.model.Group;
import com.interview.project.model.User;

@Mapper
public interface GroupMapper {
	@Insert("insert into ugroups(name) values(#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertGroup(Group group);

	@Select("select id, name from ugroups")
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name")
    })
	List<Group> findAllGroups();
	
	@Select("select id, name from ugroups WHERE id=#{id}")
	Group findGroupById(Integer id);
	
	@Select("select id, name from ugroups where id = #{id}")
    @Results(value = {
          @Result(property="id", column="id"),
          @Result(property="name", column="name"),
          @Result(property="users", javaType=List.class, column="id",
                             many=@Many(select="findUsersByGroup"))})
	Group findById(int id); 

	@Select("select * from users where group = #{groupId}")
	@Results(value = {
			@Result(property = "firstName", column = "first_name"),
	        @Result(property = "lastName", column = "last_name")})
	List<User> findUsersByGroup(String namePerson);
	
	@Delete("delete from ugroups where id=#{id}")
	void deleteGroup(int id);
	
}
