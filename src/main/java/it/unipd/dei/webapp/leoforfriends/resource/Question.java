package it.unipd.dei.webapp.leoforfriends.resource;


import com.fasterxml.jackson.core.*;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.io.*;

/**
 * Provides resources for representing and exchanging data about the main entities of the 
 * application. I represents a question made by a User.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public class Question extends Resource {


	/**
	 * The id of the question
	 */
	private final int id;

	/**
	 * The title of the question
	 */
	private final String title;

	/**
	 * The content of the question
	 */
	private final String content;

	/**
	 * The user who posted the question
	 */
	private final String actor;


	public Question(final int id, final String title, final String content, final String actor) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.actor = actor;
	}


	public final int getId() {
		return id;
	}

	public final String getTitle() {
		return title;
	}

	public final String getContent() {
		return content;
	}

	public final String getActor() {
		return actor;
	}


	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("question");

		jg.writeStartObject();

		jg.writeNumberField("id", id);

		jg.writeStringField("title", title);

		jg.writeStringField("content", content);

		jg.writeStringField("actor", actor);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	/**
	 * Creates a {@code Question} from its JSON representation.
	 *
	 * @param in the input stream containing the JSON document.
	 *
	 * @return the {@code Question} created from the JSON representation.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public static Question fromJSON(final InputStream in) throws IOException {

		// the fields read from JSON

		int jId = -1;
		String jTitle = null;
		String jContent = null;
		String jUser = null;


		final JsonParser jp = JSON_FACTORY.createParser(in);

		// while we are not on the start of an element or the element is not
		// a token element, advance to the next element (if any)
		while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "question".equals(jp.getCurrentName()) == false) {

			// there are no more events
			if (jp.nextToken() == null) {
				throw new IOException("Unable to parse JSON: no Question object found.");
			}
		}

		while (jp.nextToken() != JsonToken.END_OBJECT) {

			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

				switch (jp.getCurrentName()) {
					case "id":
						jp.nextToken();
						jId = jp.getIntValue();
						break;
					case "title":
						jp.nextToken();
						jTitle = jp.getText();
						break;
					case "content":
						jp.nextToken();
						jContent = jp.getText();
						break;
					case "actor":
						jp.nextToken();
						jUser = jp.getText();
						break;
				}
			}
		}
		return new Question(jId, jTitle, jContent, jUser);
	}
}
