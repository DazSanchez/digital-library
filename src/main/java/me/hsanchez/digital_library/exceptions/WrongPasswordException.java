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
public class WrongPasswordException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3855291654736174085L;

	public WrongPasswordException() {
        super("La contraseña es incorrecta");
    }
    
}
