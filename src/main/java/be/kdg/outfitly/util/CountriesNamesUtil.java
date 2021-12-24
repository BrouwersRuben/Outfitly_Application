package be.kdg.outfitly.util;

import java.util.*;

public class CountriesNamesUtil {

    public static Map<String, String> getCountriesNamesAndCodes() {

        List<String> codes = List.of(Locale.getISOCountries());

        Map<String, String> countries = new TreeMap<>();

        codes.forEach(code -> {
            String countryName = new Locale("", code).getDisplayName();
            countries.put(countryName, code);
        });

        return countries;

    }


}
