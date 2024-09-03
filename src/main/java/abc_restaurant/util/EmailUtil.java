package abc_restaurant.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	

    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        final String fromEmail = "abcrestaurant6@gmail.com"; 
        final String password = "Abc@#123";   


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587");            
        props.put("mail.smtp.auth", "true");          
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.debug", "true");         

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
      
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);
            message.setText(body);

   
            System.out.println("Sending email to: " + toEmail);
            System.out.println("Email subject: " + subject);
            System.out.println("Email body: " + body);
            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Failed to send email to: " + toEmail);
            e.printStackTrace();
            throw e;
        }
    }

}
