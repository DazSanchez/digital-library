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
public class AuthenticationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2028847711282851419L;

	public AuthenticationException(String message) {
        super(message);
    }
}
