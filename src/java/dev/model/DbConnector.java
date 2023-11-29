/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model;

import dev.exceptions.NoConnectException;
import dev.services.impl.AdminServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class DbConnector {

    private Connection connection;
    private Properties properties;

    public DbConnector() throws NoConnectException {
        loadDatabaseProperties();

        Connection doConnection = null;
        try {
            String driver = properties.getProperty("db.driver");
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            Class.forName(driver);
            doConnection = DriverManager.getConnection(url, user, password);
            this.connection = doConnection;
        } catch (Exception e) {
            throw new NoConnectException("Não é possível conectar ao banco de dados\n"
                    + "Procure acertar a informação: " + e.getMessage());
        }
    }

    private void loadDatabaseProperties() {
        try {
            properties = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("resources/database.properties");
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, "Informação inválida. Mensagem: {0}", ex.getMessage());
        }
    }

    public Connection connect() throws NoConnectException {
        Connection doConnection = null;
        try {
            String driver = properties.getProperty("db.driver");
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            Class.forName(driver);
            doConnection = DriverManager.getConnection(url, user, password);
            return doConnection;
        } catch (Exception e) {
            throw new NoConnectException("Não é possível conectar ao banco de dados\n"
                    + "Procure acertar a informação: " + e.getMessage());
        }
    }

    public Connection getConnect() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, "Erro de conexão. Mensagem: {0}", ex.getMessage());
        }
    }
}
