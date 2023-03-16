package com.example.emailverification.forgotpassword;
import com.example.emailverification.appuser.AppUser;
import com.example.emailverification.appuser.AppUserRepository;
import com.example.emailverification.email.EmailSender;
import com.example.emailverification.exception.PasswordNotEqualsException;
import com.example.emailverification.exception.UserNotFoundException;
import com.example.emailverification.forgotpassword.token.ResetToken;
import com.example.emailverification.forgotpassword.token.ResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    private final AppUserRepository  appUserRepository;
    private final EmailSender emailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ResetTokenService resetTokenService;
    public String  changePassword(ResetRequest request, String email) {

        String password = request.getPassword();
        String repeatPassword = request.getConfirmpassword();

        if(password == null || !password.equals(repeatPassword)) {
          throw new PasswordNotEqualsException();
        }
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException());
        String encodedPassword = bCryptPasswordEncoder
                .encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        appUserRepository.save(user);
        String token = UUID.randomUUID().toString();

        ResetToken resetToken = new ResetToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15)
        );
        String link = "http://localhost:8000/api/v1/registration/change"+email;


        emailSender.sendWithoutAttachment(
                request.getEmail(),
                buildEmail(link));
        return "changed";
        }





    private String buildEmail( String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +


                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi,</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +

                "</div></div>";
    }





}







