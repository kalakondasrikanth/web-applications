package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.database.CreateAnswerDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.Answer;
import it.unipd.dei.webapp.leoforfriends.resource.Message;
import it.unipd.dei.webapp.leoforfriends.resource.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Creates an answer into the database.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class CreateAnswerServlet extends AbstractDatabaseServlet {

	/**
	 * Creates a new answer into the database.
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
		int questionid = -1;
		String user = null;
		String content = null;

		// model
		Answer answer  = null;
		Message message = null;
		User actor = null;

		try{
			// retrieves the request parameters
			questionid = Integer.parseInt(req.getParameter("questionid"));
			HttpSession session = req.getSession();
			user = (String) session.getAttribute("actor");
			content = req.getParameter("content");
			actor = new User(user);
			// creates a new answer from the request parameters
			answer = new Answer(questionid, user, content);

			// creates a new object for accessing the database and stores the answer
			new CreateAnswerDatabase(getDataSource().getConnection(), answer).createAnswer();
			
			message = new Message(String.format("Question %s successfully created.", questionid, user));

		} catch (NumberFormatException ex) {
			message = new Message("Cannot create the answer. Invalid input parameters: question must be an integer",
					"E100", ex.getMessage());
		} catch (SQLException ex) {
			if (ex.getSQLState().equals("23505")) {
				message = new Message(String.format("Cannot create the answer: User already answered to question %s .", questionid),
						"E300", ex.getMessage());
			} else {
				message = new Message("Cannot create the answer: unexpected error while accessing the database.",
						"E200", ex.getMessage());
			}
		}
		
		// stores the answer and the message as a request attribute
		req.setAttribute("answer", answer);
		req.setAttribute("message", message);
		req.setAttribute("user",actor);
		req.setAttribute("questionid",questionid);
		
		// forwards the control to the create-answer-result JSP
		req.getRequestDispatcher("/jsp/create-answer-result.jsp").forward(req, res);
	}

}
