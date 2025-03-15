package banking.command;

import banking.manager.AccountManager;
import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

public class TransferCommand implements Command {
        private final String destinationUsername;
        private final double amount;
        private Transaction transaction;

        public TransferCommand(String destinationUsername, double amount) {
                this.destinationUsername = destinationUsername;
                this.amount = amount;
        }

        @Override
        public void execute() {
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                User destinationUser = UserManager.getInstance().getByUsername(destinationUsername);
                AccountManager.getInstance().transfer(currentUser, destinationUser, amount);
                transaction = TransactionManager.getInstance().recordTransaction(
                        TransactionType.TRANSFER, amount, currentUser.getUsername(), destinationUser.getUsername());
                System.out.println(
                        amount + " transferred to " + destinationUsername + ". Transaction ID: " + transaction.getTransactionId()
                );
        }
}
