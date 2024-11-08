package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private long id;
    private double balance;
    private User user;
//    private List<Transaction> transactions;
}
