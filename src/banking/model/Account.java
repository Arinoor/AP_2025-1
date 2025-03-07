package banking.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
        private double balance;
        private List<Transaction> transactions;

        public Account() {
                balance = 0.0;
                transactions = new ArrayList<>();
        }

        public void deposit(double amount) {
                balance += amount;
        }

        public boolean withdraw(double amount) {
                if(amount > balance)
                        return false;
                balance -= amount;
                return true;
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
}
