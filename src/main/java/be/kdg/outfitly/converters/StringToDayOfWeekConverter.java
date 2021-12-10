package be.kdg.outfitly.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.DayOfWeek;

public class StringToDayOfWeekConverter implements Converter<String, DayOfWeek> {
    @Override
    public DayOfWeek convert(String source) {
        if (source.toLowerCase().contains("monday")) return DayOfWeek.MONDAY;
//        if (source.toLowerCase().startsWith("Monday")) return DayOfWeek.MONDAY;
        if (source.toLowerCase().contains("tuesday")) return DayOfWeek.TUESDAY;
        if (source.toLowerCase().contains("wednesday")) return DayOfWeek.WEDNESDAY;
        if (source.toLowerCase().contains("thursday")) return DayOfWeek.THURSDAY;
        if (source.toLowerCase().contains("friday")) return DayOfWeek.FRIDAY;
        if (source.toLowerCase().contains("saturday")) return DayOfWeek.SATURDAY;
        if (source.toLowerCase().contains("sunday")) return DayOfWeek.SUNDAY;
        //if it manages to go to this, put it on friday (bean validation --> shouldn't get to here)
        return null;
    }
}
