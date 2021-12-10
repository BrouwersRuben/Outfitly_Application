package be.kdg.outfitly.presentation.dto.profileChanges;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;

public class WashingDayDTO {
    @Column(name = "washing_reset_day", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message ="Please fill in the washing reset day.")
    private DayOfWeek newWashingResetDay;

    public DayOfWeek getNewWashingResetDay() {
        return newWashingResetDay;
    }

    public void setNewWashingResetDay(DayOfWeek newWashingResetDay) {
        this.newWashingResetDay = newWashingResetDay;
    }

    @Override
    public String toString() {
        return "WashingDayDTO{" +
                "newWashingResetDay=" + newWashingResetDay +
                '}';
    }
}
