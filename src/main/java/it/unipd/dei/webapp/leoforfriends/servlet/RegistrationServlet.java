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
 * Creates a new user into the database.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class RegistrationServlet extends AbstractDatabaseServlet {

	/**
	 * Creates a new user into the database.
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

		String email = null;
		String username = null;
		String name = null;
		String surname = null;
		String hashedpw = null;
		String hashedpw1 = null;

		// model
		User user  = null;
		Message message = null;

		try{
			// retrieves the request parameters
			email = req.getParameter("email");
			username = req.getParameter("username");
			name = req.getParameter("name");
			surname = req.getParameter("surname");
			hashedpw = req.getParameter("hashedpw");
			hashedpw1 = req.getParameter("hashedpw1");

			// creates a new object for accessing the database and stores the user
			if (hashedpw.equals(hashedpw1) && username != null && !hashedpw.equals("")
					&& email != null && username != null && !username.equals("")
					&& !email.equals("")){
				hashedpw = Integer.toString(req.getParameter("hashedpw").hashCode());
				user = new User(email, username, hashedpw, 0, name, surname, null, false);
				new CreateUserDatabase(getDataSource().getConnection(), user).createUser();

				HttpSession session = req.getSession();
				session.setAttribute("actor", user.getUsername());
				session.setAttribute("hashedpw", user.getHashedpw());
				message = new Message(String.format("User %s registered.", username));
			}
			else if (!hashedpw.equals(hashedpw1)) {

				message = new Message("Mismatch between the two typed passwords",
						"E400", "Bad Request");
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error-result.jsp").forward(req, res);

			}
			else if (hashedpw == null || hashedpw.equals("")
					|| email == null || username == null || username.equals("")
					|| email.equals("")){
				message = new Message("Invalid input parameters",
						"E400", "Bad Request");
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error-result.jsp").forward(req, res);

			}
			else {
				message = new Message(String.format("Something went wrong with the registration of user %s," +
						" the user already exists or some parameters were wrong", username),
						"E400", "Bad Request");
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/error-result.jsp").forward(req, res);
			}



		} catch (SQLException ex) {
				message = new Message(String.format("Something went wrong with the registration of user %s," +
						"probably the user already exists or some parameters were wrong,", username),
						"E200", ex.getMessage());
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/registration-result.jsp").forward(req, res);
		}
		
		// stores the user and the message as a request attribute
		req.setAttribute("user", user);
		//	req.setAttribute("message", message);
		
		// forwards the control to the registration-result JSP
		req.getRequestDispatcher("/jsp/registration-result.jsp").forward(req, res);
	}

}
