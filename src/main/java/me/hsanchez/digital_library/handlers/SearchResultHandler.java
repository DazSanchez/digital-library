package me.hsanchez.digital_library.handlers;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.GenreDTO;

public class SearchResultHandler extends BeanListHandler<DocumentDTO> {
	public SearchResultHandler() {
		super(DocumentDTO.class);
	}

	@Override
	public List<DocumentDTO> handle(ResultSet rs) throws SQLException {
		List<DocumentDTO> documents = new ArrayList<DocumentDTO>(rs.getFetchSize());
		
		while(rs.next()) {
			DocumentDTO document = new DocumentDTO();
			document.setId(BigInteger.valueOf(rs.getLong("id")));
			document.setTitle(rs.getString("title"));
			document.setPrice(rs.getDouble("price"));
			
			AuthorDTO author = new AuthorDTO();
			author.setName(rs.getString("name"));
			author.setFirstSurname(rs.getString("firstSurname"));
			author.setSecondSurname(rs.getString("secondSurname"));
			document.setAuthor(author);
			
			GenreDTO genre = new GenreDTO();
			genre.setName(rs.getString("genreName"));
			document.setGenre(genre);
			
			documents.add(document);
		}
		
		return documents;
	}
}
