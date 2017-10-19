/*
 * This class contains all the common operations that involve retreiving data from the database, 
 * saving data to the database or updating existing database data 
 * for all three tables (Location, Flight and Booking)
 */
package uflybookingsystem;

import BusinessObject.Booking;
import BusinessObject.Flight;
import BusinessObject.Location;
import Imports.Eng.AirportCodes;
import Imports.Eng.ConvertPlane;
import Imports.Eng.FlightNumbers;
import Imports.Eng.FromSQL_Time;
import Imports.Eng.ToSQL_Time;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DatabaseOperations {
    //DbConnector connector = new DbConnector();
    static ArrayList<Location> locations = new ArrayList();
    static ArrayList<Flight> flights = new ArrayList();
   
    //method that gets all the information from the Location table
    public static ArrayList<Location> getAllLocations(){
        locations.clear();
        try(Connection connection = DbConnector.connectToDb()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM location");
            while(rs.next()){
                locations.add(new Location(rs.getString("city"),
                        rs.getString("AirportCode")));
            }
        }
        catch(SQLException sqle){
            Print.ln(sqle.getMessage());
        }
        return locations;
    }
    
    //this method obtains all the information from the Flight table based on the departure and destination airports as well as travel date
    public static ArrayList<Flight> GetAllFlightsForLocation(
            String departure, String destination, java.util.Date travelDate){
        flights.clear();
        String sql = "SELECT * FROM flight WHERE DepartureAirport = '"+departure+
                "' AND DestinationAirport = '"+destination+"' AND DateTime = '"+
                 travelDate+"'";
        
        try(Connection connection = DbConnector.connectToDb()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                flights.add(new Flight(departure,destination));
            }
        }
        catch(SQLException sqle){
            Print.ln(sqle.getMessage());
        }
        return flights;
    }
    
    //this method adds booking passed as a parameter to the Booking table in the uFly database
    //note that Booking number is set as an incrementing field, so it doesn't need to be set
    public static void AddBooking(Booking booking){
        String sql = "SELECT * FROM booking";
	try (Connection connection = DbConnector.connectToDb()){
             Statement statement = connection.createStatement(ResultSet.
                     TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery(sql);
            
            resultSet.moveToInsertRow();
            resultSet.updateString("FlightNumber", booking.getFlightNo());
            resultSet.updateDouble("Price", booking.getPrice());
            resultSet.updateString("CabinClass", booking.getCabinClass());
            resultSet.updateInt("Quantity", booking.getQuantity());
            resultSet.updateByte("Insurance", booking.getInsuranceStat());    
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
    }
    
    //this method obtains the flight based on the flightNumber parameter
    public static Flight getFlightByFlightNumber(String flightNumber){
        System.out.println("GETTING FLIGHT NO"+flightNumber);
        try(Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flight "
                    + "WHERE FlightNumber = '"+flightNumber + "';")){
            
            while(resultSet.next()){
                return new Flight(resultSet.getString("FlightNumber"),
                    resultSet.getString("DepartureAirport"),resultSet.getString(
                            "DestinationAirport"),resultSet.getDouble("Price"),
                        String.valueOf(resultSet.getDate("FlightDate")),
                    ConvertPlane.convertPlaneEnum(resultSet.getString("Plane")),
                        resultSet.getInt("SeatsTaken"));
            }
        }
        catch(SQLException sqle){
             System.out.println("getFlightByFLightNumber(): "+sqle.toString());
        }
        return null;
    }
    
     //this method obtains the flight based on the flightNumber parameter
    public static Location getLocationByAirportCode(String airportCode){
	try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Location "
                    + "WHERE AirportCode = '" + airportCode + "'")){
            
            while (resultSet.next()) {
            	Location location = new Location(resultSet.getString("City"),
                resultSet.getString("AirportCode"));
            	return location;
            }
        }
        catch(SQLException sqle){
            System.out.println("getLocationByCode(): "+sqle.toString());
        }  
        return null;
    }
    
    //this method adds location passed as a parameter to the Location table in the uFly database
    public static void AddLocation(Location location){ 
        String sql = "INSERT INTO location (City, AirportCode) VALUES"
                +"('"+location.getCity()+"', '"+location.getAirportCode()+"')"; 

	try (Connection connection = DbConnector.connectToDb()){

            Statement statement = connection.createStatement(ResultSet.
                     TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(sql);
        }
        catch(SQLException sqle){
            System.out.println("AddLocation(): "+sqle.toString());
        } 
    }
     
    //this method adds a flight passed as a para`meter to the Flight table in the uFly database
    public static void AddFlight(Flight flight){
        System.out.println("ADDING FLIGHT");
        String sql = "INSERT INTO flight (FlightNumber, DepartureAirport, "
                +"DestinationAirport, Price, FlightDate, Plane, SeatsTaken) "
                +"VALUES('";
        try(Connection connection = DbConnector.connectToDb()){
            
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sql += flight.getFlightNo()+"', '";
            sql += flight.getDepAirport()+"', '";
            sql += flight.getDestAirport()+"', '";
            sql += flight.getPrice()+"', '";
            //convert time
            ToSQL_Time toSqlTime = new ToSQL_Time(flight.getDate());
            sql += toSqlTime.rearrangedValue()+"', '";
            sql += flight.getPlane()+"', '";
            sql += flight.getSeatsTaken()+"');";
            statement.executeUpdate(sql);
        }
        catch(SQLException sqle){
            System.out.println("AddFlight(): "+sqle.toString());
        }
    }

    //this method updates the location to the one passed to it as a parameter where the airport codes are matching
    public static void UpdateLocation(Location location){
        System.out.println("UPDATEING LOCATIONS");
        String chkSql = "SELECT AirportCode FROM location";
        try (Connection connection = DbConnector.connectToDb();
            Statement chkStatement = connection.createStatement();
            //chkStatement = 
            ResultSet rs = chkStatement.executeQuery(chkSql)){
            while(rs.next()){
                if(!(AirportCodes.getCurrentAirportCodes().contains(
                        rs.getString("AirportCode")))){
                    try(Connection connection2 = DbConnector.connectToDb()){
                    Statement delStmnt = connection2.createStatement(ResultSet.
                            TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet delRs = delStmnt.executeQuery("SELECT * FROM Location"
                            + " WHERE AirportCode = '"+rs.getString("AirportCode")+
                            "';");
                    delRs.first();
                    delRs.deleteRow();
                    }
                    catch(SQLException sqle){
                        System.out.println("DELETING----------"+sqle.getMessage());
                    }
                }
            }
        }
        catch(SQLException sqle){
            System.out.println("CheckStmt AddLocation(): "+sqle.getMessage());
        }
        try(Connection connection = DbConnector.connectToDb()){
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM location"
                    + " WHERE AirportCode = '"+location.getAirportCode()+"';");
            resultSet.absolute(1);
            resultSet.updateString("City", location.getCity());  
            resultSet.updateRow();
            System.out.println("Updated Location to "+location.getCity());
        }
        catch(SQLException sqle){
            System.out.println("UpdateLocation() "+sqle.getMessage());
        }
    }
    
    //this method updates the flight to the one passed to it as a parameter where the flight numbers are matching
    public static void UpdateFlight(Flight flight){
        System.out.println("UPDATEING FLIGHTS");
        String chkSql = "SELECT FlightNumber FROM flight";
        try (Connection connection = DbConnector.connectToDb();
            Statement chkStatement = connection.createStatement();
            ResultSet rs = chkStatement.executeQuery(chkSql)){
            while(rs.next()){
                if(!(FlightNumbers.getCurrentFlightNumbers().contains(
                        rs.getString("FlightNumber")))){                       
                    try(Connection connection2 = DbConnector.connectToDb()){
                    Statement delStmnt = connection2.createStatement(ResultSet.
                            TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet delRs = delStmnt.executeQuery("SELECT * FROM flight"
                            + " WHERE FlightNumber = '"+rs.getString("FlightNumber")+
                            "';");
                    delRs.first();
                    delRs.deleteRow();
                    }
                    catch(SQLException sqle){
                        System.out.println(sqle.getMessage());
                    }
                }
            }
        }
        catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        try(Connection connection = DbConnector.connectToDb()){
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flight"
                    + " WHERE FlightNumber = '"+flight.getFlightNo()+"';");
            resultSet.absolute(1);
            resultSet.updateString("DepartureAirport", flight.getDepAirport());
            resultSet.updateString("DestinationAirport", flight.getDestAirport());
            resultSet.updateDouble("Price", flight.getPrice());
            ToSQL_Time toSqlTime = new ToSQL_Time(flight.getDate());
            resultSet.updateString("FlightDate", toSqlTime.rearrangedValue());
            resultSet.updateString("Plane", flight.getPlane());
            System.out.println("process: "+resultSet.getString("SeatsTaken")+
                    "New value: "+flight.getSeatsTaken());
            resultSet.updateInt("SeatsTaken", flight.getSeatsTaken());
            resultSet.updateRow();
            System.out.println("Updated Flights");
        }
        catch(SQLException sqle){
            System.out.println("UpdateFlight() "+sqle.getMessage());
        }
    }

    public static ArrayList<Flight> getAllFlights(String from, String to){ 
        java.util.regex.Matcher matcher;
        java.util.regex.Pattern pattern;
        String regex = "[A-Z]{3}";
        pattern = Pattern.compile(regex);
        
        String toCode = "";
        String fromCode = "";
        matcher = pattern.matcher(to);
        while(matcher.find()){
            toCode += matcher.group(0);
        }
        matcher = pattern.matcher(from);
        while(matcher.find()){
            fromCode += matcher.group(0);
        }
        
        String sql = "SELECT * FROM flight WHERE "
                + "DepartureAirport = '"+fromCode+"' AND DestinationAirport"
                + " = '"+toCode+"';";
        flights.clear();
        try(Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
            FromSQL_Time fromSql;
            while(rs.next()){
                fromSql = new FromSQL_Time(rs.getString("FlightDate"));
                flights.add(new Flight(rs.getString("FlightNumber"),
                        rs.getString("DepartureAirport"),
                        rs.getString("DestinationAirport"),
                        rs.getDouble("Price"),fromSql.rearrangedValue(),
                        ConvertPlane.convertPlaneEnum(rs.getString("Plane")),
                        rs.getInt("SeatsTaken")));
            }
            return flights;
        }
        catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return null;
    }

}
