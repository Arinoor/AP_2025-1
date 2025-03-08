package banking.manager;

import banking.model.User;

import java.util.Map;

public class DataManager {

        private static DataManager instance;
        private final String usersFile = "users.json";
        private final String transactionsFile = "transactions.json";

        private DataManager() {

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
        }
}
