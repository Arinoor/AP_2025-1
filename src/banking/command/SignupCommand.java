package banking.command;

import banking.manager.UserManager;
import banking.model.User;

public class SignupCommand implements Command{
        private String username;
        private String password;

        public SignupCommand(String username, String password) {
                this.username = username;
                this.password = password;
        }

        @Override
        public void execute() {
                if(UserManager.getInstance().isLoggedIn()) {
                        throw new RuntimeException("Please logout first.");
                }
                User signedupUser = UserManager.getInstance().signup(username, password);
                if (signedupUser != null) {
                        System.out.println("Account '" + username + "' created.");;
                } else {
                        System.out.println("Signup failed.");
                }
        }
}
