package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Searches a User in the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class SearchPictureDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM leoforfriends.Profile_picture " +
			"WHERE actor = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	private String actor = null;

	private String url = null;

	private enum certification{Yes, No}

	public SearchPictureDatabase(final Connection con, final String actor) {
		this.con = con;
		this.actor = actor;
	}

	
	public String searchPicture() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		User user = null;

		try {

			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, actor);
			
			rs = pstmt.executeQuery();
			url = null;
			while (rs.next()) {
				url = rs.getString("photo");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

		return url;
	}
}
