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
 * Authenticates an user.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class LoginServlet extends AbstractDatabaseServlet {

	/**
	 * Authenticates an user
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
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// request parameters
		String actor = null;
		String hashedpw = null;

		// model
		User user  = null;
		Message message = null;

		try{
			// retrieves the request parameters
			actor = req.getParameter("actor");
			hashedpw = Integer.toString(req.getParameter("hashedpw").hashCode());

			if (actor == null || actor.equals("") || hashedpw == null || hashedpw.equals("")) {
				message = new Message("Cannot find the user: either username or password were wrong.",
						"E400", "Bad Request");
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error-result.jsp").forward(req, res);
			}

			// creates a new object for accessing the database and searching for the user
				user = new SearchUserDatabase(getDataSource().getConnection(), actor, hashedpw).searchUser();

			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("actor", user.getUsername());
				session.setAttribute("hashedpw", user.getHashedpw());
				message = new Message(String.format("User %s logged in.", actor));
			}

			else{
				message = new Message("Cannot find the user: either username or password were wrong.",
						"E400", "Bad Request");
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error-result.jsp").forward(req, res);
			}



		} catch (SQLException ex) {
				message = new Message("Cannot find the user: unexpected error while accessing the database.",
						"E200", ex.getMessage());
				req.setAttribute("message", message);
		}
		
		// stores the user and the message as a request attribute
		req.setAttribute("user", user);
		//req.setAttribute("message", message);
		
		// forwards the control to the login-result JSP
		req.getRequestDispatcher("/jsp/login-result.jsp").forward(req, res);
	}

}
