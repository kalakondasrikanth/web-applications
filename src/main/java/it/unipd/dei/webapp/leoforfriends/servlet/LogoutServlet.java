package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.database.CreateUserDatabase;
import it.unipd.dei.webapp.leoforfriends.database.SearchUserDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.User;
import it.unipd.dei.webapp.leoforfriends.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logs out the user.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class LogoutServlet extends AbstractDatabaseServlet {

	/**
	 * Logs out the user.
	 * 
	 * @param req
	 *            the HTTP request from the client.
	 * @param res
	 *            the HTTP response from the server.
	 * 
	 * @throws ServletException
	 *             if any error occurs while executing the servlet.
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		Message message;

		if (session == null || session.getAttribute("actor") == null || session.getAttribute("hashedpw") == null){
			message = new Message("No user was logged in");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req,res);
		}
		else {
			String user = (String) session.getAttribute("actor");
			session.removeAttribute("actor");
			session.removeAttribute("hashedpw");
			session.invalidate();
			message = new Message(String.format("User %s logged out.", user));
			req.getRequestDispatcher("/html/homepage.html").forward(req,res);
			// stores the message as a request attribute
			req.setAttribute("user",null);
			req.setAttribute("message", message);

			// forwards the control to the homepage
			req.getRequestDispatcher("/html/homepage.html").forward(req, res);
		}
	}

}
