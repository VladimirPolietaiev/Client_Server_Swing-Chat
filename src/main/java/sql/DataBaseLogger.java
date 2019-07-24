package main.java.sql;

import java.sql.*;

public class DataBaseLogger {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        createTableDb("CREATE TABLE IF NOT EXISTS  REGISTRATION (" +
                " id INTEGER not NULL," +
                " first VARCHAR(255)," +
                " last VARCHAR(255)," +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))");


//        addDataBase ( "INSERT INTO Registration VALUES (100, 'Zara', 'Ali', 18)" );
//        addDataBase ( "INSERT INTO Registration VALUES (101, 'Zara', 'Ali', 19)" );
//        addDataBase ( "INSERT INTO Registration VALUES (102, 'Zara', 'Ali', 18)" );
//        addDataBase ( "INSERT INTO Registration VALUES (103, 'Zara', 'Ali', 17)" );
//        addDataBase ( "Registration", 105, "Zara", "Ali", 16);


//        updataDb("Registration","last","lik",104);

        deleteDataUS("REGISTRATION", 106);

        readTableDB( "id", "first", "last", "age", "Registration");

        closeDataBase ();


    }

    private static Connection getDbConn( ) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:file:D:/github/java/ClientServer/db/stockExchange";
        try {
            Class.forName ( JDBC_DRIVER );
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace ( );
        }
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection ( DB_URL );
        } catch (SQLException e1) {
            e1.printStackTrace ( );
        }
        return dbConn;
    }

    private static Statement getStmt() throws SQLException {
        Statement stmt = null;
        stmt = getDbConn ( ).createStatement ( );
        return stmt;
    }

    private static void createTableDb(String sql) throws SQLException {
            getStmt().executeUpdate ( sql );
            System.out.println("Created table in given database...");
    }

    private static void addDataBase(String nameTable, Integer idSet, String firstSet, String lastSet, Integer ageSet) throws SQLException {
        String sql = "INSERT INTO " + nameTable + " VALUES " + "(" + idSet + ", " + "'"+ firstSet + "'" + ", " + "'" + lastSet + "'" + ", " + ageSet + " )";
        getStmt().executeUpdate ( sql );
        System.out.println ( "Inserted records into the table..." );
        getDbConn ( ).commit ( );
        getDbConn ( ).rollback ( );
    }

    private static void updataDb(String nameTable, String firstSet, String updataString,  Integer idSet) throws SQLException {
        String sql = "UPDATE " + nameTable +" SET " + firstSet + " = "+ "'" +updataString + "' WHERE id"  + " in (" + idSet + ")";
            getStmt().executeUpdate(sql);
            System.out.println ( "Update DataBase into the table..." );
    }

    private static void closeDataBase( ) {
        try {
            if (getDbConn ( ) != null) getDbConn ( ).close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
        try {
            if (getStmt() != null) getStmt().close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
    }

    private static void readTableDB(String idSet, String firstSet, String lastSet, String ageSet, String nameTable ) throws SQLException {
        // STEP 4: Extract data from result set

        String sql = "SELECT " + idSet + ", " + firstSet + ", " + lastSet + ", " + ageSet + " " + "FROM " + nameTable;
        ResultSet rs = getResultSet(sql);
            while(rs.next()) {
                // Retrieve by column name
                int id=rs.getInt("id");
                int age=rs.getInt("age");
                String first=rs.getString("first");
                String last=rs.getString("last");
                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);

            }
    }

    private static ResultSet getResultSet(String sql) throws SQLException {
            ResultSet rs = null;
            rs = getStmt().executeQuery (sql);
            return rs;
    }

    private static void deleteDataUS(String nameTable, Integer idSet) throws SQLException {
        String sql ="DELETE FROM " + nameTable + " WHERE ID = " + idSet ;
            getStmt().executeUpdate(sql);
    }

}




