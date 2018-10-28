package sender;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
import javax.mail.*;
import javax.swing.JOptionPane;
import java.io.*;



public class Mailer {

        private String output;
        private String password;
        public void Password (String newProperty)
        {
            password = newProperty;
        }
        private String input;
        private String theme;
        private String message;
        private String domain;
        private Properties p;

        public Mailer(final String output,final String domain, final String password, final String input, final String theme, final String message) throws IOException {
            this.output = output;
            this.password = password;
            this.input = input;
            this.theme = theme;
            this.message = message;
            this.domain=domain;


            p = new Properties();
            p.put("mail.smtp.host", "smtp."+ domain.substring(1));
            p.put("mail.smtp.starttls.enable", "true");

            p.put("mail.smtp.user",output);
            p.put("mail.smtp.password", password);
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");





        }

        public void SendMail() throws Exception {

            try {
                Session s = Session.getDefaultInstance(p,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(output+domain, password); //через кого
                            }
                        });

                Message msg = new MimeMessage(s);
                msg.setFrom(new InternetAddress(output+domain)); //от кого

                Address toMail = new InternetAddress(input);//кому
                msg.setRecipient(Message.RecipientType.TO, toMail);
                msg.setSubject(theme);
                msg.setText(message);
                Transport.send(msg);

            }
            catch (Exception e)
            {
                throw new Exception("Проверьте правильность введенных данных");
            }




        }






}