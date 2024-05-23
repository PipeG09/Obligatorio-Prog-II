package Entities;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
