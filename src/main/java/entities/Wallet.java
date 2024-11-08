package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private long id;
    private double balance;
    private User user;
    private List<Transaction> transactions;

    public Wallet(long id, double balance, User user) {
        this.balance = balance;
        this.user = user;
        this.id = id;
    }

    public Wallet(double balance, User user) {
        this.balance = balance;
        this.user = user;
        this.transactions = new ArrayList<>();
    }
}

