package banking.model;

import banking.data.Parser;
import banking.data.Stringifiable;
import banking.manager.TransactionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Transaction implements Stringifiable, Parser {

        private final int id;
        private final TransactionType type;
        private final double amount;
        private final String source;
        private final String destination;
        private final Date time;

        public Transaction(TransactionType type, double amount, String source, String destination) {
                this.id = TransactionManager.getInstance().getIncreaseIdCounter();
                this.type = type;
                this.amount = amount;
                this.source = source;
                this.destination = destination;
                this.time = new Date();
        }

        public Transaction(int id, TransactionType type, double amount, String source, String destination, Date time) {
                this.id = id;
                this.type = type;
                this.amount = amount;
                this.source = source;
                this.destination = destination;
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

        public String getSource() {
                return source;
        }

        public String getDestination() {
                return destination;
        }

        public Date getTimestamp() {
                return time;
        }

        public String getDetails() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return id + ". " +
                        type + ": " + (type == TransactionType.DEPOSIT ? "+" : "-") +
                        amount +
                        " | From: " + (type == TransactionType.WITHDRAW ? "SYSTEM" : source) +
                        " | To: " + (type == TransactionType.WITHDRAW ? source : destination) +
                        " | " + sdf.format(time);
        }

        public String stringify() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                return "{" +
                        "id:" + id + "," +
                        "type:" + type.stringify() + "," +
                        "amount:" + amount + "," +
                        "source:" + source + "," +
                        "destination:" + destination + "," +
                        "time:" + sdf.format(time) +
                        "}";
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
                        return new Transaction(
                                Integer.parseInt( (String)data.get("id")),
                                TransactionType.parse((String) data.get("type")),
                                Double.parseDouble((String) data.get("amount")),
                                (String) data.get("source"),
                                (String) data.get("destination"),
                                sdf.parse((String) data.get("time"))
                        );
                } catch (ParseException e) {
                        throw new RuntimeException(e);
                }
        }


}
