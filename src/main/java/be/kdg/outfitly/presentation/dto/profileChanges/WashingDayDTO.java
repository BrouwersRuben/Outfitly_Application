package be.kdg.outfitly.presentation.dto.profileChanges;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

public class WashingDayDTO {
    @NotNull(message = "Please fill in the washing reset day.")
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
