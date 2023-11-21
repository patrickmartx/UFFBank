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

    private void createAdminTable() throws NoConnectException {
        String createTableSQL
                = "CREATE TABLE IF NOT EXISTS tb_admin ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "cpf VARCHAR(15) NOT NULL UNIQUE,"
                + "name VARCHAR(255) NOT NULL,"
                + "phone VARCHAR(15) NOT NULL UNIQUE,"
                + "cep VARCHAR(10) NOT NULL,"
                + "email VARCHAR(255) NOT NULL UNIQUE,"
                + "password VARCHAR(255) NOT NULL,"
                + "house_number INT NOT NULL,"
                + "birth_date DATE NOT NULL,"
                + "status VARCHAR(10) NOT NULL"
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

        String sql = "INSERT INTO tb_admin (cpf, name, phone, "
                + "cep, email, password, "
                + "house_number, birth_date, status) "
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
        String getSQL = "SELECT * FROM tb_admin WHERE id = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setCpf(resultSet.getString("cpf"));
                admin.setName(resultSet.getString("name"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setCep(resultSet.getString("cep"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setHouseNumber(resultSet.getInt("house_number"));

                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(resultSet.getDate("birth_date"));
                admin.setBirthDate(birthDate);

                admin.setStatus(Status.valueOf(resultSet.getString("status")));

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
        String getSQL = "SELECT * FROM tb_admin WHERE cpf = ?";

        try (Connection connection = this.connect(); 
                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setCpf(resultSet.getString("cpf"));
                admin.setName(resultSet.getString("name"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setCep(resultSet.getString("cep"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setHouseNumber(resultSet.getInt("house_number"));

                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate("birth_date"));
                admin.setBirthDate(dataNascimento);

                admin.setStatus(Status.valueOf(resultSet.getString("status")));

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
                + "SET bankAccountId = ?, status = ? "
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

    public Admin Login(String cpf, String password) throws SQLException {
        String getSQL = "SELECT * FROM tb_admin WHERE cpf = ? AND password = ?";
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
