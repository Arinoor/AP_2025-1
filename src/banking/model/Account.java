package banking.model;

import banking.data.Stringifiable;

import java.util.ArrayList;
import java.util.List;

public class Account implements Stringifiable {
        private double balance;
        private List<Transaction> transactions;

        public Account() {
                balance = 0.0;
                transactions = new ArrayList<>();
        }

        public void deposit(double amount) {
                balance += amount;
        }

        public void withdraw(double amount) {
                if(amount > balance)
                        throw new RuntimeException("Account balance is not enough");
                balance -= amount;
        }

        public double getBalance() {
                return balance;
        }

        public List<Transaction> getTransactions() {
                return transactions;
        }

        public void addTransaction(Transaction transaction) {
                transactions.add(transaction);
        }

        public String stringify() {
                StringBuilder str = new StringBuilder("{balance:" + balance + "," + "transactions:" + "[");
                for(Transaction transaction : transactions) {
                        str.append(transaction.stringify());
                }
                str.append("]" + "}");
                return String.valueOf(str);
        }
}
