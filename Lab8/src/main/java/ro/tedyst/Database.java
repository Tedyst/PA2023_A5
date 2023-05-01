package ro.tedyst;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String DB_URL = "jdbc:h2:mem:test";
    private final static String USER = "sa";
    private final static String PASSWORD = "";
    private static BasicDataSource basicDS;

    public static Connection getConn() throws SQLException {
        if(basicDS == null){
            basicDS = new BasicDataSource();
            basicDS.setDriverClassName("org.h2.Driver");
            basicDS.setUrl(DB_URL);
            basicDS.setUsername(USER);
            basicDS.setPassword(PASSWORD);
            basicDS.setInitialSize(5);
            basicDS.setMaxActive(10);
        }
        return basicDS.getConnection();
    }

    private Database() {}
}
