package banking.manager;

import banking.model.User;

public class AccountManager {

        private static AccountManager instance;

        private AccountManager() {

        }

        public double getBalance(User user){
                return user.getAccount().getBalance();
        }

        public boolean deposit(User user, double amount) {
                user.getAccount().deposit(amount);
                return true;
        }

        public boolean withdraw(User user, double amount) {
                return user.getAccount().withdraw(amount);
        }

        public boolean transfer(User source, User destination, double amount) {
                if(source.getAccount().getBalance() < amount)
                        return false;
                withdraw(source, amount);
                deposit(destination, amount);
                return true;
        }

        public static AccountManager getInstance() {
                if(instance == null)
                        instance = new AccountManager();
                return instance;
        }

}
