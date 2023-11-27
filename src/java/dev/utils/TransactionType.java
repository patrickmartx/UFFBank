/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.utils;

/**
 *
 * @author Patrick
 */
public enum TransactionType {
    TRANSFER("TRANSFER"),
    DEPOSIT("DEPOSIT"),
    INVESTMENT("INVESTMENT");
    
    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
