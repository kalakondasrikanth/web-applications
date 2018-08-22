package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.database.ListAllQuestionsDatabase;
import it.unipd.dei.webapp.leoforfriends.database.ListUserQuestionsDatabase;
import it.unipd.dei.webapp.leoforfriends.database.SearchQuestionDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.Question;
import it.unipd.dei.webapp.leoforfriends.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Retrieves from the Database all the questions inserted by a single user
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class SearchQuestionServlet extends AbstractDatabaseServlet {

	/**
	 * Retrieves from the Database all the questions inserted by a single user
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

		String user = null;
		String userInput = null;
		List<Question> selectedQuestions = new ArrayList<Question>();
		Message message = null;

		try {
		
			// retrieves the request parameter
			userInput = req.getParameter("userInput");

			if (userInput == null || userInput.equals("")){
				message = new Message("Invalid user input",
						"E201", "The input field cannot be left empty");
				req.setAttribute("message", message);
				req.getRequestDispatcher("/jsp/search-question-result.jsp").forward(req, res);
			}

			selectedQuestions = new SearchQuestionDatabase(getDataSource().getConnection(), userInput)
					.searchQuestion();

			String m = "Found " + selectedQuestions.size() + " questions";
			message = new Message(m);
			
		}  catch (SQLException ex) {
				message = new Message("Cannot search for questions: unexpected error while accessing the database.",
						"E200", ex.getMessage());
		}

		// stores the question list and the message as a request attribute
		req.setAttribute("questions", selectedQuestions);
		req.setAttribute("message", message);
		
		// forwards the control to the list-user-question-result JSP
		req.getRequestDispatcher("/jsp/search-question-result.jsp").forward(req, res);

	}

}
