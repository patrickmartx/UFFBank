/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.exceptions;

import java.io.IOException;
import java.sql.SQLException;



/**
 *
 * @author Patrick
 */
public class NoConnectException extends IOException {
    
    public NoConnectException () {
        super();
    }
    
    public NoConnectException (String message) {
        super(message);
    }
}
