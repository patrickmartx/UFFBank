/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import java.util.Calendar;
import dev.entity.Client;

/**
 *
 * @author Patrick
 */
public interface ClientService {
    
    public void save(String cpf, String name, String phone, 
                     String cep, String email, String password,
                     Integer houseNumber, Calendar birthDate, Long bankAccountId);
    
    public Client clientByCpf(String cpf);
    
    public Client Login(String cpf, String password);
}
