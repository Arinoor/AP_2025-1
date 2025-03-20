package banking.security;

public class SecurityModule {
        private static SecurityModule instance;
        private static final Character[] bannedCharacters = {',', '/', '\\'};
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

        public void verifyUsername(String username) {
                for(char bannedCharacter : bannedCharacters) {
                        if(username.indexOf(bannedCharacter) != -1) {
                                throw new RuntimeException("username should not contain " + bannedCharacter);
                        }
                }
        }

        public static SecurityModule getInstance() {
                if(instance == null) {
                        instance = new SecurityModule();
                }
                return instance;
        }
}
