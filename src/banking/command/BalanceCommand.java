package banking.command;

import banking.manager.AccountManager;
import banking.manager.UserManager;
import banking.model.User;

public class BalanceCommand implements Command {

        public BalanceCommand() {
        }

        @Override
        public void execute() {
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                double balance = AccountManager.getInstance().getBalance(currentUser);
                System.out.println("Current balance: " + balance);
        }

}