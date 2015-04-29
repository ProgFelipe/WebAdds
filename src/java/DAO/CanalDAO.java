/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.ConnectionManager;
import Bean.CanalBean;
import Bean.SuscriptorBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class CanalDAO {
      static Connection currentCon = null;
      static ResultSet rs = null;  
      
    public static boolean createCanal(CanalBean canal){
    //preparing some objects for connection 
         Statement stmt = null;
         boolean canalRegistrado = false;
         String searchQuery = "select * from t_canales where nombre='" + canal.getNombre()+ "'";
         String registerQuery = "insert into t_canales(nombre, descripcion, author, img_url) values ('"+canal.getNombre()+
                 "','"+canal.getDescripcion()+"','"+canal.getAutor()+"','"+canal.getImg_Url()+"')";
         try 
      {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);
         boolean more = rs.next();
         System.out.println("**¨*¨*¨*¨*Channel existente ? "+ more);
         if(more){
             //User exist
         }else{
             //register User
             canalRegistrado = true;
             stmt.executeUpdate(registerQuery);
         }
      } 
      catch (Exception ex) 
      {
         System.out.println("Register fail: An Exception has occurred! " + ex);
      } 
         return canalRegistrado;
    }

    public static ArrayList<CanalBean> getCanalsDataByAuthor(String autor){
        ArrayList<CanalBean> canales = new ArrayList();
          try {
              
              //connect to DB
              //preparing some objects for connection
              Statement stmt = null;
              currentCon = ConnectionManager.getConnection();
              String searchQuery = "select * from t_canales where author='" + autor+ "' ORDER BY id DESC";
              System.out.println("Consulta "+searchQuery);
              stmt=currentCon.createStatement();
              ResultSet rs = stmt.executeQuery(searchQuery);
              while(rs.next())
              {
                  CanalBean canal = new CanalBean();
                  canal.setAutor(rs.getString("author"));
                  canal.setDescripcion(rs.getString("descripcion"));
                  canal.setId(rs.getString("id"));
                  canal.setFecha(rs.getString("fecha"));
                  canal.setNombre(rs.getString("nombre"));
                  canal.setImageUrl(rs.getString("img_url"));
                  canales.add(canal);
              }
              System.out.println("Consulta size "+canales.size());
          } catch (SQLException ex) {
              Logger.getLogger(CanalDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          return canales;
    }
    
    public static ArrayList<CanalBean> getMySuscriptions(String username) throws SQLException{
        ArrayList<CanalBean> canales = new ArrayList();
                 //connect to DB 
        //preparing some objects for connection 
         Statement stmt = null;
         currentCon = ConnectionManager.getConnection();
         String searchQuery = "select id_usuario from t_users where username='" + username +"'";
         stmt=currentCon.createStatement();
         
        ResultSet rs = stmt.executeQuery(searchQuery);
        System.out.println("IDUSUARIO ?");
        String idusuario = "";
        if(rs.next()){
            System.out.println("IDUSUARIO ?"+rs.getString("id_usuario"));
            idusuario = rs.getString("id_usuario");
        }
        if(idusuario.length() != 0){
        System.out.println("Getting suscriptions Usuario "+username+" id usuario "+idusuario);
        searchQuery ="select t_canales.* from t_suscriptores INNER JOIN t_canales  ON t_suscriptores.idcanal = t_canales.id AND t_suscriptores.idsuscriptor='"+idusuario+"'";
        rs = stmt.executeQuery(searchQuery);
        while(rs.next())
        {
                  CanalBean canal = new CanalBean();
                  canal.setAutor(rs.getString("author"));
                  canal.setDescripcion(rs.getString("descripcion"));
                  canal.setId(rs.getString("id"));
                  canal.setFecha(rs.getString("fecha"));
                  canal.setNombre(rs.getString("nombre"));
                  canal.setImageUrl(rs.getString("img_url"));
                canales.add(canal);
        }}
        System.out.println("Suscripciones size "+canales.size());
        return canales;
    }
        public static ArrayList<CanalBean> getAll(){
        ArrayList<CanalBean> canales = new ArrayList();
          try {
              //connect to DB
              //preparing some objects for connection
              Statement stmt = null;
              currentCon = ConnectionManager.getConnection();
              String searchQuery = "select * from t_canales ORDER BY id DESC";
              System.out.println("Consulta "+searchQuery);
              stmt=currentCon.createStatement();
              ResultSet rs = stmt.executeQuery(searchQuery);
              while(rs.next())
              {
                  CanalBean canal = new CanalBean();
                  canal.setAutor(rs.getString("author"));
                  canal.setDescripcion(rs.getString("descripcion"));
                  canal.setId(rs.getString("id"));
                  canal.setFecha(rs.getString("fecha"));
                  canal.setNombre(rs.getString("nombre"));
                  canal.setImageUrl(rs.getString("img_url"));
                  canales.add(canal);
              }
              System.out.println("Consulta size "+canales.size());
          } catch (SQLException ex) {
              Logger.getLogger(CanalDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          return canales;
    }
    public boolean userSuscription(String canalName){
        //check if user is a suscriber
        boolean suscriber = true;
        
        return suscriber;
    }
    public static boolean setSuscription(SuscriptorBean user){
    //preparing some objects for connection 
         Statement stmt = null;
         boolean usuarioSuscrito = false;
         
         String searchQuery = "select * from t_users where username='" + user.getSuscriptor()+ "'";
         String searchQuery2 = "select * from t_canales where nombre='" + user.getCanal()+ "'";

         try 
      {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
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
          String registerQuery = "insert into t_suscriptores(idcanal, idsuscriptor) values ('"+idCanal+
                 "','"+idUsuario+"')";
          System.out.println("SQL "+registerQuery);
             //register User
             usuarioSuscrito = true;
             stmt.executeUpdate(registerQuery);
      } 
      catch (Exception ex) 
      {
         System.out.println("Register fail: An Exception has occurred! " + ex);
      } 
         return usuarioSuscrito;
    }
   public static boolean Unsubsribe(SuscriptorBean user){
        //preparing some objects for connection 
         Statement stmt = null;
         boolean usuarioDesInscrito = false;
         
         String searchQuery = "select * from t_users where username='" + user.getSuscriptor()+ "'";
         String searchQuery2 = "select * from t_canales where nombre='" + user.getCanal()+ "'";

         try 
      {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
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
          String registerQuery = "DELETE FROM t_suscriptores WHERE idcanal = '"+idCanal+"' and idsuscriptor='"+idUsuario+"'";
          System.out.println("SQL "+registerQuery);
             //register User
             usuarioDesInscrito = true;
             stmt.executeUpdate(registerQuery);
      } 
      catch (Exception ex) 
      {
         System.out.println("UnSubsribed fail: An Exception has occurred! " + ex);
      } 
         return usuarioDesInscrito;
   
   }
}
