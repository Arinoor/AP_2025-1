package banking.model;

import banking.data.Stringifiable;

public class User implements Stringifiable {
        private String username;
        private String hashedPassword;
        private Account account;

        public User(String username, String hashedPassword) {
                this.username = username;
                this.hashedPassword = hashedPassword;
                this.account = new Account();
        }

        public String getUsername() {
                return username;
        }

        public String getHashedPassword() {
                return hashedPassword;
        }

        public Account getAccount() {
                return account;
        }

        public void setAccount(Account account) {
                this.account = account;
        }

        public String stringify() {
                return "{" + username + ":" + "[" + hashedPassword + "," + account.stringify() + "]" + "}";
        }
}
