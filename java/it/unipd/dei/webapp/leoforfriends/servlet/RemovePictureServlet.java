package it.unipd.dei.webapp.leoforfriends.servlet;

import it.unipd.dei.webapp.leoforfriends.database.CreatePictureDatabase;
import it.unipd.dei.webapp.leoforfriends.database.RemovePictureDatabase;
import it.unipd.dei.webapp.leoforfriends.database.SearchPictureDatabase;
import it.unipd.dei.webapp.leoforfriends.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Modifies an user.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */

@MultipartConfig(maxFileSize = 16177215)
public final class RemovePictureServlet extends AbstractDatabaseServlet {

	/**
	 * Modifies or inserts a profile picture
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
		String user = null;
		String pathDir ="images/profile/";
		String filePath = null;
		String url = null;
		String filename = null;
		// model
		Message message = null;
		String appPath = req.getServletContext().getRealPath("");
		try{
			user = (String) req.getSession(false).getAttribute("actor");
			
			filePath = appPath + pathDir + user +".jpg";
			File f = new File(filePath);
			
			if (f.exists())
			{
				f.delete();
				new RemovePictureDatabase(getDataSource().getConnection(),user).removePicture();
				/*
				if (url.isEmpty()  || url.equalsIgnoreCase(""))
				{
					new CreatePictureDatabase(getDataSource().getConnection(), user, filename).createPicture();
				}
				else
				{
					new ModifyPictureDatabase(getDataSource().getConnection(), user, filename).updatePicture();
				}
				*/
			}
			
			else {
					message = new Message("Something went wrong",
							"E400", "Bad Request");
					req.setAttribute("message", message);
					req.getRequestDispatcher("/jsp/login-result.jsp").forward(req, res);
			}



		} catch (SQLException ex) {
				message = new Message("Cannot upload the picture: unexpected error while accessing the database.",
						"E200", ex.getMessage());
		}
		
		// stores the user and the message as a request attribute
		req.setAttribute("message", message);
		
		// forwards the control to the user-detail JSP
		req.getRequestDispatcher("/jsp/user-modify.jsp").forward(req, res);
	}

}
