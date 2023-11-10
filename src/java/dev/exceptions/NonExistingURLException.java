/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.exceptions;

/**
 *
 * @author Patrick
 */
public class NonExistingURLException extends AssertionError {

    public NonExistingURLException() {
        super();
    }
    
    public NonExistingURLException(String messege) {
        super(messege);
    }
    
}
