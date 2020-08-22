package com.tcc.backend.service;

import com.tcc.backend.model.MailModel;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendingEmailService {

    void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException;
}

