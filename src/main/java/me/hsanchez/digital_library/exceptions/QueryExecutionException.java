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

    public QueryExecutionException() {
        super("Por el momento no es posible conectar con los servicios.");
    }
    
}
