package banking.model;

public class Customer {
        private String username;
        private String hashedPassword;
        private Account account;

        public Customer(String username, String hashedPassword) {
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
}
