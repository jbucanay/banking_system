import java.time.LocalDate;

public class Customer extends Person {
    private final String customerId;
    private LocalDate dateOfBirth;

    public Customer(String customerId, String firstName, String lastName, String dateOfBirth){
        super(firstName, lastName);
        this.customerId = customerId;
        setDateOfBirth(dateOfBirth);
    }


    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

}


