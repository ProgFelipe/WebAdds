/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.CanalBean;
import Bean.UserBean;
import DAO.CanalDAO;
import DAO.MessagesDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipe
 */
@WebServlet("/addMessage")
public class AdFuente2  extends HttpServlet{
    AdFuente p;
    /*
    * Create the Channel and add it to the data Base
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String channel = request.getParameter("channelName");
        String message = request.getParameter("Message");
        String url = request.getParameter("url");
        //is channel creator or subscriber
        String type = request.getParameter("type");
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
        String autor = currentUser.getUsername().toString();
        System.out.println("Autor "+autor);
        if(message.isEmpty() || autor.isEmpty() || channel.isEmpty()){
        request.setAttribute("message", "some data is empty!");
		getServletContext().getRequestDispatcher("/manager.jsp?place=MyChannels").forward(
				request, response);
        }else{
            
            MessagesDAO canal = new MessagesDAO();
            SendEmail mail = new SendEmail();
            mail.getSubscribersAndSend(autor, channel, url, message);
            boolean register = canal.setMessage(channel, message, autor, url);            

                if (register){  
                  response.sendRedirect("InChannel.jsp?channel="+channel+"&subscribed="+type); //logged-in page      		
                }else{ 
                      request.setAttribute("pull", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Message Creation Error!</strong> Check data and try again</div>");
		getServletContext().getRequestDispatcher("/InChannel.jsp?channel="+channel+"&subscribed="+type).forward(
				request, response);
                    }
        }
    }
    /*
    public void changeName(String newName){}
    public void changeDescription(String newDescription){}
    public void delet(){}
    */
    
}
