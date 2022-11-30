package Controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Email {

    private static Properties p = System.getProperties();


    public static void sendEmail(String from, String to, String body, String subject) throws MessagingException {
        p.put("mail.smtp.host", "localhost");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(p);
        MimeMessage mm = new MimeMessage(session);
        ///Questi tre Header non sono obbligatori, ma serveno per mostrare corettamente il messagio
        mm.addHeader("Content-type", "text/HTML; charset = UTF-8");
        mm.addHeader("format", "flowed");
        mm.addHeader("Content-Transfer-Encoding", "8bit");
        mm.setFrom(from);
        mm.setRecipients(Message.RecipientType.TO, to);
        mm.setSubject(subject);
        mm.setText(body);
        mm.setSentDate(new Date());
        mm.setReplyTo(InternetAddress.parse(from, false));
        Transport.send(mm);
    }

}
