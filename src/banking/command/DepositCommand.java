package banking.command;

import banking.manager.AccountManager;
import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

public class DepositCommand implements Command {

        private final double amount;
        private Transaction transaction;

        public DepositCommand(double amount) {
                this.amount = amount;
        }

        @Override
        public void execute() {
                if (!UserManager.getInstance().isLoggedIn()) {
                        throw new RuntimeException("Please login first.");
                }
                User currentUser = UserManager.getInstance().getCurrentUser();
                AccountManager.getInstance().deposit(currentUser, amount);
                transaction = TransactionManager.getInstance().recordTransaction(
                        TransactionType.DEPOSIT,
                        amount,
                        currentUser.getUsername(),
                        currentUser.getUsername(),
                        "SYSTEM");
                System.out.println(amount + " deposited.");
        }

}
