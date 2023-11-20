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
import dev.entity.BankAccount;
import dev.exceptions.NoConnectException;
import dev.model.DAO;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccountRepository extends DAO {

    private void createBankAccountTable() throws NoConnectException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tb_bankaccount ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "saldo DOUBLE NOT NULL,"
                + "agencia INT NOT NULL,"
                + "num_conta VARCHAR(255) NOT NULL"
                + ")";

        try (Connection connection = this.connect(); 
             PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {

            preparedStatement.executeUpdate();
            System.out.println("Tabela de conta bancária criada ou já existente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBankAccount(BankAccount bankAccount) throws NoConnectException {
        createBankAccountTable();

        String sql = "INSERT INTO tb_bankaccount (saldo, agencia, num_conta) "
                + "VALUES (?, ?, ?)";

        try (Connection connection = this.connect(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, bankAccount.getSaldo());
            preparedStatement.setInt(2, bankAccount.getAgencia());
            preparedStatement.setString(3, bankAccount.getNumConta());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Conta bancária inserida com sucesso!");
            } else {
                System.out.println("Falha ao inserir a conta bancária.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Long idByNumber(Integer agencia, String number) throws NoConnectException {
        String getSQL = "SELECT id FROM tb_bankaccount WHERE agencia = ? AND num_conta = ?";
        
        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
        
        preparedStatement.setInt(1, agencia);
        preparedStatement.setString(2, number);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                System.out.println("Conta não encontrada");
                return null;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
