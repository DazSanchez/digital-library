package me.hsanchez.digital_library.handlers;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DeliveryTimeDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.EditorialDTO;
import me.hsanchez.digital_library.dto.FormatDTO;
import me.hsanchez.digital_library.dto.GenreDTO;

public class DocumentHandler extends BeanHandler<DocumentDTO> {
	public DocumentHandler() {
		super(DocumentDTO.class);
	}
	
	@Override
	public DocumentDTO handle(ResultSet rs) throws SQLException {
		if(!rs.next()) return null;
		
		DocumentDTO document = new DocumentDTO();
		document.setId(BigInteger.valueOf(rs.getLong("id")));
		document.setTitle(rs.getString("title"));
		document.setPrice(rs.getDouble("price"));
		document.setPageNumber(rs.getInt("pageNumber"));
		document.setThumbnailUrl(rs.getString("thumbnailUrl"));
		
		AuthorDTO author = new AuthorDTO();
		author.setName(rs.getString("name"));
		author.setFirstSurname(rs.getString("firstSurname"));
		author.setSecondSurname(rs.getString("secondSurname"));
		document.setAuthor(author);
		
		GenreDTO genre = new GenreDTO();
		genre.setName(rs.getString("genreName"));
		document.setGenre(genre);
		
		FormatDTO format = new FormatDTO();
		format.setName(rs.getString("format"));
		document.setFormat(format);
		
		DeliveryTimeDTO deliveryTime = new DeliveryTimeDTO();
		deliveryTime.setTime(rs.getInt("time"));
		deliveryTime.setUnit(rs.getString("unit"));
		document.setDeliveryTime(deliveryTime);
		
		EditorialDTO editorial = new EditorialDTO();
		editorial.setName(rs.getString("editorialName"));
		document.setEditorial(editorial);
		
		return document;
	}
}
