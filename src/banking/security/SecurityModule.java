package banking.security;

public class SecurityModule {
        private static SecurityModule instance;
        private HashStrategy hashStrategy;

        public SecurityModule() {
                hashStrategy = new SHA256HashStrategy();
        }

        public String hashPassword(String password) {
                return hashStrategy.hash(password);
        }

        public boolean verifyPassword(String password, String hash) {
                return hash.equals(hashStrategy.hash(password));
        }

        public static SecurityModule getInstance() {
                if(instance == null) {
                        instance = new SecurityModule();
                }
                return instance;
        }
}
