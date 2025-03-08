package banking.manager;

import banking.model.User;

public class AccountManager {

        private static AccountManager instance;

        private AccountManager() {

        }

        public double getBalance(User user){
                return user.getAccount().getBalance();
        }

        public void deposit(User user, double amount) {
                user.getAccount().deposit(amount);
        }

        public void withdraw(User user, double amount) {
                user.getAccount().withdraw(amount);
        }

        public void transfer(User source, User destination, double amount) {
                withdraw(source, amount);
                deposit(destination, amount);
        }

        public static AccountManager getInstance() {
                if(instance == null)
                        instance = new AccountManager();
                return instance;
        }

}
