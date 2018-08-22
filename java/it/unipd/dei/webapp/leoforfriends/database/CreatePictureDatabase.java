package it.unipd.dei.webapp.leoforfriends.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts a profile picture of a user in the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class CreatePictureDatabase
{
	/**
	 * The SQL statement to be executed
	 */

	private static final String STATEMENT = "INSERT INTO leoforfriends.Profile_picture (actor,photo) VALUES (?,?)";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The profile picture to be stored in the database
	 */
	private String user = null;
	private String photoURL = null;

	public CreatePictureDatabase(final Connection con, String user, String photoURL) 
	{
		this.con = con;
		this.user = user;
		this.photoURL = photoURL;
	}

	public void createPicture() throws SQLException 
	{
		PreparedStatement pstmt = null;
		try 
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, user);
			pstmt.setString(2, photoURL);
			pstmt.executeQuery();

		} 
		finally 
		{
			if (pstmt != null) 
			{
				pstmt.close();
			}
			con.close();
		}
	}
}