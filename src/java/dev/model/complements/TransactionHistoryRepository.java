/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model.complements;

import dev.exceptions.NoConnectException;
import dev.model.DAO;
import dev.model.DbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dev.entity.TransactionHistory;
import dev.utils.Status;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;
import dev.utils.TransactionType;
import dev.utils.Status;

/**
 *
 * @author Patrick
 */
public class TransactionHistoryRepository implements DAO<TransactionHistory>{
    private final DbConnector connection;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
    
    private final String table_name = "tb_transactionhistory";
    private final String col_id = "id";
    private final String col_value = "value";
    private final String col_transaction_date = "transaction_date";
    private final String col_transaction_type = "transaction_type";
    private final String col_id_other_account = "id_other_account";
    private final String col_status = "status";
    

    public TransactionHistoryRepository() {
        try {
            this.connection = new DbConnector();
        } catch (NoConnectException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
            throw new RuntimeException();
        }
    }
    
    private void createTransactionHistoryTable() throws NoConnectException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_value + " DOUBLE,"
                + col_transaction_date + " DATETIME,"
                + col_transaction_type + " VARCHAR(10),"
                + col_id_other_account + " INT,"
                + col_status + " VARCHAR(10) NOT NULL"
                + ")";

        try {
            PreparedStatement preparedStatement = this.connection.getConnect().prepareStatement(createTableSQL);

            preparedStatement.executeUpdate();
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.INFO, "Tabela já existe ou foi criada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        }
    }

    @Override
    public TransactionHistory get(Long id) {
        String getSQL = "SELECT * FROM "+table_name+" WHERE ID = ?";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            sql.setLong(1, id);
            ResultSet result = sql.executeQuery();
            TransactionHistory transactionHistory = new TransactionHistory();

            if (result != null) {
                while (result.next()) {
                    transactionHistory.setId(Long.valueOf(result.getString(col_id)));
                    transactionHistory.setValue(Double.valueOf(result.getString(col_value)));
                    Date transactionDate = dateFormat.parse(result.getString(col_transaction_date));
                    transactionHistory.setTransactionDate(transactionDate);
                    transactionHistory.setTransactionType(TransactionType.valueOf(result.getString(col_transaction_type)));
                    transactionHistory.setIdOtherAccount(Long.valueOf(result.getString(col_id_other_account)));
                    transactionHistory.setStatus(Status.valueOf(result.getString(col_status)));
                }
            }
            return transactionHistory;

        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (ParseException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<TransactionHistory> transactionHistoryList = new ArrayList();
         String selectSQL = "SELECT * FROM "+ table_name + " WHERE "+col_status+" != " + Status.DISACTIVATE.getValue();
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    TransactionHistory transactionHistory = new TransactionHistory(
                            result.getLong(col_id),
                            result.getDouble(col_value),
                            result.getDate(col_transaction_date),
                            TransactionType.valueOf(result.getString(col_transaction_type)),
                            result.getLong(col_id_other_account),
                            Status.valueOf(result.getString(col_status)));
                    transactionHistoryList.add(transactionHistory);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }
        return transactionHistoryList;
    }

    @Override
    public void insert(TransactionHistory transactionHistory) {
        String insertionSQL = "INSERT INTO "+table_name+" ("+col_value+", "+col_transaction_date+", "
                                                           +col_transaction_type+", "+col_id_other_account+", "+col_status+") "
                                                           + "VALUES (?,?,?,?,?)";
        try {
            createTransactionHistoryTable();
            
            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setDouble(1, transactionHistory.getValue());
            sql.setDate(2, (java.sql.Date) transactionHistory.getTransactionDate());
            sql.setString(3, transactionHistory.getTransactionType().getValue());
            sql.setLong(4, transactionHistory.getIdOtherAccount());
            sql.setString(5, transactionHistory.getStatus().getValue());
            
            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } catch (NoConnectException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.closeConnection();
        }
    }

    @Override
    public void update(TransactionHistory transactionHistory) {
        
        String updateSQL = "UPDATE "+table_name+" SET "+col_value+" = ?, "+
                           col_transaction_date+" = ?, "+col_transaction_type+" = ?, "+col_id_other_account+" = ?, "+col_status+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setDouble(1, transactionHistory.getValue());
            sql.setDate(2, (java.sql.Date) transactionHistory.getTransactionDate());
            sql.setString(3, transactionHistory.getTransactionType().getValue());
            sql.setLong(4, transactionHistory.getIdOtherAccount());
            sql.setString(5, transactionHistory.getStatus().getValue());
            
            sql.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TransactionHistoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } finally {
            connection.closeConnection();
        }  
    }
}