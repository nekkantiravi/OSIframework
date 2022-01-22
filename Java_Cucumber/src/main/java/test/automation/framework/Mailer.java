package test.automation.framework;

import java.time.LocalDateTime;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

class Mailer {

    public static void sendReport(String recipients) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("@gmail.com", "");
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            String[] recipientsA = recipients.split(",");
            InternetAddress[] internetAddress = new InternetAddress[recipientsA.length];
            for (int i = 0; i < recipientsA.length; i++) {
                internetAddress[i] = new InternetAddress(recipientsA[i]);
            }
            message.addRecipients(Message.RecipientType.TO, internetAddress);
            message.setSubject("Test Report - " + LocalDateTime.now());

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached test report.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = Config.getReportsDir() + "report.html";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Report.html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}