package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new Answer into the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class CreateAnswerDatabase 
{
	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO leoforfriends.Answer" +
			"(question, actor, content) VALUES (?, ?, ?)";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The Answer to be stored into the database
	 */
	private final Answer answer;

	public CreateAnswerDatabase(final Connection con, final Answer answer) 
	{
		this.con = con;
		this.answer = answer;
	}

	/**
	 * Stores a new Answer into the database
	 * 
	 * @throws SQLException
	 *             if any error occurs while storing the Answer.
	 */
	public void createAnswer() throws SQLException 
	{
		PreparedStatement pstmt = null;

		try 
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, answer.getQuestionid());
			pstmt.setString(2, answer.getActor());
			pstmt.setString(3, answer.getContent());

			pstmt.execute();

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