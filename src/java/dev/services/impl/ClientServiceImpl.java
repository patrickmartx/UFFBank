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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class ClientServiceImpl implements ClientService{
    
    private final ClientRepository repository;
    
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.repository = clientRepository;
    }

    @Override
    public void save(String cpf, String name, String phone, 
            String cep, String email, String password, 
            Integer houseNumber, Calendar birthDate, Long bankAccountId) {
        try {
            Client client = new Client();

            if (cpf.length() < 11) {
                throw new IllegalArgumentException("CPF inválido!");
            }
            client.setCpf(cpf);
            client.setName(name);
            if (cpf.length() < 11) {
                throw new IllegalArgumentException("telefone inválido!");
            }
            client.setPhone(phone);
            client.setCep(cep);
            client.setEmail(email);
            client.setPassword(password);
            client.setHouseNumber(houseNumber);
            client.setBirthDate(birthDate);
            client.setConta(bankAccountId);
            client.setStatus(Status.INACTIVE);

            repository.insertClient(client);
        } catch(Exception e) {
            System.out.println("Não foi possível criar Clente.\n messege: " + e.getMessage());
        }
    }
    
    @Override
    public Client clientByCpf(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Client Login(String cpf, String password) {
        try {
            return repository.Login(cpf, password);
        }
        catch(Exception ex){
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
