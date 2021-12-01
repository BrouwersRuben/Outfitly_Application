package be.kdg.outfitly.presentation.dto.profileChanges;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class NameDTO {
    @NotEmpty(message = "Please fill in your first name.")
    @Size(max = 50, message = "First name cannot be larger than 50 characters.")
    private String firstName;
    @NotEmpty(message = "Please fill in your last name.")
    @Size(max = 50, message = "Last name cannot be larger than 50 characters.")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
