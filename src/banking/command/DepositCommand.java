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
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                AccountManager.getInstance().deposit(currentUser, amount);
                transaction = TransactionManager.getInstance().recordTransaction(
                        TransactionType.DEPOSIT, amount, currentUser.getUsername(), null);
                System.out.println(amount + " deposited.");
        }

}
