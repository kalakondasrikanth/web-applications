package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.Question;
import it.unipd.dei.webapp.leoforfriends.resource.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches all the Answers to a question in the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class ListQuestionAnswersDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT question, actor, content " +
			"FROM leoforfriends.Answer WHERE question = ?";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	private final int questionid;

	
	public ListQuestionAnswersDatabase(final Connection con, final int questionid) {
		this.con = con;
		this.questionid = questionid;
	}

	
	public List<Answer> searchQuestionAnswers() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Answer> answers = new ArrayList<Answer>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, questionid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				answers.add(new Answer(rs.getInt("question"), rs
						.getString("actor"), rs.getString("content")));
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

		return answers;
	}
}
