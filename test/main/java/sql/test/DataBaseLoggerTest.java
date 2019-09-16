package main.java.sql.test;

import main.java.sql.DataBaseLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;

public class DataBaseLoggerTest {

    @Before
    public void setUp( ) throws Exception {
        Statement stmt;
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        String DB_URL = "jdbc:h2:file:D:/github/java/ClientServer/db/test/stockExchangeTest";
        DataBaseLogger_Test.getStmt ();

    }

    @After
    public void tearDown( ) throws Exception {
    }

    @Test
    public void getDbConn( ) {
    }

    @Test
    public void getStmt( ) {
    }

    @Test
    public void createTableDb( ) {
    }

    @Test
    public void closeDataBase( ) {
    }

    @Test
    public void readTableDB( ) {
    }
}