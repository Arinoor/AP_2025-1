package banking.manager;

import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

        private static TransactionManager instance;
        private List<Transaction> transactions;

        private  TransactionManager() {
                transactions = new ArrayList<>();
        }

        public Transaction recordTransaction(TransactionType type, double amount, User source, User destination) {
                Transaction transaction = new Transaction(type, amount, source, destination);
                transactions.add(transaction);
                return transaction;
        }

        public List<Transaction> getHistory(User user) {
                List<Transaction> history = new ArrayList<>();
                for(Transaction transaction : user.getAccount().getTransactions()) {
                        if(transaction.getSource().equals(user.getUsername())) {
                                history.add(transaction);
                        }
                }
                return history;
        }

        public void undoTransaction(int id) {
                Transaction transaction = getTransaction(id);
                if(transaction == null) {
                        throw new RuntimeException("transaction id dose not exist");
                }
                if(transaction.getSource() != AuthenticationManager.getInstance().getCurrentUser()) {
                        throw new RuntimeException("You don't have permission to undo this transaction");
                }
                switch (transaction.getType()) {
                        case DEPOSIT :
                                AccountManager.getInstance().withdraw(transaction.getSource(), transaction.getAmount());
                        case WITHDRAW :
                                AccountManager.getInstance().deposit(transaction.getSource(), transaction.getAmount());
                        case TRANSFER :
                                AccountManager.getInstance().transfer(transaction.getDestination(), transaction.getSource(), transaction.getAmount());
                }
                transactions.remove(transaction);
        }

        public Transaction getTransaction(int id) {
                for(Transaction transaction : transactions) {
                        if(transaction.getTransactionId() == id) {
                                return transaction;
                        }
                }
                return null;
        }

        public static TransactionManager getInstance() {
                if(instance == null)
                        instance = new TransactionManager();
                return instance;
        }

        public List<Transaction> getTransactions() {
                return transactions;
        }
}
