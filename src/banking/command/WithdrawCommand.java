package banking.command;

import banking.manager.AccountManager;
import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

import javax.sound.sampled.AudioSystem;

public class WithdrawCommand implements Command {
        private final double amount;
        private Transaction transaction;

        public WithdrawCommand(double amount) {
                this.amount = amount;
        }

        @Override
        public void execute() {
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                AccountManager.getInstance().withdraw(currentUser, amount);
                transaction = TransactionManager.getInstance().recordTransaction(
                        TransactionType.WITHDRAW, amount, currentUser.getUsername(), null);
                System.out.println(amount + " withdrawn.");
        }

}
