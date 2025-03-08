package banking.data;

import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
                return null;
        }

        public void saveUsers(Map<String, User> users) {
                String json = jsonParser.stringify(users);
                try (PrintWriter writer = new PrintWriter(new FileWriter(usersFile))) {
                        writer.write(json);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public List<Transaction> loadTransactions() {
                return null;
        }

        public void saveTransactions() {
        }
}
