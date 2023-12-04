/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model.complements;

import dev.entity.BankAccount;
import dev.model.DAO;
import dev.entity.InvestmentWallet;
import dev.exceptions.NoConnectException;
import dev.model.DbConnector;
import dev.utils.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class InvestmentWalletRepository implements DAO<InvestmentWallet> {
    private final DbConnector connection;
    
    private final String table_name = "tb_investmentwallet";
    private final String col_id = "id";
    private final String col_amount_invested = "amount_invested";
    private final String col_yield_percentage = "yield_percentage";
    
    public InvestmentWalletRepository() {
        try {
            this.connection = new DbConnector();
        } catch (NoConnectException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
            throw new RuntimeException();
        }
    }
    
    private void createInvestmentWalletTable() throws NoConnectException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
                + col_id + " INT AUTO_INCREMENT PRIMARY KEY,"
                + col_amount_invested + " DOUBLE NOT NULL,"
                + col_yield_percentage + " DOUBLE NOT NULL"
                + ")";

       try {
            PreparedStatement preparedStatement = this.connection.getConnect().prepareStatement(createTableSQL);

            preparedStatement.executeUpdate();
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.INFO, "Tabela já existe ou foi criada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        }
    }
    
    @Override
    public InvestmentWallet get(Long id) {
        String getSQL = "SELECT * FROM "+table_name+" WHERE ID = ?";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            sql.setLong(1, id);
            ResultSet result = sql.executeQuery();
            InvestmentWallet investmentWallet = new InvestmentWallet();

            if (result != null) {
                while (result.next()) {
                    investmentWallet.setId(Long.valueOf(result.getString(col_id)));
                    investmentWallet.setAmountInvested(Double.valueOf(result.getString(col_amount_invested)));
                    investmentWallet.setYieldPercentage(Double.valueOf(result.getString(col_yield_percentage)));
                }
            }
            return investmentWallet;

        } catch (SQLException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
    }

    @Override
    public ArrayList<InvestmentWallet> getAll() {
        ArrayList<InvestmentWallet> investmentWalletList = new ArrayList();
         String selectSQL = "SELECT * FROM "+ table_name;
        try {
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement(selectSQL);
            ResultSet result = preparedStatement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    InvestmentWallet investmentWallet = new InvestmentWallet(
                            result.getLong(col_id),
                            result.getDouble(col_amount_invested),
                            result.getDouble(col_yield_percentage)
                    );
                    investmentWalletList.add(investmentWallet);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
        return investmentWalletList;
    }

    @Override
    public void insert(InvestmentWallet investmentWallet) {
        String insertionSQL = "INSERT INTO "+table_name+" ("+col_amount_invested+", "+col_yield_percentage+") "
                                                           + "VALUES (?,?)";
        try {
            createInvestmentWalletTable();
            
            PreparedStatement sql = connection.getConnect().prepareStatement(insertionSQL);
            sql.setDouble(1, investmentWallet.getAmountInvested());
            sql.setDouble(2, investmentWallet.getYieldPercentage());
            
            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException();
        } catch (NoConnectException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } /*finally {
            connection.closeConnection();
        }*/
    }

    @Override
    public void update(InvestmentWallet investmentWallet) {
        String updateSQL = "UPDATE "+table_name+" SET "+col_amount_invested+" = ?, "+
                           col_yield_percentage+" = ? WHERE "+col_id+" = ?";
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement(updateSQL);
            sql.setDouble(1, investmentWallet.getAmountInvested());
            sql.setDouble(2, investmentWallet.getYieldPercentage());
            sql.setDouble(3, investmentWallet.getId());

            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        } /*finally {
            connection.closeConnection();
        }*/
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement sql = connection.getConnect().prepareStatement("DELETE FROM "+table_name+" WHERE "+col_id+" = ? ");
            sql.setLong(1, id);
            sql.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BankAccountRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException();
        } /*finally {
            conexao.closeConexao();
        }*/
    }
    
    public InvestmentWallet getByLastId() {
        String getSQL = "SELECT * FROM "+table_name+" WHERE "+col_id+" = (SELECT MAX("+col_id+") FROM "+table_name+");";
        try {
            PreparedStatement sql = this.connection.getConnect().prepareStatement(getSQL);
            ResultSet result = sql.executeQuery();
            InvestmentWallet investmentWallet = new InvestmentWallet();

            if (result != null) {
                while (result.next()) {
                    investmentWallet.setId(Long.valueOf(result.getString(col_id)));
                    investmentWallet.setAmountInvested(Double.valueOf(result.getString(col_amount_invested)));
                    investmentWallet.setYieldPercentage(Double.valueOf(result.getString(col_yield_percentage)));
                }
            }
            System.out.println(investmentWallet.getId() + " From repository!");
            return investmentWallet;

        } catch (SQLException ex) {
            Logger.getLogger(InvestmentWalletRepository.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException("\nClasse: " + ex.getClass() + "\nMensagem: " + ex.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/
    }
}
