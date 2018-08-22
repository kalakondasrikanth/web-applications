package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.database.ModifyUserDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.Message;
import it.unipd.dei.webapp.leoforfriends.resource.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Modifies an user.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class ModifyUserServlet extends AbstractDatabaseServlet {

	/**
	 * Modifies an user
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
		String username = null;
		String name = null;
		String surname = null;
		String academicTitle = null;
		String hashedpw = null;
		String hashedpw1 = null;


		// model
		User us  = null;
		User user = null;
		Message message = null;

		try{
			username = req.getParameter("username");
			name = req.getParameter("name");
			surname = req.getParameter("surname");
			academicTitle = req.getParameter("academicTitle");
			hashedpw = req.getParameter("hashedpw");
			hashedpw1 = req.getParameter("hashedpw1");

			if (hashedpw.equals(hashedpw1)) {

				hashedpw = Integer.toString(hashedpw.hashCode());
				us = new User("placeholderMail", username, hashedpw,
						0, name, surname, academicTitle, false);
				// creates a new object for accessing the database and modifying the user
				user = new ModifyUserDatabase(getDataSource().getConnection(), us).updateUser();

				if (user != null) {
					HttpSession session = req.getSession();
					session.setAttribute("actor", user.getUsername());
					session.setAttribute("hashedpw", user.getHashedpw());
					message = new Message(String.format("User %s modified his/her profile.", user));
				} else {
					throw new SQLException();
				}
			}
			else {
					message = new Message("Mismatch between the two typed passwords",
							"E400", "Bad Request");
					req.setAttribute("message", message);
					req.getRequestDispatcher("/jsp/login-result.jsp").forward(req, res);
			}



		} catch (SQLException ex) {
				message = new Message("Cannot find the user: unexpected error while accessing the database.",
						"E200", ex.getMessage());
		}
		
		// stores the user and the message as a request attribute
		req.setAttribute("user", user);
		req.setAttribute("message", message);
		
		// forwards the control to the user-detail JSP
		req.getRequestDispatcher("/jsp/user-detail.jsp").forward(req, res);
	}

}
