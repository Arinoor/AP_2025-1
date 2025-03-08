package banking.command;

import banking.manager.UserManager;
import banking.model.User;

public class LoginCommand implements Command {
        private String username;
        private String password;

        public LoginCommand(String username, String password) {
                this.username = username;
                this.password = password;
        }

        @Override
        public void execute() {
                User loggedInUser = UserManager.getInstance().login(username, password);
                if (loggedInUser != null) {
                        System.out.println("Logged in as '" + username + "'.");
                } else {
                        System.out.println("Login failed.");
                }
        }
}
