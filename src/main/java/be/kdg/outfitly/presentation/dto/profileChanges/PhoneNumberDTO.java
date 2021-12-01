package be.kdg.outfitly.presentation.dto.profileChanges;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PhoneNumberDTO {
    @Size(min = 10, max = 50, message = "Phone number must be between 10 to 50 characters long.")
    String newPhoneNumber;

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }
}
