package entities;

import entities.enums.TransactionType;
import lombok.Data;
import java.util.Date;

@Data
public class Transaction {
 private long id;
 private double amount;
 private TransactionType type;
 private Date date;
 private Wallet wallet;

}
