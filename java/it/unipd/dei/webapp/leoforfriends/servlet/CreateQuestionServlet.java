package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.database.CreateQuestionDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.Question;
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
 * Creates a new question into the database.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class CreateQuestionServlet extends AbstractDatabaseServlet {

	/**
	 * Creates a new question into the database.
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
		int id = -1;
		String title = null;
		String content = null;
		String actor = null;
		User user = null;

		// model
		Question question  = null;
		Question createdQuestion = null;
		Message message = null;

		try{
			// retrieves the request parameters
			// id = Integer.parseInt(req.getParameter("id"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			HttpSession session = req.getSession();
			actor = (String) session.getAttribute("actor");
			user = new User(actor);

			// creates a new question from the request parameters
			question = new Question(id, title, content, actor);

			// creates a new object for accessing the database and stores the question
			createdQuestion = new CreateQuestionDatabase(getDataSource().getConnection(), question).createQuestion();
			
			message = new Message(String.format("Question %s successfully created.", title));

		} catch (SQLException ex) {
			if (ex.getSQLState().equals("23505")) {
				message = new Message(String.format("Cannot create the question: question %s already exists.", id),
						"E300", ex.getMessage());
			} else {
				message = new Message("Cannot create the question: unexpected error while accessing the database.",
						"E200", ex.getMessage());
			}
		}
		
		// stores the question and the message as a request attribute
		req.setAttribute("question", createdQuestion);
		req.setAttribute("message", message);
		req.setAttribute("user",user);
		
		// forwards the control to the create-question-result JSP
		req.getRequestDispatcher("/jsp/create-question-result.jsp").forward(req, res);
	}

}
