package main.java.sql.test;

import main.java.sql.DataBaseLogger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseLoggerTest {

    @Before
    public void setUp( ) throws Exception {
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        String DB_URL = "jdbc:h2:file:D:/github/java/ClientServer/db/test/stockExchangeTest";
        DataBaseLogger_Test.setStmt (DB_URL);
        DataBaseLogger_Test.setDbConn ( DB_URL );
    }

    @After
    public void tearDown( ) throws Exception {
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();

        DataBaseLogger_Test.closeDataBase ();
    }

    @Test
    public void createTableDbTestTableNameEquals( ) throws SQLException {
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        DatabaseMetaData metaData = null;
        DataBaseLogger_Test.createTableDb("CREATE TABLE IF NOT EXISTS  TABLELOGTEST (" +
                " id INTEGER not NULL," +
                " userName VARCHAR(255)," +
                " userMessage VARCHAR(255)," +
                " date TIMESTAMP, " +
                " PRIMARY KEY ( id ))");

    {
        try {
            metaData = DataBaseLogger_Test.getDbConn().getMetaData ();
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    ResultSet resMetaData = metaData.getTables  ( null,null,"TABLELOGTEST",null );

        String resTableName = null;
        while (resMetaData.next()) {
             resTableName = resMetaData.getString ( 3 );
             System.out.println("result in while: " + resTableName );

        }
    Assert.assertEquals(resTableName,"TABLELOGTEST");
}


//    @Test
//    public void createTableDb2( ) throws SQLException {
//        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
//        DatabaseMetaData metaData = null;
//        DataBaseLogger_Test.createTableDb("CREATE TABLE IF NOT EXISTS  TABLELOGTEST (" +
//                " id INTEGER not NULL," +
//                " userName VARCHAR(255)," +
//                " userMessage VARCHAR(255)," +
//                " date TIMESTAMP, " +
//                " PRIMARY KEY ( id ))");
//        {
//            try {
//                metaData = DataBaseLogger_Test.getDbConn ( )
//                        .getMetaData ();
//            } catch (SQLException e) {
//                e.printStackTrace ( );
//            }
//        }
//        ResultSet resMetaData = metaData.getTables  ( null,null,"TABLELOGTEST",null );
//        while (resMetaData.next()) {
//            String str = resMetaData.getString ( 3 );
//            System.out.println("result in while: " + str);
//        }
//        Assert.assertEquals(str,"TABLELOGTEST");
//    }

//    @Test
//    public void createTableDb3( ) throws SQLException {
//        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
//        String DB_URL = "jdbc:h2:file:D:/github/java/ClientServer/db/test/stockExchangeTest";
//        DataBaseLogger_Test.setStmt (DB_URL);
//        DataBaseLogger_Test.setDbConn ( DB_URL );
//
//        DataBaseLogger_Test.createTableDb("CREATE TABLE IF NOT EXISTS  TABLELOGTEST (" +
//                " id INTEGER not NULL," +
//                " userName VARCHAR(255)," +
//                " userMessage VARCHAR(255)," +
//                " date TIMESTAMP, " +
//                " PRIMARY KEY ( id ))");
//
//        DatabaseMetaData metaData = null;
//        {
//            try {
//                DataBaseLogger_Test.setDbConn (DB_URL);
//                metaData = DataBaseLogger_Test.getDbConn().getMetaData ();
//            } catch (SQLException e) {
//                e.printStackTrace ( );
//            }
//        }
//
//        ResultSet ResMetaData = metaData.getTables  ( null,null,"TABLELOGTEST",null );
//
//        while (ResMetaData.next()) {
//            String str = ResMetaData.getString ( 3 );
//            System.out.println("result in while!!: " + str );
//        }
//
//
//        Assert.assertEquals(str,"TABLELOGTEST");
//    }

    @Test
    public void addDataBaseTestCheckValueNameUser() throws SQLException {
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        String userNameSet;
        String nameTable;
        String resultCheck = null;
        DataBaseLogger_Test.addDataBase ( "TABLELOGTEST", 1, "Fara---", "Adfffffli");
        String sql = "SELECT * FROM TABLELOGTEST";
        ResultSet rs = DataBaseLogger_Test.getResultSet(sql);

        while(rs.next()) {
            if (rs.getInt (1 ) == 1){
                resultCheck = rs.getString ( 2 );
            }
            Assert.assertEquals("Fara---",resultCheck);
        }
        DataBaseLogger_Test.deleteDataUS("TABLELOGTEST", 1);;

    }

    @Test
    public void deleteDataUSTest(){
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        try {
            DataBaseLogger_Test.deleteDataUS("TABLELOGTEST", 103);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    };


    @Test
    public void getDbConn( ) {
    }

    @Test
    public void getStmt( ) {
    }



    @Test
    public void closeDataBase( ) {
    }

    @Test
    public void readTableDB( ) {
    }
}