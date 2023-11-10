/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model;

import dev.exceptions.NoConnectException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;

public class DAO {

    private Properties properties;

    public DAO() {
        loadDatabaseProperties();
    }

    private void loadDatabaseProperties() {
        try {
            properties = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("resources/databaseTest.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar as configurações do banco de dados: " + e.getMessage(), e);
        }
    }

    protected Connection connect() throws NoConnectException {
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
}
