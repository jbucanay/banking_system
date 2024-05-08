import java.time.LocalDate;

public class Customer extends Person {

    public Customer(String firstName, String lastName, String dateOfBirth){
        super(firstName, lastName);
        setDateOfBirth(dateOfBirth);
    }


    public void setDateOfBirth(String dateOfBirth) {
        LocalDate dateOfBirth1 = LocalDate.parse(dateOfBirth);
    }

}


