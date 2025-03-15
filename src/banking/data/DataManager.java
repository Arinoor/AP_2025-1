package banking.data;

import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.User;

import java.io.*;
import java.util.*;

public class DataManager {

        private static DataManager instance;
        private final String usersFile = "users.json";
        private final String transactionsFile = "transactions.json";

        JSONParser jsonParser;

        private DataManager() {
                jsonParser = new JSONParser();
        }

        public static DataManager getInstance() {
                if(instance == null)
                        instance = new DataManager();
                return instance;
        }

        public Map<String, User> loadUsers() {
                HashMap<String, User> users = new HashMap<>();
                File file = new File(usersFile);
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String data = reader.readLine();
                        ArrayList<HashMap<String, Object>> usersData = (ArrayList<HashMap<String, Object>>) jsonParser.parse(data);
                        if(usersData == null) {
                                return users;
                        }
                        for(HashMap<String, Object> userData : usersData) {
                                User user = User.parse(userData);
                                users.put(user.getUsername(), user);
                        }
                        return users;

                } catch (IOException e) {
                        e.printStackTrace();
                }
                return new HashMap<String, User>();
        }

        public void saveUsers(Collection<User> users) {
                try {
                        PrintWriter writer = new PrintWriter(new FileWriter(usersFile));
                        writer.write("[");
                        boolean first = true;
                        for(User user : users) {
                                if(!first)
                                        writer.write(",");
                                else
                                        first = false;
                                writer.write(user.stringify());
                        }
                        writer.write("]");
                        writer.close();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        public List<Transaction> loadTransactions() {
                ArrayList<Transaction> transactions = new ArrayList<>();
                File file = new File(transactionsFile);
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String data = reader.readLine();
                        ArrayList<HashMap<String, Object>> transactionsData = (ArrayList<HashMap<String, Object>>) jsonParser.parse(data);
                        if(transactionsData == null)
                                return transactions;
                        for(HashMap<String, Object> transactionData : transactionsData) {
                                Transaction transaction = Transaction.parse(transactionData);
                                transactions.add(transaction);
                        }
                        return transactions;

                } catch (IOException e) {
                        e.printStackTrace();
                }
                return new ArrayList<>();
        }

        public void saveTransactions(Collection<Transaction> transactions) {
                try {
                        PrintWriter writer = new PrintWriter(new FileWriter(transactionsFile));
                        writer.write("[");
                        boolean first = true;
                        for(Transaction transaction : transactions) {
                                if(!first)
                                        writer.write(",");
                                else
                                        first = false;
                                writer.write(transaction.stringify());
                        }
                        writer.write("]");
                        writer.close();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        public void saveAll() {
                saveUsers(UserManager.getInstance().getUsers());
                saveTransactions(TransactionManager.getInstance().getTransactions());
        }
}
