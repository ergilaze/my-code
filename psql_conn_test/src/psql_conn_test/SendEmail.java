package psql_conn_test;

import javax.mail.internet.MimeMessage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.net.ssl.internal.ssl.*;


public class SendEmail {

    public SendEmail(String to, String token){
        try{
            String host ="smtp.gmail.com" ;
            String user = "designstudiopro01@gmail.com";
            String pass = "123ergi1";
            String from = "designstudiopro01@gmail.com";
            String subject = "Reset your password";
            String messageText = "Your code is " + token + ". This code will expire in 10 minutes. ";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
}
