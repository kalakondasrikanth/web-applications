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
public final class RemovePictureDatabase
{
	/**
	 * The SQL statement to be executed
	 */

	private static final String STATEMENT = "DELETE FROM leoforfriends.Profile_picture WHERE actor = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The profile picture to be stored in the database
	 */
	private String user = null;
	private String photoURL = null;

	public RemovePictureDatabase(final Connection con, String user)
	{
		this.con = con;
		this.user = user;
	}

	public void removePicture() throws SQLException
	{
		PreparedStatement pstmt = null;
		try 
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, user);
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