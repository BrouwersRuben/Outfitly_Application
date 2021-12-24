package be.kdg.outfitly.util;

import java.util.*;

public class CountriesCodesAndNames {

    public static Map<String, String> getCountryCodesAndNames() {

        List<String> codes = List.of(Locale.getISOCountries());

//        Map<String, String> countries = new HashMap<>();
        Map<String, String> countries = new TreeMap<>();

        codes.forEach(code -> {
            String countryName = new Locale("", code).getDisplayName();
            countries.put(countryName, code);
        });

        return countries;

    }

    public static Set<Map.Entry<String, String>> getAlphabeticallySortedEntrySet(){
//        Set<Map.Entry<String, String>> countries = getCountryCodesAndNames().entrySet();
        Map<String, String> countries = getCountryCodesAndNames();

    return null;

    }

}
