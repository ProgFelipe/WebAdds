/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.SuscriptorBean;
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
@WebServlet("/deletMessage")
public class DeletMessage  extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String messageId = request.getParameter("id");
        String suscriptor = "";
        try{
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
         suscriptor = currentUser.getUsername().toString();
        
        if(messageId.isEmpty()){
        request.setAttribute("message", "channel is not defined!");
		getServletContext().getRequestDispatcher("/InChannel.jsp").forward(
				request, response);
        }else{
            SuscriptorBean user = new SuscriptorBean();
            user.setSuscriptor(suscriptor);
            String idUser = CanalDAO.getIdSubscriptor(user);
            boolean deletedMessage = MessagesDAO.deletMessage(messageId, idUser);
                if (deletedMessage){        
                  response.sendRedirect("manager.jsp?place=Suscriptions"); //logged-in page      		
                }else{ 
                      request.setAttribute("message", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Deleted Message Fail!</strong> Probably you are not the author</div>");
		getServletContext().getRequestDispatcher("/manager.jsp?place=Suscriptions").forward(
				request, response);}
        }
        }catch(Exception e){System.err.println("Exception"+e);}
    }
    
}
