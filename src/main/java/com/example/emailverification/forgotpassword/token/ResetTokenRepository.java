package com.example.emailverification.forgotpassword.token;

import com.example.emailverification.forgotpassword.token.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken,Long> {

    Optional<ResetToken> findByToken(String token);


}
