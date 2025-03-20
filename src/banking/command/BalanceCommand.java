package banking.command;

import banking.manager.AccountManager;
import banking.manager.UserManager;
import banking.model.User;

public class BalanceCommand implements Command {

        public BalanceCommand() {
        }

        @Override
        public void execute() {
                if (!UserManager.getInstance().isLoggedIn()) {
                        throw new RuntimeException("Please login first.");
                }
                User currentUser = UserManager.getInstance().getCurrentUser();
                double balance = AccountManager.getInstance().getBalance(currentUser);
                System.out.println("Current balance: " + balance);
        }

}