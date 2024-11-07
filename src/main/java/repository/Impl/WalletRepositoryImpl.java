package repository.Impl;

import entities.Wallet;
import repository.WalletRepository;

import java.sql.SQLException;

public class WalletRepositoryImpl implements WalletRepository {
    private static final String INSERT_SQL = """
            INSERT INTO Wallet(balance , user_id)
            VALUES (?, ?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM wallet
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM wallet
            WHERE id = ?
            """;

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Wallet read(int id) throws SQLException {
        return null;
    }

    @Override
    public Wallet create(Wallet wallet) throws SQLException {
        return null;
    }
}
