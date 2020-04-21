package me.hsanchez.digital_library.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DeliveryTimeDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.DocumentTypeDTO;
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
		document.setId(rs.getInt("id"));
		document.setTitle(rs.getString("title"));
		document.setPrice(rs.getDouble("price"));
		document.setPageNumber(rs.getInt("pageNumber"));
		document.setThumbnailUrl(rs.getString("thumbnailUrl"));
		
		AuthorDTO author = new AuthorDTO();
		author.setId(rs.getInt("authorId"));
		author.setName(rs.getString("name"));
		author.setFirstSurname(rs.getString("firstSurname"));
		author.setSecondSurname(rs.getString("secondSurname"));
		document.setAuthor(author);
		
		GenreDTO genre = new GenreDTO();
		genre.setId(rs.getInt("genreId"));
		genre.setName(rs.getString("genreName"));
		document.setGenre(genre);
		
		FormatDTO format = new FormatDTO();
		format.setId(rs.getInt("formatId"));
		format.setName(rs.getString("format"));
		document.setFormat(format);
		
		DeliveryTimeDTO deliveryTime = new DeliveryTimeDTO();
		deliveryTime.setId(rs.getInt("deliveryTimeId"));
		deliveryTime.setTime(rs.getInt("time"));
		deliveryTime.setUnit(rs.getString("unit"));
		document.setDeliveryTime(deliveryTime);
		
		EditorialDTO editorial = new EditorialDTO();
		editorial.setId(rs.getInt("editorialId"));
		editorial.setName(rs.getString("editorialName"));
		document.setEditorial(editorial);
		
		DocumentTypeDTO documentType = new DocumentTypeDTO();
		documentType.setId(rs.getInt("docTypeId"));
		documentType.setName("docTypeName");
		document.setDocumentType(documentType);
		
		return document;
	}
}
