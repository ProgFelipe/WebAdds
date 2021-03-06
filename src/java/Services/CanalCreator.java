/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.CanalBean;
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
@WebServlet("/ChannelCreator")
public class CanalCreator  extends HttpServlet{
    AdFuente p;
    /*
    * Create the Channel and add it to the data Base
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String name = request.getParameter("channelName");
        String description = request.getParameter("description");
        String url = request.getParameter("imgChannel");
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
        String autor = currentUser.getUsername().toString();
        System.out.println("Autor "+autor);
        if(name == null || description == null || autor == null || url == null){
        request.setAttribute("message", "some data is empty!");
		getServletContext().getRequestDispatcher("manager.jsp?place=MyChannels").forward(
				request, response);
        }else{
            
            CanalBean canal = new CanalBean();
            canal.setNombre(name);
            canal.setDescripcion(description);
            canal.setAutor(autor);
            canal.setImageUrl(url);

            boolean register = CanalDAO.createCanal(canal);
                Subscribe sus = new Subscribe();
                sus.SubscribeToChannel(autor, name);
                if (register){  
                  response.sendRedirect("manager.jsp?place=MyChannels"); //logged-in page      		
                }else{ 
                      request.setAttribute("message", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Creation Error!</strong> Check data and try again</div>");
		getServletContext().getRequestDispatcher("/manager.jsp?place=MyChannels").forward(
				request, response);
                    }
        }
    }
    
}
