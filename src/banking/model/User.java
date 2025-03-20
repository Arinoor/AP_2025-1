package banking.model;

import banking.data.Parser;
import banking.data.Stringifiable;

import java.util.HashMap;

public class User implements Stringifiable, Parser {
        private final String username;
        private final String hashedPassword;
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

        public void addTransaction(Transaction transaction) {
                account.addTransaction(transaction);
        }

        public void setAccount(Account account) {
                this.account = account;
        }

        public String stringify() {
                return "{" +
                        "username" + ":" + username + "," +
                        "password" + ":" + hashedPassword + "," +
                        "account" + ":" +  account.stringify() +
                        "}";
        }

        public static User parse(HashMap<String, Object> data) {
                User user = new User((String)data.get("username"), (String)data.get("password"));
                //System.out.println(user.getUsername());
                user.setAccount(Account.parse((HashMap<String, Object>) data.get("account")));
                return user;
        }
}
