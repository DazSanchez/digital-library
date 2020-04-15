package me.hsanchez.digital_library.dao.queries;

public class AuthorQueries {
	public static final String GET_AUTHORS_BY_FULL_NAME = "SELECT ID id, NAME name, FIRST_SURNAME firstSurname, SECOND_SURNAME secondSurname "
			+ "FROM AUTHOR WHERE NAME LIKE ? AND FIRST_SURNAME LIKE ? AND SECOND_SURNAME LIKE ?";
	public static final String INSERT_AUTHOR = "INSERT INTO AUTHOR(NAME, FIRST_SURNAME, SECOND_SURNAME) VALUES(?,?,?)";
}
