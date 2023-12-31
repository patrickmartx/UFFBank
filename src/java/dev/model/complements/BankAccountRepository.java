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
import dev.model.DbConnector;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.utils.Status;
import java.text.ParseException;

public class BankAccountRepository implements DAO<BankAccount> {
    private final String table_name = "tb_bankaccount";
    private final String col_id = "id";
    private final String col_accountBalance = "account_balance";
    private final String col_bankNumber = "bank_number";
    private final String col_accountNumber = "account_number";
    private final String col_investmentWalletId = "investment_wallet_id";
    private final String col_status = "status";
    
    public BankAccountRepository() {
    }

    private void createBankAccountTable() throws NoConnectException {
        DbConnector connection = new DbConnector();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_accountBalance + " DOUBLE NOT NULL,"
                + col_bankNumber + " INT NOT NULL,"
                + col_accountNumber + " VARCHAR(255) NOT NULL UNIQUE,"
                + col_investmentWalletId + " INT NOT NULL UNIQUE,"
                + col_status + " VARCHAR(10) NOT NULL "
                + ")";

       try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(createTableSQL);

            preparedStatement.executeUpdate();
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.INFO, "Tabela já existe ou foi criada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        } finally {
            connection.closeConnection();
        }
    }
    
    @Override
    public BankAccount get(Long id) {
        DbConnector connection = new DbConnector();
        String getSQL = "SELECT * FROM "+table_name+" WHERE ID = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(getSQL);
            sql.setLong(1, id);
            ResultSet result = sql.executeQuery();
            BankAccount bankAccount = new BankAccount();

            if (result != null) {
                while (result.next()) {
                    bankAccount.setId(Long.valueOf(result.getString(col_id)));
                    bankAccount.setAccountBalance(Double.valueOf(result.getString(col_accountBalance)));
                    bankAccount.setBankNumber(Integer.valueOf(result.getString(col_bankNumber)));
                    bankAccount.setAccountNumber(result.getString(col_accountNumber));
                    bankAccount.setInvestmentWalletId(result.getLong(col_investmentWalletId));
                    bankAccount.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return bankAccount;

        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public ArrayList getAll() {
        DbConnector connection = new DbConnector();
        ArrayList<BankAccount> bankAccountList = new ArrayList();
         String selectSQL = "SELECT * FROM "+ table_name + " WHERE "+col_status+" != " + Status.DISACTIVATE.getValue();
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    BankAccount bankAccount = new BankAccount(
                            result.getLong(col_id),
                            result.getDouble(col_accountBalance),
                            result.getInt(col_bankNumber),
                            result.getString(col_accountNumber),
                            result.getLong(col_investmentWalletId),
                            Status.valueOf(result.getString(col_status)));
                    bankAccountList.add(bankAccount);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
        return bankAccountList;
    }

    @Override
    public void insert(BankAccount bankAccount) {
        DbConnector connection = new DbConnector();
        String insertionSQL = "INSERT INTO "+table_name+" ("+col_accountBalance+", "+col_bankNumber+", "
                                                           +col_accountNumber+", "+col_investmentWalletId+", "+col_status+") "
                                                           + "VALUES (?,?,?,?,?)";
        try {
            createBankAccountTable();
            
            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setDouble(1, bankAccount.getAccountBalance());
            sql.setInt(2, bankAccount.getBankNumber());
            sql.setString(3, bankAccount.getAccountNumber());
            sql.setLong(4, bankAccount.getInvestmentWalletId());
            sql.setString(5, bankAccount.getStatus().getValue());
            
            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (NoConnectException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public void update(BankAccount bankAccount) {
        DbConnector connection = new DbConnector();
        String updateSQL = "UPDATE "+table_name+" SET "+col_accountBalance+" = ?, "+
                           col_bankNumber+" = ?, "+col_accountNumber+" = ?, "+col_investmentWalletId+" = ?, "+col_status+" = ? WHERE "+col_id+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setDouble(1, bankAccount.getAccountBalance());
            sql.setInt(2, bankAccount.getBankNumber());
            sql.setString(3, bankAccount.getAccountNumber());
            sql.setLong(4, bankAccount.getInvestmentWalletId());
            sql.setString(5, bankAccount.getStatus().getValue());
            sql.setLong(6, bankAccount.getId());

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }
    
    public BankAccount getAccountByBankNumberAndAccountNumber(Integer bankNumber, String accountNumber) {
        DbConnector connection = new DbConnector();
        String getSQL = "SELECT * FROM "+table_name+" WHERE "+col_bankNumber+" = ? AND "+col_accountNumber+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(getSQL);
            sql.setInt(1, bankNumber);
            sql.setString(2, accountNumber);
            ResultSet result = sql.executeQuery();
            BankAccount bankAccount = new BankAccount();

            if (result != null) {
                while (result.next()) {
                    bankAccount.setId(result.getLong(col_id));
                    bankAccount.setAccountBalance(Double.valueOf(result.getString(col_accountBalance)));
                    bankAccount.setBankNumber(result.getInt(col_bankNumber));
                    bankAccount.setAccountNumber(result.getString(col_accountNumber));
                    bankAccount.setInvestmentWalletId(result.getLong(col_investmentWalletId));
                    bankAccount.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            System.out.println("AQUI DO REPOSITORY: id = " + bankAccount.getId());
            return bankAccount;

        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }
}
