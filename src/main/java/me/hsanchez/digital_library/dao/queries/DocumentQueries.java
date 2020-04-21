package me.hsanchez.digital_library.dao.queries;

public class DocumentQueries {
	public static final String GET_DOCUMENTS = "SELECT d.ID id, TITLE title, PRICE price, THUMBNAIL_URL thumbnailUrl,"
			+ "a.NAME name, a.FIRST_SURNAME firstSurname, a.SECOND_SURNAME secondSurname,"
			+ "g.NAME genreName "
			+ "FROM DOCUMENT d "
			+ "INNER JOIN AUTHOR a ON d.AUTHOR_ID = a.ID "
			+ "INNER JOIN CAT_GENRE g ON d.GENRE_ID = g.ID "
			+ "WHERE d.DOCUMENT_TYPE_ID = ? AND d.TITLE LIKE ? LIMIT ?, ?";
	public static final String COUNT_DOCUMENTS = "SELECT COUNT(*) FROM DOCUMENT WHERE DOCUMENT_TYPE_ID = ? AND TITLE LIKE ?";
	public static final String INSERT_DOCUMENT = "INSERT INTO DOCUMENT(TITLE, PRICE, PAGE_NUMBER, AUTHOR_ID, GENRE_ID, DELIVERY_TIME_ID, EDITORIAL_ID, FORMAT_ID, DOCUMENT_TYPE_ID, THUMBNAIL_URL) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_DOCUMENT_BY_ID = "SELECT d.ID id, TITLE title, PRICE price, PAGE_NUMBER pageNumber, THUMBNAIL_URL thumbnailUrl,"
			+ "a.ID authorId, a.NAME name, a.FIRST_SURNAME firstSurname, a.SECOND_SURNAME secondSurname,"
			+ "g.ID genreId, g.NAME genreName,"
			+ "f.ID formatId, f.NAME format,"
			+ "dt.ID deliveryTimeId, dt.`TIME` `time`, dt.UNIT unit,"
			+ "e.ID editorialId, e.NAME editorialName,"
			+ "cdt.ID docTypeId, cdt.NAME docTypeName "
			+ "FROM DOCUMENT d "
			+ "INNER JOIN AUTHOR a ON d.AUTHOR_ID = a.ID "
			+ "INNER JOIN CAT_GENRE g ON d.GENRE_ID = g.ID "
			+ "INNER JOIN CAT_FORMAT f ON d.FORMAT_ID = f.ID "
			+ "INNER JOIN DELIVERY_TIME dt ON d.DELIVERY_TIME_ID = dt.ID "
			+ "INNER JOIN EDITORIAL e ON d.EDITORIAL_ID = e.ID "
			+ "INNER JOIN CAT_DOCUMENT_TYPE cdt ON d.DOCUMENT_TYPE_ID = cdt.ID "
			+ "WHERE d.ID = ?";
	public static final String DELETE_DOCUMENT_BY_ID = "DELETE FROM DOCUMENT WHERE ID = ?";
	public static final String GET_DELIVERY_TIME_BY_DOCUMENT_ID = "SELECT DELIVERY_TIME_ID FROM DOCUMENT WHERE ID = ?";
	public static final String GET_DOCUMENT_TITLE_BY_ID = "SELECT TITLE FROM DOCUMENT WHERE ID = ?";
	public static String updateBasicData(boolean hasNewThumbnail) {
		return "UPDATE DOCUMENT SET TITLE = ?, PRICE = ?, PAGE_NUMBER = ?, FORMAT_ID = ?, DOCUMENT_TYPE_ID = ?"
				+ (hasNewThumbnail ? ", THUMBNAIL_URL = ? " : " ") 
				+ "WHERE ID = ?";
	}
}
