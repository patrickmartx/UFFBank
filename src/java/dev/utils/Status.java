/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.utils;

/**
 *
 * @author Patrick
 */
public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DISACTIVATE("DISACTIVATE");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

