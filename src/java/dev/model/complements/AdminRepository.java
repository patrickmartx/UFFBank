package dev.model.complements;

import dev.exceptions.NoConnectException;
import dev.model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dev.entity.Admin;
import java.util.Calendar;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.model.DbConnector;
import dev.utils.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class AdminRepository implements DAO<Admin>{
    
    private final String table_name = "tb_admin";
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
    private final String col_status = "status";
    
    public AdminRepository(){
    }

    private void createAdminTable() throws NoConnectException {
        DbConnector connection = new DbConnector();
        String createTableSQL
                = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
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
                + col_status + " VARCHAR(10) NOT NULL"
                + ")";

        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(createTableSQL);

            preparedStatement.executeUpdate();
            Logger.getLogger(AdminRepository.class.getName()).log(Level.INFO, "Tabela já existe ou foi criada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        }finally {
            connection.closeConnection();
        }
    }
    
    @Override
    public Admin get(Long id) {
        DbConnector connection = new DbConnector();
        String getSQL = "SELECT * FROM "+table_name+" WHERE "+col_id+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(getSQL);
            sql.setLong(1, id);
            ResultSet result = sql.executeQuery();
            Admin admin = new Admin();

            if (result != null) {
                while (result.next()) {
                    admin.setId(result.getLong(col_id));
                    admin.setCpf(result.getString(col_cpf));
                    admin.setName(result.getString(col_name));
                    admin.setPhone(result.getString(col_phone));
                    admin.setCep(result.getString(col_cep));
                    admin.setAddress(result.getString(getSQL));
                    admin.setEmail(result.getString(col_email));
                    admin.setPassword(result.getString(col_password));
                    admin.setHouseNumber(result.getInt(col_houseNumber));
                    Date birthDate = result.getDate(col_birthDate);
                    admin.setBirthDate(birthDate);
                    admin.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return admin;

        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public ArrayList<Admin> getAll() {
        DbConnector connection = new DbConnector();
         ArrayList<Admin> adminList = new ArrayList();
         String selectSQL = "SELECT * FROM "+ table_name + " WHERE "+col_status+ " != ?";
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            preparedStatement.setString(1, Status.DISACTIVATE.getValue());
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Admin admin = new Admin(
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
                            Status.valueOf(result.getString(col_status)));
                    adminList.add(admin);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
        return adminList;
    }

    @Override
    public void insert(Admin admin) {
        DbConnector connection = new DbConnector();
        String insertionSQL = "INSERT INTO "+table_name+" ("+col_cpf+", "+col_name+", "+col_phone+", "+ col_cep
                                                           +", "+col_address+", "+col_email+", "+col_password+", "+col_houseNumber
                                                           +", "+col_birthDate+", "+col_status+") "
                                                           + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            createAdminTable();
            
            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setString(1, admin.getCpf());
            sql.setString(2, admin.getName());
            sql.setString(3, admin.getPhone());
            sql.setString(4, admin.getCep());
            sql.setString(5, admin.getAddress());
            sql.setString(6, admin.getEmail());
            sql.setString(7, admin.getPassword());
            sql.setInt(8, admin.getHouseNumber());
            sql.setDate(9, (java.sql.Date) admin.getBirthDate());
            sql.setString(10, admin.getStatus().getValue());
            
            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public void update(Admin admin) {
        DbConnector connection = new DbConnector();
        String updateSQL = "UPDATE "+table_name+" SET "+col_cpf+" = ?, "+col_name+" = ?, "+col_phone+" = ?, "+ col_cep
                                                           +" = ?, "+col_address+" = ?, "+col_email+" = ?, "+col_password+" = ?, "+col_houseNumber
                                                           +" = ?, "+col_birthDate+" = ?, "+col_status+" = ? WHERE "+col_cpf+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setString(1, admin.getCpf());
            sql.setString(2, admin.getName());
            sql.setString(3, admin.getPhone());
            sql.setString(4, admin.getCep());
            sql.setString(5, admin.getAddress());
            sql.setString(6, admin.getEmail());
            sql.setString(7, admin.getPassword());
            sql.setInt(8, admin.getHouseNumber());
            sql.setDate(9, (java.sql.Date) admin.getBirthDate());
            sql.setString(10, admin.getStatus().getValue());
            sql.setString(11, admin.getCpf());

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public void delete(Long id) {
        DbConnector connection = new DbConnector();
        String updateSQL = "UPDATE "+table_name+" SET "+col_status+" = ? WHERE "+col_id+" = ?" ;
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setString(1, Status.DISACTIVATE.getValue());
            sql.setLong(2, id);

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }  
    }
    
    public Admin getByLogin(String cpf, String password) {
        DbConnector connection = new DbConnector();
        String getSQL = "SELECT * FROM "+table_name+" WHERE "+col_cpf+" = ? AND "+col_password+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(getSQL);
            sql.setString(1, cpf);
            sql.setString(2, password);
            ResultSet result = sql.executeQuery();
            Admin admin = new Admin();

            if (result != null) {
                while (result.next()) {
                    admin.setId(result.getLong(col_id));
                    admin.setCpf(result.getString(col_cpf));
                    admin.setName(result.getString(col_name));
                    admin.setPhone(result.getString(col_phone));
                    admin.setCep(result.getString(col_cep));
                    admin.setAddress(result.getString(col_address));
                    admin.setEmail(result.getString(col_email));
                    admin.setPassword(result.getString(col_password));
                    admin.setHouseNumber(result.getInt(col_houseNumber));
                    Date birthDate = result.getDate(col_birthDate);
                    admin.setBirthDate(birthDate);
                    admin.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return admin;

        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }
    
    public Admin getByCpf(String cpf) {
        DbConnector connection = new DbConnector();
        String getSQL = "SELECT * FROM "+table_name+" WHERE "+col_cpf+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(getSQL);
            sql.setString(1, cpf);
            ResultSet result = sql.executeQuery();
            Admin admin = new Admin();

            if (result != null) {
                while (result.next()) {
                    admin.setId(result.getLong(col_id));
                    admin.setCpf(result.getString(col_cpf));
                    admin.setName(result.getString(col_name));
                    admin.setPhone(result.getString(col_phone));
                    admin.setCep(result.getString(col_cep));
                    admin.setAddress(result.getString(col_address));
                    admin.setEmail(result.getString(col_email));
                    admin.setPassword(result.getString(col_password));
                    admin.setHouseNumber(result.getInt(col_houseNumber));
                    Date birthDate = result.getDate(col_birthDate);
                    admin.setBirthDate(birthDate);
                    admin.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return admin;

        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }
}
