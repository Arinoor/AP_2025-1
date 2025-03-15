package banking.command;

import banking.UI.CLIInterface;

public class CommandProcessor {

        private static CommandProcessor instance;

        private CommandProcessor(){}

        public void process(String commandLine) {
                commandLine = commandLine.trim();
                CLIInterface cli = CLIInterface.getInstance();
                if(commandLine.isEmpty()) {
                        cli.displayError("Empty Command");
                        return;
                }
                String[] parts = commandLine.split("\\s+");
                String commandName = parts[0].toLowerCase();
                Command command = null;
                try {
                        switch (commandName) {
                                case "signup":
                                        if (parts.length < 3) {
                                                cli.displayError("Usage: signup <username> <password>");
                                        } else {
                                                command = new SignupCommand(parts[1], parts[2]);
                                        }
                                        break;
                                case "login":
                                        if (parts.length < 3) {
                                                cli.displayError("Usage: login <username> <password>");
                                        } else {
                                                command = new LoginCommand(parts[1], parts[2]);
                                        }
                                        break;
                                case "balance":
                                        command = new BalanceCommand();
                                        break;
                                case "history":
                                        command = new HistoryCommand();
                                        break;
                                case "deposit":
                                        if (parts.length < 2) {
                                                cli.displayError("Usage: deposit <amount>");
                                        } else {
                                                double amount = Double.parseDouble(parts[1]);
                                                command = new DepositCommand(amount);
                                        }
                                        break;
                                case "withdraw":
                                        if (parts.length < 2) {
                                                cli.displayError("Usage: withdraw <amount>");
                                        } else {
                                                double amount = Double.parseDouble(parts[1]);
                                                command = new WithdrawCommand(amount);
                                        }
                                        break;
                                case "transfer":
                                        if (parts.length < 3) {
                                                cli.displayError("Usage: transfer <destinationAccountNumber> <amount>");
                                        } else {
                                                String destAccount = parts[1];
                                                double amount = Double.parseDouble(parts[2]);
                                                command = new TransferCommand(destAccount, amount);
                                        }
                                        break;
                                case "undo":
                                        if(parts.length < 2) {
                                                cli.displayError("Usage: undo <TransactionID>");
                                        } else {
                                                int id = Integer.parseInt(parts[1]);
                                                command = new UndoCommand(id);
                                        }
                                        break;
                                case "logout":
                                        command = new LogoutCommand();
                                        break;
                                default:
                                        cli.displayError("Unknown command: " + commandName);
                                        break;
                        }
                        if (command != null) {
                                command.execute();
                        }
                } catch (Exception e) {
                        cli.displayError(e.getMessage());
                }

        }

        public static CommandProcessor getInstance() {
                if(instance == null)
                        instance = new CommandProcessor();
                return instance;
        }
}
