package main.java.sql.test;

import main.java.sql.DataBaseLogger;
import main.java.sql.getDataChat;
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
        DataBaseLogger_Test.deleteData("TABLELOGTEST", 1);

    }

    @Test
    public void deleteDataTestCheckNameUserBeforeAndAfterDelete() throws SQLException {
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        DataBaseLogger_Test.addDataBase ( "TABLELOGTEST", 1, "Fara---", "Adfffffli");
        String sql = "SELECT * FROM TABLELOGTEST";
        ResultSet rs = DataBaseLogger_Test.getResultSet(sql);
        String CheckNameUser = null;
        String resultCheckNameUser = null;
        while(rs.next()) {
            if (rs.getInt (1 ) == 1){
                CheckNameUser = rs.getString ( 2 );
            }
        }
        try {
            DataBaseLogger_Test.deleteData("TABLELOGTEST", 1);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        ResultSet rsEnd = DataBaseLogger_Test.getResultSet(sql);
        while(rsEnd.next()) {
            if (rsEnd.getInt (1 ) == 1){
                resultCheckNameUser = rsEnd.getString ( 2 );
            }
        }
        Assert.assertNotEquals ( CheckNameUser,resultCheckNameUser );
    }



    @Test
    public void updataDbTestCheckNameUserBeforeAndAfterUpdate( ) throws SQLException {
        DataBaseLogger DataBaseLogger_Test = new DataBaseLogger ();
        DataBaseLogger_Test.addDataBase ( "TABLELOGTEST", 1, "Fara", "Adfffffli");
        String sql = "SELECT * FROM TABLELOGTEST";
        ResultSet rs = DataBaseLogger_Test.getResultSet(sql);
        String CheckNameUser = null;
        String resultCheckNameUser = null;
        while(rs.next()) {
            if (rs.getInt (1 ) == 1){
                CheckNameUser = rs.getString ( 2 );
            }
        }
        System.out.println("result in while after insert: " + CheckNameUser );
        DataBaseLogger_Test.updataDb ( "TABLELOGTEST","userName","Supermen",1 );
        ResultSet rsEnd = DataBaseLogger_Test.getResultSet(sql);
        while(rsEnd.next()) {
            if (rsEnd.getInt (1 ) == 1){
                resultCheckNameUser = rsEnd.getString ( 2 );
            }
        }
        System.out.println("result in while after updata: " + resultCheckNameUser );
        Assert.assertNotEquals ( CheckNameUser,resultCheckNameUser );

        try {
            DataBaseLogger_Test.deleteData("TABLELOGTEST", 1);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    @Test
    public void getDataChat(){
        System.out.println ( "test- " );
        String getDataChatUsName= getDataChat.getDataUserName ();
        System.out.println ( getDataChatUsName + "testRes" );
    }
}