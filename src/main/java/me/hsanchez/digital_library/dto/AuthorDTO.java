/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.dto;

/**
 *
 * @author dell
 */
public class AuthorDTO {
    private Integer id;
    private String name;
    private String firstSurname;
    private String secondSurname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer authorId) {
        this.id = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
    
    public String getFullName() {
    	return String.format("%s %s %s", this.getName(), this.getFirstSurname(), this.getSecondSurname());
    }
}
