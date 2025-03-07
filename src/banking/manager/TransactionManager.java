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

        public Transaction recordTransaction(TransactionType type, double amount, String source, String destination) {
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

        public boolean undoTransaction(int id) {
                Transaction transaction = getTransaction(id);
                if(transaction == null) {
                        return false;
                }
                boolean status = switch (transaction.getType()) {
                        case DEPOSIT :
                                yield AccountManager.getInstance().withdraw(transaction.getSource(), transaction.getAmount());
                        case WITHDRAW :
                                yield AccountManager.getInstance().deposit(transaction.getSource(), transaction.getAmount());
                        case TRANSFER :
                                yield AccountManager.getInstance().transfer(transaction.getDestination(), transaction.getSource(), transaction.getAmount());
                };
                if(status) {
                        transactions.remove(transaction);
                }
                return status;
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
