package it.unipd.dei.webapp.leoforfriends.resource;

import com.fasterxml.jackson.core.*;

import java.io.*;

/**
 * Provides resources for representing and exchanging data about the main entities of the 
 * application. It represents a User.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
public class User extends Resource {


	/**
	 * The email of the user
	 */
	private final String email;
	
	/**
	 * The username of the user
	 */
	private final String username;
	
	/**
	 * The hashed password of the user
	 */
	private final String hashedpw;
	
	/**
	 * The rating of the user
	 */
	private double rating;
	
	/**
	 * The name of the user
	 */
	private final String name;
	
	/**
	 * The surname of the user
	 */
	private final String surname;
	
	/**
	 * The academic title of the user
	 */
	private final String academicTitle;
	
	/**
	 * Whether the user is certified or not
	 */
	private final boolean certified;
	

	public User(final String email, final String username, final String hashedpw, final double rating, final String name, final String surname,
	final String academicTitle, final boolean certified) {
		this.email = email;
		this.username = username;
		this.hashedpw = hashedpw;
		this.rating = rating;
		this.name = name;
		this.surname = surname;
		this.academicTitle = academicTitle;
		this.certified = certified;
	}

	public User(final String username) {
		this.email = null;
		this.username = username;
		this.hashedpw = null;
		this.rating = 0;
		this.name = null;
		this.surname = null;
		this.academicTitle = null;
		this.certified = false;
	}


	public final String getEmail() {
		return email;
	}
	public final String getUsername() {
		return username;
	}
	public final String getHashedpw() {
		return hashedpw;
	}
	public final double getRating() {
		return rating;
	}
	public final String getName() {
		return name;
	}
	public final String getSurname() {
		return surname;
	}
	public final String getAcademicTitle() {
		return academicTitle;
	}
	public final boolean isCertified() {
		return certified;
	}


	@Override
	public final void toJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("user");

		jg.writeStartObject();

		jg.writeStringField("surname", email);

		jg.writeStringField("username", username);

		jg.writeStringField("hashedpw", hashedpw);

		jg.writeStringField("name", name);

		jg.writeStringField("surname", surname);

		jg.writeNumberField("rating", rating);

		jg.writeStringField("academicTitle", academicTitle);

		jg.writeBooleanField("certified", certified);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	/**
	 * Creates a {@code User} from its JSON representation.
	 *
	 * @param in the input stream containing the JSON document.
	 *
	 * @return the {@code User} created from the JSON representation.
	 *
	 * @throws IOException if something goes wrong while parsing.
	 */
	public static User fromJSON(final InputStream in) throws IOException {

		// the fields read from JSON
		
		String jEmail = null;
		String jUsername = null;
		String jHashedpw = null;
		double jRating = -1;
		String jName = null;
		String jSurname = null;
		boolean jCertified = false;
		String jAcademicTitle = null;

		

		final JsonParser jp = JSON_FACTORY.createParser(in);

		// while we are not on the start of an element or the element is not
		// a token element, advance to the next element (if any)
		while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "user".equals(jp.getCurrentName()) == false) {

			// there are no more events
			if (jp.nextToken() == null) {
				throw new IOException("Unable to parse JSON: no user object found.");
			}
		}

		while (jp.nextToken() != JsonToken.END_OBJECT) {

			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

				switch (jp.getCurrentName()) {
					case "email":
						jp.nextToken();
						jEmail = jp.getText();
						break;
					case "username":
						jp.nextToken();
						jUsername = jp.getText();
						break;
					case "hashedpw":
						jp.nextToken();
						jHashedpw = jp.getText();
						break;
					case "name":
						jp.nextToken();
						jName = jp.getText();
						break;
					case "surname":
						jp.nextToken();
						jSurname = jp.getText();
						break;
					case "rating":
						jp.nextToken();
						jRating = jp.getDoubleValue();
						break;
					case "academicTitle":
						jp.nextToken();
						jAcademicTitle = jp.getText();
						break;
					case "certified":
						jp.nextToken();
						jCertified = jp.getBooleanValue();
						break;
				}
			}
		}
		
		return new User(jEmail, jUsername, jHashedpw, jRating, jName, jSurname, jAcademicTitle, jCertified);
	}
}
