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
@WebServlet("/DeletChannel")
public class DeletChannel  extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String channel = request.getParameter("channel");
        String suscriptor = "";
        try{
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
         suscriptor = currentUser.getUsername().toString();
        
        if(channel.isEmpty()){
        request.setAttribute("message", "channel is not defined!");
		getServletContext().getRequestDispatcher("/InChannel.jsp").forward(
				request, response);
        }else{
            SuscriptorBean user = new SuscriptorBean();
            user.setCanal(channel);
            user.setSuscriptor(suscriptor);
                CanalDAO.deletAllmesasages(channel);
                CanalDAO.deletAllsubscribers(channel);
                boolean deletedChannel = CanalDAO.deletChannel(channel);
                if (deletedChannel){        
                  response.sendRedirect("manager.jsp?place=Suscriptions"); //logged-in page      		
                }else{ 
                      request.setAttribute("message", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Unsubscribed Fail!</strong> Try again!</div>");
		getServletContext().getRequestDispatcher("/manager.jsp?place=Suscriptions").forward(
				request, response);
                    }
        }
        }catch(Exception e){System.err.println("Exception"+e);}
    }
    
}
