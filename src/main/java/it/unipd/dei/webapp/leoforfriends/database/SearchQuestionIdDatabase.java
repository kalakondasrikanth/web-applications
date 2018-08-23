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
public final class SearchQuestionIdDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM leoforfriends.Question " +
			"WHERE title = ? AND content = ? AND actor = ? ORDER BY question_number DESC";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	private Question question;

	
	public SearchQuestionIdDatabase(final Connection con, Question question) {
		this.con = con;
		this.question = question;
	}

	
	public Question searchQuestionId() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		Question questions = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, question.getTitle());
			pstmt.setString(2, question.getContent());
			pstmt.setString(3, question.getActor());

			rs = pstmt.executeQuery();

			if(rs.next()){
				questions = new Question(rs.getInt("question_number"), rs
						.getString("title"), rs.getString("content"),
						rs.getString("actor"));
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
