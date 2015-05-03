/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.CanalBean;
import Bean.UserBean;
import DAO.CanalDAO;
import DAO.UserDAO;
import java.util.ArrayList;
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
@WebServlet("/unsubscribe")
public class UnRegister extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
     /*
        *All user information will be erase
        */   
        try{
        HttpSession s=request.getSession();
                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
        ArrayList<CanalBean> channels = null;
        channels = CanalDAO.getCanalsDataByAuthor(currentUser.getUsername());
        for(int c = 0; c< channels.size(); c++){
            CanalDAO.deletAllmesasages(channels.get(c).getNombre());
            CanalDAO.deletAllsubscribers(channels.get(c).getNombre());
            CanalDAO.deletChannel(channels.get(c).getNombre());
        }
        boolean unregister = UserDAO.deletUser(currentUser);
        if(unregister){
        HttpSession session = request.getSession(false);
        if(session != null){
        session.invalidate();}
        request.setAttribute("message", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>You been removed to!</strong> Adds Service ;-(</div>");
		getServletContext().getRequestDispatcher("/index.jsp").forward(
				request, response);
        }else{
            request.setAttribute("message", "Error unbsubscribing... Try again!");
		getServletContext().getRequestDispatcher("/manager.jsp?place=MyChannels").forward(
				request, response);
        }
        }catch(Exception e){System.err.println("Exception"+e);}    
    }
}
