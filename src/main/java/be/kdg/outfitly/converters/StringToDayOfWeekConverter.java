package be.kdg.outfitly.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.DayOfWeek;

public class StringToDayOfWeekConverter implements Converter<String, DayOfWeek> {
    @Override
    public DayOfWeek convert(String source) {
        if (source.contains("Monday")) return DayOfWeek.MONDAY;
//        if (source.toLowerCase().startsWith("Monday")) return DayOfWeek.MONDAY;
        if (source.contains("Tuesday")) return DayOfWeek.TUESDAY;
        if (source.contains("Wednesday")) return DayOfWeek.WEDNESDAY;
        if (source.contains("Thursday")) return DayOfWeek.THURSDAY;
        if (source.contains("Friday")) return DayOfWeek.FRIDAY;
        if (source.contains("Saturday")) return DayOfWeek.SATURDAY;
        if (source.contains("Sunday")) return DayOfWeek.SUNDAY;
        return null;
    }
}
