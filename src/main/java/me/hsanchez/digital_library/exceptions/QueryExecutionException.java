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
public class QueryExecutionException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6565116226609022874L;
	
	private String technicalReason;

	public QueryExecutionException() {
        super("Por el momento no es posible conectar con los servicios.");
    }
	
	public QueryExecutionException(String message, String technicalReason) {
		super(message);
		this.technicalReason = technicalReason;
	}
	
	public String getTechnicalReason() {
		return technicalReason;
	}
    
}
