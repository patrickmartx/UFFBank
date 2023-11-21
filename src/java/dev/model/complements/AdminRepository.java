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
import dev.utils.Status;

/**
 *
 * @author Patrick
 */
public class AdminRepository extends DAO {
    
    private final String col_id = "id";
    private final String col_cpf = "cpf";
    private final String col_name = "name";
    private final String col_phone = "phone";
    private final String col_cep = "cep";
    private final String col_email = "email";
    private final String col_password = "user_password";
    private final String col_houseNumber = "house_number";
    private final String col_birthDate = "birth_date";
    private final String col_status = "status";

    private void createAdminTable() throws NoConnectException {
        String createTableSQL
                = "CREATE TABLE IF NOT EXISTS tb_admin ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_cpf + " VARCHAR(15) NOT NULL UNIQUE,"
                + col_name + " VARCHAR(255) NOT NULL,"
                + col_phone + " VARCHAR(15) NOT NULL UNIQUE,"
                + col_cep + " VARCHAR(10) NOT NULL,"
                + col_email + " VARCHAR(255) NOT NULL UNIQUE,"
                + col_password + " VARCHAR(255) NOT NULL,"
                + col_houseNumber + " INT NOT NULL,"
                + col_birthDate + " DATE NOT NULL,"
                + col_status + " VARCHAR(10) NOT NULL"
                + ")";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {

            preparedStatement.executeUpdate();
            System.out.println("Tabela Admin criada ou já existente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAdmin(Admin admin) throws NoConnectException {
        createAdminTable();

        String sql = "INSERT INTO tb_admin ("+col_cpf+", "+col_name+", "+col_phone+", "
                + ""+col_cep+", "+col_email+", "+col_password+", "
                + ""+col_houseNumber+", "+col_birthDate+", "+col_status+") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, admin.getCpf());
            preparedStatement.setString(2, admin.getName());
            preparedStatement.setString(3, admin.getPhone());
            preparedStatement.setString(4, admin.getCep());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getPassword());
            preparedStatement.setInt(7, admin.getHouseNumber());
            preparedStatement.setDate(8, new java.sql.Date(admin.getBirthDate().getTimeInMillis()));
            preparedStatement.setString(9, admin.getStatus().getValue());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Administrador inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir o Administrador.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin findById(Long id) throws NoConnectException {
        String getSQL = "SELECT * FROM tb_admin WHERE "+col_id+" = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong(col_id));
                admin.setCpf(resultSet.getString(col_cpf));
                admin.setName(resultSet.getString(col_name));
                admin.setPhone(resultSet.getString(col_phone));
                admin.setCep(resultSet.getString(col_cep));
                admin.setEmail(resultSet.getString(col_email));
                admin.setPassword(resultSet.getString(col_password));
                admin.setHouseNumber(resultSet.getInt(col_houseNumber));

                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(resultSet.getDate(col_birthDate));
                admin.setBirthDate(birthDate);

                admin.setStatus(Status.valueOf(resultSet.getString(col_status)));

                return admin;
            } else {
                System.out.println("Administrador não encontrado com o ID: " + id);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Admin findByCpf(String cpf) throws NoConnectException {
        String getSQL = "SELECT * FROM tb_admin WHERE "+col_cpf+" = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong(col_id));
                admin.setCpf(resultSet.getString(col_cpf));
                admin.setName(resultSet.getString(col_name));
                admin.setPhone(resultSet.getString(col_phone));
                admin.setCep(resultSet.getString(col_cep));
                admin.setEmail(resultSet.getString(col_email));
                admin.setPassword(resultSet.getString(col_password));
                admin.setHouseNumber(resultSet.getInt(col_houseNumber));

                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(resultSet.getDate(col_birthDate));
                admin.setBirthDate(birthDate);

                admin.setStatus(Status.valueOf(resultSet.getString(col_status)));

                return admin;
            } else {
                System.out.println("Administrador não encontrado com o CPF: " + cpf);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void activateClient(Long clientId, Long accountId, String status) throws SQLException {
        
        String getSQL = "UPDATE tb_client "
                + "SET bank_account_id = ?, status = ? "
                + "WHERE id = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, status);
            preparedStatement.setLong(3, clientId);

            preparedStatement.executeQuery();
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Admin login(String cpf, String password) throws SQLException {
        String getSQL = "SELECT * FROM tb_admin WHERE "+col_cpf+" = ? AND "+col_password+" = ?";
        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return findByCpf(cpf);
                }
            }
        } catch (NoConnectException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
