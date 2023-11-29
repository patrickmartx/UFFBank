/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.entity.Client;
import dev.exceptions.NoConnectException;
import dev.model.complements.ClientRepository;
import dev.services.ClientService;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.exceptions.NoEntityFoundException;

/**
 *
 * @author Patrick
 */
public class ClientServiceImpl implements ClientService {
    private ClientRepository repository;
    
    public ClientServiceImpl() {
        this.repository = new ClientRepository();
    }

    @Override
    public Client getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Client> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(String name, String phone, String cep, String email, String password, Integer houseNumber, Date birthDate, Long idBankAccount, Status status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(String name, String phone, String cep, String email, String password, Integer houseNumber, Date birthDate, Long idBankAccount, Status status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Client getClientByLogin(String cpf, String password) {
        try {
            Client existingClient = new Client();
            existingClient = repository.getByLogin(cpf, password);
            if (!existingClient.equals(null)) {
                return existingClient;
            }
            else
                throw new NoEntityFoundException();
        } catch(Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw  new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }
    
    public Double getAccountBalance(){
        Double accountBalance = repository.getAccountBalance();
        return accountBalance;
    }
}
