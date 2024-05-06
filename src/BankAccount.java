public class BankAccount {
    private final String customerId;
    private double balance;
    private final String accountType;

    public BankAccount(String accountType, String customerId){
        this.accountType = accountType;
        this.balance = 0.0;
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void withdraw(String accountType, double amount ){
        if(accountType.equalsIgnoreCase("Savings") && amount > 0 && balance >= amount){
            double withDrawn = balance - amount;
            this.balance -= amount;
            System.out.printf("Successfully withdrew $%.2f from savings%n", withDrawn);
            System.out.printf("Savings remaining balance $%.2f%n", balance);
        } else if(accountType.equalsIgnoreCase("Checking") && amount > 0 && balance >= amount){
            double withDrawn = balance - amount;
            this.balance -= amount;
            System.out.printf("Successfully withdrew $%.2f from checking%n", withDrawn);
            System.out.printf("Checking remaining balance $%.2f%n", balance);
        } else {
            System.out.println("You have insufficient funds");
        }
    }

    public void deposit(String accountType, double amount){
        if(amount > 0){
            if(accountType.equalsIgnoreCase("Savings")){
                this.balance += amount;
                System.out.printf("Successfully deposited $%.2f to savings%n", this.balance);
                System.out.printf("Savings new balance $%.2f%n", this.balance);
            } else if(accountType.equalsIgnoreCase("Checking")){
                this.balance += amount;
                System.out.printf("Successfully deposited $%.2f to checking%n", this.balance);
                System.out.printf("Checking new balance $%.2f%n", this.balance);
            }
        } else {
            System.out.println("Amount must be greater than $0.00");
        }

    }
}
