package uflybookingsystem;
import java.sql.*;
/**
 *
 * @author Steven
 */

public class DbConnector {
    public static Connection connectToDb() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/";
        final String userName = "root";
        final String password = "oveshoalm21";
        final String database = "ufly";
        
        return DriverManager.getConnection(url+database,userName,password);
                
    }
}
//"jdbc:mysql://localhost:3306/"+"ufly", "root", "oveshoalm21"