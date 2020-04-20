package me.hsanchez.digital_library.dto;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class EditorialDTO {
    private Integer id;
    private String name;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer editorialId) {
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
