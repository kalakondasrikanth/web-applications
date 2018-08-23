package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches a qQestion in the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class SearchQuestionDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM leoforfriends.Question " +
			"WHERE title ILIKE ? OR content ILIKE ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	private final String query;

	
	public SearchQuestionDatabase(final Connection con, final String query) {
		this.con = con;
		this.query = "%" +query + "%";
	}

	
	public List<Question> searchQuestion() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Question> questions = new ArrayList<Question>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, query);
			pstmt.setString(2, query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				questions.add(new Question(rs.getInt("question_number"), rs
						.getString("title"), rs.getString("content"),
						rs.getString("actor")));
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

		return questions;
	}
}
