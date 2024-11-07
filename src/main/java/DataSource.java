import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    @Getter
    private static final Connection connection;

    private DataSource() {
    }

    static {
        final String DB_URL ="jdbc:postgresql://localhost:5432/postgres";
        final String USER = "postgres";
        final String PASS = "6250074678";
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // JVM Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}


