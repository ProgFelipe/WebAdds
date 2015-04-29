/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.UserBean;
import DAO.UserDAO;
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
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("confirm-password");
        String email = request.getParameter("email");
        if(email.isEmpty() || username.isEmpty() || pass.isEmpty() || pass2.isEmpty()){
        request.setAttribute("message", "UserId or password is empty!");
		getServletContext().getRequestDispatcher("/registro.jsp").forward(
				request, response);
        }else{
            if(!pass.equals(pass2)){
            request.setAttribute("message", "passwords are not the same");
		getServletContext().getRequestDispatcher("/index.jsp").forward(
				request, response);
            }else{
            UserBean user = new UserBean();
            user.setUserName(username);
            user.setPassword(pass);
            user.setEmail(email);
            boolean register = UserDAO.Register(user);
                if (register){        
                  HttpSession session = request.getSession(true);	    
                  session.setAttribute("currentSessionUser",user); 
                  response.sendRedirect("myChannels.jsp"); //logged-in page      		
                }else{ 
                      request.setAttribute("message", "<div class=\"alert alert-danger alert-error\">\n" +
"            <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a><strong>Register Error!</strong> Check data or Change user</div>");
		getServletContext().getRequestDispatcher("/index.jsp").forward(
				request, response);
                    }
        }
        }
    }     
}
