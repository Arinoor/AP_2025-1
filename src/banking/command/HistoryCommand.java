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
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                List<Transaction> history = TransactionManager.getInstance().getHistory(currentUser);
                if (history.isEmpty()) {
                        System.out.println("No transactions found.");
                } else {
                        for (Transaction transaction : history) {
                                System.out.println(transaction.getDetails());
                        }
                }
        }
}
