package com.example.abstractionizer.login.jwt2.schedulers;

import com.example.abstractionizer.login.jwt2.login.services.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@Component
public class VerificationTokenScheduler {

    private final VerificationTokenService verificationTokenService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void clearExpiredToken(){
        log.info("Time to delete expired token: {}", new Date());
        verificationTokenService.deleteExpiredToken();
    }
}
