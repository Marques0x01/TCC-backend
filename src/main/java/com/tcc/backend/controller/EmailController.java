package com.tcc.backend.controller;

import com.tcc.backend.model.Email;
import com.tcc.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

    @Autowired
    private EmailService sendingEmailService;

    @PostMapping
    public ResponseEntity<?> restPostLoanRequest(@RequestBody Email mailModel) {
        try {
            sendingEmailService.sendConfirmationEmail(mailModel);
            return ResponseEntity.ok().body(mailModel.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
