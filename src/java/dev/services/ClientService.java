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
    
    public void save(String cpf, String nome, String telefone, 
                     String cep, String email, String password,
                     Integer numeroCasa, Calendar dataNascimento, Long contaId);
}
