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
public final class SearchUserDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM leoforfriends.Actor " +
			"WHERE username = ? AND hashed_password = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	private String actor = null;

	private String hashedpw = null;

	private enum certification{Yes, No}
	
	public SearchUserDatabase(final Connection con, final String actor, final String hashedpw) {
		this.con = con;
		this.actor = actor;
		this.hashedpw = hashedpw;
	}

	
	public User searchUser() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		User user = null;

		try {

			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, actor);
			pstmt.setString(2, hashedpw);


			rs = pstmt.executeQuery();
			while (rs.next()) {
				String certifiedString = certification.valueOf(rs.getString("certified")).toString();
				Boolean certified = false;
				if (certifiedString.equals("Yes"))
					certified = true;
				user = new User(rs.getString("email"), rs.getString("username"), rs.getString("hashed_password"),
						rs.getDouble("rating"), rs.getString("name"), rs.getString("surname"),
						rs.getString("academic_title"), certified);
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

		return user;
	}
}
