/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model.complements;

/**
 *
 * @author Patrick
 */
import dev.entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dev.entity.Client;
import dev.exceptions.NoConnectException;
import dev.model.DAO;
import dev.model.DbConnector;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.logging.Level;
import dev.utils.Status;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ClientRepository implements DAO<Client> {

    private final DbConnector connection;

    private final String table_name = "tb_client";
    private final String col_id = "id";
    private final String col_cpf = "cpf";
    private final String col_name = "name";
    private final String col_phone = "phone";
    private final String col_cep = "cep";
    private final String col_address = "address";
    private final String col_email = "email";
    private final String col_password = "user_password";
    private final String col_houseNumber = "house_number";
    private final String col_birthDate = "birth_date";
    private final String col_bankAccountId = "bank_account_id";
    private final String col_status = "status";

    public ClientRepository() {
        try {
            this.connection = new DbConnector();
        } catch (NoConnectException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
            throw new RuntimeException();
        }
    }

    private void createClientTable() throws NoConnectException {
        String createTableSQL
                = "CREATE TABLE IF NOT EXISTS " + table_name + " ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_cpf + " VARCHAR(15) NOT NULL UNIQUE,"
                + col_name + " VARCHAR(255) NOT NULL,"
                + col_phone + " VARCHAR(15) NOT NULL UNIQUE,"
                + col_cep + " VARCHAR(10) NOT NULL,"
                + col_address + " VARCHAR(255) NOT NULL,"
                + col_email + " VARCHAR(255) NOT NULL UNIQUE,"
                + col_password + " VARCHAR(255) NOT NULL,"
                + col_houseNumber + " INT NOT NULL,"
                + col_birthDate + " DATE NOT NULL,"
                + col_bankAccountId + " INT UNIQUE,"
                + col_status + " VARCHAR(10) NOT NULL, "
                + "FOREIGN KEY (" + col_bankAccountId + ") REFERENCES tb_bankaccount(id)"
                + ")";

        try {
            PreparedStatement preparedStatement = this.connection.getConnect().prepareStatement(createTableSQL);

            preparedStatement.executeUpdate();
            Logger.getLogger(ClientRepository.class.getName()).log(Level.INFO, "Tabela já existe ou foi criada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        }
    }

    @Override
    public Client get(Long id) {
        String getSQL = "SELECT * FROM " + table_name + " WHERE "+col_id+" = ?";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            sql.setLong(1, id);
            ResultSet result = sql.executeQuery();
            Client client = new Client();

            if (result != null) {
                while (result.next()) {
                    client.setId(Long.valueOf(result.getString(col_id)));
                    client.setCpf(result.getString(col_cpf));
                    client.setName(result.getString(col_name));
                    client.setPhone(result.getString(col_phone));
                    client.setCep(result.getString(col_cep));
                    client.setAddress(result.getString(col_address));
                    client.setEmail(result.getString(col_email));
                    client.setPassword(result.getString(col_password));
                    client.setHouseNumber(result.getInt(col_houseNumber));
                    Date birthDate = result.getDate(col_birthDate);
                    client.setBirthDate(birthDate);
                    client.setBankAccountId(result.getLong(col_bankAccountId));
                    client.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return client;

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
    }

    @Override
    public ArrayList<Client> getAll() {
        ArrayList<Client> clientList = new ArrayList();
        String selectSQL = "SELECT * FROM " + table_name + " WHERE " + col_status + " != ?";
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            preparedStatement.setString(1, Status.DISACTIVATE.getValue());
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Client client = new Client(
                            result.getLong(col_id),
                            result.getString(col_cpf),
                            result.getString(col_name),
                            result.getString(col_phone),
                            result.getString(col_cep),
                            result.getString(col_address),
                            result.getString(col_email),
                            result.getString(col_password),
                            result.getInt(col_houseNumber),
                            result.getDate(col_birthDate),
                            result.getLong(col_bankAccountId),
                            Status.valueOf(result.getString(col_status))
                    );
                    clientList.add(client);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
        return clientList;
    }

    @Override
    public void insert(Client client) {
        String insertionSQL = "INSERT INTO " + table_name + " (" + col_cpf + ", " + col_name + ", " + col_phone + ", " + col_cep
                + ", " + col_address + ", " + col_email + ", " + col_password + ", " + col_houseNumber
                + ", " + col_birthDate + ", " + col_status + ") "
                + "VALUES (?,?,?,?,?,?,?,?,?, ?)";
        try {
            createClientTable();

            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setString(1, client.getCpf());
            sql.setString(2, client.getName());
            sql.setString(3, client.getPhone());
            sql.setString(4, client.getCep());
            sql.setString(5, client.getAddress());
            sql.setString(6, client.getEmail());
            sql.setString(7, client.getPassword());
            sql.setInt(8, client.getHouseNumber());
            sql.setDate(9, new java.sql.Date(client.getBirthDate().getTime()));
            sql.setString(10, client.getStatus().getValue());

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (NoConnectException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
        } /*finally {
            connection.closeConnection();
        }*/
    }

    @Override
    public void update(Client client) {
        String updateSQL = "UPDATE " + table_name + " SET " + col_cpf + " = ?, " + col_name + " = ?, " + col_phone + " = ?, " + col_cep
                + " = ?, " + col_address + " = ?, " + col_email + " = ?, " + col_password + " = ?, " + col_houseNumber
                + " = ?, " + col_birthDate + " = ?, " + col_bankAccountId + " = ?, " + col_status + " = ? WHERE "+col_cpf+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setString(1, client.getCpf());
            sql.setString(2, client.getName());
            sql.setString(3, client.getPhone());
            sql.setString(4, client.getCep());
            sql.setString(5, client.getAddress());
            sql.setString(6, client.getEmail());
            sql.setString(7, client.getPassword());
            sql.setInt(8, client.getHouseNumber());
            sql.setDate(9, (java.sql.Date) client.getBirthDate());
            sql.setLong(10, client.getBankAccountId());
            sql.setString(11, client.getStatus().getValue());
            sql.setString(12, client.getCpf());

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
    }

    @Override
    public void delete(Long id) {
        String updateSQL = "UPDATE " + table_name + " SET " + col_status + " = ? WHERE " + col_id + " = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setString(1, Status.DISACTIVATE.getValue());
            sql.setLong(2, id);

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
    }
    
    public Client getByLogin(String cpf, String password) {
        String getSQL = "SELECT * FROM " + table_name + " WHERE "+col_cpf+" = ? AND "+col_password+" = ?";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            sql.setString(1, cpf);
            sql.setString(2, password);
            ResultSet result = sql.executeQuery();
            Client client = new Client();

            if (result != null) {
                while (result.next()) {
                    client.setId(Long.valueOf(result.getString(col_id)));
                    client.setCpf(result.getString(col_cpf));
                    client.setName(result.getString(col_name));
                    client.setPhone(result.getString(col_phone));
                    client.setCep(result.getString(col_cep));
                    client.setAddress(result.getString(col_address));
                    client.setEmail(result.getString(col_email));
                    client.setPassword(result.getString(col_password));
                    client.setHouseNumber(result.getInt(col_houseNumber));
                    Date birthDate = result.getDate(col_birthDate);
                    client.setBirthDate(birthDate);
                    client.setBankAccountId(result.getLong(col_bankAccountId));
                    client.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return client;

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            throw new RuntimeException("Erro ao buscar cliente. mensagem: " + ex.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/
    }
    
    public Double getAccountBalance(Long id) {
        String getSQL = "SELECT a.account_balance \n" +
                        "FROM tb_bankaccount a \n" +
                        "INNER JOIN "+table_name+" tc ON a.id = tc."+col_bankAccountId+" " +
                        "WHERE a.id = "+id+"";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            ResultSet result = sql.executeQuery();
            Double saldo = 0.00;

            if (result != null) {
                if (result.next()) {
                    saldo = result.getDouble("account_balance");
                }
            }
            return saldo;

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao selecionar o saldo do cliente. Mensagem: " + ex.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/
    }
    
    public void closeConnection() {
        connection.closeConnection();
    }
    
    public ArrayList<Client> getAllInactiveClients() {
        ArrayList<Client> clientList = new ArrayList();
        String selectSQL = "SELECT * FROM " + table_name + " WHERE " + col_status + " = ?";
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            preparedStatement.setString(1, Status.INACTIVE.getValue());
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Client client = new Client(
                            result.getLong(col_id),
                            result.getString(col_cpf),
                            result.getString(col_name),
                            result.getString(col_phone),
                            result.getString(col_cep),
                            result.getString(col_address),
                            result.getString(col_email),
                            result.getString(col_password),
                            result.getInt(col_houseNumber),
                            result.getDate(col_birthDate),
                            result.getLong(col_bankAccountId),
                            Status.valueOf(result.getString(col_status))
                    );
                    clientList.add(client);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
        return clientList;
    }
    
    public Client getByCpf(String cpf) {
        String getSQL = "SELECT * FROM " + table_name + " WHERE "+col_cpf+" = ?";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            sql.setString(1, cpf);
            ResultSet result = sql.executeQuery();
            Client client = new Client();

            if (result != null) {
                while (result.next()) {
                    client.setId(Long.valueOf(result.getString(col_id)));
                    client.setCpf(result.getString(col_cpf));
                    client.setName(result.getString(col_name));
                    client.setPhone(result.getString(col_phone));
                    client.setCep(result.getString(col_cep));
                    client.setAddress(result.getString(col_address));
                    client.setEmail(result.getString(col_email));
                    client.setPassword(result.getString(col_password));
                    client.setHouseNumber(result.getInt(col_houseNumber));
                    Date birthDate = result.getDate(col_birthDate);
                    client.setBirthDate(birthDate);
                    client.setBankAccountId(result.getLong(col_bankAccountId));
                    client.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return client;

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar cliente. mensagem: " + ex.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/
    }
}
