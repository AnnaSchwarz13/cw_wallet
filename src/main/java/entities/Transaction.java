package entities;

import entities.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
 private long id;
 private double amount;
 private TransactionType type;
 private Date date;
 private Wallet wallet;

}
