/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.entity;

import dev.utils.Status;
import java.util.Calendar;

/**
 *
 * @author Patrick
 */
public class Client {
    private Long id;
    private String cpf;
    private String name;
    private String phone;
    private String cep;
    private String email;
    private String password;
    private Integer houseNumber;
    private Calendar birthDate;
    private Long bankAccountId;
    private Status status;

    public Client() {
    }

    public Client(Long id, String cpf, String name,
            String phone, String cep, String email,
            String password, Integer houseNumber, Calendar birthDate,
            Long bankAccountId, Status status) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.phone = phone;
        this.cep = cep;
        this.email = email;
        this.password = password;
        this.houseNumber = houseNumber;
        this.birthDate = birthDate;
        this.bankAccountId = bankAccountId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setConta(Long contaId) {
        this.bankAccountId = contaId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
