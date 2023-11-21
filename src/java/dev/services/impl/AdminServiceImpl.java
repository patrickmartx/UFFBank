/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.model.complements.AdminRepository;
import dev.services.AdminService;
import java.util.Calendar;
import dev.entity.Admin;
import dev.exceptions.NoConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.services.BankAccountService;
import dev.utils.Status;
import java.sql.SQLException;

/**
 *
 * @author Patrick
 */
public class AdminServiceImpl implements AdminService {

    AdminRepository repository;
    
    BankAccountService bankService;

    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(String cpf, String name, String phone,
            String cep, String email, String password,
            Integer houseNumber, Calendar birthDate, String status) {
        try {
            Admin admin = new Admin();

            if (cpf.length() < 11) {
                throw new IllegalArgumentException("CPF inválido!");
            }
            admin.setCpf(cpf);
            admin.setName(name);
            if (cpf.length() < 11) {
                throw new IllegalArgumentException("telefone inválido!");
            }
            admin.setPhone(phone);
            admin.setCep(cep);
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setHouseNumber(houseNumber);
            admin.setBirthDate(birthDate);
            admin.setStatus(Status.ACTIVE);

            repository.insertAdmin(admin);
        } catch (Exception e) {
            System.out.println("Não foi possível criar Clente.\n messege: " + e.getMessage());
        }
    }

    @Override
    public Admin findById(Long id) {
        try {
            return repository.findById(id);
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Admin findByCpf(String Cpf) {
        try {
            return repository.findByCpf(Cpf);
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void ativateClient(Long id, Integer bankNumber, String accountNumber) {
        Long accountId = bankService.getIdByAccount(bankNumber, accountNumber);
        
        try {
            repository.activateClient(id, accountId, Status.ACTIVE.getValue());
        } catch (SQLException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Admin Login(String cpf, String password) {
        try {
            return repository.Login(cpf, password);
        }
        catch(Exception ex){
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
