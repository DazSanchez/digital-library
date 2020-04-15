package me.hsanchez.digital_library.dao.queries;

public class DocumentQueries {
	public static final String GET_DOCUMENTS = "SELECT d.ID id, TITLE title, PRICE price,"
			+ "a.NAME name, a.FIRST_SURNAME firstSurname, a.SECOND_SURNAME secondSurname,"
			+ "g.NAME genreName FROM DOCUMENT d INNER JOIN AUTHOR a ON d.AUTHOR_ID = a.ID "
			+ "INNER JOIN CAT_GENRE g ON d.GENRE_ID = g.ID "
			+ "WHERE d.DOCUMENT_TYPE_ID = ? AND d.TITLE LIKE ? LIMIT ?, ?";
	public static final String INSERT_DOCUMENT = "INSERT INTO DOCUMENT(TITLE, PRICE, PAGE_NUMBER, AUTHOR_ID, GENRE_ID, DELIVERY_TIME_ID, EDITORIAL_ID, FORMAT_ID, DOCUMENT_TYPE_ID, THUMBNAIL_URL) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_DOCUMENT_BY_ID = "SELECT d.ID id, TITLE title, PRICE price, PAGE_NUMBER pageNumber,"
			+ "AUTHOR_ID authorId, a.NAME name, a.FIRST_SURNAME firstSurname, a.SECOND_SURNAME secondSurname,"
			+ "GENRE_ID genreId, g.NAME genreName, f.NAME format,"
			+ "DELIVERY_TIME_ID deliveryTimeId, dt.`TIME` `time`, dt.UNIT unit,"
			+ "e.ID editorialId, e.NAME editorialName FROM DOCUMENT d "
			+ "INNER JOIN AUTHOR a ON d.AUTHOR_ID = a.ID INNER JOIN CAT_GENRE g ON d.GENRE_ID = g.ID "
			+ "INNER JOIN CAT_FORMAT f ON d.FORMAT_ID = f.ID "
			+ "INNER JOIN DELIVERY_TIME dt ON d.DELIVERY_TIME_ID = dt.ID "
			+ "INNER JOIN EDITORIAL e ON d.EDITORIAL_ID = e.ID "
			+ "WHERE d.ID = ?";
}
