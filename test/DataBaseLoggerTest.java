import main.java.sql.DataBaseLogger;
import org.junit.Test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataBaseLoggerTest {
    @Test
    public void createTable() throws SQLException {
        //GIVEN
        DataBaseLogger testDataBaseLogger = new DataBaseLogger();
        try {
            testDataBaseLogger.getStmt ();
        } catch (SQLException e) {
            System.out.println("Oooops, something wrong with statment: ");
            e.printStackTrace();
        }

        //Создадим имена таблиц такие чтобы они не совбпадали с нашими сущуствующими в проде (production, те которые в использовании)
        String test_table_name = "TEST_TABLE_NAME";
        String wrong_test_table_name = "WRONG_TEST_TABLE_NAME";

        testDataBaseLogger.createTableDb("CREATE TABLE IF NOT EXISTS " + test_table_name + " (" +
                " id INTEGER not NULL," +
                " userName VARCHAR(255)," +
                " userMessage VARCHAR(255)," +
                " date TIMESTAMP, " +
                " PRIMARY KEY ( id ))");

        //WHEN
        //https://stackoverflow.com/a/2943166
        DatabaseMetaData dbm = testDataBaseLogger.getDbConn().getMetaData();
        ResultSet tables1 = dbm.getTables(null, null, test_table_name, null);
        ResultSet tables2 = dbm.getTables(null, null, wrong_test_table_name, null);

        //Интерестно: я бы мог ипользовать tables1.next() не сохраняя ее в переменной, но как оказалось (почитав доку этого метода) он проверяет есть ли строка в таблице,
        //если есть то вохвращает Истину и переходит проверять следуюзую строку. Тоесть повторные вызов  tables1.next() вернет Ложь
        Boolean isThereAreAnyRowInTable_1 = tables1.next();
        Boolean isThereAreAnyRowInTable_2 = tables1.next();

        //THEN
        /*
        Почему и true и false? Потому что если бы я проверял только на тру то есть вероятность что там баг и он всегда возвращает истину.
        А так я проверил что если мы ищем заведомо неправильное имя таблицы то его не долдно существовать
        */
        assertTrue(isThereAreAnyRowInTable_1);
        assertFalse(isThereAreAnyRowInTable_2);

        //Подчистим после теста
        if (isThereAreAnyRowInTable_1) {
            String dropQuery = "DROP TABLE " + test_table_name;
            testDataBaseLogger.getStmt().executeUpdate(dropQuery);
            if (!tables1.next()) {
                System.out.println("Table " + test_table_name + " deleted in given database...");
            } else {
                System.out.println("Ooops, I can not delete " + test_table_name);
            }
        }

        //БАГ!!!
        //Выводит false и false. А должно false и true. Угадай почему. Ну и исправь. А то выходит мы не можем закрыть конекшн впринципе.
        System.out.println(testDataBaseLogger.getDbConn().isClosed());
        testDataBaseLogger.getDbConn().close();
        System.out.println(testDataBaseLogger.getDbConn().isClosed());
    }
}
