/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package me.hsanchez.digital_library.dto;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class UserDTO {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
