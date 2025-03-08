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
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                        System.out.println("Please login first.");
                        return;
                }
                TransactionManager.getInstance().undoTransaction(id);
                System.out.println("Transaction with id " + id + " undone");
        }
}
