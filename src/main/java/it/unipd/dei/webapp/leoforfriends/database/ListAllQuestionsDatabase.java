
package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lists all the Questions in the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class ListAllQuestionsDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT question_number, title, content, actor FROM leoforfriends.Question order by question_number desc";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	public ListAllQuestionsDatabase(final Connection con) {
		this.con = con;
	}

	public List<Question> listQuestions() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Question> questions = new ArrayList<Question>();

		try {
			pstmt = con.prepareStatement(STATEMENT);

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
