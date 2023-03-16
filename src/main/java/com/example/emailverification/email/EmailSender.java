package com.example.emailverification.email;


public interface EmailSender {

    void sendWithoutAttachment(String to, String text);
}
