package com.example.emailverification.appuser;

import com.example.emailverification.registration.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class Convert {
   public static AppUser registrationRequest(RegistrationRequest from){
      return AppUser
              .builder().firstName(from.getFirstName())
              .lastName(from.getLastName())
              .email(from.getEmail())
              .password(from.getPassword()).build();


   }


}
