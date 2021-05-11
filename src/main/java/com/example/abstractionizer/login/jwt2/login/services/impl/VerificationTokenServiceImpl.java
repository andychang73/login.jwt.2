package com.example.abstractionizer.login.jwt2.login.services.impl;

import com.example.abstractionizer.login.jwt2.db.rmdb.entities.VerificationToken;
import com.example.abstractionizer.login.jwt2.db.rmdb.mappers.VerificationTokenMapper;
import com.example.abstractionizer.login.jwt2.enums.ErrorCode;
import com.example.abstractionizer.login.jwt2.exceptions.CustomException;
import com.example.abstractionizer.login.jwt2.login.services.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenMapper verificationTokenMapper;

    @Override
    public boolean isUsernameExists(String username) {
        return verificationTokenMapper.countByUsernameAndExpiration(username) > 0;
    }

    @Override
    public void createVerificationToken(VerificationToken verificationToken) {
        if(verificationTokenMapper.insert(verificationToken) != 1){
            throw new CustomException(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public VerificationToken getVerificationToken(String uuid) {
        return Optional.ofNullable(verificationTokenMapper.selectByIdAndExpiration(uuid)).orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_VALIDATION_EXPIRED));
    }

    @Override
    public void deleteExpiredToken() {
        verificationTokenMapper.deleteByExpiration();
    }
}
