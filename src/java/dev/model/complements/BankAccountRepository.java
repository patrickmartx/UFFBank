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
    private final DbConnector connection;
    
    private final String table_name = "tb_bankaccount";
    private final String col_id = "id";
    private final String col_accountBalance = "account_balance";
    private final String col_bankNumber = "bank_number";
    private final String col_accountNumber = "account_number";
    private final String col_status = "status";
    
    public BankAccountRepository() {
        try {
            this.connection = new DbConnector();
        } catch (NoConnectException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
            throw new RuntimeException();
        }
    }

    private void createBankAccountTable() throws NoConnectException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_accountBalance + " DOUBLE NOT NULL,"
                + col_bankNumber + " INT NOT NULL,"
                + col_accountNumber + " VARCHAR(255) NOT NULL,"
                + col_status + " VARCHAR(10) NOT NULL, "
                + ")";

       try {
            PreparedStatement preparedStatement = this.connection.getConnect().prepareStatement(createTableSQL);

            preparedStatement.executeUpdate();
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.INFO, "Tabela já existe ou foi criada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        }
    }
    
    @Override
    public BankAccount get(Long id) {
        String getSQL = "SELECT * FROM "+table_name+" WHERE ID = ?";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            sql.setLong(1, id);
            ResultSet result = sql.executeQuery();
            BankAccount bankAccount = new BankAccount();

            if (result != null) {
                while (result.next()) {
                    bankAccount.setId(Long.valueOf(result.getString(col_id)));
                    bankAccount.setAccountBalance(Double.valueOf(result.getString(col_accountBalance)));
                    bankAccount.setBankNumber(Integer.valueOf(result.getString(col_bankNumber)));
                    bankAccount.setAccountNumber(result.getString(col_accountNumber));
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
        
        String insertionSQL = "INSERT INTO "+table_name+" ("+col_accountBalance+", "+col_bankNumber+", "
                                                           +col_accountNumber+", "+col_status+") "
                                                           + "VALUES (?,?,?,?,?)";
        try {
            createBankAccountTable();
            
            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setDouble(1, bankAccount.getAccountBalance());
            sql.setInt(2, bankAccount.getBankNumber());
            sql.setString(3, bankAccount.getAccountNumber());
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
        String updateSQL = "UPDATE "+table_name+" SET "+col_accountBalance+" = ?, "+
                           col_bankNumber+" = ?, "+col_accountNumber+" = ?, "+col_status+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setDouble(1, bankAccount.getAccountBalance());
            sql.setInt(2, bankAccount.getBankNumber());
            sql.setString(3, bankAccount.getAccountNumber());
            sql.setString(5, bankAccount.getStatus().getValue());

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

//    public void insertBankAccount(BankAccount bankAccount) throws NoConnectException {
//        createBankAccountTable();
//
//        String sql = "INSERT INTO tb_bankaccount ("+col_accountBalance+", "+col_bankNumber+", "+col_accountNumber+") "
//                + "VALUES (?, ?, ?)";
//
//        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setDouble(1, bankAccount.getAccountBalance());
//            preparedStatement.setInt(2, bankAccount.getBankNumber());
//            preparedStatement.setString(3, bankAccount.getAccountNumber());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Conta bancária inserida com sucesso!");
//            } else {
//                System.out.println("Falha ao inserir a conta bancária.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public BankAccount getAccountById(Long id) throws NoConnectException {
//        String getSQL = "SELECT * FROM tb_bankaccount WHERE "+col_id+" = ?";
//
//        try (Connection connection = this.connect(); 
//                PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
//
//            preparedStatement.setLong(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                BankAccount bankAccount =  new BankAccount();
//                
//                bankAccount.setId(resultSet.getLong(col_id));
//                bankAccount.setAccountNumber(resultSet.getString(col_accountNumber));
//                bankAccount.setBankNumber(resultSet.getInt(col_bankNumber));
//                bankAccount.setAccountBalance(resultSet.getDouble(col_accountBalance));
//                
//                return bankAccount;
//            } else {
//                System.out.println("Conta bancária não encontrada");
//                return null;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public Long getIdByAccount(Integer agencia, String number) throws NoConnectException {
//        String getSQL = "SELECT "+col_id+" FROM tb_bankaccount WHERE "+col_bankNumber+" = ? AND "+col_accountNumber+" = ?";
//
//        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
//
//            preparedStatement.setInt(1, agencia);
//            preparedStatement.setString(2, number);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getLong("id");
//            } else {
//                System.out.println("Conta não encontrada");
//                return null;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public Double getSaldoById(Long id) throws NoConnectException, SQLException {
//        String getSQL = "SELECT "+col_accountBalance+" FROM tb_bankaccount WHERE "+col_id+" = ?";
//        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(getSQL)) {
//
//            preparedStatement.setLong(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getDouble(col_accountBalance);
//            } else {
//                System.out.println("Conta não encontrada");
//                return null;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public void transfer(Double value, Integer bankNumberSender, String accountNumberSender,
//            Integer bankAccountReceiver, String accountNumberReceiver) throws NoConnectException {
//
//        try (Connection connection = this.connect()) {
//            connection.setAutoCommit(false);
//
//            Long idSenderAccount = getIdByAccount(bankNumberSender, accountNumberSender);
//            Double balanceSenderAccount = getSaldoById(idSenderAccount);
//
//            Long idReceiverAccount = getIdByAccount(bankAccountReceiver, accountNumberReceiver);
//            Double idReceiverBallance = getSaldoById(idReceiverAccount);
//
//            String updateSQL = "UPDATE tb_bankaccount SET "+col_accountBalance+" = ? WHERE "+col_id+" = ?";
//            try (PreparedStatement preparedStatementRemetente = connection.prepareStatement(updateSQL); PreparedStatement preparedStatementDestino = connection.prepareStatement(updateSQL)) {
//
//                preparedStatementRemetente.setDouble(1, balanceSenderAccount - value);
//                preparedStatementRemetente.setLong(2, idSenderAccount);
//                preparedStatementRemetente.executeUpdate();
//
//                preparedStatementDestino.setDouble(1, idReceiverBallance + value);
//                preparedStatementDestino.setLong(2, idReceiverAccount);
//                preparedStatementDestino.executeUpdate();
//
//                connection.commit();
//            } catch (SQLException ex) {
//                connection.rollback();
//                Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                connection.setAutoCommit(true);
//            }
//        } catch (SQLException | NoConnectException ex) {
//            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void deposit(Double value, Integer bankNumber, String numberAccount) throws NoConnectException {
//
//        try (Connection connection = this.connect()) {
//            connection.setAutoCommit(false);
//
//            Long idAccount = getIdByAccount(bankNumber, numberAccount);
//            Double BalanceAccount = getSaldoById(idAccount);
//
//            String updateSQL = "UPDATE tb_bankaccount SET "+col_accountBalance+" = ? WHERE "+col_id+" = ?";
//            try (PreparedStatement preparedStatementRemetente = connection.prepareStatement(updateSQL); PreparedStatement preparedStatementDestino = connection.prepareStatement(updateSQL)) {
//
//                preparedStatementRemetente.setDouble(1, BalanceAccount + value);
//                preparedStatementRemetente.setLong(2, idAccount);
//                preparedStatementRemetente.executeUpdate();
//
//                connection.commit();
//            } catch (SQLException ex) {
//                connection.rollback();
//                Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                connection.setAutoCommit(true);
//            }
//        } catch (SQLException | NoConnectException ex) {
//            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
