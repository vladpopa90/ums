package com.interview.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.interview.project.model.User;

@Mapper
public interface UserMapper {
	@Insert("insert into users(first_name, last_name, email, password) values(#{firstName},#{lastName},#{email}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertUser(User user);

	@Select("select id, first_name, last_name, email, password from users WHERE id=#{id}")
	@Results({
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
	User findUserById(Integer id);
	
	@Select("select id, first_name, last_name, email, password from users WHERE email=#{email}")
	@Results({
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
	User findUserByEmail(String email);
	
	@Select("select id, first_name, last_name, email, password from users WHERE token=#{token}")
	@Results({
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
	User findUserByToken(String token);

	@Select("select id, first_name, last_name, email, password from users")
	@Results({
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
	List<User> findAllUsers();
	
	@Select("select id, first_name, last_name, email, password from users WHERE email=#{email} AND password=#{password}")
	@Results({
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastName", column = "last_name")
    })
	User login(String email, String password);
	
	@Update("UPDATE users SET token=#{token} WHERE id=#{id}")
	void updateToken(String token, int id);
	
	@Update("UPDATE users SET token=#{token} WHERE id=#{id}")
	void updateGroup(int groupid, int id);
	
	@Delete("delete from users where id=#{id}")
	void deleteUser(int id);
}
