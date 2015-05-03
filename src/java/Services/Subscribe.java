/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.SuscriptorBean;
import Bean.UserBean;
import DAO.CanalDAO;
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
@WebServlet("/Subscriptor")
public class Subscribe extends HttpServlet{
     public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String channel = request.getParameter("channel");
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
        String autor = currentUser.getUsername().toString();
        if(channel.isEmpty()){
        request.setAttribute("message", "channel is empty");
		getServletContext().getRequestDispatcher("/InChannel.jsp?channel=Art&subscribed=MyChannels").forward(
				request, response);
        }else{
            boolean register = SubscribeToChannel(autor,  channel);
                if (register){        
                  request.setAttribute("pull", "<div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Yeah!</strong> You are now subscribed</div>");
		getServletContext().getRequestDispatcher("/InChannel.jsp?channel="+channel+"&subscribed=Suscriptions").forward(
				request, response);
                }else{ 
                      request.setAttribute("pull", "<div class=\"alert alert-danger\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>"
                              + "Erro!</strong> It was not possible to subscribe try again!</div>");
		getServletContext().getRequestDispatcher("/InChannel.jsp?channel="+channel).forward(
				request, response);
                    }
        }
    }
     public boolean SubscribeToChannel(String autor, String channel){
         SuscriptorBean user = new SuscriptorBean();
            user.setSuscriptor(autor);
            user.setCanal(channel);
            return CanalDAO.setSuscription(user);
     }
}
