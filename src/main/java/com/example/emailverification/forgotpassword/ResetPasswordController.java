package com.example.emailverification.forgotpassword;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reset")
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PutMapping("/change/{email}")
    public String changePassword(@RequestBody ResetRequest request, @PathVariable String email){
        return resetPasswordService.changePassword(request,email);

    }



}
