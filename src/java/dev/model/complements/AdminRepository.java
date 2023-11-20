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
                + "nome VARCHAR(255) NOT NULL,"
                + "telefone VARCHAR(15) NOT NULL UNIQUE,"
                + "cep VARCHAR(10) NOT NULL,"
                + "email VARCHAR(255) NOT NULL UNIQUE,"
                + "password VARCHAR(255) NOT NULL,"
                + "numero_casa INT NOT NULL,"
                + "data_nascimento DATE NOT NULL,"
                + "status VARCHAR(10) NOT NULL"
                + ")";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {

            preparedStatement.executeUpdate();
            System.out.println("Tabela cliente criada ou já existente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAdmin(Admin admin) throws NoConnectException {
        createAdminTable();

        String sql = "INSERT INTO tb_admin (cpf, nome, telefone, "
                + "cep, email, password, "
                + "numero_casa, data_nascimento, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, admin.getCpf());
            preparedStatement.setString(2, admin.getNome());
            preparedStatement.setString(3, admin.getTelefone());
            preparedStatement.setString(4, admin.getCep());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getPassword());
            preparedStatement.setInt(7, admin.getNumeroCasa());
            preparedStatement.setDate(8, new java.sql.Date(admin.getDataNascimento().getTimeInMillis()));
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

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setCpf(resultSet.getString("cpf"));
                admin.setNome(resultSet.getString("nome"));
                admin.setTelefone(resultSet.getString("telefone"));
                admin.setCep(resultSet.getString("cep"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setNumeroCasa(resultSet.getInt("numero_casa"));

                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate("data_nascimento"));
                admin.setDataNascimento(dataNascimento);
                
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
    
    public Admin findByCPF(String cpf) throws NoConnectException {
        String getSQL = "SELECT * FROM tb_admin WHERE cpf = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {

            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setCpf(resultSet.getString("cpf"));
                admin.setNome(resultSet.getString("nome"));
                admin.setTelefone(resultSet.getString("telefone"));
                admin.setCep(resultSet.getString("cep"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setNumeroCasa(resultSet.getInt("numero_casa"));

                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate("data_nascimento"));
                admin.setDataNascimento(dataNascimento);
                
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
    
    public void ativateClient(Long clientId, Long accountId, String status) throws SQLException {
        String getSQL = "UPDATE tb_client "
                      + "SET bank_account_id = ?, status = ? "
                      + "WHERE id = ?";
        
        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, status);
            preparedStatement.setLong(3, clientId);
        } 
        catch (NoConnectException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
