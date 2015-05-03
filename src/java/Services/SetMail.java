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
@WebServlet("/getMail")
public class SetMail extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String channel = request.getParameter("channel");
        String mail = request.getParameter("mail");
        String suscriptor = "";
        try{
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
         suscriptor = currentUser.getUsername();
        
        if(channel.isEmpty()){
        request.setAttribute("poll", "channel is not defined!");
		getServletContext().getRequestDispatcher("/InChannel.jsp?channel="+channel).forward(
				request, response);
        }else{
            SuscriptorBean user = new SuscriptorBean();
            user.setCanal(channel);
            user.setSuscriptor(suscriptor);
            System.out.println("Set mail dao");
            int mailSet = CanalDAO.setMail(user, mail);
            System.out.println("mail set "+mailSet);
                if (mailSet == 1){        
                  response.sendRedirect("InChannel.jsp?channel="+channel+"&subscribed=Suscriptions");
                }else{ 
                      request.setAttribute("poll", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Mail set Error!</strong> Try again!</div>");
		getServletContext().getRequestDispatcher("/InChannel.jsp?channel="+channel+"&subscribed=Suscriptions").forward(
				request, response);
                    }
        }
        }catch(Exception e){System.err.println("Exception"+e);}
    }

}
