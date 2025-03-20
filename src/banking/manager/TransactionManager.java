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
                for(Transaction transaction : transactions) {
                        UserManager.getInstance().getByUsername(transaction.getAuthor()).addTransaction(transaction);
                        if(transaction.getType() == TransactionType.TRANSFER) {
                                UserManager.getInstance().getByUsername(transaction.getDestination()).addTransaction(transaction);
                        }
                }
                idCounter = transactions.size() + 1;
        }

        public Transaction recordTransaction(TransactionType type, double amount, String author, String source, String destination) {
                Transaction transaction = new Transaction(type, amount, author, source, destination);
                transactions.add(transaction);
                UserManager.getInstance().getByUsername(author).addTransaction(transaction);
                if(type == TransactionType.TRANSFER) {
                        UserManager.getInstance().getByUsername(destination).addTransaction(transaction);
                }
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
                transaction.undo();
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
