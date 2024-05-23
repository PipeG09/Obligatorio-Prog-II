package Entities;

import java.util.HashMap;

public class DailyTop {
    private HashMap<String, CountryTop> dailyTop;

    public DailyTop() {
        dailyTop = new HashMap<>();
    }

    public void addCountryTop(String date, CountryTop countryTop) {
        dailyTop.put(date, countryTop);
    }

    public CountryTop getCountryTop(String date) {
        return dailyTop.get(date);
    }

    @Override
    public String toString() {
        return "DailyTop{" +
                "dailyTop=" + dailyTop +
                '}';
    }
}

