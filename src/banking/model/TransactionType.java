package banking.model;

public enum TransactionType {
        DEPOSIT ("deposit"),
        WITHDRAW ("withdraw"),
        TRANSFER ("transfer");

        private String string;

        TransactionType(String string) {
                this.string = string;
        }

        public String getString() {
                return string;
        }
}