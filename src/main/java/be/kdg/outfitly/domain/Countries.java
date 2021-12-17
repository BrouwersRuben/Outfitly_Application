package be.kdg.outfitly.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Countries {

    public static Map<String, String> getCountryCodesAndNames() {

        List<String> codes = List.of(Locale.getISOCountries());

        Map<String, String> countries = new HashMap<>();

        codes.forEach(code -> {
            String countryName = new Locale("", code).getDisplayName();
            countries.put(code, countryName);
        });

        return countries;

    }
}
