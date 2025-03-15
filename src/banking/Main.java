package banking;

import banking.UI.CLIInterface;
import banking.command.CommandProcessor;
import banking.manager.AccountManager;
import banking.manager.TransactionManager;
import banking.manager.UserManager;
import banking.model.Transaction;
import banking.model.TransactionType;
import banking.model.User;

import java.util.concurrent.TimeUnit;

public class Main {

        public static void main(String[] args) {
                CLIInterface cli = CLIInterface.getInstance();
                while (true) {
                        String commandLine = cli.readCommand();
                        CommandProcessor.getInstance().process(commandLine);
                        if(commandLine.equals("exist")) break;
                        try {
                                TimeUnit.MILLISECONDS.sleep(400);
                        } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                        }
                }
        }

}
