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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientRepository implements DAO<Client> {
    private final DbConnector connection;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    private final String table_name = "tb_client";
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
                = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
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
        String getSQL = "SELECT * FROM "+table_name+" WHERE ID = ?";
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
                    client.setEmail(result.getString(col_email));
                    client.setPassword(result.getString(col_password));
                    client.setHouseNumber(Integer.valueOf(result.getString(col_houseNumber)));
                    Date birthDate = dateFormat.parse(result.getString(col_birthDate));
                    client.setBirthDate(birthDate);
                    client.setBankAccountId(Long.valueOf(result.getString(col_bankAccountId)));
                    client.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return client;

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (ParseException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public ArrayList<Client> getAll() {
         ArrayList<Client> clientList = new ArrayList();
         String selectSQL = "SELECT * FROM "+ table_name + " WHERE "+col_status+" != " + Status.DISACTIVATE.getValue();
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Client client = new Client(
                            result.getLong(col_id),
                            result.getString(col_cpf),
                            result.getString(col_name),
                            result.getString(col_phone),
                            result.getString(col_cep),
                            result.getString(col_email),
                            result.getString(col_password),
                            Integer.valueOf(result.getString(col_houseNumber)),
                            dateFormat.parse(result.getString(col_birthDate)),
                            result.getLong(col_bankAccountId),
                            Status.valueOf(result.getString(col_status)));
                    clientList.add(client);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (ParseException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }
        return clientList;
    }

    @Override
    public void insert(Client client) {
        String insertionSQL = "INSERT INTO "+table_name+" ("+col_cpf+", "+col_name+", "+col_phone+", "+ col_cep
                                                           +", "+col_email+", "+col_password+", "+col_houseNumber
                                                           +", "+col_birthDate+", "+col_bankAccountId+", "+col_status+") "
                                                           + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            createClientTable();
            
            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setString(1, client.getCpf());
            sql.setString(2, client.getName());
            sql.setString(3, client.getPhone());
            sql.setString(4, client.getCep());
            sql.setString(5, client.getEmail());
            sql.setString(6, client.getPassword());
            sql.setInt(7, client.getHouseNumber());
            sql.setDate(8, (java.sql.Date) client.getBirthDate());
            sql.setLong(9, client.getBankAccountId());
            sql.setString(10, client.getStatus().getValue());
            
            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (NoConnectException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public void update(Client client) {
        String updateSQL = "UPDATE "+table_name+" SET "+col_cpf+" = ?, "+col_name+" = ?, "+col_phone+" = ?, "+ col_cep
                                                           +" = ?, "+col_email+" = ?, "+col_password+" = ?, "+col_houseNumber
                                                           +" = ?, "+col_birthDate+" = ?, "+col_bankAccountId+" = ?, "+col_status+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setString(1, client.getCpf());
            sql.setString(2, client.getName());
            sql.setString(3, client.getPhone());
            sql.setString(4, client.getCep());
            sql.setString(5, client.getEmail());
            sql.setString(6, client.getPassword());
            sql.setInt(7, client.getHouseNumber());
            sql.setDate(8, (java.sql.Date) client.getBirthDate());
            sql.setLong(9, client.getBankAccountId());
            sql.setString(10, client.getStatus().getValue());

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public void delete(Long id) {
        String updateSQL = "UPDATE "+table_name+" SET "+col_status+" = ? WHERE "+col_id+" = ?" ;
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setString(1, Status.DISACTIVATE.getValue());
            sql.setLong(2, id);

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }  
   }

//    public void insertClient(Client client) throws NoConnectException {
//        createClientTable();
//
//        String sql = "INSERT INTO tb_client ("+col_cpf+", "+col_name+", "+col_phone+", "
//                + ""+col_cep+", "+col_email+", "+col_password+", "
//                + ""+col_houseNumber+", "+col_birthDate+", "+col_bankAccountId+", "+col_status+") "
//                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try (Connection connection = this.connect(); 
//                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setString(1, client.getCpf());
//            preparedStatement.setString(2, client.getName());
//            preparedStatement.setString(3, client.getPhone());
//            preparedStatement.setString(4, client.getCep());
//            preparedStatement.setString(5, client.getEmail());
//            preparedStatement.setString(6, client.getPassword());
//            preparedStatement.setInt(7, client.getHouseNumber());
//            preparedStatement.setDate(8, new java.sql.Date(client.getBirthDate().getTimeInMillis()));
//            preparedStatement.setLong(9, client.getBankAccountId());
//            preparedStatement.setString(10, client.getStatus().getValue());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Cliente inserido com sucesso!");
//            } else {
//                System.out.println("Falha ao inserir o cliente.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public Client findById(Long id) throws NoConnectException {
//        String getSQL = "SELECT * FROM tb_client WHERE "+col_id+" = ?";
//
//        try (Connection connection = this.connect(); 
//                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
//
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Client client = new Client();
//                client.setId(resultSet.getLong(col_id));
//                client.setCpf(resultSet.getString(col_cpf));
//                client.setName(resultSet.getString(col_name));
//                client.setPhone(resultSet.getString(col_phone));
//                client.setCep(resultSet.getString(col_cep));
//                client.setEmail(resultSet.getString(col_email));
//                client.setPassword(resultSet.getString(col_password));
//                client.setHouseNumber(resultSet.getInt(col_houseNumber));
//
//                Calendar dataNascimento = Calendar.getInstance();
//                dataNascimento.setTime(resultSet.getDate(col_birthDate));
//                client.setBirthDate(dataNascimento);
//                
//                client.setConta(resultSet.getLong(col_bankAccountId));
//                client.setStatus(Status.valueOf(resultSet.getString(col_status)));
//
//                return client;
//            } else {
//                System.out.println("Administrador não encontrado com o CPF: " + id);
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    public Client findByCpf(String cpf) throws NoConnectException {
//        String getSQL = "SELECT * FROM tb_client WHERE "+col_cpf+" = ?";
//
//        try (Connection connection = this.connect(); 
//                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
//
//            preparedStatement.setString(1, cpf);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Client client = new Client();
//                client.setId(resultSet.getLong(col_id));
//                client.setCpf(resultSet.getString(col_cpf));
//                client.setName(resultSet.getString(col_name));
//                client.setPhone(resultSet.getString(col_phone));
//                client.setCep(resultSet.getString(col_cep));
//                client.setEmail(resultSet.getString(col_email));
//                client.setPassword(resultSet.getString(col_password));
//                client.setHouseNumber(resultSet.getInt(col_houseNumber));
//
//                Calendar dataNascimento = Calendar.getInstance();
//                dataNascimento.setTime(resultSet.getDate(col_birthDate));
//                client.setBirthDate(dataNascimento);
//                
//                client.setConta(resultSet.getLong(col_bankAccountId));
//                client.setStatus(Status.valueOf(resultSet.getString(col_status)));
//
//                return client;
//            } else {
//                System.out.println("Administrador não encontrado com o CPF: " + cpf);
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public Client Login(String cpf, String password) throws SQLException {
//        String getSQL = "SELECT * FROM tb_client WHERE "+col_cpf+" = ? AND "+col_password+" = ?";
//        try (Connection connection = this.connect(); 
//                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
//
//            preparedStatement.setString(1, cpf);
//            preparedStatement.setString(2, password);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return findByCpf(cpf);
//                }
//            }
//        } catch (NoConnectException ex) {
//            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

}
