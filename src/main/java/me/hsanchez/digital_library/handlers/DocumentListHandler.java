/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DeliveryTimeDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.GenreDTO;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentListHandler extends BeanListHandler<DocumentDTO> {
	
	public DocumentListHandler() {
		super(DocumentDTO.class);
	}
	
	@Override
	public List<DocumentDTO> handle(ResultSet rs) throws SQLException {
		DocumentDTO document = new DocumentDTO();
		document.setId(rs.getInt("id"));
		document.setTitle(rs.getString("title"));
		document.setPrice(rs.getDouble("price"));
		document.setPageNumber(rs.getInt("pageNumber"));
		
		AuthorDTO author = new AuthorDTO();
		author.setId(rs.getInt("authorId"));
		author.setName(rs.getString("name"));
		author.setFirstSurname(rs.getString("firstName"));
		author.setSecondSurname(rs.getString("secondSurname"));
		document.setAuthor(author);
		
		DeliveryTimeDTO deliveryTime = new DeliveryTimeDTO();
		deliveryTime.setId(rs.getInt("deliveryTimeId"));
		deliveryTime.setTime(rs.getInt("time"));
		deliveryTime.setUnit(rs.getString("unit"));
		document.setDeliveryTime(deliveryTime);
		
		GenreDTO genre = new GenreDTO();
		genre.setId(rs.getInt("genreId"));
		genre.setName(rs.getString("ganreName"));
		document.setGenre(genre);
		
		
		
		// TODO Auto-generated method stub
		return super.handle(rs);
	}

}
