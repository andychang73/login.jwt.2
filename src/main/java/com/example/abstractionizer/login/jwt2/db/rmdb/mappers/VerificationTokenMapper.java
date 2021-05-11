package com.example.abstractionizer.login.jwt2.db.rmdb.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abstractionizer.login.jwt2.db.rmdb.entities.VerificationToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VerificationTokenMapper extends BaseMapper<VerificationToken> {

    int countByUsernameAndExpiration(@Param("username") String username);

    VerificationToken selectByIdAndExpiration(@Param("uuid") String uuid);

    void deleteByExpiration();
}
