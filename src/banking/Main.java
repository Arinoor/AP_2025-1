package banking;

import banking.UI.CLIInterface;
import banking.command.CommandProcessor;
import banking.data.DataManager;
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
                UserManager.getInstance();
                try { TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) { throw new RuntimeException(e); }
                TransactionManager.getInstance();
                while (true) {
                        String commandLine = cli.readCommand();
                        if(commandLine.equals("exit")) break;
                        CommandProcessor.getInstance().process(commandLine);
                        try {
                                TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                        }
                }
        }

}
