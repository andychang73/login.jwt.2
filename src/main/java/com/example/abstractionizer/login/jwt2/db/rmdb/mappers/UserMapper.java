package com.example.abstractionizer.login.jwt2.db.rmdb.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abstractionizer.login.jwt2.db.rmdb.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    int countByUsername(@Param("username") String username);

    Optional<User> selectByUsername(@Param("username") String username);

    int updateLastLoginTime(@Param("id") Integer userId, @Param("time") Date lastLoginTime);
}
