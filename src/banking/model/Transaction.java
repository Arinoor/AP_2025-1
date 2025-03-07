package banking.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
        private static int idCounter = 1;

        private int id;
        private TransactionType type;
        private double amount;
        private User source;
        private User destination;
        private Date time;

        public Transaction(TransactionType type, double amount, User source, User destination) {
                this.id = idCounter++;
                this.type = type;
                this.amount = amount;
                this.source = source;
                this.destination = destination;
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

        public User getSource() {
                return source;
        }

        public User getDestination() {
                return destination;
        }

        public Date getTimestamp() {
                return time;
        }

        public String getDetails() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return id + ". " + type + ": " + (type == TransactionType.DEPOSIT ? "+" : "-") + amount +
                        " | From: " + source + " | To: " + destination + " | " + sdf.format(time);
        }
}
