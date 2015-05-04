/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Felipe
 */
import DAO.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{
      // Assuming you are sending email from localhost
      final static String host = "localhost";
      static Connection currentCon = null;
      static ResultSet rs = null;  

    private static String USER_NAME = "felipegi91";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "*%&%"; // GMail password
       
      
      /*public static void main(String args[]){
          SendEmail send = new SendEmail();
          send.sendHTMLemail("felipegi91@gmail.com", "felipegi91@gmail.com", "Ciencia", 
                  "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRUbenm7YNyQfZ9crz24bWhsjJwg_n28k4H46iDJh2igiyD9xp-2g", "Super email sender" );
      }*/

    public void getSubscribersAndSend(String author, String channel, String url, String text){
        System.out.println("get and send email");
        //connect to DB 
        //preparing some objects for connection 
         Statement stmt = null;
         currentCon = ConnectionManager.getConnection();
         String from = "";
         String channelId = "";
         
         try{
         //Get author mail
         String searchQuery = "select email from t_users where username='" + author +"'";
         stmt=currentCon.createStatement();
         ResultSet rs = stmt.executeQuery(searchQuery);
         
        if(rs.next()){
            from = rs.getString("email");
        }
        System.out.println("get and author email "+from);
        //Get channel id
         String searchQuery2 = "select id from t_canales where nombre='" + channel +"'";
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery2);
        if(rs.next()){
            channelId = rs.getString("id");
        }
        System.out.println("get comment channelID "+channelId);
                 String RECIPIENT = "";
         String fromSite = USER_NAME;
         String pass = PASSWORD;
         String subject = "WebAdds new comment on "+channel+"!";
         //String body = "Welcome to JavaMail!";
         String body = "<h1>New content!</h1><span>On <b>"+channel+"<b></span><p>"+text+"</p><img style='width: 300px, height: auto'src='"+url+"'/>";
        if(from.length() != 0 && channelId.length() != 0){
         //Get subscriber with email option and send
        searchQuery ="select t_users.email from t_suscriptores INNER JOIN t_users  "
                + "ON t_users.id_usuario = t_suscriptores.idsuscriptor"
                + " AND t_suscriptores.idcanal = '"+channelId+"' AND t_suscriptores.mailme='1'";
        System.out.println("Query of subscribers "+searchQuery);
        rs = stmt.executeQuery(searchQuery);
        while(rs.next())
              {
                  RECIPIENT = rs.getString("email");   
                  System.out.println("Enviando Email... "+RECIPIENT);
                  String[] to = { RECIPIENT }; // list of recipient email addresses
                  sendFromGMail(fromSite, pass, to, subject, body);
                  //sendHTMLemail(to,  from, channel, url, text );
              }
        }}catch(Exception e){System.err.println("Error en envio de mail "+e);}
        
        
    }

   
   private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            // Send the actual HTML message, as big as you like
            message.setContent(body, "text/html; charset=utf-8");
            //message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
   }
       /*public void sendMail(String channel,String from, String to, String text){
      
      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);
      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("New content! on "+channel);

         // Now set the actual message
         message.setText(text);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
      
   public void sendHTMLemail(String to, String from, String channel, String url, String text )
   {
      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>New content!</h1><span>On <b>"+channel+"<b></span>"
                 + "<p>"+text+"</p><img style='width: 300px, height: auto'src='"+url+"'/>",
                            "text/html" );

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }*/
}
