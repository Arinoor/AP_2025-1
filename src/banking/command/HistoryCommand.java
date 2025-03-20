package banking.command;

import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.User;

import java.util.List;

public class HistoryCommand implements Command {

        public HistoryCommand() {
        }

        @Override
        public void execute() {
                if (!UserManager.getInstance().isLoggedIn()) {
                        throw new RuntimeException("Please login first.");
                }
                User currentUser = UserManager.getInstance().getCurrentUser();
                List<Transaction> history = currentUser.getAccount().getTransactions();
                if (history.isEmpty()) {
                        System.out.println("No transactions found.");
                } else {
                        for (Transaction transaction : history) {
                                System.out.println(transaction.getDetails());
                        }
                }
        }
}
