package banking.manager;

import banking.data.DataManager;
import banking.model.User;
import banking.security.SecurityModule;

import java.util.Collection;
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
                if(!SecurityModule.getInstance().verifyPassword(password, user.getHashedPassword())) {
                        throw new RuntimeException("Incorrect password.");
                }
                currentUser = user;
                return user;
        }

        public User signup(String username, String password) {
                if (users.containsKey(username)) {
                        throw new RuntimeException("User already exists.");
                }
                SecurityModule.getInstance().verifyUsername(username);
                User user = new User(username, SecurityModule.getInstance().hashPassword(password));
                users.put(username, user);
                DataManager.getInstance().saveUsers(users.values());
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

        public Collection<User> getUsers () {
                return users.values();
        }

        public void setUsers() {

        }

        public static UserManager getInstance() {
                if(instance == null)
                        instance = new UserManager();
                return instance;
        }
}
