package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.User;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Modifies a user in the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class ModifyPictureDatabase {

	/**
	 * The SQL statement to be executed
	 */

	private static final String STATEMENT = "UPDATE leoforfriends.Profile_picture SET photo = ? WHERE actor = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The User to be updated in the database
	 */


	private String user = null;
	private String photoURL = null;

	public ModifyPictureDatabase(final Connection con, String user, String photoURL) {
		this.con = con;
		this.user = user;
		this.photoURL = photoURL;
	}

	public void updatePicture() throws SQLException {

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, photoURL);
			pstmt.setString(2, user);
			
			pstmt.executeQuery();

		} finally {

			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
	}
}
