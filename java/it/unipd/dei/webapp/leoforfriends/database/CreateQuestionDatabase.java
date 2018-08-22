package it.unipd.dei.webapp.leoforfriends.database;

import it.unipd.dei.webapp.leoforfriends.resource.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new Question into the database
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class CreateQuestionDatabase 
{
	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO leoforfriends.Question" +
			"(title,content,actor) VALUES (?, ?, ?)";

	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The Question to be stored into the database
	 */
	private final Question question;

	public CreateQuestionDatabase(final Connection con, final Question question) 
	{
		this.con = con;
		this.question = question;
	}

	/**
	 * Stores a new Question into the database
	 * 
	 * @throws SQLException
	 *             if any error occurs while storing the Question.
	 */
	public Question createQuestion() throws SQLException 
	{
		PreparedStatement pstmt = null;
		Question questions = null;
		try 
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, question.getTitle());
			pstmt.setString(2, question.getContent());
			pstmt.setString(3, question.getActor());

			pstmt.execute();
			questions = new SearchQuestionIdDatabase(con,question).searchQuestionId();

		} 
		finally 
		{

			if (pstmt != null) 
			{
				pstmt.close();
			}
			con.close();
		}
		return questions;
	}
}