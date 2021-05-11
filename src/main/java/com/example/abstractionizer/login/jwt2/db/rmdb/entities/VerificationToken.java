package com.example.abstractionizer.login.jwt2.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("verification_token")
public class VerificationToken {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Date expiration;

    private Date createTime;

    private Date updateTime;
}
