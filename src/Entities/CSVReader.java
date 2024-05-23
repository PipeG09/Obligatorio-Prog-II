package Entities;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
        String filePath = "path/to/your/file.csv"; // Reemplaza con la ruta a tu archivo CSV

        DailyTop dailyTop = new DailyTop();

        try (FileReader reader = new FileReader(filePath)) {
            CsvToBean<SpotifyDataSet> csvToBean = new CsvToBeanBuilder<SpotifyDataSet>(reader)
                    .withType(SpotifyDataSet.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<SpotifyDataSet> songs = csvToBean.parse();
            for (SpotifyDataSet song : songs) {
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
