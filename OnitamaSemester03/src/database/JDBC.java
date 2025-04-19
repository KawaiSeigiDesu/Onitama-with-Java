
package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBC {
    
    private static final String CONFIG_PATH = "src\\Database\\config.properties";
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String ipAddress;
    private String port;
    private String database;
    private String username;
    private String password;
    
    private static JDBC instance;
    
    private JDBC(String ipAddress, String port, String database, String username, String password) {
        setIpAddress(ipAddress);
        setPort(port);
        setDatabase(database);
        setUsername(username);
        setPassword(password);
    }
    
    public static JDBC getInstance(String ipAddress, String port, String database, String username, String password) {
        if(instance == null) {
            return new JDBC(ipAddress, port, database, username, password);
        } else {
            return instance;
        }
    }
    
    public static JDBC getInstance() {
        if(instance == null) {
            try {
                File configFile = new File(CONFIG_PATH);
                FileReader fileReader = new FileReader(configFile);
                Properties properties = new Properties();
                properties.load(fileReader);
                
                return new JDBC(
                        null, 
                        properties.getProperty("port"), 
                        properties.getProperty("database"), 
                        properties.getProperty("username"), 
                        properties.getProperty("password"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException("Error getting properties", ex);
            } catch (IOException ex) {
                throw new RuntimeException("Error getting properties", ex);
            }
        } else {
            return instance;
        }
    }

    public void setIpAddress(String ipAddress) {
        if (ipAddress == null) {
            InetAddress myIP;
            try {
                myIP = InetAddress.getLocalHost();
                this.ipAddress = myIP.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } 
        else {
            this.ipAddress = ipAddress;
        }
    }

    public void setPort(String port) {
        if(port == null) {
            this.port = "1433";
        } else {
            this.port = port;
        }
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String getURL() {
        return "jdbc:sqlserver://" + ipAddress + "\\SQLEXPRESS:" + port + ";databaseName=" + database;
    }
    
    public Connection getConnection() {
        try {
            Class.forName(JDBC.JDBC_DRIVER);
            return DriverManager.getConnection(getURL(),username, password);
        } catch(SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-------------------- JDBC Configuration --------------------+").append("\n");
        sb.append("| JDBC Driver: ").append(String.format("%-" + 45 + "s",JDBC_DRIVER)).append(" |\n");
        sb.append("| IP Address : ").append(String.format("%-" + 45 + "s",ipAddress)).append(" |\n");
        sb.append("| Port       : ").append(String.format("%-" + 45 + "s",port)).append(" |\n");
        sb.append("| Database   : ").append(String.format("%-" + 45 + "s",database)).append(" |\n");
        sb.append("| Username   : ").append(String.format("%-" + 45 + "s",username)).append(" |\n");
        sb.append("| Password   : ").append(String.format("%-" + 45 + "s",password)).append(" |\n");
        sb.append("+------------------------------------------------------------+").append("\n");

        return sb.toString();
    }
}
