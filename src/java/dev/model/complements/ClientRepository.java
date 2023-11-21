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
    
    private final String col_id = "id";
    private final String col_cpf = "cpf";
    private final String col_name = "name";
    private final String col_phone = "phone";
    private final String col_cep = "cep";
    private final String col_email = "email";
    private final String col_password = "user_password";
    private final String col_houseNumber = "house_number";
    private final String col_birthDate = "birth_date";
    private final String col_bankAccountId = "bank_account_id";
    private final String col_status = "status";

    private void createClientTable() throws NoConnectException {
        String createTableSQL
                = "CREATE TABLE IF NOT EXISTS tb_client ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_cpf + " VARCHAR(15) NOT NULL UNIQUE,"
                + col_name + " VARCHAR(255) NOT NULL,"
                + col_phone + " VARCHAR(15) NOT NULL UNIQUE,"
                + col_cep + " VARCHAR(10) NOT NULL,"
                + col_email + " VARCHAR(255) NOT NULL UNIQUE,"
                + col_password + " VARCHAR(255) NOT NULL,"
                + col_houseNumber + " INT NOT NULL,"
                + col_birthDate + " DATE NOT NULL,"
                + col_bankAccountId + " INT UNIQUE,"
                + col_status + " VARCHAR(10) NOT NULL,"
                + "FOREIGN KEY (" + col_bankAccountId + ") REFERENCES tb_bankaccount(id)"
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

        String sql = "INSERT INTO tb_client ("+col_cpf+", "+col_name+", "+col_phone+", "
                + ""+col_cep+", "+col_email+", "+col_password+", "
                + ""+col_houseNumber+", "+col_birthDate+", "+col_bankAccountId+", "+col_status+") "
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
    
    public Client findById(Long id) throws NoConnectException {
        String getSQL = "SELECT * FROM tb_client WHERE "+col_id+" = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(col_id));
                client.setCpf(resultSet.getString(col_cpf));
                client.setName(resultSet.getString(col_name));
                client.setPhone(resultSet.getString(col_phone));
                client.setCep(resultSet.getString(col_cep));
                client.setEmail(resultSet.getString(col_email));
                client.setPassword(resultSet.getString(col_password));
                client.setHouseNumber(resultSet.getInt(col_houseNumber));

                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate(col_birthDate));
                client.setBirthDate(dataNascimento);
                
                client.setConta(resultSet.getLong(col_bankAccountId));
                client.setStatus(Status.valueOf(resultSet.getString(col_status)));

                return client;
            } else {
                System.out.println("Administrador não encontrado com o CPF: " + id);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Client findByCpf(String cpf) throws NoConnectException {
        String getSQL = "SELECT * FROM tb_client WHERE "+col_cpf+" = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(col_id));
                client.setCpf(resultSet.getString(col_cpf));
                client.setName(resultSet.getString(col_name));
                client.setPhone(resultSet.getString(col_phone));
                client.setCep(resultSet.getString(col_cep));
                client.setEmail(resultSet.getString(col_email));
                client.setPassword(resultSet.getString(col_password));
                client.setHouseNumber(resultSet.getInt(col_houseNumber));

                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate(col_birthDate));
                client.setBirthDate(dataNascimento);
                
                client.setConta(resultSet.getLong(col_bankAccountId));
                client.setStatus(Status.valueOf(resultSet.getString(col_status)));

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
        String getSQL = "SELECT * FROM tb_client WHERE "+col_cpf+" = ? AND "+col_password+" = ?";
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
