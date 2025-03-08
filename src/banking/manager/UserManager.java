package banking.manager;

import banking.model.User;

import java.util.Map;

public class UserManager {

        private static UserManager instance;
        private User currentUser;
        private Map<String, User> users;

        private UserManager() {
                users = DataManager.getInstance().loadUsers();
        }

        public User login(String username, String password) {
                if (!users.containsKey(username)) {
                        throw new RuntimeException("User does not exist.");
                }
                User user = users.get(username);
                //TODO check hashed password with hashed password of user
                currentUser = user;
                return user;
        }

        public User signup(String username, String password) {
                if (users.containsKey(username)) {
                        throw new RuntimeException("User already exists.");
                }
                //TODO get hashed password
                User user = new User(username, password);
                users.put(username, user);
                DataManager.getInstance().saveUsers(users);
                return user;
        }

        public void logout() {
                if(currentUser == null) {
                        throw new RuntimeException("No login user");
                }
                currentUser = null;
        }

        public User getCurrentUser() {
                return currentUser;
        }

        public User getByUsername(String username) {
                return users.get(username);
        }

        public static UserManager getInstance() {
                if(instance == null)
                        instance = new UserManager();
                return instance;
        }
}
