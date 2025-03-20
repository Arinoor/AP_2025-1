package banking.command;

import banking.manager.UserManager;
import banking.model.User;

public class LogoutCommand implements Command {

        LogoutCommand() {}

        @Override
        public void execute() {
                if (!UserManager.getInstance().isLoggedIn()) {
                        throw new RuntimeException("Please login first.");
                }
                UserManager.getInstance().logout();
                System.out.println("Logged out");
        }
}
