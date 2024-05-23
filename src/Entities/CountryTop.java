package Entities;

import java.util.HashMap;

public class CountryTop {
    private HashMap<String, Top> countryTop;

    public CountryTop() {
        countryTop = new HashMap<>();
    }

    public void addTop(String country, Top top) {
        countryTop.put(country, top);
    }

    public Top getTop(String country) {
        return countryTop.get(country);
    }

    @Override
    public String toString() {
        return "CountryTop{" +
                "countryTop=" + countryTop +
                '}';
    }
}
