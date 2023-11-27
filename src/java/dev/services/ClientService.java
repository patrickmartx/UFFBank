/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import dev.entity.Client;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public interface ClientService {
    
    public Client getById(Long id);
    public ArrayList<Client> getAll();
    public void insert(String name, String phone, String cep, 
                       String email, String password, Integer houseNumber, 
                       Date birthDate, Long idBankAccount, Status status);
    public void update(String name, String phone, String cep, 
                       String email, String password, Integer houseNumber, 
                       Date birthDate, Long idBankAccount, Status status);
    public void deleteById(Long id);
}
