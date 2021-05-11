package com.example.abstractionizer.login.jwt2.login.services;

import com.example.abstractionizer.login.jwt2.db.rmdb.entities.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {

    boolean isUsernameExists(String username);

    void createVerificationToken(VerificationToken verificationToken);

    VerificationToken getVerificationToken(String uuid);

    void deleteExpiredToken();
}
