package banking.command;

import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.User;

public class UndoCommand implements Command {

        private final int id;

        UndoCommand(int id) {
                this.id = id;
        }

        @Override
        public void execute() {
                if (!UserManager.getInstance().isLoggedIn()) {
                        throw new RuntimeException("Please login first.");
                }
                TransactionManager.getInstance().undoTransaction(id);
                System.out.println("Transaction with id " + id + " undone");
        }
}
