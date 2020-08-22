package com.tcc.backend.controller;




import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.backend.model.MailModel;
import com.tcc.backend.service.AuthService;
import com.tcc.backend.service.SendingEmailService;

import freemarker.template.TemplateException;

@CrossOrigin(
        allowCredentials = "true",
        origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@RestController
@RequestMapping("/sendmail")
public class SendEmailController {

    @Autowired
    private SendingEmailService sendingEmailService;
    
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> restPostLoanRequest(@RequestBody MailModel mailModel) {




        try {
            sendingEmailService.sendEmail(mailModel);
            return ResponseEntity.ok().body(mailModel.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        } catch (TemplateException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        }


    }
}

