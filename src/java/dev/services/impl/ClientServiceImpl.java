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
public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class.getName());

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
            if (phone.length() < 11) {
                throw new IllegalArgumentException("Telefone inválido!");
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
        } catch (NoConnectException | IllegalArgumentException ex) {
            LOGGER.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, 
                    "Não foi possível criar Cliente. Mensagem: {0}", ex.getMessage());
        }
    }
    
    @Override
    public Client findById(Long id) {
        try {
            return repository.findById(id);
        } catch (NoConnectException ex) {
            LOGGER.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, 
                    "Erro ao buscar cliente por Id. Mensagem: {0}", ex.getMessage());
        }
        return null;
    }
    
    @Override
    public Client findByCpf(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (NoConnectException ex) {
            LOGGER.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, 
                    "Erro ao buscar cliente por CPF. Mensagem: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Client login(String cpf, String password) {
        try {
            return repository.Login(cpf, password);
        } catch (Exception ex) {
            LOGGER.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE,
                    "Erro durante o login do cliente. {0}", ex.getMessage());
        }
        return null;
    }
}
