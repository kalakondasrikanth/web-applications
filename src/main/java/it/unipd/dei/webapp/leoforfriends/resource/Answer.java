package it.unipd.dei.webapp.leoforfriends.resource;


import com.fasterxml.jackson.core.*;

import java.io.*;

/**
 * Provides resources for representing and exchanging data about the main entities of the 
 * application. It represents an answer made to an user to a question.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public class Answer extends Resource {


	/**
	 * The question the answer answers
	 */
	private final int questionid;
	
	/**
	 * The user who posted the question
	 */
	private final String actor;
	
	/**
	 * The content of the answer
	 */
	private final String content;
	
	
	

	public Answer(final int questionid, final String user, final String content) {
		this.questionid = questionid;
		this.actor = user;
		this.content = content;
	}


	public final int getQuestionid() {
		return questionid;
	}
	public final String getActor() {
		return actor;
	}
	public final String getContent() {
		return content;
	}
	


	

	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("answer");

		jg.writeStartObject();

		jg.writeNumberField("questionid", questionid);

		jg.writeStringField("user", actor);

		jg.writeStringField("content", content);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	/**
	 * Creates a {@code Answer} from its JSON representation.
	 *
	 * @param in the input stream containing the JSON document.
	 *
	 * @return the {@code Answer} created from the JSON representation.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public static Answer fromJSON(final InputStream in) throws IOException {

		// the fields read from JSON
		
		int jQuestionid = -1;
		String jUser = null;
		String jContent = null;
		
		

		final JsonParser jp = JSON_FACTORY.createParser(in);

		// while we are not on the start of an element or the element is not
		// a token element, advance to the next element (if any)
		while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "answer".equals(jp.getCurrentName()) == false) {

			// there are no more events
			if (jp.nextToken() == null) {
				throw new IOException("Unable to parse JSON: no question object found.");
			}
		}

		while (jp.nextToken() != JsonToken.END_OBJECT) {

			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

				switch (jp.getCurrentName()) {
					case "questionid":
						jp.nextToken();
						jQuestionid = jp.getIntValue();
						break;
					case "user":
						jp.nextToken();
						jUser = jp.getText();
						break;
					case "content":
						jp.nextToken();
						jContent = jp.getText();
						break;
					
				}
			}
		}
		
		return new Answer(jQuestionid,jUser,jContent);
	}
}
