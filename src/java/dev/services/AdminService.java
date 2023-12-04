/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import java.util.Calendar;
import dev.entity.Admin;
import dev.entity.Client;
import dev.entity.TransactionHistory;
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
    public void insert(String cpf, String name, String phone, String cep, String adress, 
                       String email, String password, Integer houseNumber, Date birthDate);
    public void update(String cpf, String name, String phone, String cep, String adress,
                       String email, String password, Integer houseNumber, Date birthDate, Status status);
    public void deleteById(Long id);
    public Admin getAdminByLogin(String cpf, String password);
    public ArrayList<Client> getInactiveClients();
    public ArrayList<Client> getActiveClients();
    public ArrayList<TransactionHistory> generateTransactionHistory(Long clientAccountId);
    public void activateClient(Long clientId, Integer bankNumber, String accountNumber);
    public ArrayList<TransactionHistory> getExtractById(Long id);
}