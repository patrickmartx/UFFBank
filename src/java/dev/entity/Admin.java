/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.entity;

import java.util.Calendar;

/**
 *
 * @author Patrick
 */
public class Admin {
    private Long id;
    private String cpf;
    private String telefone;
    private String cep;
    private String email;
    private String password;
    private Integer numeroCasa;
    private Calendar dataNascimento;

    public Admin() {
    }

    public Admin(Long id, String cpf, String telefone, String cep, String email, String password, Integer numeroCasa, Calendar dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.password = password;
        this.numeroCasa = numeroCasa;
        this.dataNascimento = dataNascimento;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
    
}
