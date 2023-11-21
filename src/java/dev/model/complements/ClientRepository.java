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
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.logging.Level;
import dev.utils.Status;

public class ClientRepository extends DAO {

    private void createClientTable() throws NoConnectException {
        String createTableSQL
                = "CREATE TABLE IF NOT EXISTS tb_client ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "cpf VARCHAR(15) NOT NULL UNIQUE,"
                + "name VARCHAR(255) NOT NULL,"
                + "phone VARCHAR(15) NOT NULL UNIQUE,"
                + "cep VARCHAR(10) NOT NULL,"
                + "email VARCHAR(255) NOT NULL UNIQUE,"
                + "password VARCHAR(255) NOT NULL,"
                + "house_number INT NOT NULL,"
                + "birth_date DATE NOT NULL,"
                + "bank_account_id INT UNIQUE,"
                + "status VARCHAR(10) NOT NULL,"
                + "FOREIGN KEY (bankAccountId) REFERENCES tb_bankaccount(id)"
                + ")";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {

            preparedStatement.executeUpdate();
            System.out.println("Tabela cliente criada ou já existente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertClient(Client client) throws NoConnectException {
        createClientTable();

        String sql = "INSERT INTO tb_client (cpf, name, phone, "
                + "cep, email, password, "
                + "house_number, birth_date, bank_account_id, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getCpf());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setString(4, client.getCep());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.setString(6, client.getPassword());
            preparedStatement.setInt(7, client.getHouseNumber());
            preparedStatement.setDate(8, new java.sql.Date(client.getBirthDate().getTimeInMillis()));
            preparedStatement.setLong(9, client.getBankAccountId());
            preparedStatement.setString(10, client.getStatus().getValue());

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
    
    public Client findByCpf(String cpf) throws NoConnectException {
        String getSQL = "SELECT * FROM tb_client WHERE cpf = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setCpf(resultSet.getString("cpf"));
                client.setName(resultSet.getString("name"));
                client.setPhone(resultSet.getString("phone"));
                client.setCep(resultSet.getString("cep"));
                client.setEmail(resultSet.getString("email"));
                client.setPassword(resultSet.getString("password"));
                client.setHouseNumber(resultSet.getInt("house_number"));

                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate("birth_date"));
                client.setBirthDate(dataNascimento);
                
                client.setConta(resultSet.getLong("bank_account_id"));
                client.setStatus(Status.valueOf(resultSet.getString("status")));

                return client;
            } else {
                System.out.println("Administrador não encontrado com o CPF: " + cpf);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Client Login(String cpf, String password) throws SQLException {
        String getSQL = "SELECT * FROM tb_cliente WHERE cpf = ? AND password = ?";
        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return findByCpf(cpf);
                }
            }
        } catch (NoConnectException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
