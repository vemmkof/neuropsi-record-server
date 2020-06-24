package com.ipn.escom.neuropsi.record.server.service.support;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Map;

@Component
@AllArgsConstructor
public class MailSupport {

    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfiguration;

    public static final String NEW_USER = "Registro de nuevo usuario";
    public static final String NEW_USER_TEMPLATE = "new-user.ftl";
    public static final String NEW_MODULE_TEMPLATE = "new-module.ftl";

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSupport.class);

    public void sendMail(String to, String subject, String templateName, Map<String, Object> parameters) throws IOException, TemplateException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(getMimeBodyPart(getHtml(templateName, parameters)));
        mimeMessage.setContent(multipart);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(subject);
        LOGGER.info("Sending mail to {}", to);
        javaMailSender.send(mimeMessage);
    }

    private MimeBodyPart getMimeBodyPart(String html) throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(html, "text/html");
        return bodyPart;
    }

    private String getHtml(String templateName, Map<String, Object> parameters) throws IOException, TemplateException {
        Template template = freemarkerConfiguration.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, parameters);
    }
}
