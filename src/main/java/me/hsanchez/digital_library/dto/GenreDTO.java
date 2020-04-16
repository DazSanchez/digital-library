package me.hsanchez.digital_library.dto;

import java.math.BigInteger;

/**
 *
 * @author hsanchez
 */
public class GenreDTO {
    private BigInteger id;
    private String name;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger genreId) {
        this.id = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
