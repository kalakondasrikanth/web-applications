package it.unipd.dei.webapp.leoforfriends.servlet;

import com.sun.webkit.dom.NamedNodeMapImpl;
import it.unipd.dei.webapp.leoforfriends.database.SearchPictureDatabase;
import it.unipd.dei.webapp.leoforfriends.database.SearchUserDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.Message;
import it.unipd.dei.webapp.leoforfriends.resource.User;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Verifies whether the client has the authorization to view the selected content
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

public final class AuthenticationServlet extends AbstractDatabaseServlet implements Filter {

/**
 * Effectively verifies whether the client has the authorization to view the selected content
 *
 * @param req
 *            the HTTP request from the client.
 * @param res
 *            the HTTP response from the server.
 * @param chainObj
 * 			  required to complete the filtering
 *
 * @throws ServletException
 *             if any error occurs while executing the servlet.
 * @throws IOException
 *             if any error occurs in the client/server communication.
 */

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chainObj) throws IOException, ServletException {
		String appPath = req.getServletContext().getRealPath("");
		String pathDir ="images/profile/";
		
		Message message = null;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		// If there is no session or the required parameters are missing, require to log in or register
		if (session == null || session.getAttribute("actor") == null || session.getAttribute("hashedpw") == null) {
				message = new Message("You must be logged in to view this content.");
				request.getRequestDispatcher("/jsp/register-login-redirect.jsp").forward(request, response);
		}

		else {
			try {
				// Verifying whether the provided credentials are valid
				User user = new SearchUserDatabase(getDataSource().getConnection(), (String) session.getAttribute("actor"),
						(String) session.getAttribute("hashedpw")).searchUser();

				// If the user is not found, redirect
				if (user == null) {
						message = new Message("Authentication failed: you must be logged in to access this content!",
								"E401", "Unauthorized");
						request.getRequestDispatcher("/jsp/register-login-redirect.jsp").forward(request, response);
				}
				//String url = new SearchPictureDatabase(getDataSource().getConnection(),user.getUsername()).searchPicture();
				String url = "/leoforfriends-1.00/images/profile/" + user.getUsername() + ".jpg";
				String userPicture = appPath + pathDir + user.getUsername()+".jpg";
				File f = new File(userPicture);
				if(!f.exists())
				{
					url = "/leoforfriends-1.00/images/profile/default/default.jpg";
				}
				request.setAttribute("user", user);
				request.setAttribute("url", url);
				chainObj.doFilter(request, response);

			}
			catch (SQLException ex){
				message = new Message("Cannot authenticate: unexpected error while accessing the database.",
						"E200", ex.getMessage());
			}
		}
	}
}