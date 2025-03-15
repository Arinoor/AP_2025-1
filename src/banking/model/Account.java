package banking.model;

import banking.data.Parser;
import banking.data.Stringifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account implements Stringifiable, Parser {
        private double balance;
        private List<Transaction> transactions;

        public Account() {
                balance = 0.0;
                transactions = new ArrayList<>();
        }

        public Account(double balance) {
                this.balance = balance;
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

        public void setTransactions(List<Transaction> transactions) {
                this.transactions = transactions;
        }

        public String stringify() {
                StringBuilder str = new StringBuilder("{balance:" + balance + "," + "transactions:" + "[");
                for(Transaction transaction : transactions) {
                        str.append(transaction.stringify());
                }
                str.append("]" + "}");
                return String.valueOf(str);
        }

        public static Account parse(HashMap<String, Object> data) {
                Account account = new Account(Double.parseDouble((String) data.get("balance")));
                account.setTransactions(Transaction.parse((ArrayList<HashMap<String, Object>>) data.get("transactions")));
                return account;
        }
}
