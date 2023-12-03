/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.entity.Client;
import dev.entity.BankAccount;
import dev.model.complements.ClientRepository;
import dev.entity.TransactionHistory;
import dev.services.ClientService;
import dev.services.BankAccountService;
import dev.services.TransactionHistoryService;
import dev.services.impl.BankAccountServiceImpl;
import dev.services.impl.TransactionHistoryServiceImpl;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.exceptions.NoEntityFoundException;
import dev.utils.TransactionType;
import java.time.LocalDateTime;

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
        try{
            Client client = repository.get(id);
            
            if (client == null || client.getId() == 0) {
                throw new NoEntityFoundException("Cliente não encontrado");
            } else {
//                repository.closeConnection();
                return client;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }

    @Override
    public ArrayList<Client> getAll() {
        try{
            ArrayList<Client> clientList = repository.getAll();
            
            if(clientList.isEmpty() || clientList == null){
                throw new NoEntityFoundException("Não há clientes no banco.");
            } else {
//                repository.closeConnection();
                return clientList;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }

    @Override
    public void insert(String cpf, String name, String phone, String cep, String email, String password, Integer houseNumber, Date birthDate) {
    try {
            Client checkClient = repository.getByCpf(cpf);
            if (checkClient.getCpf() == null) {
                Client newClient = new Client();
                
                newClient.setCpf(cpf);
                newClient.setName(name);
                newClient.setPhone(phone);
                newClient.setCep(cep);
                newClient.setEmail(email);
                newClient.setPassword(password);
                newClient.setHouseNumber(houseNumber);
                newClient.setBirthDate(birthDate);
                newClient.setStatus(Status.INACTIVE);
            
                repository.insert(newClient);
                Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.INFO, "Cliente inserido com sucesso!");
            } else {
                Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.INFO, "Cliente já existe no banco!");
                throw new IllegalAccessError("Cliente já cadastrado no banco de dados.");
            }
        } catch(Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void update(String cpf, String name, String phone, String cep, String email, String password, Integer houseNumber, Date birthDate, Long idBankAccount, Status status) {
        try {
            if (repository.getByCpf(cpf) != null) {
                Client existingClient = new Client();
                
                existingClient.setCpf(cpf);
                existingClient.setName(name);
                existingClient.setPhone(phone);
                existingClient.setCep(cep);
                existingClient.setEmail(email);
                existingClient.setPassword(password);
                existingClient.setHouseNumber(houseNumber);
                existingClient.setBirthDate(birthDate);
                existingClient.setBankAccountId(idBankAccount);
                existingClient.setStatus(Status.ACTIVE);
            
                repository.update(existingClient);
                Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.INFO, "Cliente atualizado com sucesso!");
            } else {
                throw new IllegalAccessError("Cliente não existe no banco de dados.");
            }
        } catch(Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
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
    public Client getClientByLogin(String cpf, String password) {
        try {
            Client existingClient = repository.getByLogin(cpf, password);

            if (existingClient == null || existingClient.getCpf() == null) {
                throw new NoEntityFoundException("Cliente não encontrado");
            } else {
//                repository.closeConnection();
                return existingClient;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente. " + ex.getMessage());
        }
    }
    
    @Override
    public Double getAccountBalance(Long id) {
        try {
            Double accountBalance = repository.getAccountBalance(id);
            
            if(accountBalance == null) {
                throw new NoEntityFoundException("Conta não encontrado");
            } else {
                return accountBalance;
            }
        } catch (NoEntityFoundException ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao buscar o cliente.");
        }
    }
    
    @Override
    public void depositInBankAccount(Long bankAccountId, Double value) {
        try {
            BankAccountService bankService = new BankAccountServiceImpl();
            TransactionHistory transactionHistory = new TransactionHistory();
            TransactionHistoryService transactionService = new TransactionHistoryServiceImpl();
            Date currentDate = new Date();
            
            BankAccount bankAccount = bankService.getById(bankAccountId);
            Double lastValue = bankAccount.getAccountBalance();
            
            bankService.update((bankAccount.getAccountBalance() + value), bankAccount.getBankNumber(), bankAccount.getAccountNumber());
            
            System.out.println(lastValue + " x " + bankAccount.getAccountNumber());
            transactionService.deposit(value, currentDate, bankAccountId);
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Ocorreu algum erro ao depositar valor. " + ex.getClass() + " " + ex.getMessage());
        }
    }
    
    @Override
    public void transferBetweenTwoAccounts(Long idSenderAccount, Long idReceiverAccount, Double value) {
        try {
            BankAccountService bankService = new BankAccountServiceImpl();
            TransactionHistory transactionHistory = new TransactionHistory();
            TransactionHistoryService transactionService = new TransactionHistoryServiceImpl();
            Date currentDate = new Date();
            
            BankAccount bankAccountSender = bankService.getById(idSenderAccount);
            BankAccount bankAccountReceiver = bankService.getById(idReceiverAccount);
            
            if (bankAccountSender.getAccountBalance() < value) {
                throw new ArithmeticException("Você não tem valor suficiente para fazer transferência.");
            }
            if (bankAccountSender.getId() == bankAccountReceiver.getId()) {
                throw new ArithmeticException("Você não pode transferir para si mesmo.");
            }
            
            bankService.update((bankAccountSender.getAccountBalance() - value), bankAccountSender.getBankNumber(), bankAccountSender.getAccountNumber());
            bankService.update((bankAccountReceiver.getAccountBalance() + value), bankAccountReceiver.getBankNumber(), bankAccountReceiver.getAccountNumber());
            
            System.out.println("conta 1 id: "+bankAccountSender.getId()+" saldo: "+bankAccountSender.getAccountBalance()+""
                    + "conta 2 id: "+bankAccountReceiver.getId()+" saldo: "+bankAccountReceiver.getAccountBalance());

            
            transactionService.insert((value*-1), currentDate, idSenderAccount, idReceiverAccount);
            transactionService.insert(value, currentDate, idSenderAccount, idReceiverAccount);
            
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException("Ocorreu algum erro ao fazer transferência. " + ex.getMessage());
        }
    }
}
