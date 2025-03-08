package banking.model;

import banking.data.Stringifiable;
import banking.manager.UserManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Stringifiable {
        private static int idCounter = 1;

        private final int id;
        private final TransactionType type;
        private final double amount;
        private final String sourceUsername;
        private final String destinationUsername;
        private final Date time;

        public Transaction(TransactionType type, double amount, String sourceUsername, String destinationUsername) {
                this.id = idCounter++;
                this.type = type;
                this.amount = amount;
                this.sourceUsername = sourceUsername;
                this.destinationUsername = destinationUsername;
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

        public String getSourceUsername() {
                return sourceUsername;
        }

        public String getDestinationUsername() {
                return destinationUsername;
        }

        public User getSource() {
                return UserManager.getInstance().getByUsername(sourceUsername);
        }

        public User getDestination() {
                return UserManager.getInstance().getByUsername(destinationUsername);
        }

        public Date getTimestamp() {
                return time;
        }

        public String getDetails() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return id + ". " + type + ": " + (type == TransactionType.DEPOSIT ? "+" : "-") + amount +
                        " | From: " + sourceUsername + " | To: " + destinationUsername + " | " + sdf.format(time);
        }

        public String stringify() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                StringBuilder str = new StringBuilder(
                        "{" +
                                "id:" + id + "," +
                                "type:" + type.getString() + "," +
                                "amount:" + amount + "," +
                                "source:" + sourceUsername + "," +
                                "destination:" + destinationUsername + "," +
                                "time:" + sdf.format(time) +
                        "}"
                );
                return String.valueOf(str);
        }


}
