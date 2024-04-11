package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import dto.Task;
import dto.User;

@WebServlet("updatetask")
public class UpdateTask extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int taskid=Integer.parseInt(req.getParameter("taskid"));
		String tasktitle=req.getParameter("tasktitle");
		String taskdescription=req.getParameter("taskdescription");
		String taskpriority=(req.getParameter("taskpriority")==null)?req.getParameter("pre-priority"):req.getParameter("taskpriority");
		String taskduedate=req.getParameter("taskduedate");
		String taskstatus=req.getParameter("taskstatus");
		int userid=Integer.parseInt(req.getParameter("userid"));

		Task task=new Task(taskid,tasktitle,taskdescription,taskpriority,taskduedate,taskstatus,userid);
		
		DAO dao=new DAO();	
		
		try {
		      int res=dao.createUpdateTask(task);
		      if(res>0) {
		    	  //resp.getWriter().println("task has been created");
		    	 
		    	  HttpSession session=req.getSession();
					User u= (User)session.getAttribute("user");
		    	  
		    	  req.setAttribute("tasks",dao.getalltasksById(u.getUserid()));
		    	  RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
		    	  dispatcher.include(req,resp);
		    	  					
		    	  
		      }else {
		    	  HttpSession session=req.getSession();
					User u= (User)session.getAttribute("user");
		    	  
		    	  req.setAttribute("tasks",dao.getalltasksById(u.getUserid()));
		    	  RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
		    	  dispatcher.include(req,resp);
		      }
		}
		catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
