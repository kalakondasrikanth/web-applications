/*
 * Copyright 2018 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.unipd.dei.webapp.leoforfriends.rest;

import it.unipd.dei.webapp.leoforfriends.database.*;
import it.unipd.dei.webapp.leoforfriends.resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manages the REST API for the {@link Question} resource.
 * 
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public final class QuestionRestResource extends RestResource {

	/**
	 * Creates a new REST resource for managing {@code Question} resources.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @param con the connection to the database.
	 */
	public QuestionRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(req, res, con);
	}

	/**
	 * Searches an question from the database.
	 *
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void searchUserQuestion() throws IOException {

		List<Question> questions  = null;
		Message m = null;

		try{
			// parse the URI path to extract the badge
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("users") + 6);

			final String user = path;


			// creates a new object for accessing the database and reads the question
			questions = new ListUserQuestionsDatabase(con, user).searchUserQuestion();

			if(questions != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(questions).toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("Question %d not found.", user), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot read question: unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}

	/**
	 * Lists all the questions.
	 *
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void listAllQuestions() throws IOException {

		List<Question> questions  = null;
		Message m = null;

		try{
			// creates a new object for accessing the database and lists all the questions
			questions = new ListAllQuestionsDatabase(con).listQuestions();

			if(questions != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(questions).toJSON(res.getOutputStream());
			} else {
				// it should not happen
				m = new Message("Cannot list questions: unexpected error.", "E5A1", null);
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot list questions: unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}

	public List<Answer> searchQuestionAnswers() throws IOException {

		List<Answer> answers  = null;
		Message m = null;

		try{
			// parse the URI path to extract the badge
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("question") + 9);

			final int questionid = Integer.parseInt(path);


			// creates a new object for accessing the database and reads the answers
			answers = new ListQuestionAnswersDatabase(con, questionid).searchQuestionAnswers();

			if(answers != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(answers).toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("Answers not found for question %d.", questionid), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot read question: unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
		return answers;
	}

	public List<Question> searchQuestion() throws IOException {

		List<Question> questions  = null;
		Message m = null;

		try{
			// parse the URI path to extract the badge
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("questionid") + 11);

			final int questionid = Integer.parseInt(path);


			// creates a new object for accessing the database and reads the answers
			questions = new SearchQuestionByIdDatabase(con, questionid).searchQuestionById();

			if(questions != null) {
				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(questions).toJSON(res.getOutputStream());
			} else {
				m = new Message(String.format("Cannot find a question with id number = %d.", questionid), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (Throwable t) {
			m = new Message("Cannot read question: unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
		return questions;
	}

}
