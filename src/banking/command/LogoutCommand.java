package banking.command;

import banking.manager.UserManager;
import banking.model.User;

public class LogoutCommand implements Command {

        LogoutCommand() {}

        @Override
        public void execute() {
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                UserManager.getInstance().logout();
                System.out.println("Logged out");
        }
}
