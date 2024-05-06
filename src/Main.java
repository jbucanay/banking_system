import javax.sound.midi.Soundbank;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.UUID;


public class Main {

    public static void main(String[] args) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bank = new BankingSystem();
        System.out.println("Welcome to the Branch, do you have an account with us? type (yes, no)");
        String accountChoice = scanner.next();
        if(accountChoice.equalsIgnoreCase("yes")){
            handleExistingAccount(scanner, bank);

        } else if(accountChoice.equalsIgnoreCase("no")){
            //create user account
           handleNewAccount(scanner, bank);

        } else {
            throw new IllegalArgumentException("Invalid choice");
        }
        scanner.close();
    }

    private static void handleExistingAccount(Scanner scanner, BankingSystem bank){
        //grab account number
        System.out.println("Enter your account number: ");
        String providedId = scanner.next();
        if(!bank.getCustomerInfo(providedId)){
            System.out.println("Account invalid... directing to new account creation");
            handleNewAccount(scanner, bank);
        }
        int choice;
        while((choice = userInteractionChoice(scanner)) != 4){
            if(choice == 1) {
                bank.viewBalance(providedId);
            } else if(choice == 2){
                int withdrawChoice;
                while((withdrawChoice = userWithdrawal(scanner)) !=3){
                    if(withdrawChoice == 1){
                        System.out.println("amount to withdraw from savings");
                        double savingsAmount = scanner.nextDouble();
                        bank.withdrawFromAccount("Savings", providedId, savingsAmount);
                    }else if(withdrawChoice ==2){
                        System.out.println("amount to withdraw from checking");
                        double checkingAmount = scanner.nextDouble();
                        bank.withdrawFromAccount("Checking", providedId, checkingAmount);
                    }
                }
            } else if(choice == 3){
                int depositChoice;
                while ((depositChoice = userDeposit(scanner)) != 3){
                    if(depositChoice == 1){
                        System.out.println("Amount to deposit to savings");
                        double savingsAmount = scanner.nextDouble();
                        bank.depositToAccount("Savings", providedId, savingsAmount);
                    }else if(depositChoice ==2){
                        System.out.println("Amount to deposit to checking");
                        double checkingAmount = scanner.nextDouble();
                        bank.depositToAccount("Checking", providedId, checkingAmount);
                    }
                }
            }
        }
    }

    private static void handleNewAccount(Scanner scanner, BankingSystem bank){
        System.out.println("What is your first name: ");
        String firstName = scanner.next();
        System.out.println("What is your last name: ");
        String lastName = scanner.next();
        System.out.println("What is your date of birth (yyyy-mm-dd): ");
        String dateOfBirth = scanner.next();
        String customerId = generateCustomerId();

        //Create account
        bank.addCustomer(customerId, firstName, lastName, dateOfBirth);
        System.out.printf("Welcome to the bank %s %s your account number is %s %n", firstName, lastName, customerId);
        //Use their name and ask what kind of account they would like to create
        System.out.println("Select the account you would like to create: ");
        System.out.println("Type 1 - Checking account");
        System.out.println("Type 2 - Savings account");
        System.out.println("Type 3 - Both savings and checking account");
        Integer userAccountType = scanner.nextInt();
        switch (userAccountType) {
            case 1 -> bank.openCheckingAccount(customerId);
            case 2 -> bank.openSavingsAccount(customerId);
            case 3 -> bank.openBothAccounts(customerId);
            default -> System.out.println("Please select 1,2, or 3");
        }
        System.out.printf("Thank you %s, you can use our services on your phone%n", firstName);
        handleExistingAccount(scanner, bank);
    }

    private static String generateCustomerId() {
        UUID uuid = UUID.randomUUID();
        String hex = uuid.toString().replace("-", "");
        return hex.substring(0,8);
    }

    private static int userInteractionChoice(Scanner scanner){
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Make a withdrawal");
        System.out.println("Type 3 - Make a deposit");
        System.out.println("Type 4 - Exit");
        return scanner.nextInt();
    }

    private static int userWithdrawal(Scanner scanner){
        System.out.println("Type 1 - Withdraw from savings account");
        System.out.println("Type 2 - Withdraw from checking account");
        System.out.println("Type 3 - Exit");
        return scanner.nextInt();
    }

    private static int userDeposit(Scanner scanner){
        System.out.println("Type 1 - Deposit to savings account");
        System.out.println("Type 2 - Deposit to checking account");
        System.out.println("Type 3 - Exit");
        return scanner.nextInt();
    }
}
