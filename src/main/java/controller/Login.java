package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dto.User;

@WebServlet("/userlogin")
public class Login extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String email = req.getParameter("email");
	String password = req.getParameter("password");
DAO dao=new DAO();
try {
	User u =dao.findByEmail(email);
	if(u!=null) {
		//verify the password
		if(u.getUserpassword().equals(password)) {
			//login success
			//create session and set the data 
			req.getSession().setAttribute("user", u);
			req.getRequestDispatcher("home.jsp").include(req, resp);
		}
		else {
			//password wrong
			req.setAttribute("message", "password wrong");
			req.getRequestDispatcher("login.jsp").include(req, resp);
            }
	}
	else {
		//email is wrong
		req.setAttribute("message", "wrong email");
        req.getRequestDispatcher("login.jsp").include(req, resp);

	}
}
catch (Exception e) {

}
}
}
