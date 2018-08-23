package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.resource.*;
import it.unipd.dei.webapp.leoforfriends.rest.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Manages the REST API for the different REST resources.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class RestManagerServlet extends AbstractDatabaseServlet {

	/**
	 * The JSON MIME media type
	 */
	private static final String JSON_MEDIA_TYPE = "application/json";

	/**
	 * The JSON UTF-8 MIME media type
	 */
	private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

	/**
	 * The any MIME media type
	 */
	private static final String ALL_MEDIA_TYPE = "*/*";

	@Override
	protected final void service(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType(JSON_UTF_8_MEDIA_TYPE);
		final OutputStream out = res.getOutputStream();

		try {
			// if the request method and/or the MIME media type are not allowed, return.
			// Appropriate error message sent by {@code checkMethodMediaType}
			if (!checkMethodMediaType(req, res)) {
				return;
			}

			// if the requested resource was a question, delegate its processing and return
			if (processQuestion(req, res)) {
				return;
			}

			// if none of the above process methods succeeds, it means an unknow resource has been requested
			final Message m = new Message("Unknown resource requested.", "E4A6",
										  String.format("Requested resource is %s.", req.getRequestURI()));
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			m.toJSON(out);
		} finally {
			// ensure to always flush and close the output stream
			out.flush();
			out.close();
		}
	}

	/**
	 * Checks that the request method and MIME media type are allowed.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @return {@code true} if the request method and the MIME type are allowed; {@code false} otherwise.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	private boolean checkMethodMediaType(final HttpServletRequest req, final HttpServletResponse res)
			throws IOException {

		final String method = req.getMethod();
		final String contentType = req.getHeader("Content-Type");
		final String accept = req.getHeader("Accept");
		final OutputStream out = res.getOutputStream();

		Message m = null;

		if(accept == null) {
			m = new Message("Output media type not specified.", "E4A1", "Accept request header missing.");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			m.toJSON(out);
			return false;
		}

		if(!accept.contains(JSON_MEDIA_TYPE) && !accept.equals(ALL_MEDIA_TYPE)) {
			m = new Message("Unsupported output media type. Resources are represented only in application/json.",
							"E4A2", String.format("Requested representation is %s.", accept));
			res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			m.toJSON(out);
			return false;
		}

		switch(method) {
			case "GET":
			case "DELETE":
				// nothing to do
				break;

			case "POST":
			case "PUT":
				if(contentType == null) {
					m = new Message("Input media type not specified.", "E4A3", "Content-Type request header missing.");
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					m.toJSON(out);
					return false;
				}

				if(!contentType.contains(JSON_MEDIA_TYPE)) {
					m = new Message("Unsupported input media type. Resources are represented only in application/json.",
									"E4A4", String.format("Submitted representation is %s.", contentType));
					res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
					m.toJSON(out);
					return false;
				}

				break;
			default:
				m = new Message("Unsupported operation.",
								"E4A5", String.format("Requested operation %s.", method));
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				m.toJSON(out);
				return false;
		}

		return true;
	}


	/**
	 * Checks whether the request if for a {@link Question} resource and, in case, processes it.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @return {@code true} if the request was for an {@code User}; {@code false} otherwise.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */

	private boolean processQuestion(HttpServletRequest req, HttpServletResponse res) throws IOException {

		final String method = req.getMethod();
		final OutputStream out = res.getOutputStream();

		String path = req.getRequestURI();
		Message m = null;

		// the requested resource was not an user
		if(path.lastIndexOf("rest/questions") <= 0) {
			return false;
		}

		try {
			// strip everyhing until after the /questions
			path = path.substring(path.lastIndexOf("questions") + 9);

			// the request URI is: /questions
			if (path.length() == 0 || path.equals("/")) {

				switch (method) {
					case "GET":
						new QuestionRestResource(req, res, getDataSource().getConnection()).listAllQuestions();
						break;
					default:
						m = new Message("Unsupported operation for URI /questions.",
								"E4A5", String.format("Requested operation %s.", method));
						res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						m.toJSON(res.getOutputStream());
						break;
				}
			} // the request URI is: /questions/users/{user}
			else if (path.contains("users")) {
				path = path.substring(path.lastIndexOf("users") + 5);
				if (path.length() == 0 || path.equals("/")) {
					m = new Message("Wrong format for URI /questions/users/{user}: no {user} specified.",
						"E4A7", String.format("Requested URI: %s.", req.getRequestURI()));
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					m.toJSON(res.getOutputStream());
				} else {
					switch (method) {
						case "GET":
							new QuestionRestResource(req, res, getDataSource().getConnection()).searchUserQuestion();
							break;
							default:
								m = new Message("Unsupported operation for URI /questions/users/{user}.",
								"E4A5", String.format("Requested operation %s.", method));
								res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
								m.toJSON(res.getOutputStream());
					}
				}
			}
			// the request URI is: /questions/questionid/{question}
			else if (path.contains("questionid")) {
				path = path.substring(path.lastIndexOf("questionid") + 10);
				if (path.length() == 0 || path.equals("/")) {
					m = new Message("Wrong format for URI /questions/questionid/{question}: no {question} specified.",
							"E4A7", String.format("Requested URI: %s.", req.getRequestURI()));
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					m.toJSON(res.getOutputStream());
				}
				else{
					switch (method) {
						case "GET":
							new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestion();
							break;
						default:
							m = new Message("Unsupported operation for URI /questions/questionid/{question}.",
									"E4A5", String.format("Requested operation %s.", method));
							res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
							m.toJSON(res.getOutputStream());
					}
				}
			}

			// the request URI is: /questions/question/{question}
			else if (path.contains("question")) {
				path = path.substring(path.lastIndexOf("question") + 8);
				if (path.length() == 0 || path.equals("/")) {
					m = new Message("Wrong format for URI /questions/question/{question}: no {question} specified.",
							"E4A7", String.format("Requested URI: %s.", req.getRequestURI()));
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					m.toJSON(res.getOutputStream());
				}
				else{
					switch (method) {
						case "GET":
							new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestionAnswers();
							break;
						default:
							m = new Message("Unsupported operation for URI /questions/question/{question}.",
									"E4A5", String.format("Requested operation %s.", method));
							res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
							m.toJSON(res.getOutputStream());
					}
				}
			}

		} catch(Throwable t) {
			m = new Message("Unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}

		return true;

	}
}
