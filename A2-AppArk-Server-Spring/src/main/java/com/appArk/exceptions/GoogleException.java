/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appArk.exceptions;

/**
 *
 * @author Alvaro
 */
public class GoogleException extends RuntimeException {
    
    public GoogleException () {
        super();
    }
    
    public GoogleException (String msg) {
        super(msg);
    }
}
