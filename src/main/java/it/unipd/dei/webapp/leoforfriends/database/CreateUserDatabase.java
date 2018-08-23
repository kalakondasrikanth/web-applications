package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new User into the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class CreateUserDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO leoforfriends.Actor" +
			"(email, username, hashed_password, rating, name, surname, academic_title, certified)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?, CAST(? AS certification))";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The User to be stored into the database
	 */
	private final User user;


	private enum certification{Yes, No} // Required for insertion

	public CreateUserDatabase(final Connection con, final User user) {
		this.con = con;
		this.user = user;
	}

	/**
	 * Stores a new User into the database
	 * 
	 * @throws SQLException
	 *             if any error occurs while storing the User.
	 */
	public void createUser() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getHashedpw());
			pstmt.setDouble(4, user.getRating());
			pstmt.setString(5, user.getName());
			pstmt.setString(6, user.getSurname());
			pstmt.setString(7, user.getAcademicTitle());
			if (user.isCertified())
				pstmt.setString(8, certification.Yes.toString()) ;
			else
				pstmt.setString(8, certification.No.toString());
			

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}
}
