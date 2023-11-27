/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import java.util.Calendar;
import dev.entity.Admin;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public interface AdminService {
    
    public Admin getById(Long id);
    public ArrayList<Admin> getAll();
    public void insert(String name, String phone, String cep, 
                       String email, String password, Integer houseNumber, Date birthDate, Status status);
    public void update(String name, String phone, String cep, 
                       String email, String password, Integer houseNumber, Date birthDate, Status status);
    public void deleteById(Long id);
}