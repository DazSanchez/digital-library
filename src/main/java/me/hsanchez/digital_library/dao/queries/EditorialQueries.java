package me.hsanchez.digital_library.dao.queries;

public class EditorialQueries {
	public static final String GET_EDITORIALS_BY_NAME = "SELECT ID id, NAME name FROM EDITORIAL WHERE NAME LIKE ?";
	public static final String INSERT_EDITORIAL = "INSERT INTO EDITORIAL(NAME) VALUES(?)";
}
