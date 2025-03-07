package banking.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
        private static int idCounter = 1;

        private int id;
        private TransactionType type;
        private double amount;
        private String sourceAccount;
        private String destinationAccount;
        private Date time;

        public Transaction(TransactionType type, double amount, String sourceAccount, String destinationAccount) {
                this.id = idCounter++;
                this.type = type;
                this.amount = amount;
                this.sourceAccount = sourceAccount;
                this.destinationAccount = destinationAccount;
                this.time = new Date();
        }

        public int getTransactionId() {
                return id;
        }

        public TransactionType getType() {
                return type;
        }

        public double getAmount() {
                return amount;
        }

        public String getSourceAccount() {
                return sourceAccount;
        }

        public String getDestinationAccount() {
                return destinationAccount;
        }

        public Date getTimestamp() {
                return time;
        }

        public String getDetails() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return id + ". " + type + ": " + (type == TransactionType.DEPOSIT ? "+" : "-") + amount +
                        " | From: " + sourceAccount + " | To: " + destinationAccount + " | " + sdf.format(time);
        }
}
