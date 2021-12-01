package be.kdg.outfitly.presentation.dto.profileChanges;

import be.kdg.outfitly.util.ValidPassword;

import javax.validation.constraints.NotEmpty;

public class PasswordDTO {

    @NotEmpty
    private String currentPassword;

    @ValidPassword
    @NotEmpty()
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
