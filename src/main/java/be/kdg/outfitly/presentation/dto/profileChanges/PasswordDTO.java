package be.kdg.outfitly.presentation.dto.profileChanges;

import be.kdg.outfitly.util.ValidPassword;

import javax.validation.constraints.NotEmpty;

public class PasswordDTO {
    @ValidPassword
    @NotEmpty()
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
