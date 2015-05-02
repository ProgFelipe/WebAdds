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
import Bean.CanalBean;
import Bean.MessageBean;
import DAO.CanalDAO;
import DAO.MessagesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/GetJSON")
public class jsonHandler extends HttpServlet {
private static final long serialVersionUID = 1L;

public jsonHandler() {
super();
}

@SuppressWarnings("empty-statement")
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
String username = request.getParameter("username");
String place = request.getParameter("place");
String channel = request.getParameter("channel");
    System.err.println("username "+username+" place "+place+" channel "+channel);
PrintWriter out = response.getWriter();
if(place != null){
    getChannels(place, username, out);};
if(channel != null){getMessages(channel, out);};
}

public void getChannels(String place, String username, PrintWriter out){
try
{
if(place.equalsIgnoreCase("MyChannels")){
ArrayList<CanalBean> messagesData = null;
messagesData = CanalDAO.getCanalsDataByAuthor(username);
JSONArray jArray = new JSONArray();
    for(int c = 0; c< messagesData.size(); c++){
      JSONObject obj = new JSONObject();
      obj.put("autor", messagesData.get(c).getAutor());
      obj.put("descripcion", messagesData.get(c).getDescripcion());
      obj.put("id", messagesData.get(c).getId());
      obj.put("fecha", messagesData.get(c).getFecha());
      obj.put("nombre", messagesData.get(c).getNombre());
      obj.put("url", messagesData.get(c).getImg_Url());
      jArray.add(c,obj);
    }

String messages = jArray.toJSONString();
//System.out.println("{\"Messages\":"+messages+"}");
out.println(messages);
}else if(place.equalsIgnoreCase("Suscriptions")){
ArrayList<CanalBean> messagesData = null;
messagesData = CanalDAO.getMySuscriptions(username);
JSONArray jArray = new JSONArray();
    for(int c = 0; c< messagesData.size(); c++){
      JSONObject obj = new JSONObject();
      obj.put("autor", messagesData.get(c).getAutor());
      obj.put("descripcion", messagesData.get(c).getDescripcion());
      obj.put("id", messagesData.get(c).getId());
      obj.put("fecha", messagesData.get(c).getFecha());
      obj.put("nombre", messagesData.get(c).getNombre());
      obj.put("url", messagesData.get(c).getImg_Url());
      jArray.add(c,obj);
    }    
String messages = jArray.toJSONString();
//System.out.println("{\"Messages\":"+messages+"}");
out.println(messages);    

}else if(place.equalsIgnoreCase("allchannels")){
ArrayList<CanalBean> messagesData = null;
messagesData = CanalDAO.getAll();
JSONArray jArray = new JSONArray();
    for(int c = 0; c< messagesData.size(); c++){
      JSONObject obj = new JSONObject();
      obj.put("autor", messagesData.get(c).getAutor());
      obj.put("descripcion", messagesData.get(c).getDescripcion());
      obj.put("id", messagesData.get(c).getId());
      obj.put("fecha", messagesData.get(c).getFecha());
      obj.put("nombre", messagesData.get(c).getNombre());
      obj.put("url", messagesData.get(c).getImg_Url());
      jArray.add(c,obj);
    }    
String messages = jArray.toJSONString();
//System.out.println("{\"Messages\":"+messages+"}");
out.println(messages);    
}
}
catch (Exception ex)
{
out.println("Error: " + ex.getMessage());
}
finally
{
out.close();
}
}
public void getMessages(String channel, PrintWriter out){    
try
{

ArrayList<MessageBean> messagesData = null;
messagesData = MessagesDAO.getMessages(channel);
JSONArray jArray = new JSONArray();
    for(int c = 0; c< messagesData.size(); c++){
      JSONObject obj = new JSONObject();
      obj.put("idMessage", messagesData.get(c).getId());
      obj.put("idauthor", messagesData.get(c).getAutor());
      obj.put("idchannel", messagesData.get(c).getChannel());
      obj.put("message", messagesData.get(c).getMessage());
      obj.put("urlMedia", messagesData.get(c).getUrl());
      obj.put("date", messagesData.get(c).getFecha());
      jArray.add(c,obj);
    }    
String messages = jArray.toJSONString();
System.out.println(messages);
out.println(messages);    
}
catch (Exception ex)
{
out.println("Error: " + ex.getMessage());
}
finally
{
out.close();
}
}
}
