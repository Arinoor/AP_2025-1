package banking.manager;

import banking.data.DataManager;
import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

        private static TransactionManager instance;
        private int idCounter;
        private final List<Transaction> transactions;

        private  TransactionManager() {
                transactions = DataManager.getInstance().loadTransactions();
                idCounter = transactions.size() + 1;
                for(Transaction transaction : transactions) {
                        User user = UserManager.getInstance().getByUsername(transaction.getSource());
                        user.getAccount().addTransaction(transaction);
                }
        }

        public Transaction recordTransaction(TransactionType type, double amount, String sourceUsername, String destinationUsername) {
                Transaction transaction = new Transaction(type, amount, sourceUsername, destinationUsername);
                transactions.add(transaction);
                UserManager.getInstance().getByUsername(sourceUsername).addTransaction(transaction);
                DataManager.getInstance().saveAll();
                return transaction;
        }

        public void undoTransaction(int id) {
                Transaction transaction = getTransaction(id);
                if(transaction == null) {
                        throw new RuntimeException("transaction id dose not exist");
                }
                User user = UserManager.getInstance().getByUsername(transaction.getSource());
                if(user != UserManager.getInstance().getCurrentUser()) {
                        throw new RuntimeException("You don't have permission to undo this transaction");
                }
                switch (transaction.getType()) {
                        case DEPOSIT :
                                AccountManager.getInstance().withdraw(user, transaction.getAmount());
                                break;
                        case WITHDRAW :
                                AccountManager.getInstance().deposit(user, transaction.getAmount());
                                break;
                        case TRANSFER :
                                AccountManager.getInstance().transfer(
                                        UserManager.getInstance().getByUsername(transaction.getDestination()),
                                        user,
                                        transaction.getAmount()
                                );
                                break;
                }
                transactions.remove(transaction);
                DataManager.getInstance().saveAll();
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

        public int getIncreaseIdCounter() {
                return idCounter ++;
        }
}
