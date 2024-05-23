import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "path/to/your/file.csv"; // Reemplaza con la ruta a tu archivo CSV

        DailyTop dailyTop = new DailyTop();

        try (FileReader reader = new FileReader(filePath)) {
            CsvToBean<Song> csvToBean = new CsvToBeanBuilder<Song>(reader)
                    .withType(Song.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Song> songs = csvToBean.parse();
            for (Song song : songs) {
                String date = song.getSnapshotDate();
                String country = song.getCountry();
                int rank = song.getDailyRank();

                // Obtener o crear la estructura de datos
                CountryTop countryTop = dailyTop.getCountryTop(date);
                if (countryTop == null) {
                    countryTop = new CountryTop();
                    dailyTop.addCountryTop(date, countryTop);
                }

                Top top = countryTop.getTop(country);
                if (top == null) {
                    top = new Top();
                    countryTop.addTop(country, top);
                }

                top.addSong(rank, song);
            }

            // Imprimir la estructura de datos para verificar
            System.out.println(dailyTop);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
