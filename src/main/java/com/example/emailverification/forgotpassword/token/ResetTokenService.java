package com.example.emailverification.forgotpassword.token;

import com.example.emailverification.forgotpassword.token.ResetToken;
import com.example.emailverification.forgotpassword.token.ResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResetTokenService {
    private final ResetTokenRepository resetTokenRepository;
    public void saveResetToken(ResetToken token) {
        resetTokenRepository.save(token);
    }

    public Optional<ResetToken> getToken(String token) {
        return resetTokenRepository.findByToken(token);
    }
}
