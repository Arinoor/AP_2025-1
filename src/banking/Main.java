package banking;

import banking.manager.AccountManager;
import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

public class Main {

        public static void main(String[] args) {
                User u1 = UserManager.getInstance().signup("Arian", "asf23");
                User u2 = UserManager.getInstance().signup("Ali", "df34");
                AccountManager.getInstance().deposit(u1, 100);
                Transaction transaction = TransactionManager.getInstance().recordTransaction(
                        TransactionType.DEPOSIT,  100, "Arian", "Ali");


        }

}
