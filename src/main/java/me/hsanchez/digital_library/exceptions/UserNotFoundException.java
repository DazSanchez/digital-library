/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.exceptions;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class UserNotFoundException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9072772960764919771L;

	public UserNotFoundException(String username) {
        super("No se encontró el usuario: " + username);
    }
    
}
