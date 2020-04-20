package me.hsanchez.digital_library.dto;

/**
 *
 * @author hsanchez
 */
public class GenreDTO {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer genreId) {
		this.id = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
