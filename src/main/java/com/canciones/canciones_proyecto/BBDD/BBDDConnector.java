package com.canciones.canciones_proyecto.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BBDDConnector {
    private static BBDDConnector instance =  null;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/musica_proyecto?autoReconnect=true&useSSL=false";
    private Connection connection;
    private static final String USER = "root";
    private static final String PASS = "root";

    public BBDDConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
            System.out.println("Conexión exitosa");
        }catch (SQLException e) {
            System.out.println("No hay conexión");
            System.out.println(e.getErrorCode());
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static BBDDConnector GetInstance() {
        if (instance == null) {
            instance = new BBDDConnector();

            System.out.println("Instancia creada");
            System.out.println(instance.connection != null);
        }

        return instance;
    }

    public Connection GetConnection() {
        return this.connection;
    }

    public void CloseConnection() {
        if (this.connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
