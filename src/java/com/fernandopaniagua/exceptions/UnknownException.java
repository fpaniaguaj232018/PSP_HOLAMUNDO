/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernandopaniagua.exceptions;

/**
 *
 * @author fernando.paniagua
 */
public class UnknownException extends Exception {
    public UnknownException(String mensaje){
        super(mensaje);
    }
}
