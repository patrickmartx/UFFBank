/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import dev.entity.Client;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public interface ClientService {
    
    public Client getById(Long id);
    public ArrayList<Client> getAll();
    public void insert(String cpf, String name, String phone, String cep, String address, 
                       String email, String password, Integer houseNumber, 
                       Date birthDate);
    public void update(String cpf, String name, String phone, String cep, String address,
                       String email, String password, Integer houseNumber, 
                       Date birthDate, Long idBankAccount, Status status);
    public void deleteById(Long id);
    public Client getClientByLogin(String cpf, String password);
    public Double getAccountBalance(Long id);
    public void depositInBankAccount(Long bankAccountId, Double value);
    public void withdrawalInBankAccount(Long bankAccountId, Double value);
    public void transferBetweenTwoAccounts(Long idSenderAccount, Long idReceiverAccount, Double value);
    public Double getInvestmentWalletBallance(Long bankAccountId);
    public Double getYieldPercentage(Long bankAccountId);
    public void investing(Long bankAccountId, Long walletId, Double value);
}
