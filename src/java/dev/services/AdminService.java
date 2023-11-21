/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import java.util.Calendar;
import dev.entity.Admin;

/**
 *
 * @author Patrick
 */
public interface AdminService {
    
    public void save(String cpf, String name, String phone, 
            String cep, String email, String password, 
            Integer houseNumber, Calendar birthDate, String status);
    
    public Admin findById(Long id);
    
    public Admin findByCpf(String Cpf);
    
    public void activateClient(Long id, Integer bankNumber, String accountNumber);
    
    public Admin login(String cpf, String password);
    
}
