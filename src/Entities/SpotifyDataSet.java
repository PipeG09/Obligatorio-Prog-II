package Entities;

import com.opencsv.bean.CsvBindByName;

public class SpotifyDataSet {
    @CsvBindByName(column = "spotify_id")
    private String spotifyId;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "artists")
    private String artists;

    @CsvBindByName(column = "daily_rank")
    private int dailyRank;

    @CsvBindByName(column = "country")
    private String country;

    @CsvBindByName(column = "snapshot_date")
    private String snapshotDate;

    @CsvBindByName(column = "tempo")
    private double tempo;


    // Getters and setters

    public String getSpotifyId() {
        return spotifyId;
    }

    public String getName() {
        return name;
    }

    public String getArtists() {
        return artists;
    }

    public int getDailyRank() {
        return dailyRank;
    }

    public String getCountry() {
        return country;
    }

    public String getSnapshotDate() {
        return snapshotDate;
    }

    public double getTempo() {
        return tempo;
    }

    @Override
    public String toString() {
        return "Song{" +
                "spotifyId='" + spotifyId + '\'' +
                ", name='" + name + '\'' +
                ", artists='" + artists + '\'' +
                ", dailyRank=" + dailyRank +
                ", country='" + country + '\'' +
                ", snapshotDate='" + snapshotDate + '\'' +
                ", tempo=" + tempo +
                '}';
    }

    // Add default constructor, getters, and setters
}

