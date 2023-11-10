/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.entity.Client;
import dev.model.complements.ClientRepository;
import dev.services.ClientService;
import java.util.Calendar;

/**
 *
 * @author Patrick
 */
public class ClientServiceImpl implements ClientService{
    
    private final ClientRepository clientRepository;
    
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(String cpf, String nome, String telefone, 
            String cep, String email, String password, 
            Integer numeroCasa, Calendar dataNascimento, Long contaId) {
        
        try {
            Client client = new Client();

            if (cpf.length() < 11) {
                throw new IllegalArgumentException("CPF inválido!");
            }
            client.setCpf(cpf);
            client.setNome(nome);
            if (cpf.length() < 11) {
                throw new IllegalArgumentException("telefone inválido!");
            }
            client.setTelefone(telefone);
            client.setCep(cep);
            client.setEmail(email);
            client.setPassword(password);
            client.setNumeroCasa(numeroCasa);
            client.setDataNascimento(dataNascimento);
            client.setConta(contaId);

            clientRepository.insertClient(client);
        } catch(Exception e) {
            System.out.println("Não foi possível criar Clente.\n messege: " + e.getMessage());
        }
    }
    
}
