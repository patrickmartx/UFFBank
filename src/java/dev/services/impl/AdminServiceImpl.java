/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.model.complements.AdminRepository;
import dev.model.complements.ClientRepository;
import dev.model.complements.TransactionHistoryRepository;
import dev.model.complements.BankAccountRepository;
import dev.services.AdminService;
import dev.services.ClientService;
import dev.services.BankAccountService;
import dev.services.impl.ClientServiceImpl;
import dev.services.impl.BankAccountServiceImpl;
import dev.entity.Admin;
import dev.entity.Client;
import dev.entity.TransactionHistory;
import dev.entity.BankAccount;
import dev.exceptions.NoEntityFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Patrick
 */
public class AdminServiceImpl implements AdminService {
    private AdminRepository repository;
    
    public AdminServiceImpl() {
        this.repository = new AdminRepository();
    }

    @Override
    public Admin getById(Long id) {
        try{
            Admin admin = repository.get(id);
            
            if (admin == null || admin.getId() == 0) {
                throw new NoEntityFoundException("Administrador não encontrado");
            } else {
//                repository.closeConnection();
                return admin;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o administrador.");
        }
    }

    @Override
    public ArrayList<Admin> getAll() {
        try{
            ArrayList<Admin> adminList = repository.getAll();
            
            if(adminList.isEmpty() || adminList == null){
                throw new NoEntityFoundException("Não há administradores no banco.");
            } else {
//                repository.closeConnection();
                return adminList;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }

    @Override
    public void insert(String cpf, String name, String phone, String cep, String address, String email, String password, Integer houseNumber, Date birthDate) {
        try {
            if (repository.getByCpf(cpf) == null) {
                Admin newAdmin = new Admin();
                
                newAdmin.setCpf(cpf);
                newAdmin.setName(name);
                newAdmin.setPhone(phone);
                newAdmin.setCep(cep);
                newAdmin.setAddress(address);
                newAdmin.setEmail(email);
                newAdmin.setPassword(password);
                newAdmin.setHouseNumber(houseNumber);
                newAdmin.setBirthDate(birthDate);
                newAdmin.setStatus(Status.ACTIVE);
            
                repository.insert(newAdmin);
                Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.INFO, "Administrador inserido com sucesso!");
            } else {
                throw new IllegalAccessError("Administrador já cadastrado no banco de dados.");
            }
        } catch(Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void update(String cpf, String name, String phone, String cep, String address, String email, String password, Integer houseNumber, Date birthDate, Status status) {
        try {
            if (repository.getByCpf(cpf) != null) {
                Admin updatedAdmin = repository.getByCpf(cpf);
                
                updatedAdmin.setCpf(cpf);
                updatedAdmin.setName(name);
                updatedAdmin.setPhone(phone);
                updatedAdmin.setCep(cep);
                updatedAdmin.setAddress(address);
                updatedAdmin.setEmail(email);
                updatedAdmin.setPassword(password);
                updatedAdmin.setHouseNumber(houseNumber);
                updatedAdmin.setBirthDate(birthDate);
                updatedAdmin.setStatus(Status.ACTIVE);
            
                repository.update(updatedAdmin);
                Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.INFO, "Administrador atualizado com sucesso!");
            } else {
                throw new IllegalAccessError("Administrador não existe no banco de dados.");
            }
        } catch(Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.delete(id);
        } catch(Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }
    
    @Override
    public Admin getAdminByLogin(String cpf, String password) {
        try {
            Admin existingAdmin = repository.getByLogin(cpf, password);

            if (existingAdmin == null || existingAdmin.getCpf() == null) {
                throw new NoEntityFoundException("Cliente não encontrado");
            } else {
//                repository.closeConnection();
                return existingAdmin;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }
    
    @Override
    public ArrayList<Client> getInactiveClients() {
        ClientRepository clientRepository = new ClientRepository();
        
        try {
            ArrayList<Client> inactiveClients = clientRepository.getAllInactiveClients();

            if(inactiveClients == null){
                return inactiveClients;
            } else {
//                repository.closeConnection();
                return inactiveClients;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }

        @Override
    public ArrayList<Client> getActiveClients() {
        ClientRepository clientRepository = new ClientRepository();
        
        try {
            ArrayList<Client> activeClients = clientRepository.getAllActiveClients();

            if(inactiveClients == null){
                return activeClients;
            } else {
//                repository.closeConnection();
                return activeClients;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }
    
    @Override
    public ArrayList<TransactionHistory> generateTransactionHistory(Long clientAccountId) {
        TransactionHistoryRepository transactionHistoryRepository = new TransactionHistoryRepository();
        try {
            ArrayList<TransactionHistory> transactionsList = transactionHistoryRepository.getAllHistoricOfOneAccount(clientAccountId);
            if(transactionsList.isEmpty() || transactionsList == null){
                throw new NoEntityFoundException("Não há clientes inativos no banco.");
            } else {
//                repository.closeConnection();
                return transactionsList;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }
    
    private Admin getAdminBycpf(String cpf) {
        try {
            Admin existingAdmin = repository.getByCpf(cpf);

            if (existingAdmin == null || existingAdmin.getCpf() == null) {
                throw new NoEntityFoundException("Cliente não encontrado");
            } else {
//                repository.closeConnection();
                return existingAdmin;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }
    
    @Override
    public void activateClient(Long clientId, Integer bankNumber, String accountNumber) {
        ClientService clientService = new ClientServiceImpl();
        BankAccountService bankService = new BankAccountServiceImpl();
        Client clientToActivate;
        BankAccount bankAccountForClient;
        
        try {
            clientToActivate = clientService.getById(clientId);
            bankAccountForClient = bankService.getAccountByBankNumberAndAccountNumber(bankNumber, accountNumber);
            if (clientToActivate.getCpf() == null || bankAccountForClient.getAccountNumber() == null) {
                throw new NoEntityFoundException("Algum deles não existe.");
            }
            clientService.update(clientToActivate.getCpf(), clientToActivate.getName(), clientToActivate.getPhone(),
                    clientToActivate.getCep(), clientToActivate.getAddress(), clientToActivate.getEmail(), clientToActivate.getPassword(), 
                    clientToActivate.getHouseNumber(), clientToActivate.getBirthDate(), bankAccountForClient.getId(),
                    Status.ACTIVE);
        } catch (Exception ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao ativar o cliente.");
        }
    }
}
