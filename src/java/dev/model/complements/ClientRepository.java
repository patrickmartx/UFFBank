/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model.complements;

/**
 *
 * @author Patrick
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dev.entity.Client;
import dev.exceptions.NoConnectException;
import dev.model.DAO;

public class ClientRepository extends DAO {

    // Método para criar a tabela de cliente
    private void createClientTable() throws NoConnectException {
        String createTableSQL = 
                "CREATE TABLE IF NOT EXISTS tb_client ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "cpf VARCHAR(15) NOT NULL UNIQUE,"
                + "nome VARCHAR(255) NOT NULL,"
                + "telefone VARCHAR(15) NOT NULL UNIQUE,"
                + "cep VARCHAR(10) NOT NULL,"
                + "email VARCHAR(255) NOT NULL UNIQUE,"
                + "password VARCHAR(255) NOT NULL,"
                + "numero_casa INT NOT NULL,"
                + "data_nascimento DATE NOT NULL,"
                + "bank_account_id INT UNIQUE,"
                + "FOREIGN KEY (bank_account_id) REFERENCES tb_bankaccount(id)"
                + ")";

        try (Connection connection = this.connect(); 
             PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {

            preparedStatement.executeUpdate();
            System.out.println("Tabela cliente criada ou já existente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um cliente no banco de dados
    public void insertClient(Client client) throws NoConnectException {
        createClientTable(); // Chama o método para criar a tabela

        String sql = "INSERT INTO tb_client (cpf, nome, telefone, cep, email, password, numero_casa, data_nascimento, bank_account_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.connect(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Defina os parâmetros da consulta com base no objeto Client
            preparedStatement.setString(1, client.getCpf());
            preparedStatement.setString(2, client.getNome());
            preparedStatement.setString(3, client.getTelefone());
            preparedStatement.setString(4, client.getCep());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.setString(6, client.getPassword());
            preparedStatement.setInt(7, client.getNumeroCasa());
            preparedStatement.setDate(8, new java.sql.Date(client.getDataNascimento().getTimeInMillis()));
            // Defina o bank_account_id com base no objeto BankAccount
            preparedStatement.setLong(9, client.getContaId());

            // Execute a consulta de inserção
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir o cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

