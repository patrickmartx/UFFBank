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

    private final AdminRepository repository;
    private final BankAccountService bankService;

    public AdminServiceImpl(AdminRepository repository, BankAccountService bankService) {
        this.repository = repository;
        this.bankService = bankService;
    }

    @Override
    public void save(String cpf, String name, String phone,
            String cep, String email, String password,
            Integer houseNumber, Calendar birthDate, String status) {
        try {
            Admin admin = new Admin();

            if (cpf.length() != 11) {
                throw new IllegalArgumentException("CPF inválido!");
            }
            admin.setCpf(cpf);
            admin.setName(name);
            if (phone.length() < 11) {
                throw new IllegalArgumentException("Telefone inválido!");
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
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Não foi possível criar Cliente. Mensagem: {0}", e.getMessage());
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
    public Admin findByCpf(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void activateClient(Long id, Integer bankNumber, String accountNumber) {
        Long accountId = bankService.getIdByAccount(bankNumber, accountNumber);

        try {
            repository.activateClient(id, accountId, Status.ACTIVE.getValue());
        } catch (SQLException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Erro ao ativar cliente. Mensagem: {0}", ex.getMessage());
        }
    }

    @Override
    public Admin login(String cpf, String password) {
        try {
            return repository.login(cpf, password);
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Erro durante o login. Mensagem: {0}", ex.getMessage());
        }
        return null;
    }
}
