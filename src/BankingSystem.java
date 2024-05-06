import java.util.HashMap;
import java.util.Map;

public class BankingSystem {
    private final HashMap<String, Customer> customers;
    private final HashMap<String, BankAccount> savingsAccount;
    private final HashMap<String, BankAccount> checkingAccount;

    public BankingSystem(){
        this.customers = new HashMap<>();
        this.savingsAccount = new HashMap<>();
        this.checkingAccount = new HashMap<>();
    }

    public void addCustomer(String customerId, String firstName, String lastName, String dateOfBirth){
        customers.put(customerId, new Customer(customerId,firstName, lastName, dateOfBirth));
    }

    public boolean getCustomerInfo(String customerId){
        if(this.customers.containsKey(customerId)){
            Customer customer = this.customers.get(customerId);
            System.out.printf("Welcome %s %s, please select an option", customer.getFirstName(), customer.getLastName());
            return true;
        } else {
            return false;
        }

    }

    public void viewBalance(String customerId){
        if(this.savingsAccount.containsKey("Savings")){
            BankAccount savingsAccount = this.savingsAccount.get("Savings");
            if(savingsAccount.getCustomerId().equals(customerId)){
                System.out.printf("Savings account balance: $%s%n", savingsAccount.getBalance());
            } else {
                System.out.println("invalid account or customer id");
            }
        }if(this.checkingAccount.containsKey("Checking")){
            BankAccount checkingAccount = this.checkingAccount.get("Checking");
            if(checkingAccount.getCustomerId().equals(customerId)){
                System.out.printf("Checking account balance: $%s%n", checkingAccount.getBalance());
            } else {
                System.out.println("Invalid accout or customer id");
            }
        }else {
            System.out.println("You do not have an account with us");
        }
    }

    public void openCheckingAccount(String customerId){
        checkingAccount.put("Checking", new BankAccount("Checking", customerId));
    }

    public void openSavingsAccount(String customerId){
            savingsAccount.put("Savings", new BankAccount("Savings", customerId));
    }

    public void openBothAccounts(String customerId){
        openSavingsAccount(customerId);
        openCheckingAccount(customerId);
    }


    public HashMap<String, BankAccount> getSavingsAccount() {
        return savingsAccount;
    }

    public HashMap<String, BankAccount> getCheckingAccount() {
        return checkingAccount;
    }

    public void withdrawFromAccount(String accountName, String customerId, double amount){
        try {
            if (this.savingsAccount.containsKey(accountName)) {
                BankAccount savingsAccount = this.savingsAccount.get(accountName);
                if (savingsAccount.getCustomerId().equals(customerId)) {
                    savingsAccount.withdraw("Savings", amount);
                    return; // Withdrawal successful, exit the method
                }
            }

            if (this.checkingAccount.containsKey(accountName)) {
                BankAccount checkingAccount = this.checkingAccount.get(accountName);
                if (checkingAccount.getCustomerId().equals(customerId)) {
                    checkingAccount.withdraw("Checking", amount);
                    return; // Withdrawal successful, exit the method
                }
            }

            // If none of the accounts match or if the account doesn't exist
            throw new IllegalArgumentException("Account not found or customer ID mismatch.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void depositToAccount(String accountName, String customerId, double amount) {
        try {
            if (this.savingsAccount.containsKey(accountName)) {
                BankAccount savingsAccount = this.savingsAccount.get(accountName);
                if (savingsAccount.getCustomerId().equals(customerId)) {
                    savingsAccount.deposit("Savings", amount);
                    return; // Deposit successful, exit the method
                }
            }

            if (this.checkingAccount.containsKey(accountName)) {
                BankAccount checkingAccount = this.checkingAccount.get(accountName);
                if (checkingAccount.getCustomerId().equals(customerId)) {
                    checkingAccount.deposit("Checking", amount);
                    return; // Deposit successful, exit the method
                }
            }

            // If none of the accounts match or if the account doesn't exist
            throw new IllegalArgumentException("Account not found or customer ID mismatch.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
