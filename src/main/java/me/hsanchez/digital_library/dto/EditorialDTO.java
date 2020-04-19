package me.hsanchez.digital_library.dto;

import java.math.BigInteger;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class EditorialDTO {
    private BigInteger id;
    private String name;
    
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger editorialId) {
		this.id = editorialId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public String toString() {
    	return this.name;
    }
}
