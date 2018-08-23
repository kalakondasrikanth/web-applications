package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.User;

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
public final class ModifyUserDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "UPDATE leoforfriends.Actor SET username = ?, hashed_password = ?," +
			" name = ?, surname = ?, academic_title = ? WHERE username = ? RETURNING *";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The User to be updated in the database
	 */

	private enum certification{Yes, No}

	private final User user;

	public ModifyUserDatabase(final Connection con, final User user) {
		this.con = con;
		this.user = user;
	}

	public User updateUser() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the updated User
		User us = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getHashedpw());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getSurname());
			pstmt.setString(5, user.getAcademicTitle());
			pstmt.setString(6, user.getUsername());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String certifiedString = rs.getString("certified").toString();
				Boolean certified = false;
				if (certifiedString.equals("Yes"))
					certified = true;
				us = new User(rs.getString("email"), rs.getString("username"), rs.getString("hashed_password"),
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

		return us;
	}
}
