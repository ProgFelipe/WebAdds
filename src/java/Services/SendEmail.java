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
      static Connection currentCon = null;
      static ResultSet rs = null;  

      public static void main(String args[]){
          SendEmail send = new SendEmail();
          send.sendHTMLemail("felipegi91@gmail.com", "felipegi91@gmail.com", "Ciencia", 
                  "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRUbenm7YNyQfZ9crz24bWhsjJwg_n28k4H46iDJh2igiyD9xp-2g", "Super email sender" );
      }
    // Assuming you are sending email from localhost
      final static String host = "localhost:25";
    public void getSubscribersAndSend(String author, String channel, String url, String text){
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
        //Get channel id
         String searchQuery2 = "select id from t_canales where nombre='" + channel +"'";
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery2);
        if(rs.next()){
            channelId = rs.getString("id");
        }
        if(from.length() != 0 && channelId.length() != 0){
         //Get subscriber with email option and send
        searchQuery ="select t_users.email from t_suscriptores INNER JOIN t_users  "
                + "ON t_users.id_usuario = t_suscriptores.idsuscriptor"
                + " AND t_suscriptores.idcanal = '"+channelId+"' AND t_suscriptores.mailme='1'";
        rs = stmt.executeQuery(searchQuery);
        while(rs.next())
              {
                  String to =rs.getString("email");
                  sendHTMLemail(to,  from, channel, url, text );
              }
        }}catch(Exception e){System.err.println("Error en envio de mail "+e);}
        
        
    }
    public void sendMail(String channel,String from, String to, String text){
      
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
   }

}
