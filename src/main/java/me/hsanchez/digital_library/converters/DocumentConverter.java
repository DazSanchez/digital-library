package me.hsanchez.digital_library.converters;

import java.util.Map;

import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DeliveryTimeDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.DocumentTypeDTO;
import me.hsanchez.digital_library.dto.EditorialDTO;
import me.hsanchez.digital_library.dto.FormatDTO;
import me.hsanchez.digital_library.dto.GenreDTO;

public class DocumentConverter {
	public static DocumentDTO toDTO(Map<String, String> fields) {
		DocumentDTO document = new DocumentDTO();
		document.setTitle(fields.get("title"));
		document.setPrice(Double.parseDouble(fields.get("price")));
		document.setPageNumber(Integer.parseInt(fields.get("pageNumber"), 10));
		
		FormatDTO format = new FormatDTO();
		format.setId(Integer.parseInt(fields.get("format"), 10));
		document.setFormat(format);
		
		DeliveryTimeDTO deliveryTime = new DeliveryTimeDTO();
		deliveryTime.setTime(Integer.parseInt(fields.get("deliveryTime"), 10));
		deliveryTime.setUnit(fields.get("timeUnit"));
		document.setDeliveryTime(deliveryTime);
		
		AuthorDTO author = new AuthorDTO();
		author.setName(fields.get("authorName"));
		author.setFirstSurname(fields.get("authorFirstName"));
		author.setSecondSurname(fields.get("authorSecondName"));
		document.setAuthor(author);
		
		GenreDTO genre = new GenreDTO();
		genre.setName(fields.get("genre"));
		document.setGenre(genre);
		
		EditorialDTO editorial = new EditorialDTO();
		editorial.setName(fields.get("editorial"));
		document.setEditorial(editorial);
		
		DocumentTypeDTO documentType = new DocumentTypeDTO();
		documentType.setId(Integer.parseInt(fields.get("documentType"), 10));
		document.setDocumentType(documentType);
		
		return document;
	}
}
