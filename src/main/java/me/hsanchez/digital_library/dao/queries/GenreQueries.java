package me.hsanchez.digital_library.dao.queries;

public class GenreQueries {
	public static final String GET_GENRES_BY_NAME = "SELECT ID id, NAME name FROM CAT_GENRE WHERE NAME LIKE ?";
	public static final String INSERT_GENRE = "INSERT INTO CAT_GENRE(NAME) VALUES(?)";
	public static final String UPDATE_GENRE = "UPDATE CAT_GENRE SET NAME = ? WHERE ID = ?";
}
