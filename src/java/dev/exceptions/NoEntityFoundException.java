/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.exceptions;

/**
 *
 * @author Patrick
 */
public class NoEntityFoundException extends RuntimeException {
    
    public NoEntityFoundException() {
        super();
    }
    
    public NoEntityFoundException(String msg) {
        super(msg);
    }
    
}
