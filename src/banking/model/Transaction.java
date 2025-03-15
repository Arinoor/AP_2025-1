package banking.model;

import banking.data.Parser;
import banking.data.Stringifiable;
import banking.manager.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Transaction implements Stringifiable, Parser {
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

        public Transaction(int id, TransactionType type, double amount, String sourceUsername, String destinationUsername, Date time) {
                this.id = id;
                this.type = type;
                this.amount = amount;
                this.sourceUsername = sourceUsername;
                this.destinationUsername = destinationUsername;
                this.time = time;
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
                                "type:" + type.stringify() + "," +
                                "amount:" + amount + "," +
                                "source:" + sourceUsername + "," +
                                "destination:" + destinationUsername + "," +
                                "time:" + sdf.format(time) +
                        "}"
                );
                return String.valueOf(str);
        }

        public static List<Transaction> parse(ArrayList<HashMap<String, Object>> transactionsData) {
                ArrayList<Transaction> transactions = new ArrayList<>();
                if(transactionsData == null)
                        return transactions;
                for(HashMap<String, Object> transactionData : transactionsData) {
                        transactions.add(Transaction.parse(transactionData));
                }
                return transactions;
        }

        public static Transaction parse(HashMap<String, Object> data) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                try {
                        Transaction transaction = new Transaction(
                                Integer.parseInt( (String)data.get("id")),
                                TransactionType.parse((String) data.get("type")),
                                Double.parseDouble((String) data.get("amount")),
                                (String) data.get("source"),
                                (String) data.get("destination"),
                                sdf.parse((String) data.get("time"))
                        );
                        return transaction;
                } catch (ParseException e) {
                        throw new RuntimeException(e);
                }
        }


}
