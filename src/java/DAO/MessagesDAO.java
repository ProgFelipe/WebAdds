/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.CanalBean;
import Bean.MessageBean;
import static DAO.CanalDAO.currentCon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author Felipe
 */
public class MessagesDAO {
    static Connection currentCon = null;
      static ResultSet rs = null; 
    public boolean setMessage(String channel, String Message, String autor, String url){
        boolean register = false;
        
         if(url.isEmpty()){
             url = "";
         }
         String searchQuery = "select * from t_users where username='" + autor + "'";
         String searchQuery2 = "select * from t_canales where nombre='" + channel + "'";

         try 
      {
          //preparing some objects for connection 
        currentCon = ConnectionManager.getConnection();
         Statement stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);
         String idUsuario = "";
         if(rs.next()){
             idUsuario = rs.getString("id_usuario");
         }
         rs = stmt.executeQuery(searchQuery2);
         String idCanal = "";
         if(rs.next()){
             idCanal = rs.getString("id");
         }
          System.out.println("Canalid = "+idCanal+" Usuario "+idUsuario);
          String registerQuery = "insert into t_messages(idauthor, idchannel, message, urlMedia) values ('"+idUsuario+
                 "','"+idCanal+"','"+Message+"','"+url+"')";
          System.out.println("SQL "+registerQuery);
             register = true;
             stmt.executeUpdate(registerQuery);
      } 
      catch (Exception ex) 
      {
         System.out.println("Register fail: An Exception has occurred! " + ex);
      } 
        return register;
    }

    public static ArrayList<MessageBean> getMessages(String channel){
        ArrayList<MessageBean> canales = new ArrayList();
          try {
              
              //connect to DB
                //preparing some objects for connection 
              currentCon = ConnectionManager.getConnection();
              Statement stmt=currentCon.createStatement();
              String idChannel = "";
              String searchQuery1 = "select id from t_canales where nombre='" + channel+ "'";
              System.out.println("SEARCHqUERY1 "+searchQuery1);
              rs = stmt.executeQuery(searchQuery1);
              if(rs.next()){
                idChannel = rs.getString("id");
                
                }
              System.out.println("Canal id "+idChannel);
              String searchQuery2 = "select * from t_messages where idchannel='" + idChannel+ "'";
              System.out.println("Consulta "+searchQuery2);
              stmt=currentCon.createStatement();
              rs = stmt.executeQuery(searchQuery2);
              while(rs.next())
              {
                  MessageBean canal = new MessageBean();                  
                  canal.setId(rs.getString("idMessage"));
                  canal.setAutor(rs.getString("idauthor"));
                  canal.setChannel(rs.getString("idchannel"));
                  canal.setMessage(rs.getString("message"));
                  canal.setUrl(rs.getString("urlMedia"));
                  canal.setFecha(rs.getString("date"));
                  canales.add(canal);
              }
              System.out.println("Consulta size "+canales.size());
          } catch (SQLException ex) {
              Logger.getLogger(CanalDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          return canales;
    }
    public static boolean deletMessage(String idMessage, String idAuthor){
    //preparing some objects for connection 
         Statement stmt = null;
         boolean deletedMessage = false;
         if(isAuthor(idMessage, idAuthor)){
         try {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
          String deletQuery = "DELETE FROM t_messages WHERE idMessage = '"+idMessage+"'";
          System.out.println("Delet message "+deletQuery);
             //register User
             int r = stmt.executeUpdate(deletQuery);
             if(r == 1){deletedMessage = true;}
      } 
      catch (Exception ex) 
      {
         System.out.println("deletMessage fail: An Exception has occurred! " + ex);
      } }
         return deletedMessage;
    }
    public static boolean isAuthor(String idMessage, String idAuthor){
       //preparing some objects for connection 
         Statement stmt = null;
         boolean Author = false;
         try {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
          String deletQuery = "select * FROM t_messages WHERE idMessage = '"+idMessage+"' AND idauthor = '"+idAuthor+"'";
          System.out.println("isAuthor message "+deletQuery);
             //register User
             ResultSet r = stmt.executeQuery(deletQuery);
             if(r.next()){Author = true;}
      } 
      catch (Exception ex) 
      {
         System.out.println("isAuthor fail: An Exception has occurred! " + ex);
      } 
         return Author; 
    }
}
