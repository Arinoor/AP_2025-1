package banking.model;

public enum TransactionType {
        DEPOSIT ("deposit"),
        WITHDRAW ("withdraw"),
        TRANSFER ("transfer");

        private final String string;

        TransactionType(String string) {
                this.string = string;
        }

        public static TransactionType parse(String type) {
                switch (type) {
                        case "deposit" -> {
                                return DEPOSIT;
                        }
                        case "withdraw" -> {
                                return WITHDRAW;
                        }
                        case "transfer" -> {
                                return TRANSFER;
                        }
                }
                return null;
        }

        public String stringify() {
                return string;
        }
}